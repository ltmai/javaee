package mai.linh.project.connector;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.resource.NotSupportedException;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionEvent;
import javax.resource.spi.ConnectionEventListener;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.LocalTransaction;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionMetaData;

import javax.security.auth.Subject;
import javax.transaction.xa.XAResource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mai.linh.project.connector.api.TcpConnection;
import mai.linh.project.connector.api.TcpConnectionRequestInfo;

/**
 * TcpManagedConnection (physical connection)
 */
public class TcpManagedConnection implements ManagedConnection {

   private static Logger logger = LogManager.getLogger(TcpManagedConnection.class);  

   private PrintWriter logwriter;

   @SuppressWarnings("unused")
   private TcpResourceAdapter ra;

   private TcpManagedConnectionFactory mcf;

   private List<ConnectionEventListener> listeners;

   /**
    * The logical connection
    */
   private TcpConnectionImpl connection;

   private TcpSocketHandler socket;

   private TcpConnectionRequestInfo cxRequestInfo;

   /**
    * ManagedConnection constructor is called by ManagedConnectionFactory therefore it
    * may take any relevant parameters as needed. This is not limited by the specs.
    * @param mcf The ManagedConnectionFactory
    */
   public TcpManagedConnection(TcpResourceAdapter ra, TcpManagedConnectionFactory mcf, ConnectionRequestInfo cxRequestInfo)
         throws ResourceException {
      this.ra = ra;
      this.mcf = mcf;
      this.logwriter = null;
      this.listeners = Collections.synchronizedList(new ArrayList<ConnectionEventListener>(1));
      this.connection = null;

      if (cxRequestInfo instanceof TcpConnectionRequestInfo) {
         this.cxRequestInfo = (TcpConnectionRequestInfo) cxRequestInfo;
         this.socket = new TcpSocketHandler(this.cxRequestInfo.getHost(), this.cxRequestInfo.getPort());
         this.socket.connect();
      } else {
         throw new ResourceException("Invalid ConnectionRequestInfo type");
      }
   }

   /**
    * Creates a new connection handle for the underlying physical connection 
    * represented by the ManagedConnection instance. 
    *
    * @param subject Security context as JAAS subject
    * @param cxRequestInfo ConnectionRequestInfo instance
    * @return generic Object instance representing the connection handle. 
    * @throws ResourceException generic exception if operation fails
    */
   public Object getConnection(Subject subject, ConnectionRequestInfo cxRequestInfo) throws ResourceException
   {
      logger.debug(()->"getConnection()");
      connection = new TcpConnectionImpl(this, mcf);
      return connection;
   }

   /**
    * Used by the container to change the association of an 
    * application-level connection handle with a ManagedConneciton instance.
    *
    * @param connection Application-level connection handle
    * @throws ResourceException generic exception if operation fails
    */
   public void associateConnection(Object connection) throws ResourceException
   {
      logger.debug(()->"associateConnection()");

      if (connection == null)
         throw new ResourceException("Null connection handle");

      if (!(connection instanceof TcpConnectionImpl))
         throw new ResourceException("Wrong connection handle");

      this.connection = (TcpConnectionImpl)connection;
   }

   private void disassociateConnection() {
      if (connection != null) {
         connection.setManagedConnection(null);
         connection = null;
      }
   } 

   /**
    * Application server calls this method to force any cleanup on the ManagedConnection instance.
    *
    * @throws ResourceException generic exception if operation fails
    */
   public void cleanup() throws ResourceException
   {
      logger.debug(()->"cleanup()");
      disassociateConnection();
   }

   /**
    * Destroys the physical connection to the underlying resource manager.
    *
    * @throws ResourceException generic exception if operation fails
    */
   public void destroy() throws ResourceException
   {
      logger.debug(()->"destroy()");

      if (socket != null)
            socket.close();
   }

   /**
    * Adds a connection event listener to the ManagedConnection instance.
    *
    * @param listener A new ConnectionEventListener to be registered
    */
   public void addConnectionEventListener(ConnectionEventListener listener)
   {
      logger.debug(()->"addConnectionEventListener()");
      if (listener == null)
         throw new IllegalArgumentException("Listener is null");
      listeners.add(listener);
   }

   /**
    * Removes an already registered connection event listener from the ManagedConnection instance.
    *
    * @param listener already registered connection event listener to be removed
    */
   public void removeConnectionEventListener(ConnectionEventListener listener)
   {
      logger.debug(()->"removeConnectionEventListener()");
      if (listener == null)
         throw new IllegalArgumentException("Listener is null");
      listeners.remove(listener);
   }

   /**
    * Close handle
    *
    * @param handle The handle
    */
   void closeHandle(TcpConnection handle)
   {
      ConnectionEvent event = new ConnectionEvent(this, ConnectionEvent.CONNECTION_CLOSED);
      event.setConnectionHandle(handle);
      for (ConnectionEventListener cel : listeners)
      {
         cel.connectionClosed(event);
      }
   }

   /**
    * Gets the log writer for this ManagedConnection instance.
    *
    * @return Character output stream associated with this Managed-Connection instance
    * @throws ResourceException generic exception if operation fails
    */
   public PrintWriter getLogWriter() throws ResourceException
   {
      logger.debug(()->"getLogWriter()");
      return logwriter;
   }

   /**
    * Sets the log writer for this ManagedConnection instance.
    *
    * @param out Character Output stream to be associated
    * @throws ResourceException  generic exception if operation fails
    */
   public void setLogWriter(PrintWriter out) throws ResourceException
   {
      logger.debug(()->"setLogWriter()");
      logwriter = out;
   }

   /**
    * Returns an <code>javax.resource.spi.LocalTransaction</code> instance.
    *
    * @return LocalTransaction instance
    * @throws ResourceException generic exception if operation fails
    */
   public LocalTransaction getLocalTransaction() throws ResourceException
   {
      throw new NotSupportedException("getLocalTransaction() not supported");
   }

   /**
    * Returns an <code>javax.transaction.xa.XAresource</code> instance. 
    *
    * @return XAResource instance
    * @throws ResourceException generic exception if operation fails
    */
   public XAResource getXAResource() throws ResourceException
   {
      throw new NotSupportedException("getXAResource() not supported");
   }

   /**
    * Gets the metadata information for this connection's underlying EIS resource manager instance. 
    *
    * @return ManagedConnectionMetaData instance
    * @throws ResourceException generic exception if operation fails
    */
   public ManagedConnectionMetaData getMetaData() throws ResourceException
   {
      logger.debug(()->"getMetaData()");
      return new TcpManagedConnectionMetaData();
   }

   public int read(byte[] buffer, int offset, int length)
   {
      return socket.read(buffer, offset, length);
   }

   public void write(byte[] buffer, int offset, int length)
   {
      logger.debug(()->"write()");
      socket.write(buffer, offset, length);
   }

   public boolean matches(ConnectionRequestInfo cxRequestInfo) {
      return this.cxRequestInfo.equals(cxRequestInfo);
   }

}
