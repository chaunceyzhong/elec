package cn.itcast.elec.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chauncey 2018/1/16.
 */
public class Stock {

    private String stock_id;
    private String name;

    private Set<StockItem> stockItems = new HashSet<>();

    public Set<StockItem> getStockItems() {
        return stockItems;
    }

    public void setStockItems(Set<StockItem> stockItems) {
        this.stockItems = stockItems;
    }

    public String getStock_id() {
        return stock_id;
    }

    public void setStock_id(String stock_id) {
        this.stock_id = stock_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
