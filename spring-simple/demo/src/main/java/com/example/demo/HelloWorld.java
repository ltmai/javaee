package com.example.demo;

/**
 * HelloWorld
 */
public class HelloWorld {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void init() {
        System.out.println("[init-method] Initializing bean");
    }

    public void destroy() {
        System.out.println("[destroy-method] Destroying bean");
    }
}