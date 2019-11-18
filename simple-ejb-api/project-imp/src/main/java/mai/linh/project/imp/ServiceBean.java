package mai.linh.project.imp;

import javax.ejb.Stateless;

import mai.linh.project.api.Service;

/**
 * Service implementierung
 */
@Stateless
public class ServiceBean implements Service
{

    @Override
    public String saySomething() {
        return "[ServiceBean] What just happened?";
    }
}