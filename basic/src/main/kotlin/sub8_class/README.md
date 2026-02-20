## 헷갈리기 쉬운 것 정리..
- val x	
  - public getter
  - setter 없음
- var x	
  - public getter
  - public setter
- private val x	
  - private getter
  - setter 없음
- private var x
  - private getter
  - private setter

## 만약.. var 변수인데.. getter 만 열고 싶다면..
- 아래와 같이 기존의 프로퍼티는 private 로 숨겨서 getter, setter 를 둘다 없애고(변수이름도 prefix 로 언더바를 붙임)
- backing property?, custom getter 를 이용하여 getter 만 열어두는 식으로 코드를 짠다..
- 예시..  
class Calculater(private var _number: Int) {
  
  val number: Int
    get() = this._number
}