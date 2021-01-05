# 스프링 시큐리티 

## 스프링 시큐리티: 아키텍처

### ThreadLocal

변수의 scope 을 thread 단위로 작업될 수 있도록 해주는 객체 

따라서 동일 thread 에서는 매개변수를 넘기거나 하지 않고 접근하여 객체를 사용할 수 있음

스프링 시큐리티에서는 기본 전략으로 SecurityContextHolder 가 ThreadLocal 을 통해 SecurityContext 를 가지고 있음.


