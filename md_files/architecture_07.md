# 스프링 시큐리티 

## 스프링 시큐리티: 아키텍처

### AccessDecisionManager

권한을 처리하기 위하여 사용되는 인가를 위한 매니저이다.

Access Control 결정을 내리는 인터페이스로, 구현체 3가지를 기본 제공한다.
1. AffirmativeBased : 여러 Voter 중 하나라도 허용 시 (기본 전략)
2. ConsensusBased : 다수결
3. UnanimousBased : 만장일치


// SecurityConfig 클래스 참고 (AccessDecisionManager)

권한을 허용하기 위해서 롤에 대한 계층 구조를 통해 만약 ADMIN이 접속한다면 USER 페이지도 접근이 가능하게끔 해줘야한다.

이러한 권한은 AccessDecisionManager를 통해서 할 수 있고 구현을 위하 voter list 를 정의해서 넘겨주어야한다.




 




 