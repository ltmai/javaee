package mai.linh.project.standalone;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import java.util.Properties;

import mai.linh.project.api.Service;

/**
 * Hello world!
 *
 */
public class App 
{
    /**
     * JSR 342 - Java EE Platform Specification EE.5.2.5 Annotations and Injection:
     * Because application clients use the same lifecycle as Java SE applications, no
     * instance of the application client main class is created by the application client
     * container. Instead, the static main method is invoked. To support injection for 
     * the application client main class, the fields or methods annotated for injection 
     * must be static.
     */
    @EJB
    private static Service service;

    public static void main( String[] args ) throws Exception
    {
        System.out.println(service.saySomething());

        EJBContainer ejbContainer = null;
        Context context = null;
        Properties properties = new Properties();

        try {
            ejbContainer = EJBContainer.createEJBContainer(properties);
            context = ejbContainer.getContext();
            
            Service service = (Service) context.lookup("java:global/project-tst/project-imp/ServiceBean");
            System.out.println(service.saySomething());
        }
        finally {
            if (ejbContainer != null)   
                ejbContainer.close();
        }
    }
}
