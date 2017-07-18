package com.wyt.learn.restaurant.mcrsrvc.domain.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 餐厅的实体
 *
 * @author Darcy
 *         Created by Darcy on 2017/7/17.
 */
public class Restaurant extends BaseEntity<String> {

    private List<Table> tables = new ArrayList<>();

    public Restaurant(String id, String name, List<Table> tables) {
        super(id, name);
        this.tables = tables;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "tables=" + tables +
                '}';
    }
}
