package com.linhmai.simple.webapp;

import com.linhmai.simple.webapp.qualifier.Random;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named("hellobean")
public class HelloWorld implements Serializable {

    @Inject @Random
    Instance<Integer> randomInt;
	
	public int getRandomInt() {
		return randomInt.get();
	}

    public HelloWorld() {
        System.out.println("HelloWorld started!");
    }
}