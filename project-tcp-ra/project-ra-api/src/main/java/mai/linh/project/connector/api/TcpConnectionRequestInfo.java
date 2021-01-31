package mai.linh.project.connector.api;

import javax.resource.spi.ConnectionRequestInfo;

/**
 * TcpConnectionRequestInfo
 * This class describes the client request to resource adapter to obtain a
 * connection. The same request MAY result in the same physical connection 
 * instance, the logic is decided by ManagedConnectionFactory.
 * A connection request implementation is optional to implement a resource
 * adapter, e.g. client may ask to obtain any available connection.
 */
public class TcpConnectionRequestInfo implements ConnectionRequestInfo {
    
    private String host;

    private Integer port;

    public TcpConnectionRequestInfo(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((host == null) ? 0 : host.hashCode());
        result = prime * result + ((port == null) ? 0 : port.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TcpConnectionRequestInfo other = (TcpConnectionRequestInfo) obj;
        if (host == null) {
            if (other.host != null)
                return false;
        } else if (!host.equals(other.host))
            return false;
        if (port == null) {
            if (other.port != null)
                return false;
        } else if (!port.equals(other.port))
            return false;
        return true;
    }

    
}
