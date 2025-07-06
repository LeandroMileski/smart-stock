package dev.mileski.smartstock.service;

import dev.mileski.smartstock.domain.CsvStockItem;
import org.springframework.stereotype.Service;

@Service
public class SmartStockService {

    private final ReportService reportService;

    public SmartStockService(ReportService reportService) {
        this.reportService = reportService;
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

                    // save to mongoDB the bought items

                }
            });
        } catch (Exception e) {
            throw new RuntimeException("Failed to process the stock report", e);
        }

        // 4. Log results of the processing to mongoDB

    }

    private Integer calculateOrderQuantity(CsvStockItem item) {
        return item.getReorderThreshold() + (int) Math.ceil(item.getReorderThreshold() * 0.2);
    }
}
