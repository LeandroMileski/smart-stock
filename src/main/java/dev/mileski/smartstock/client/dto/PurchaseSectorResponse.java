package dev.mileski.smartstock.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PurchaseSectorResponse(@JsonProperty("message") String message) {
}
