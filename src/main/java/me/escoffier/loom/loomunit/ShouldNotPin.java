package me.escoffier.loom.loomunit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.temporal.ChronoUnit;

/**
 * Marker indicating that the test method should not pin the carrier thread.
 * If, during the execution of the test, a virtual thread pins the carrier thread, the test fails.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface ShouldNotPin {

    int threshHold() default 10;

    ChronoUnit unit() default ChronoUnit.MILLIS;
}
