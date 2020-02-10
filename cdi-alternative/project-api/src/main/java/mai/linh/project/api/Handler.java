package mai.linh.project.api;

import javax.inject.Inject;

/**
 * Handler: 
 * This class has a Service injected with a default impmemtation.
 */
public class Handler {

    @Inject
    private Service service;

    public void say() {
        service.saySomething();
    }
}