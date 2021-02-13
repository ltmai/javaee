package mai.linh.project.connector;

import javax.naming.NamingException;
import javax.naming.Reference;

import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mai.linh.project.connector.api.TcpConnection;
import mai.linh.project.connector.api.TcpConnectionFactory;
import mai.linh.project.connector.api.TcpConnectionRequestInfo;

/**
 * TcpConnectionFactoryImpl
 * Implementation of client connection factory interface.
 */
public class TcpConnectionFactoryImpl implements TcpConnectionFactory
{
   private static final long serialVersionUID = 1L;

   private static Logger logger = LogManager.getLogger(TcpConnectionFactoryImpl.class);

   private Reference reference;

   private TcpManagedConnectionFactory mcf;

   private ConnectionManager connectionManager;

   public TcpConnectionFactoryImpl()
   {
   }

   /**
    * Constructor
    * @param mcf ManagedConnectionFactory
    * @param cxManager ConnectionManager
    */
   public TcpConnectionFactoryImpl(TcpManagedConnectionFactory mcf, ConnectionManager cxManager)
   {
      this.mcf = mcf;
      this.connectionManager = cxManager;
   }

   /** 
    * Get connection from factory (delegiert die Implementierung an den ConnectionManager)
    *
    * @return TcpConnection instance
    * @exception ResourceException Thrown if a connection can't be obtained
    */
   @Override
   public TcpConnection getConnection(TcpConnectionRequestInfo info) throws ResourceException
   {
      logger.debug(()->"getConnection()");
      return (TcpConnection)connectionManager.allocateConnection(mcf, info);
   }

   /**
    * Get the Reference instance.
    *
    * @return Reference instance
    * @exception NamingException Thrown if a reference can't be obtained
    */
   @Override
   public Reference getReference() throws NamingException
   {
      logger.debug(()->"getReference()");
      return reference;
   }

   /**
    * Set the Reference instance.
    *
    * @param reference A Reference instance
    */
   @Override
   public void setReference(Reference reference)
   {
      logger.debug(()->"setReference()");
      this.reference = reference;
   }

}
