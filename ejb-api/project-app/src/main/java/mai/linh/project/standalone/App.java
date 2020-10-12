package mai.linh.project.standalone;

import java.util.Hashtable;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import mai.linh.project.api.Service;

/**
 * This client-application demonstrates two possibilities to use remote EJB
 * by directly injecting (@EJB) and JNDI lookup.
 * 
 * Remote EJB can be directly injected into static member of the main class.
 */
public class App 
{
    /**
     * This constant is specific to JBoss application server.
     */
    private static final String JBOSS_CONTEXT_FACTORY = "org.jboss.ejb.client.naming";

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

    /**
     * lookupRemoteBean looks up remote bean. The JNDI lookup string is of the form:
     * ejb:<application-name>/<module-name>/<bean-name>!<full-qualified-interface-name>
     */
    public static void lookupRemoteBean() throws NamingException {
        final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, JBOSS_CONTEXT_FACTORY);

        final Context context = new InitialContext(jndiProperties);

        String lookupName = "ejb:project-tst/project-imp/ServiceBean!" + Service.class.getName();
        System.out.println("Looking up " + lookupName);

        Service bean = (Service) context.lookup(lookupName);
        System.out.println(bean.saySomething("I'm remotely looked up"));
    }

    public static void main( String[] args ) throws Exception
    {
        System.out.println(service.saySomething("I'm injected with @EJB"));

        lookupRemoteBean();
    }
}
