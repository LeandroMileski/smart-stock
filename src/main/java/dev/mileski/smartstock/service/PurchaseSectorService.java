package dev.mileski.smartstock.service;

import dev.mileski.smartstock.client.PurchaseSectorClient;
import dev.mileski.smartstock.client.dto.PurchaseSectorRequest;
import dev.mileski.smartstock.domain.CsvStockItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PurchaseSectorService {

    private static final Logger logger = LoggerFactory.getLogger(PurchaseSectorService.class);

    private final AuthService authService;
    private final PurchaseSectorClient purchaseSectorClient;

    public PurchaseSectorService(AuthService authService, PurchaseSectorClient purchaseSectorClient) {
        this.authService = authService;
        this.purchaseSectorClient = purchaseSectorClient;
    }

    public boolean sendPurchaseRequest(CsvStockItem item,
                                       Integer quantity) {
        if (item == null || quantity <= 0) {
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
                quantity
        );
        var response = purchaseSectorClient.sendPurchaseRequest(token, request);

        // Validate the response
        if(response.getStatusCode().value() != HttpStatus.ACCEPTED.value()) {
            logger.error("Failed to send purchase request for item {}: {}",
                    item.getItemName(), response.getStatusCode());
            return false;
        }
        return true;
    }

}
