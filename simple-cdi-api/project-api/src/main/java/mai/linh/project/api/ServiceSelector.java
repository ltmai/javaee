package mai.linh.project.api;

import javax.enterprise.util.AnnotationLiteral;

/**
 * ServiceSelector
 */
public class ServiceSelector 
    extends AnnotationLiteral<ServiceType> implements ServiceType {

    private static final long serialVersionUID = 1L;
    
    Type type;

    public ServiceSelector(Type type) {
        this.type = type;
    }

    @Override
    public Type value() {
        return type;
    }
           
}