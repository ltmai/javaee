package mai.linh.webapp.demo;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import mai.linh.webapp.demo.ejb.Service;
import mai.linh.webapp.demo.ejb.ServiceSelection;

/**
 * CDI Managed bean The @Named (javax.inject.Named) annotation in a class, along
 * with a scope annotation, automatically registers that class as a resource
 * with the JavaServer Faces implementation. A bean that uses these annotations
 * is a CDI managed bean. CDI managed bean can have the following scopes:
 * 
 * @ApplicationScoped
 * @SessionScoped
 * @FlowScoped
 * @RequestScoped
 * @Dependent (default when used with @Named)
 */
@Named("welcome")
@SessionScoped
public class WelcomeBean {
    @Inject
    @ServiceSelection
    private Service service;

    public WelcomeBean() 
    {
        System.out.println("WelcomeBean instantiated");
    }
    
    public String getMessage() 
    {
        return service.getMessage();
    }
}