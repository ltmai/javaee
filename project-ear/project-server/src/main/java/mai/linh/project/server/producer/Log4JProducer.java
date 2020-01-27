package mai.linh.project.server.producer;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Log4jProducer
 */
public class Log4JProducer {

    @Produces @Log4J
    public Logger getLogger(InjectionPoint ip) {
        return LogManager.getLogger(ip.getMember().getDeclaringClass().getName());
    }
}