package dev.mileski.smartstock.service;

import dev.mileski.smartstock.client.PurchaseSectorClient;
import dev.mileski.smartstock.client.dto.PurchaseSectorRequest;
import dev.mileski.smartstock.domain.CsvStockItem;
import org.springframework.stereotype.Service;

@Service
public class PurchaseSectorService {

    private final AuthService authService;
    private final PurchaseSectorClient purchaseSectorClient;

    public PurchaseSectorService(AuthService authService, PurchaseSectorClient purchaseSectorClient) {
        this.authService = authService;
        this.purchaseSectorClient = purchaseSectorClient;
    }

    public boolean sendPurchaseRequest(CsvStockItem item,
                                       Integer orderQuantity) {
        if (item == null || orderQuantity <= 0) {
            return false; // Invalid item or orderQuantity
        }
        // Send Auth to API and get the token.
        var token = authService.getToken();

        // Send the purchase request to the purchase sector API with the token
        var request = new PurchaseSectorRequest(
                item.getItemId(),
                item.getItemName(),
                item.getSupplierName(),
                item.getSupplierEmail(),
                orderQuantity
        );
        var response = purchaseSectorClient.sendPurchaseRequest(token, request);



        // Validate the response
        return true;
    }

}
