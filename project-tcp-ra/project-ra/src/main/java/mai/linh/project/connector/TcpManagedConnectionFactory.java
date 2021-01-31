package mai.linh.project.connector;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionDefinition;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionFactory;
import javax.resource.spi.ResourceAdapter;
import javax.resource.spi.ResourceAdapterAssociation;

import javax.security.auth.Subject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mai.linh.project.connector.api.TcpConnection;
import mai.linh.project.connector.api.TcpConnectionFactory;

/**
 * TcpManagedConnectionFactory
 */
@ConnectionDefinition(
   connection = TcpConnection.class,
   connectionImpl = TcpConnectionImpl.class,
   connectionFactory = TcpConnectionFactory.class,
   connectionFactoryImpl = TcpConnectionFactoryImpl.class)
public class TcpManagedConnectionFactory implements ManagedConnectionFactory, ResourceAdapterAssociation
{
   private static final long serialVersionUID = 1L;

   private static Logger logger = LogManager.getLogger(TcpManagedConnectionFactory.class);

   private ResourceAdapter ra;

   private PrintWriter logwriter;

   public TcpManagedConnectionFactory()
   {
   }

   /**
    * Creates a Connection Factory instance. 
    *
    * @param cxManager ConnectionManager to be associated with created EIS connection factory instance
    * @return EIS-specific Connection Factory instance or javax.resource.cci.ConnectionFactory instance
    * @throws ResourceException Generic exception
    */
   public Object createConnectionFactory(ConnectionManager cxManager) throws ResourceException
   {
      logger.debug("createConnectionFactory()");
      return new TcpConnectionFactoryImpl(this, cxManager);
   }

   /**
    * Creates a Connection Factory instance. 
    *
    * @return EIS-specific Connection Factory instance or javax.resource.cci.ConnectionFactory instance
    * @throws ResourceException Generic exception
    */
   public Object createConnectionFactory() throws ResourceException
   {
      throw new ResourceException("This resource adapter doesn't support non-managed environments");
   }

   /**
    * Creates a new physical connection to the underlying EIS resource manager.
    *
    * @param subject Caller's security information
    * @param cxRequestInfo Additional resource adapter specific connection request information
    * @throws ResourceException generic exception
    * @return ManagedConnection instance 
    */
   public ManagedConnection createManagedConnection(Subject subject, ConnectionRequestInfo cxRequestInfo) throws ResourceException
   {
      logger.debug("createManagedConnection()");
      return new TcpManagedConnection(this, cxRequestInfo);
   }

   /**
    * Returns a matched connection from the candidate set of connections. 
    *
    * @param connectionSet Candidate connection set
    * @param subject Caller's security information
    * @param cxRequestInfo Additional resource adapter specific connection request information
    * @throws ResourceException generic exception
    * @return ManagedConnection if resource adapter finds an acceptable match otherwise null 
    */
   public ManagedConnection matchManagedConnections(@SuppressWarnings("rawtypes") Set connectionSet,
         Subject subject, ConnectionRequestInfo cxRequestInfo) throws ResourceException
   {
      logger.debug("matchManagedConnections()");
      ManagedConnection result = null;
      @SuppressWarnings("rawtypes") 
      Iterator it = connectionSet.iterator();
      while (result == null && it.hasNext())
      {
         ManagedConnection mc = (ManagedConnection)it.next();
         if (mc instanceof TcpManagedConnection)
         {
            if (((TcpManagedConnection)mc).matches(cxRequestInfo))
               result = mc;
         }

      }
      return result;
   }

   /**
    * Get the log writer for this ManagedConnectionFactory instance.
    *
    * @return PrintWriter
    * @throws ResourceException generic exception
    */
   public PrintWriter getLogWriter() throws ResourceException
   {
      logger.debug("getLogWriter()");
      return logwriter;
   }

   /**
    * Set the log writer for this ManagedConnectionFactory instance.
    *
    * @param out PrintWriter - an out stream for error logging and tracing
    * @throws ResourceException generic exception
    */
   public void setLogWriter(PrintWriter out) throws ResourceException
   {
      logger.debug("setLogWriter()");
      logwriter = out;
   }

   /**
    * Get the resource adapter
    *
    * @return The handle
    */
   public ResourceAdapter getResourceAdapter()
   {
      logger.debug("getResourceAdapter()");
      return ra;
   }

   /**
    * Set the resource adapter
    *
    * @param ra The handle
    */
   public void setResourceAdapter(ResourceAdapter ra)
   {
      logger.debug("setResourceAdapter()");
      this.ra = ra;
   }

   /**
	 * In Section 16.4 der Spec ist gefordert, dass die Standard equals 
	 * Implementierung NICHT überschrieben wird ("no two Java objects are 
	 * considerd equals"), damit muss auch {@link #hashCode()} bleiben.
	 */ 	
	@Override
	public final int hashCode() {
		return super.hashCode();
	}

	@Override
	public final boolean equals(Object obj) {
		return super.equals(obj);
	}

}
