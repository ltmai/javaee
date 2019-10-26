package mai.linh.webapp;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import mai.linh.project.server.Utilities;

/**
 * HelloBean CDI
 */
@Named("hello")
@SessionScoped
public class HelloBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
    private Utilities utils;

    private String message = "Hello World! ";

    @PostConstruct
    public void init() 
    {
        System.out.println("HelloBean::PostConstruct");
    }

    @PreDestroy
    public void cleanup() 
    {
        System.out.println("HelloBean::PreDestroy");
    }

    public String getMessage() 
    {
        // ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        // HttpServletRequest request = (HttpServletRequest)context.getRequest();
        // HttpServletResponse response = (HttpServletResponse)context.getResponse();
        return message + utils.timestampAsString();
    }
    
    public void setMessage(String message) 
    {
        this.message = message;
    }
}