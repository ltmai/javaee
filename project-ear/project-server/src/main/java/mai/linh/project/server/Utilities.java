package mai.linh.project.server;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Utilities CDI injected by JSF bean
 */
public class Utilities implements Serializable
{
    private static final long serialVersionUID = 1L;

    public String timestampAsString() 
    {
        return LocalDateTime.now().toString();
    }
}