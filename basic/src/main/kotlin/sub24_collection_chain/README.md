## Kotlin collection chain..
- Java Stream API 와 비슷하게 생겼지만 Kotlin 표준 라이브러리의 컬렉션 확장함수이다.
- 스트림 파이프라인이 아니라 그냥 리스트를 한 번씩 돌면서 새 리스트를 만드는 함수 호출 체인..
  - 내부 구현은 대부분 for-loop로 도는 eager(즉시 평가) 연산
  - Java 의 경우엔.. collection -> stream -> 원소단위로 흘려보내서 -> 최종 api 로 collection 생성..
  - Kotlin 은... collection -> collection
- 놀랍게도 성능상 문제를 걱정할 필요는 없다고 한다...;;
- Java 처럼 원소가 흐르는게 아니라는 점을 아주 극명하게 느낄수 있는게..
  - sub10.sub1_flatmap 임.