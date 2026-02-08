## Kotlin collection

- Java 와 다르게 mutable, immutable 인터페이스가 따로 존재해버린다.
  - List, Set, Map -> immutable
  - MutableList, MutableSet, MutableMap -> mutable

## null 과 collection
- List<Int?>
  - element 에 null 들어갈 수 있음
  - List 가 절대 null 이 아님
- List<Int>?
  - element 에 null 이 들어갈 수 없음
  - List 가 null 일 수 있음
- List<Int?>?
  - element 에 null 들어갈 수 있음
  - List 가 null 일 수 있음

## Java <---> Kotlin
- Java 코드에서 만들어진 collection 을 Kotlin 에서 사용할 때 주의..
  - Java 에서는 nullable/non-nullable 타입을 구분하지 않기 때문에 Kotlin 에서는 non-nullable 타입으로 해놨지만.. null 이 들어있을 수 있음..
  - Java 에서는 타입 자체에서 mutable/immutable 을 구분하지 않기 때문에 Kotlin 에서는 immutable 타입으로 해놨지만.. 변경되어 있을 수 있음..
- 반대로 Kotlin 의 collection 을 Java 에서 사용하면..
  - Kotlin 에서는 nullable/non-nullable, mutable/immutable 을 구분했지만 Java 에서는 그러한 제약을 다 무시가능..