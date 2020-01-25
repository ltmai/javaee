# Example of simple CDI application

Project modules:
- project-api
- project-imp
- project-logic
- project-ear

### Instantiate an annotation type with AnnotationLiteral
https://docs.oracle.com/javaee/7/api/javax/enterprise/util/AnnotationLiteral.html

```java
/**
 * T - the annotation type
 */
public abstract class AnnotationLiteral<T extends Annotation>
    extends Object
    implements Annotation, Serializable
```

`AnnotationLiteral` supports inline instantiation of annotation type instances.

An instance of an annotation type `T` may be obtained by subclassing `AnnotationLiteral<T>`.

```java
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD})
public @interface PayBy {
    PaymentMethod value();
    enum PaymentMethod {
        CHEQUE,
        CREDIT
    };
}

public abstract class PayByQualifier extends AnnotationLiteral<PayBy> implements PayBy {
}
 
// paybyCheque is an instance of PayBy annotation
PayBy paybyCheque = new PayByQualifier() {
    public PaymentMethod value() {
        return CHEQUE;
    }
};

Instance<Payment> paymentInstance;

Payment chequePayment = paymentInstance.select(paybyCheque).get()
```