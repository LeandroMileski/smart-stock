package dev.mileski.smartstock.domain;

import com.opencsv.bean.CsvBindByName;

public class CsvStockItem {
//exameple CSV format:
   //item_id,item_name,quantity,reorder_threshold,supplier_name,supplier_email,last_stock_update_time
    //1f3b5d6a-4bce-4b90-8c0c-dc71a9f81e65,Apple AirPods Pro,4,10,Apple Inc.,support@apple.com,2024-09-08T14:30:00
    @CsvBindByName(column = "item_id")
    private String itemId;

    @CsvBindByName(column = "item_name")
    private String itemName;

    @CsvBindByName(column = "quantity")
    private Integer quantity;

    @CsvBindByName(column = "reorder_threshold")
    private Integer reorderThreshold;

    @CsvBindByName(column = "supplier_name")
    private String supplierName;

    @CsvBindByName(column = "supplier_email")
    private String supplierEmail;

    @CsvBindByName(column = "last_stock_update_time")
    private String lastStockUpdateTime;

    public CsvStockItem() {
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getReorderThreshold() {
        return reorderThreshold;
    }

    public void setReorderThreshold(Integer reorderThreshold) {
        this.reorderThreshold = reorderThreshold;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getLastStockUpdateTime() {
        return lastStockUpdateTime;
    }

    public void setLastStockUpdateTime(String lastStockUpdateTime) {
        this.lastStockUpdateTime = lastStockUpdateTime;
    }
}
