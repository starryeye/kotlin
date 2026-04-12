package sub12_continuation.step_3

import kotlinx.coroutines.delay
import util.myPrint

/**
 * Continuation 을 알아보며..
 * 코루틴이 어떤 원리로 중단과 재개를 반복하는지 알아본다.
 *
 * step_2 에서는 label 이 0에서 1로 변경된 이후 다시 findUser 를 누군가 불러주지 않아서
 * label 1 이 수행되지 않는다..
 *
 * step_3 에서는 위를 해결하기 위해 실행 함수에 MyContinuation 을 전달하면서 callback 함수로 활용한다.
 *
 * 순서..
 * 1. 최초 findUser() 호출
 * 2. Continuation 생성 및 findProfile() 호출
 *      내부에서 중단 (IO 등)
 * 3. 중단에서 빠져나오면 결과를 Continuation 에 저장
 * 4. resumeWith 가 호출됨.
 *      현재 label 을 보고 Continuation 내부 데이터가 무엇인지 판단후 얻는다.(Profile)
 *      label++ 을 하여 다음 단계로 갈 준비.
 *      다시 findUser() 호출
 * 5. Continuation 은 null 이 아니므로 그대로 이용. label 이 1 이므로 findImage() 호출
 *      내부에서 중단 (IO 등)
 * 6. 중단에서 빠져나오면 결과를 Continuation 에 저장
 * 7. resumeWith 가 호출됨.
 *      현재 label 을 보고 Continuation 내부 데이터가 무엇인지 판단후 얻는다.(Image)
 *      label++ 을 하여 다음 단계로 갈 준비.
 *      다시 findUser() 호출
 * 8. Continuation 은 null 이 아니므로 그대로 이용. label 이 2 이므로 UserDto 생성 및 최종 반환
 *
 * 참고.
 *      step_1 을 디컴파일 해보면 step_3 와 비슷한 것을 알 수 있다.
 *
 * 참고
 *      FindUserContinuation 추상 클래스의 필드들은 실제 Continuation 인터페이스 에서 CoroutineContext 에 해당
 *      public interface Continuation<in T> {
 *          public val context: CoroutineContext
 *          public fun resumeWith(result: Result<T>)
 *      }
 *
 * 결국..
 * Continuation 이란..
 *      - suspend 이후에 이어 실행할 위치와 필요한 상태를 담은 객체
 *      - resume(value) 를 호출하면 suspend 지점 다음 줄부터 다시 실행된다.
 * 이러한 패턴을 Continuation Passing Style (CPS) 라 부르기도 한다.
 *
 */
suspend fun main() {
    val service = UserService()
    println(service.findUser(1L, null))
}

interface MyContinuation {
    // 중단 재개를 위해 필요한 "상태 및 리모컨" 객체의 인터페이스이다.

    suspend fun resumeWith(data: Any?) // 재개 함수, 일종의 callback
}

class UserService {
    private val userProfileRepository = UserProfileRepository()
    private val userImageRepository = UserImageRepository()

    private abstract class FindUserContinuation : MyContinuation { // MyContinuation 을 상속한 추상 클래스
        var label = 0 // 중단 재개 시점에 따른 단계

        // 캡쳐 필요한 객체들 (재개할때 필요한 데이터들은 중단시점에 저장해야한다.)
        var profile: Profile? = null
        var image: Image? = null
    }

    suspend fun findUser(userId: Long, continuation: MyContinuation?) : UserDto {

        val stateMachine = continuation as? FindUserContinuation ?: object : FindUserContinuation() { // FindUserContinuation 을 구현한 익명 클래스, 파라미터가 null 이면 생성 / null 이 아니면 그대로 사용
            override suspend fun resumeWith(data: Any?) { // 재개 함수 구현
                when (label) {
                    0 -> { // label 이 0 이면 data 는 Profile 이다.
                        profile = data as Profile
                        label++
                    }
                    1 -> { // label 이 1 이면 data 는 Image 이다.
                        image = data as Image
                        label++
                    }
                }
                findUser(userId, this)
            }
        }

        when(stateMachine.label) {
            0 -> { // stateMachine.label 이 0 이면 실행

                myPrint("get profile")
                userProfileRepository.findProfile(userId, stateMachine)
            }
            1 -> { // stateMachine.label 이 1 이면 실행

                myPrint("get image")
                userImageRepository.findImage(stateMachine.profile!!, stateMachine)
            }
        }
        return UserDto(stateMachine.profile!!, stateMachine.image!!)
    }
}

data class UserDto(
    val profile: Profile,
    val image: Image,
)
class Profile
class Image

class UserProfileRepository {
    suspend fun findProfile(userId: Long, continuation: MyContinuation) {
        delay(100L) // 중단

        continuation.resumeWith(Profile())
    }
}

class UserImageRepository {
    suspend fun findImage(profile: Profile, continuation: MyContinuation) {
        delay(100L) // 중단

        continuation.resumeWith(Image())
    }
}


