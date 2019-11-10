package com.example.demo;

/**
 * ServiceManager
 * Demonstrates constructor-based dependency injection
 */
public class ServiceManager {

    private Service service;

    public ServiceManager(Service service) {
        this.service = service;
    }

    public void doService() {
        service.doService();
    }
}