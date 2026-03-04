package dev.starryeye.book_library.domain.book

enum class BookCategory(
    val description: String,
) {
    COMPUTER("컴퓨터"),
    ECONOMY("경제"),
    SOCIETY("사회"),
    LANGUAGE("언어"),
    SCIENCE("과학"),

    /**
     * enum 은 보통 value object 로 사용하므로 모든 계층에서 공유하여 사용한다.
     *      그래서, type 이라는 패키지로 최상위 패키지로 VO 들을 따로 모아두는 경우도 존재함.
     * domain 객체 처럼 controller 에서 직접 의존하지 않는 것과 좀 다르다.
     */
}