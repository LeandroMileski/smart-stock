package dev.mileski.smartstock.repository;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Document(collection = "col_purchase_requests")
public class PurchaseRequestEntity {
    @MongoId
    @Field(name = "item_id")
    private String itemId;

    @Field(name = "item_name")
    private String itemName;

    @Field(name = "quantity_on_stock")
    private Integer quantityOnStock;

    @Field(name = "reorder_threshold")
    private String reorderThreshold;

    @Field(name = "supplier_email")
    private String supplierEmail;

    @Field(name = "supplier_name")
    private String supplierName;

    @Field(name = "last_stock_update_time")
    private String lastStockUpdateTime;

    @Field(name = "purchase_quantity")
    private Integer purchaseQuantity;

    @Field(name = "purchase_approved")
    private boolean purchaseApproved;

    @Field(name = "purchase_date_time")
    private LocalDateTime purchaseDateTime;

    public PurchaseRequestEntity() {

    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQuantityOnStock() {
        return quantityOnStock;
    }

    public void setQuantityOnStock(Integer quantityOnStock) {
        this.quantityOnStock = quantityOnStock;
    }

    public String getReorderThreshold() {
        return reorderThreshold;
    }

    public void setReorderThreshold(String reorderThreshold) {
        this.reorderThreshold = reorderThreshold;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getLastStockUpdateTime() {
        return lastStockUpdateTime;
    }

    public void setLastStockUpdateTime(String lastStockUpdateTime) {
        this.lastStockUpdateTime = lastStockUpdateTime;
    }

    public Integer getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(Integer purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public boolean isPurchaseApproved() {
        return purchaseApproved;
    }

    public void setPurchaseApproved(boolean purchaseApproved) {
        this.purchaseApproved = purchaseApproved;
    }

    public LocalDateTime getPurchaseDateTime() {
        return purchaseDateTime;
    }

    public void setPurchaseDateTime(LocalDateTime purchaseDateTime) {
        this.purchaseDateTime = purchaseDateTime;
    }
}
