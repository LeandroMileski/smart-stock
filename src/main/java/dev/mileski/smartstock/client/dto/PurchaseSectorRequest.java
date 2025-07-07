package dev.mileski.smartstock.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 "item_id": "6e9c24e7-5d4a-4c77-9ed3",
 "item_name": "Samsung 32”",
 "supplier_name": "Samsung",
 "supplier_email": "supplier@samsung.com",
 "quantity"
 */

public record PurchaseSectorRequest(@JsonProperty("item_id") String itemId,
                                    @JsonProperty("item_name") String itemName,
                                    @JsonProperty("supplier_name") String supplierName,
                                    @JsonProperty("supplier_email") String supplierEmail,
                                    @JsonProperty("quantity") Integer quantity)
                                    {

}
