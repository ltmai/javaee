package mai.linh.project.api;

import javax.ejb.Remote;

/**
 * Service API
 */
@Remote
public interface Service
{
    void saySomething();
}