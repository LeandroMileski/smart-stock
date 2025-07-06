package dev.mileski.smartstock.service;

import dev.mileski.smartstock.domain.CsvStockItem;
import org.springframework.stereotype.Service;

@Service
public class PurchaseSectorService {

    private final AuthService authService;

    public PurchaseSectorService(AuthService authService) {
        this.authService = authService;
    }

    public boolean sendPurchaseRequest(CsvStockItem item,
                                       Integer quantity) {
        if (item == null || quantity <= 0) {
            return false; // Invalid item or quantity
        }
        // Send Auth to API and get the token.
        var token = authService.getToken();

        // Send the purchase request to the purchase sector API with the token
        

        // Validate the response
        return true;
    }

}
