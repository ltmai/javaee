package com.linhmai.simple.webapp.bean;

import com.linhmai.simple.webapp.qualifier.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.io.Serializable;


/**
 * A managed bean that provide @Produces method to generate random numbers.
 * When a contextual instance of the bean is injected, the @Produces method
 * is invoked every time the instance is referenced. 
 */
@ApplicationScoped
public class RandomProducer implements Serializable {
	
	private static final long serialVersionUID = -1540057713866812859L;

	private static final int MAX_RANDOM = 100;
	
	private java.util.Random random = new java.util.Random( System.currentTimeMillis() );

	@Produces @Random
    int next() {
	    return random.nextInt(MAX_RANDOM);
	}
}
