## Deployment
### Wildfly
Add this to Wildfly standalone.xml to enable TCP resource adapter:
```xml
<subsystem xmlns="urn:jboss:domain:resource-adapters:5.0">
    <resource-adapters>
        <resource-adapter id="tcp-connector-rar" statistics-enabled="false">
            <archive>
                project-ear.ear#project-rar.rar
            </archive>
            <transaction-support>NoTransaction</transaction-support>
            <connection-definitions>
                <connection-definition class-name="mai.linh.project.connector.TcpManagedConnectionFactory" jndi-name="java:/eis/TcpConnectionFactory" enabled="true" pool-name="tcp-connector">
                    <security>
                        <application/>
                    </security>
                    <validation>
                        <background-validation>false</background-validation>
                    </validation>
                </connection-definition>
            </connection-definitions>
        </resource-adapter>            
    </resource-adapters>
</subsystem>
```

## TODO  
- Configuration parameter: socket timeout
- Configuration parameter: max connections
