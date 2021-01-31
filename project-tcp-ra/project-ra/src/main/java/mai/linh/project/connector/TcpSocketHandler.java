package mai.linh.project.connector;

import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TcpSocketHandler {

    private static Logger logger = LogManager.getLogger(TcpResourceAdapter.class);
    
    private Socket socket;
    private String host;
    private int port;

    public TcpSocketHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    public void close() {
        logger.debug(String.format("Closing connection to %s:%d", host, port));
        try {
            socket.close();
        } catch (Exception e) {
            logger.error(String.format("Failed to close connection %s:%d: %s", host, port, e.getMessage()));
            e.printStackTrace();
        }
    }

    public void connect() {
        logger.debug(String.format("Connecting to %s:%d", host, port));
        try {
            socket = new Socket(host, port);
        } catch (Exception e) {
            logger.error(String.format("Failed to connect to %s:%d: %s ", host, port, e.getMessage()));
            e.printStackTrace();
        }
    }

    /**
     * read and retry reading once if fails.
     * @param buffer
     * @param offset
     * @param length
     * @return
     */
    public int read(byte[] buffer, int offset, int length) {
        int ret = tryRead(buffer, offset, length);
        if (ret == -1) {
            close();
            connect();
            ret = tryRead(buffer, offset, length);
        }
        return ret;
    }

    /**
     * Try reading with error handling
     * @param buffer
     * @param offset
     * @param length
     * @return -1 if an error has occurred
     */
    public int tryRead(byte[] buffer, int offset, int length) {
        try {
            return socket.getInputStream().read(buffer, offset, length);
        } catch (Exception e) {
            logger.error(String.format("Failed to read from %s:%d: %s ", host, port, e.getMessage()));
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Writes and retries if exception occurs. In general it cannot
     * detect if server has closed the connection or is reachable.
     * A protocol of confirmation may be helpful on error case. 
     * @param buffer
     * @param offset
     * @param length
     */
	public void write(byte[] buffer, int offset, int length) {
        boolean noException = tryWrite(buffer, offset, length);
        if (!noException) {
            close();
            connect();
            tryWrite(buffer, offset, length);
        }       
    }
    
    /**
     * Try writing with error handling
     * @param buffer
     * @param offset
     * @param length
     * @return true if no exception occurs, false otherwise
     */
    public boolean tryWrite(byte[] buffer, int offset, int length) {
        try {
            socket.getOutputStream().write(buffer, offset, length);
            return true;
        } catch (Exception e) {
            logger.error(String.format("Failed to write to %s:%d: %s ", host, port, e.getMessage()));
            e.printStackTrace();
            return false;
        }
    }    
}
