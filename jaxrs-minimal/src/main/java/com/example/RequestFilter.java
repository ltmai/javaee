package com.example;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class RequestFilter implements ContainerRequestFilter {
    private static final Logger logger = Logger.getLogger(RequestFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        logger.info("Incoming request: " + requestContext.getUriInfo());
    }
    
}