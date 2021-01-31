package mai.linh.project.connector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mai.linh.project.connector.api.TcpConnection;

/**
 * TcpConnectionImpl
 * Implementation of connection interface (logical connection). In general 
 * all method calls are delegated to the associated physical connection
 * instance.
 */
public class TcpConnectionImpl implements TcpConnection
{
   @SuppressWarnings("unused")
   private static Logger logger = LogManager.getLogger(TcpConnectionImpl.class);

   private TcpManagedConnection mc;

   @SuppressWarnings("unused")
   private TcpManagedConnectionFactory mcf;

   /**
    * Constructor as required by JCA specification
    * @param mc TcpManagedConnection
    * @param mcf TcpManagedConnectionFactory
    */
   public TcpConnectionImpl(TcpManagedConnection mc, TcpManagedConnectionFactory mcf)
   {
      this.mc = mc;
      this.mcf = mcf;
   }

   /**
    * Setter required by JCA specification
    * @param mc the underlying physical connection 
    */
   public void setManagedConnection(TcpManagedConnection mc) {
      this.mc = mc;
   }

   /**
    * Connection specific API
    * @return byte[]
    */
   public int read(byte[] buffer, int offset, int length)
   {
      return mc.read(buffer, offset, length);
   }

   /**
    * 
    */
   public void write(byte[] buffer, int offset, int length)
   {
      mc.write(buffer, offset, length);
   }

   /**
    * Release the associated physical connection.
    */
   public void close()
   {
      mc.closeHandle(this);
   }
}
