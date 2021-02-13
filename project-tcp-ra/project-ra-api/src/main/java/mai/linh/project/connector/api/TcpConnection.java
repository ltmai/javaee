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
    * @param buffer : the buffer into which the data is read 
    * @param offset : the start offset in array b at which the data is written 
    * @param length : the maximum number of bytes to read
    * @return number of bytes read into buffer or -1 if error occurs
    */
   public int read(byte[] buffer, int offset, int length);

   /**
    * Connection specific API
    * @param buffer : the data to write
    * @param offset : the start offset in the data
    * @param length : the number of bytes to write
    */
   public void write(byte[] buffer, int offset, int length);

   /**
    * close() is required by JCA specification. Client calls this
    * method to inform RA that the connection can be released.
    */
   public void close();
}
