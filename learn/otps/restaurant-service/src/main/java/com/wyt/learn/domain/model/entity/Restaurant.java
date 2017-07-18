package com.wyt.learn.domain.model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Darcy
 *         Created by Darcy on 2017/7/18.
 */
public class Restaurant extends BaseEntity<String> {
    private List<Table> tables = new ArrayList<>();
    private String address;

    public Restaurant(String name, String id, String address, List<Table> tables) {
        super(id, name);
        this.tables = tables;
        this.address = address;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Overridden toString() method that return String presentation of the
     * Object
     *
     * @return
     */
    @Override
    public String toString() {
        return "{id: " + id + ", name: " +
                name + ", address: " + address +
                ", tables: " + tables + "}";
    }
}
