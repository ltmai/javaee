package mai.linh.project.connector.api;

/**
 * TcpConnection 
 * This interface describes a the connection provided by resource adapter 
 * and specifies the API to use that connection. An connection interface
 * is required by JCA  specification.
 */
public interface TcpConnection
{
   /**
    * Connection specific API
    * @return byte[]
    */
   public int read(byte[] buffer, int offset, int length);

   /**
    * Connection specific API
    * @param buffer
    * @param offset
    * @param length
    */
   public void write(byte[] buffer, int offset, int length);

   /**
    * close() is required by JCA specification. Client calls this
    * method to inform RA that the connection can be released.
    */
   public void close();
}
