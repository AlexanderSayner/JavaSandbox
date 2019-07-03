package sayner.sandbox.annotations;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component // для создания бина
public class MyAspect1 {

    @Pointcut("@annotation(sayner.sandbox.annotations.Annotation1)")
    public void annotated() {}

    @Before("annotated()")
    public void printABit() {
        System.out.println("Aspect1");
    }

}