package com.linhmai.simple.webapp;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;

import com.linhmai.simple.webapp.qualifier.Random;

@SessionScoped
@Named("hellobean")
public class HelloWorld implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Inject
    @Random
    Instance<Integer> randomInt;
	
	public int getRandomInt() {
		return randomInt.get();
	}

    public HelloWorld() {
        System.out.println("HelloWorld started!");
    }
}