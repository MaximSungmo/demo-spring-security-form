# 스프링 시큐리티 

## 스프링 시큐리티: 아키텍처

###  Authentication 과 SecurityContextHolder

변수의 scope 을 thread 단위로 작업될 수 있도록 해주는 객체 

따라서 동일 thread 에서는 매개변수를 넘기거나 하지 않고 접근하여 객체를 사용할 수 있음

스프링 시큐리티에서는 기본 전략으로 SecurityContextHolder 가 ThreadLocal 을 통해 SecurityContext 를 가지고 있음.


Authentication 을 관리하는 필터는 2가지가 있다.
1. UsernamePasswordAuthentication()

--> SecurityContextHolder.getContext().setAuthentication() 


2. SecurityContextPersistenceFilter()

--> Http Session에서 authentication를 공유하는 필터  이를 Http session이 아닌 다른 곳에 저장하는 것도 가능하다.



