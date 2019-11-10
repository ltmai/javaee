package com.example.demo;

/**
 * HelloWorld
 * This child bean inherits configuration data from a parent definition.
 * It must not necessarily extend the parent class (by declaring Spring 
 * inheritance with in beans.xml) but it can do that either.  
 */
public class HelloWorldUpper extends HelloWorld {

    @Override
    public void setMessage(String message) {
        super.setMessage(message.toUpperCase());
    }

}