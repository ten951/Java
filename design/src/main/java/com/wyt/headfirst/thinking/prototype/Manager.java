package com.wyt.headfirst.thinking.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/24.
 */
public class Manager {
    private Map<String, Product> showCase = new HashMap<>();

    public void register(String name, Product product) {
        showCase.put(name, product);
    }

    public Product create(String protoName) {
        Product product = showCase.get(protoName);
        return product.createClone();
    }
}
