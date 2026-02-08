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