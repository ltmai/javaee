package com.example.webapp.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

@Named
@SessionScoped
public class CartController implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String, Integer> cart = new HashMap<>();

    public int getItemCount() {
        return cart.values()
                   .stream()
                   .collect(Collectors.summingInt(Integer::valueOf));
    }
    
    /**
     * AJAX listener
     * @param event
     * @throws AbortProcessingException
     */
    public void addToCart(AjaxBehaviorEvent event) throws AbortProcessingException {
        String article = (String)event.getComponent().getAttributes().get("article");
        Integer count = Integer.valueOf((String)event.getComponent().getAttributes().get("count"));

        System.out.println("Adding " + count + " " + article + " to cart");
        cart.compute(article, (k, v) -> (v == null) ? 1 : v + count);
    } 
}
