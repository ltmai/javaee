# Project structure
# Technical notes
## Configure Log4j2

### Maven dependency
Log4j2 dependency is declared provided because we want to make it available at run-time via JBoss module.
The Log4j2 .jar files are therefore not be packaged into project EAR, makeing it smaller in size.

```xml
<!--log4j2-->
<dependency> 
    <groupId>org.apache.logging.log4j</groupId> 
    <artifactId>log4j-api</artifactId> 
    <version>2.6.2</version> 
    <scope>provided</scope>
</dependency> 
```

### JBoss module
The module.xml file declares a JBoss module and makes it available to all applications running on this JVM.
The referenced resources are automatically added by application server to application classpath at run-time.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<module name="org.apache.logging.log4j" xmlns="urn:jboss:module:1.3">
    <resources>
        <resource-root path="log4j-api-2.6.2.jar"/>
        <resource-root path="log4j-core-2.6.2.jar"/>
        <resource-root path="log4j-to-slf4j-2.12.1.jar"/>    
    </resources>
</module>
```

### JBoss system properties
The following system property is defined in standalone.xml to be referenced by Log4J2 engine.
The property name can be anything, and not necessarily as in the following example:

```xml
    <system-properties>
        <property name="org.apache.logging.log4j.dir" value="${jboss.server.log.dir}"/>
    </system-properties>
```

### Log4j2 configuration
In order to write log files in specified target directory, we define a configuration property `basePath`
as follows. The value of this property refers to the system property defined in standalone.xml as above.
Note that the value is referenced as `$${sys:property-name}`. 

For more Log4j2 configuration see https://logging.apache.org/log4j/2.x/manual/configuration.html.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="warn">
    <Properties>
        <Property name="basePath">$${sys:org.apache.logging.log4j.dir}</Property>
    </Properties>
 
    <Appenders>
        <RollingFile name="fileLogger" fileName="${basePath}/project-server.log" filePattern="${basePath}/project-server-%d{yyyy-MM-dd}.log">
            <PatternLayout>
                <pattern>[%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %c{1} - %msg%n</pattern>
            </PatternLayout>
            <Policies>
                <TimeBasedTriggeringPolicy interval="1" modulate="true" />
            </Policies>
        </RollingFile>
 
        <Console name="console" target="SYSTEM_OUT">
            <PatternLayout   pattern="[%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %c{1} - %msg%n" />
        </Console>
    </Appenders>
    <Loggers>
        <Logger name="mai.linh.project.server" level="debug" additivity="true">
            <appender-ref ref="fileLogger" level="debug" />
        </Logger>
        <Root level="debug" additivity="false">
            <appender-ref ref="console" />
        </Root>
    </Loggers>
</Configuration>
```

```
```