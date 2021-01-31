package mai.linh.project.connector.api;

import java.io.Serializable;

import javax.resource.Referenceable;
import javax.resource.ResourceException;

/**
 * TcpConnectionFactory
 * This interface describes the API to get a connection from resource adapter.
 * A connection factory interface is required by JCA specification.
 */
public interface TcpConnectionFactory extends Serializable, Referenceable
{
   /** 
    * Get connection from factory (required by JCA specification)
    *
    * @return TcpConnection instance
    * @exception ResourceException Thrown if a connection can't be obtained
    */
   public TcpConnection getConnection(TcpConnectionRequestInfo info) throws ResourceException;

}
