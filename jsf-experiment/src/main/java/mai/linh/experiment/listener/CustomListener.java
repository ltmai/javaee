package mai.linh.experiment.listener;

import java.util.logging.Logger;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

public class CustomListener implements ActionListener {
    static public final Logger logger = Logger.getLogger(CustomListener.class.getName());

    @Override
    public void processAction(ActionEvent event) throws AbortProcessingException {
        logger.info("Custom listener fired");
    }
}
