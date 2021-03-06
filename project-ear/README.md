# Project structure

This project is an example of simple web application.

1. project-ear : creates an project EAR package.
2. project-entities : contains the entities (Customer) that can be shared by modules.
3. project-server : contains example of startup EJBs, CDI interceptor, producer, JPA. 
4. project-webapp : contains example of simple web requests.

## Configure JPA 

### persistence.xml

The following content of `persistence.xml` specifies Hibernate as the JPA provider and lets it
initialize the database objects (tables) using metadata in entity beans.

- The tag `jar-file` tells the JPA provider where to find the entities. 
- The property `javax.persistence.schema-generation.database.action` specifies what to do on 
  schema generation. In this case drop and (re)create.
- The property `javax.persistence.schema-generation.drop-source` specifies how to drop the 
  schema. In this case, it is interpreted from entity metadata (annotations, ...).  
- The property `javax.persistence.schema-generation.create-source` specifies how to (re)create
  the schema. In this case, it is interpreted from entity metadata (annotations, ...).
- The property `javax.persistence.sql-load-script-source` specifies how to initialize data on
  schema generation. In this case, the script `META-INF/init-data.sql` will be executed.

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<persistence xmlns="http://java.sun.com/xml/ns/persistence" 
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
             xsi:schemaLocation="http://java.sun.com/xml/ns/persistence
                                 http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd" 
             version="2.0">
    <persistence-unit name="H2" transaction-type="JTA">
        <description>H2 persistence unit</description>    
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
        <jta-data-source>java:jboss/datasources/ExampleDS</jta-data-source>
        <!--tell the entity manager to find the entities in another component, 
            so tables can be dropped and recreated via metadata. This it not 
            necessary if no database initialization should be done-->
        <jar-file>lib/project-entities.jar</jar-file>
        <properties>
            <property name="javax.persistence.lock.timeout" value="10"/>
            <property name="javax.persistence.query.timeout" value="10"/>
            <!--drop and recreate database objects on deployment (for testing purposes)-->
            <property name="javax.persistence.schema-generation.database.action" value="drop-and-create" />
            <property name="javax.persistence.schema-generation.create-source" value="metadata" />
            <property name="javax.persistence.schema-generation.drop-source" value="metadata" />
            <property name="javax.persistence.sql-load-script-source" value="META-INF/init-data.sql" />
        </properties>
    </persistence-unit>
</persistence>
```

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

The `module.xml` file declares a JBoss module and makes it available to all applications running on this JVM.
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

The depdendencies can be downloaded as follows:
```cmd
mvn dependency:copy -Dartifact=org.apache.logging.log4j:log4j-api:2.6.2 -DoutputDirectory=.
mvn dependency:copy -Dartifact=org.apache.logging.log4j:log4j-core:2.6.2 -DoutputDirectory=.
mvn dependency:copy -Dartifact=org.apache.logging.log4j:log4j-to-slf4j:2.14.0 -DoutputDirectory=.
```

Put the JAR files and `module.xml` into `%WILDFLY%/modules/system/layers/base/org/apache/logging/log4j`
to create the module.

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
## Avoid using Instance<> to inject bean dynamically

```java
@Inject
private Instance<BigBean> bigBean;

// periodically scheduled task
BigBean bean = bigBean.get()
bean.soSomething();
```
The above code creates a new instance of `BigBean` each time `Instance<>::get` is invoked.
In my test the created instances seem not garbage collected for non-annotated CDI bean (or
@Dependent by default) causing increasing use of memory.

The CDI specification says:

> The method `destroy()` instructs the container to destroy the instance. The bean instance 
> passed to `destroy()` should be a dependent scoped bean instance, or a client proxy for a 
> normal scoped bean. Applications are encouraged to always call `destroy()` when they no longer 
> require an instance obtained from `Instance`. All built-in normal scoped contexts support
> destroying bean instances. An `UnsupportedOperationException` is thrown if the active context 
> object for the scope type of the bean does not support destroying bean instances.

That may not what you would expect or want it to work. The simpler way would be to inject
directly using `@Inject` or `JNDI` lookup. 