package dev.mileski.smartstock.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.mileski.smartstock.client.dto.PurchaseSectorRequest;
import dev.mileski.smartstock.client.dto.PurchaseSectorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "purchase-sector-client", url = "${app.purchase-sector.url}")
public interface PurchaseSectorClient {

    ResponseEntity<PurchaseSectorResponse> sendPurchaseRequest(
            @RequestHeader("Authorization") String token,
            @RequestHeader PurchaseSectorRequest request);
}
