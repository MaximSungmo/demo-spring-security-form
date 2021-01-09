# 스프링 시큐리티 

## 스프링 시큐리티: 아키텍처

###  DelegatingFilterProxy 와 FilterChainProxy


Servlet filter 구현체 중 스프링IOC 컨테이너에  DelegatingFilterProxy 가 있다.

이를 통해서 FilterChainProxy로 Servlet filter를 위임할 수 있다.

이를 위해 빈을 설정해서 `AbstractSecurityWebApplicationInitializer` 를 사용해서 등록할 수 있고

스프링부트를 사용하는 경우에는 자동으로 등록된다. 

DelegatingFilterProxy 가 스프링 빈의 이름을 알아야만 FilterChainProxy에 위임할 수 있는데.
기본값의 빈의 이름은 `springSecurityFilterChain` 이다.


 



 