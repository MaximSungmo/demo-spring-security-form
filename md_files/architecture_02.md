# 스프링 시큐리티 

## 스프링 시큐리티: 아키텍처

### AuthenticationManager 와 Authentication

AuthenticationManager 는 실제로 인증을 담당하고 있는 인터페이스이다.

`authenticate` 메소드로 인증을 하고 유효하면 `Principal` 을 담고 있는 `Authentication` 객체를 리턴해준다.

기본 구현체는 `ProviderManager` 클래스를 통해서 인증에 대한 `authenticate` 구현된 메소드를 확인할 수 있다.


 

