package mai.linh.experiment.listener;

import java.util.logging.Logger;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.ActionListenerWrapper;

/**
 * ActionListenerWrapper provides a simple implementation of ActionListener 
 * that can be subclassed by developers wishing to provide specialized behavior 
 * to an *existing* ActionListener instance. The default implementation of all 
 * methods is to call through to the wrapped ActionListener.
 */
public class ListenerWrapper extends ActionListenerWrapper {

    static public final Logger logger = Logger.getLogger(CustomListener.class.getName());

    private final ActionListener listener = new CustomListener();

    @Override
    public ActionListener getWrapped() {
        return listener;
    }
    
    @Override    
    public void processAction(ActionEvent event) throws AbortProcessingException {
        logger.info("Before calling wrapped listener");
        getWrapped().processAction(event);
        logger.info("After calling wrapped listener");
    }
}
