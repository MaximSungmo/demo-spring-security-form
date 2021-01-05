# 스프링 시큐리티 

## 스프링 시큐리티: 아키텍처

### SecurityContextHolder 와 Authentication

```
SecurityContextHolder
---------------------
SecurityContext
---------------------
Authentication
```

ThreadLocal<sup>[1](#footnote_1)</sup>로 인해 SecurityContextHolder 에서 파라미터 넘기지 않고도 Principal 객체로 접근이 가능하다.

Principal 은 UserDetailsService를 상속받아 `@Override` 한 메소드인 `loadUserByUsername` 의 리턴 객체임을 알 수 있다. 

Servlet 기반에선 Request 당 Thread 가 생성되므로 하나의 Request 에선 ThreadLocal에 동일하게 접근이 가능하다. 

<!-- 글 뒷 부분에 -->
<a name="footnote_1">1</a>: 하나의 쓰레드에서 쉐어하는 저장소


 

