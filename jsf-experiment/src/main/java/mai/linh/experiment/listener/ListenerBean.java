package mai.linh.experiment.listener;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

@Named
@RequestScoped
public class ListenerBean {
    static private final Logger logger = Logger.getLogger(ListenerBean.class.getName());

    public String action() {
        logger.info("action fired");    
        return "listener";
    }

    public void listener(ActionEvent e) {
        logger.info("listener fired");
    }
}
