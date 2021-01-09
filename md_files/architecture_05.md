# 스프링 시큐리티 

## 스프링 시큐리티: 아키텍처

###  스프링 시큐리티 필터와 FilerChainProxy

1. UsernamePasswordAuthentication()

--> SecurityContextHolder.getContext().setAuthentication() 


2. SecurityContextPersistenceFilter()

--> Http Session에서 authentication를 공유하는 필터  이를 Http session이 아닌 다른 곳에 저장하는 것도 가능하다.



FilterChainProxy 클래스에서 시큐리티 필터 목록을 순차적으로 호출한다. 

필터 목록을 선택할 때는 시큐리티필터체인 목록을 보면서 선택한다. 

이 필터체인 목록은 SecurityConfig 설정에 따라서 달라진다. 해당 SecurityConfig 안에서 여러 체인을 걸면 그만큼 필터목록을 호출하게 되는 것이다.

// SecuriyConfig 와 AnotherSecurityConfig 를 통해 내용 다르게 입력하고 @Order 옵션을 통해 체이닝 순서를 다르게 가져가보자

결과적으로 필터체인이 SecurityConfig 를 관리하는 곳에서 지정이 되는 것으로 알 수 있다. 

 