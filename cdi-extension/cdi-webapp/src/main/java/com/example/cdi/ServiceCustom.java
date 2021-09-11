package com.example.cdi;

@Custom
public class ServiceCustom implements Service {

    @Override
    public void say() {
        System.out.println("CUSTOM");
    }
    
}