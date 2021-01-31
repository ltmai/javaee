package mai.linh.project.connector;

import javax.resource.ResourceException;
import javax.resource.spi.ManagedConnectionMetaData;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * TcpManagedConnectionMetaData
 */
public class TcpManagedConnectionMetaData implements ManagedConnectionMetaData
{
   private static Logger logger = LogManager.getLogger(TcpManagedConnectionMetaData.class);

   public TcpManagedConnectionMetaData()
   {
   }

   /**
    * Returns Product name of the underlying EIS instance connected through the ManagedConnection.
    *
    * @return Product name of the EIS instance
    * @throws ResourceException Thrown if an error occurs
    */
   @Override
   public String getEISProductName() throws ResourceException
   {
      logger.debug("getEISProductName()");
      return null; //TODO
   }

   /**
    * Returns Product version of the underlying EIS instance connected through the ManagedConnection.
    *
    * @return Product version of the EIS instance
    * @throws ResourceException Thrown if an error occurs
    */
   @Override
   public String getEISProductVersion() throws ResourceException
   {
      logger.debug("getEISProductVersion()");
      return null; //TODO
   }

   /**
    * Returns maximum limit on number of active concurrent connections 
    *
    * @return Maximum limit for number of active concurrent connections
    * @throws ResourceException Thrown if an error occurs
    */
   @Override
   public int getMaxConnections() throws ResourceException
   {
      logger.debug("getMaxConnections()");
      return 0; //TODO
   }

   /**
    * Returns name of the user associated with the ManagedConnection instance
    *
    * @return Name of the user
    * @throws ResourceException Thrown if an error occurs
    */
   @Override
   public String getUserName() throws ResourceException
   {
      logger.debug("getUserName()");
      return null; //TODO
   }
}
