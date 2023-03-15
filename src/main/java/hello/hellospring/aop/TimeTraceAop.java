package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    //AOP가 적용되면 스프링 컨테이너에 복제된 빈이 프록시로 생성되고 프록시에서 코드진행하다가 proceed()가되면 실제 빈을 호출하는 원리

    @Around("execution(* hello.hellospring..*(..))") // hello.hellospring 패키지 밑에 모두 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START : "+joinPoint.toString());
        try {
            return joinPoint.proceed(); // AOP가 적용된 곳 메소드 리턴값을 받을수있다. 이 proceed() 이후 코드는 메소드 수행 후 적용할 코드를 작성해야한다.
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : "+joinPoint.toString()+" "+timeMs+"ms");
        }
    }
}
