package mai.linh.project.connector;

import javax.resource.ResourceException;
import javax.resource.spi.ActivationSpec;
import javax.resource.spi.BootstrapContext;
import javax.resource.spi.ConfigProperty;
import javax.resource.spi.Connector;
import javax.resource.spi.ResourceAdapter;
import javax.resource.spi.ResourceAdapterInternalException;
import javax.resource.spi.TransactionSupport;
import javax.resource.spi.endpoint.MessageEndpointFactory;

import javax.transaction.xa.XAResource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * TcpResourceAdapter
 */
@Connector(
   displayName = "TCP outbound Resource Adapter",
   vendorName = "Community", 
   eisType = "TCP",
   reauthenticationSupport = false,
   transactionSupport = TransactionSupport.TransactionSupportLevel.NoTransaction)
public class TcpResourceAdapter implements ResourceAdapter, java.io.Serializable
{
   private static final long serialVersionUID = 1L;

   private static Logger logger = LogManager.getLogger(TcpResourceAdapter.class);

   @ConfigProperty(defaultValue = "Charlie")
   private String rAExampleConfigProperty;

   public TcpResourceAdapter()
   {
      logger.debug("TcpResourceAdapter");
   }

   /** 
    * Set rAExampleConfigProperty
    * @param rAExampleConfigProperty The value
    */
   public void setRAExampleConfigProperty(String rAExampleConfigProperty)
   {
      logger.debug("setRAExampleConfigProperty");
      this.rAExampleConfigProperty = rAExampleConfigProperty;
   }

   /** 
    * Get rAExampleConfigProperty
    * @return The value
    */
   public String getRAExampleConfigProperty()
   {
      logger.debug("getRAExampleConfigProperty");
      return rAExampleConfigProperty;
   }

   /**
    * This is called during the activation of a message endpoint.
    *
    * @param endpointFactory A message endpoint factory instance.
    * @param spec An activation spec JavaBean instance.
    * @throws ResourceException generic exception 
    */
   public void endpointActivation(MessageEndpointFactory endpointFactory, ActivationSpec spec) throws ResourceException
   {
      logger.debug("endpointActivation");
   }

   /**
    * This is called when a message endpoint is deactivated. 
    *
    * @param endpointFactory A message endpoint factory instance.
    * @param spec An activation spec JavaBean instance.
    */
   public void endpointDeactivation(MessageEndpointFactory endpointFactory, ActivationSpec spec)
   {
      logger.debug("endpointDeactivation");
   }

   /**
    * This is called when a resource adapter instance is bootstrapped.
    *
    * @param ctx A bootstrap context containing references 
    * @throws ResourceAdapterInternalException indicates bootstrap failure.
    */
   public void start(BootstrapContext ctx) throws ResourceAdapterInternalException
   {
      logger.debug("start");
   }

   /**
    * This is called when a resource adapter instance is undeployed or
    * during application server shutdown. 
    */
   public void stop()
   {
      logger.debug("stop");
   }

   /**
    * This method is called by the application server during crash recovery.
    *
    * @param specs An array of ActivationSpec JavaBeans 
    * @throws ResourceException generic exception 
    * @return An array of XAResource objects
    */
   public XAResource[] getXAResources(ActivationSpec[] specs) throws ResourceException
   {
      logger.debug("getXAResources");
      return null;
   }

   /** 
    * Returns a hash code value for the object.
    * @return A hash code value for this object.
    */
   @Override
   public int hashCode()
   {
      int result = 17;
      if (rAExampleConfigProperty != null)
         result += 31 * result + 7 * rAExampleConfigProperty.hashCode();
      else
         result += 31 * result + 7;
      return result;
   }

   /** 
    * Indicates whether some other object is equal to this one.
    * @param other The reference object with which to compare.
    * @return true if this object is the same as the obj argument, false otherwise.
    */
   @Override
   public boolean equals(Object other)
   {
      if (other == null)
         return false;
      if (other == this)
         return true;
      if (!(other instanceof TcpResourceAdapter))
         return false;
      boolean result = true;
      TcpResourceAdapter obj = (TcpResourceAdapter)other;
      if (result)
      {
         if (rAExampleConfigProperty == null)
            result = obj.getRAExampleConfigProperty() == null;
         else
            result = rAExampleConfigProperty.equals(obj.getRAExampleConfigProperty());
      }
      return result;
   }

}
