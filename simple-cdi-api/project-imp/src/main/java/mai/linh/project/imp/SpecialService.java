package mai.linh.project.imp;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import mai.linh.project.api.Service;
import mai.linh.project.api.ServiceType;
import mai.linh.project.bean.EjbBean;

/**
 * CDI Service implementierung
 */
@RequestScoped
@ServiceType(ServiceType.Type.SPECIAL)
public class SpecialService implements Service
{
    @Inject
    private EjbBean ejbBean;

    @Override
    public void saySomething() {
        ejbBean.doSomething();
        System.out.println("[CDI] Thanks for using our special service");
    }
}