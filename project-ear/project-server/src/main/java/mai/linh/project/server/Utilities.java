package mai.linh.project.server;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.enterprise.context.Dependent;

/**
 * Utilities CDI
 */
@Dependent
public class Utilities implements Serializable
{
    private static final long serialVersionUID = 1L;

    public String timestampAsString() 
    {
        return LocalDateTime.now().toString();
    }
}