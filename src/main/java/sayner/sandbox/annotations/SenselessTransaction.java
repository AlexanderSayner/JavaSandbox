package sayner.sandbox.annotations;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Transactional(
        rollbackFor = Exception.class,
        isolation = Isolation.READ_UNCOMMITTED,
        propagation = Propagation.REQUIRES_NEW)
public @interface SenselessTransaction {
}
