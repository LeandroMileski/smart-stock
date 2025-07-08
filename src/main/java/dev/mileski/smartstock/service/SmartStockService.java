package dev.mileski.smartstock.service;

import dev.mileski.smartstock.domain.CsvStockItem;
import dev.mileski.smartstock.repository.PurchaseRequestEntity;
import dev.mileski.smartstock.repository.PurchaseRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class SmartStockService {

    private final ReportService reportService;
    private final PurchaseSectorService purchaseSectorService;
    private final PurchaseRequestRepository purchaseRequestRepository;


    public SmartStockService(ReportService reportService, PurchaseSectorService purchaseSectorService, PurchaseRequestRepository purchaseRequestRepository) {
        this.reportService = reportService;
        this.purchaseSectorService = purchaseSectorService;
        this.purchaseRequestRepository = purchaseRequestRepository;
    }

    public void start(String reportPath) {
        // 1. Validate the report path
        if (reportPath == null || reportPath.isEmpty()) {
            throw new IllegalArgumentException("Report path cannot be null or empty");
        }
        // 2. Check if the report file exists
        java.io.File reportFile = new java.io.File(reportPath);
        if (!reportFile.exists()) {
            throw new IllegalArgumentException("Report file does not exist at the specified path: " + reportPath);
        }
        // 3. Process the stock report
        try {
            var items = reportService.readStockReport(reportPath);
            // Process the stock items as needed
            // To verify item by item (by stream) if stock is below reorder threshold
            items.forEach(item -> {
                if (item.getQuantity() < item.getReorderThreshold()) {
                    // calculate how many items to buy (reorderThreshold) + (security factor(20%))
                    int orderQuantity = calculateOrderQuantity(item);
                    // buy more items, interacting with API of the supplier
                    var purchaseApproved = purchaseSectorService.sendPurchaseRequest(item, orderQuantity);
                    // save to mongoDB the bought items
                    persistResults(item, orderQuantity, purchaseApproved);

                }
            });
        } catch (Exception e) {
            throw new RuntimeException("Failed to process the stock report", e);
        }

        // 4. Log results of the processing to mongoDB

    }

    private void persistResults(CsvStockItem item,
                                int orderQuantity,
                                boolean purchaseApproved) {

        var entity = new PurchaseRequestEntity();
        entity.setItemId(item.getItemId());
        entity.setItemName(item.getItemName());
        entity.setQuantityOnStock(item.getQuantity());
        entity.setReorderThreshold(item.getReorderThreshold().toString());
        entity.setSupplierEmail(item.getSupplierEmail());
        entity.setSupplierName(item.getSupplierName());
        entity.setLastStockUpdateTime(item.getLastStockUpdateTime());

        entity.setPurchaseQuantity(orderQuantity);
        entity.setPurchaseApproved(purchaseApproved);
        entity.setPurchaseDateTime(java.time.LocalDateTime.now());
        purchaseRequestRepository.save(entity);
    }


    private Integer calculateOrderQuantity(CsvStockItem item) {
        return item.getReorderThreshold() + (int) Math.ceil(item.getReorderThreshold() * 0.2);
    }
}
