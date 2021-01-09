# 스프링 시큐리티 

## 스프링 시큐리티: 아키텍처

### ExceptionTranslationFilter


필터 체인에서 발생하는 AccessDeniedException 과 AuthenticationException 을 처리하는 필터

AuthenticationException 발생 시 
- AuthenticationEntryPoint 실행
- AbstactSecurityInterceptor 하위클래스에서 발생하는 예외만 처리


AccessDeniedException 발생 시 
- 익명 사용자라면 AuthenticationEntryPoint
- 익명 사용자가 아니다 AccessDeniedHandler 가 처리 

