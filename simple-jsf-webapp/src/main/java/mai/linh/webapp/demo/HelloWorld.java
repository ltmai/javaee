package mai.linh.webapp.demo;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * CDI bean used in JSF
 */
@Named
@SessionScoped
public class HelloWorld implements Serializable {

    private static final long serialVersionUID = 1L;
    private String text = "[CDI] Hello World!";

    @PostConstruct
    public void initialize() 
    {
        System.out.println(HelloWorld.class.getSimpleName() + " was constructed");
    }

    public String getText() 
    {
        return text;
    }
}