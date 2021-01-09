# 스프링 시큐리티 

## 스프링 시큐리티: 아키텍처

### 총정리


서블릿 컨테이너에 요청이 들어오면
 
DeligatingFilterProxy 서블릿 필터로 등록이 되면 이를 위임을 한다.  

-> FilterChainProxy 필터 체인 프록시로 필터들을 체인 형태로 가져가게하며 이는 WebSecurity로 만들어진다.

-> 시큐리티 필터 목록

`SecurityConfig` 에서 `WebSecurityConfigureAdapter` 상속받아서 구현하는 것으로 WebSecurity 를 만드는 것이다. 이를 통해 필터체인프록시를 만드는 것이며 이를 통해 delegating filter proxy 가 위임한 클래스다.

인증을 관련한 필터는 `AuthenticationManager`를 통해서 진행

`DaoAuthenticationProvier` 에서 사용자 입력정보와 일치하는 지 확인해서 `UserDetailsService` 를 구현하고 `SecurityContextHolder`에 담아놓고 사용하게 된다.


인가 관련한 필터(`FilterSecurityInterceptor`)에서 사용하는 것은 `AccessDecisionManager` 를 통해서 진행한다.

인가를 확인하는 전략은 3가지이며 기본으로는 하나라도 허용되면 리소스를 허용하는 방식이 있었으며 이에 대한 방식을 핸들링하기 위하여
`SecurityExpressionHandler` 를 구현하여 `WebExpressionVoter`에 넣어주면 된다.


  


