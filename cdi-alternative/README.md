# Example of CDI @Alternative

Project modules:
- project-ear : creates project EAR package
- project-api : provides CDI `Handler` that has an injected `Service` and its default implementation `DefaultService`. 
- project-imp : provides CDI implementation `CustomService` that implements `Service`.
- project-logic : entry point that invokes `Handler` methods.

This project is an example of using `@Alternative` to select CDI implementation at deployment time. This also refers to 
my answer on Stackoverflow (https://stackoverflow.com/questions/59295219/cdi-how-to-substitute-an-injected-bean-with-another/)

## Question on Stackoverflow

Given an API with default injected CDI implementation inside a module (with `beans.xml`) that your code depends on, how can you 
substitute this default implementation with your own custom one, without changing the module?

## Answer

It should be possible with `@Alternative`, but you need `@Priority` to declare your implementation a selected alternatives for 
the application:

```java
@Alternative @Priority(Interceptor.Priority.APPLICATION)
public class MyServiceHolder implements IServiceHolder {
   ...
}
```

The CDI Spec says:

5.1 Modularity
An alternative is not available for injection, lookup or EL resolution to classes or JSP/JSF pages in a module unless the module 
is a bean archive and the alternative is explicitly selected for the bean archive **or the application**.

5.1.4 Inter-module injection
A bean is available for injection in a certain module if:

- the bean is not an interceptor or decorator
- the bean is enabled,
- the bean is either not an alternative, or the module is a bean archive and the bean is a selected alternative of the bean archive, 
  or the bean is a **selected alternative of the application**, and 
- the bean class is required to be accessible to classes in the module, according to the class accessibility requirements of the 
  module architecture.

The alternatives that you specify in the `beans.xml` file apply only to **classes in the same archive**. Use the `@Priority` annotation to 
specify alternatives globally for an **application that consists of multiple modules**, as in the following example:

```java
@Alternative
@Priority(Interceptor.Priority.APPLICATION+10)
public class TestCoderImpl implements Coder { ... }
```

The alternative with higher priority value is selected if several alternative beans that implement the same interface are annotated with 
`@Priority`. You do not need to specify the alternative in the `beans.xml` file when you use the `@Priority` annotation.
