# ✨카페 키오스크 프로그램

- 테스트 코드 작성을 위한 카페 키오스크 프로그램
- 인프런의 **Practical Testing: 실용적인 테스트 가이드**를 학습하며 만들었습니다

## ✔ 요구사항

    - 주문 목록에 음료 추가/삭제 기능
    - 주문 목록 전체 지우기
    - 주문 목록 총 금액 계산하기
    - 주문 생성하기
      - 가게 운영 시간(10:00~22:00) 외에는 주문을 생성할 수 없다
    - 한 종류의 음료 여러 잔을 한 번에 담는 기능
    - 키오스크 주문을 위한 상품 후보 리스트 조회하기
        - 화면에서 필요한 데이터: ID, 상품 번호, 상품 타입, 판매 상태, 상품 이름, 가격
        - 상품의 판매 상태: 판매중, 판매보류, 판매중지
            - 판매중, 판매보류인 상태의 상품을 화면에 보여준다
    - 상품 번호 리스트를 받아 주문 생성하기
        - 주문은 주문 상태, 주문 등록 시간을 가진다
        - 주문의 총 금액을 계산할 수 있어야 한다
    - 주문 생성 시 재고 확인 및 개수 차감 후 생성하기
        - 재고는 상품범호를 가진다
        - 재고와 관련 있는 상품 타입은 병 음료, 베이커리이다
    - 관리자 페이지에서 신규 상품을 등록할 수 있다
      - 상품 명, 상품 타입, 판매 상태, 가격 등을 입력받는다
    - 관리자 페이지에서 일일 매출에 대한 통계를 메일로 받을 수 있다

## ✔ 구성

- Spring Boot
- h2
- JPA
- JUnit5
- Spring REST Docs

## ✔ 테스트 개념 잡기

### 🤔 테스트가 필요한 이유는 무엇일까?

    - 프로덕션 코드를 계속 개발하고 고도화하면서..
        - 커버할 수 없는 영역 발생
        - 프로젝트를 오래 작업한 개발자의 경험과 감에 의존
        - 늦은 피드백
        - 유지 보수 어려움

### 🤔 테스트를 추가하면서 얻고자 하는 것

    - 빠른 피드백
    - 안정감, 신뢰성
    - 자동화 테스트로 비교적 빠른 시간 안에 버그를 발견할 수 있고, 수동 테스트에 드는 비용을 크게 절약할 수 있다

### 🤔 잘못된 테스트 코드를 작성한다면

    - 프로덕션 코드의 안정성을 제공하기 힘들어진다.
    - 테스트코드 자체가 유지보수하기 어려운 새로운 짐이 된다
    - 잘못된 검증이 이루어질 가능성이 생긴다

### 단위 테스트

    - 작은 코드 단위(class or method)를 독립적으로 검증하는 테스트
    - 검증 속도가 빠르고, 안정적이다
    - JUnit5: 단위 테스트를 위한 테스트 프레임워크, XUnit 시리즈(SUnit, NUnit, JUnit 등등..) - 개발자: Kent Beck
    - AssertJ: 테스트 코드 작성을 원활하게 돕는 테스트 라이브러리, 풍부한 API, 메서드 체이닝 지원. 주로 JUnit과 함께 사용
