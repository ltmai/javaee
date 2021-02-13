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
    * @param buffer : the buffer into which the data is read 
    * @param offset : the start offset in array b at which the data is written 
    * @param length : the maximum number of bytes to read
    * @return number of bytes read into buffer or -1 if error occurs
    */
   public int read(byte[] buffer, int offset, int length)
   {
      return mc.read(buffer, offset, length);
   }

   /**
    * Connection specific API
    * @param buffer : the data to write
    * @param offset : the start offset in the data
    * @param length : the number of bytes to write
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
