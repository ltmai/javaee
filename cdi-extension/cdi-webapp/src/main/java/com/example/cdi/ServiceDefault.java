package com.example.cdi;

public class ServiceDefault implements Service {

    @Override
    public void say() {
        System.out.println("DEFAULT");
    }
    
}