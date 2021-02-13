package mai.linh.project.connector;

import javax.resource.cci.ResourceAdapterMetaData;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * TcpRaMetaData
 */
public class TcpRaMetaData implements ResourceAdapterMetaData
{
   private static Logger logger = LogManager.getLogger(TcpRaMetaData.class);
   /**
    * Default constructor
    */
   public TcpRaMetaData()
   {
      logger.debug("constructor");
   }

   /**
    * Gets the version of the resource adapter.
    *
    * @return String representing version of the resource adapter
    */
   @Override
   public String getAdapterVersion()
   {
      logger.debug(()->"getAdapterVersion");
      return "0.1-SNAPSHOT";
   }

   /**
    * Gets the name of the vendor that has provided the resource adapter.
    *
    * @return String representing name of the vendor 
    */
   @Override
   public String getAdapterVendorName()
   {
      logger.debug(()->"getAdapterVendorName");
      return "LM";
   }

   /**
    * Gets a tool displayable name of the resource adapter.
    *
    * @return String representing the name of the resource adapter
    */
   @Override
   public String getAdapterName()
   {
      logger.debug(()->"getAdapterName");
      return "TCP outbound Resource Adapter";
   }

   /**
    * Gets a tool displayable short desription of the resource adapter.
    *
    * @return String describing the resource adapter
    */
   @Override
   public String getAdapterShortDescription()
   {
      logger.debug("getAdapterShortDescription");
      return "Outbound TCP connection to EIS"; 
   }

   /**
    * Returns a string representation of the version
    *
    * @return String representing the supported version of the connector architecture
    */
   @Override
   public String getSpecVersion()
   {
      logger.debug(()->"getSpecVersion");
      return "JCA 1.7"; 
   }

   /**
    * Returns an array of fully-qualified names of InteractionSpec
    *
    * @return Array of fully-qualified class names of InteractionSpec classes
    */
   @Override
   public String[] getInteractionSpecsSupported()
   {
      logger.debug(()->"getInterdactionSpecsSupported");
      return null; 
   }

   /**
    * Returns true if the implementation class for the Interaction
    *
    * @return boolean Depending on method support
    */
   @Override
   public boolean supportsExecuteWithInputAndOutputRecord()
   {
      logger.debug(()->"supportsExecuteWithInputAndOutputRecord");
      return false; 
   }

   /**
    * Returns true if the implementation class for the Interaction
    *
    * @return boolean Depending on method support
    */
   @Override
   public boolean supportsExecuteWithInputRecordOnly()
   {
      logger.debug(()->"supportsExecuteWithInputRecordOnly");
      return false; 
   }

   /**
    * Returns true if the resource adapter implements the LocalTransaction
    *
    * @return true If resource adapter supports resource manager local transaction demarcation 
    */
   @Override
   public boolean supportsLocalTransactionDemarcation()
   {
      logger.debug(()->"supportsLocalTransactionDemarcation");
      return false; 
   }


}
