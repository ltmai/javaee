# CDI

This project is an example of: 
- iterating over the CDI implementations of a bean (Service).
- using CDI extension to customize the bean discovery process (CdiExtension).

## Classes

1. `Main` - the startup EJB singleton containing the example logic.
2. `Service` - The interface to be implemented by CDI beans.
3. `ServiceDefault` - The default implementation of `Service`. 
4. `ServiceCustom` - The custom implementation of `Service`, annotated with `@Custom`.
5. `Custom` - the qualifier annotation.
6. `CdiExtension` - CDI extensions that lists all `Service` beans discovered . 

## Iterating over CDI implementaions

By declaring an Instance of Service like the following, we can iterate over all 
implementations of a Bean type. However, if an alternative is defined for this
module (see beans.xml), that alternative will hide the all implementations.

```java
@Inject
@Any
private Instance<Service> serviceInstance;
```

To inject specific implementation, we can simply use a qualifier (e.g. @Custom).

## CDI Extension

CDI extension allows us to customize the bean discovery process, e.g. add some
annotation to given type of bean, or register specific class as CDI bean to the
container. In this example, we only use CDI extension to list the CDI beans of
given type, that are discovered during the startup of CDI container. 
