package mai.linh.project.api;

import javax.ejb.Remote;

/**
 * Service API
 */
@Remote
public interface Service
{
    /**
     * This JDNI is used by remote clients to lookup the current API implementation.
     * In case a new implementation should be used, JDNI string will be updated.
     * 
     * Local clients in the other hand, can inject Service directly. If there are 
     * more than one implementations, the Service should be injected with @Qualifier. 
     */
    public static final String JDNI_LOOKUP="";

    String saySomething();
}