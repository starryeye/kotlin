package sub12_continuation.step_2

import kotlinx.coroutines.delay
import util.myPrint

/**
 * Continuation 을 알아보며..
 * 코루틴이 어떤 원리로 중단과 재개를 반복하는지 알아본다.
 *
 * step_2 에서는 label 이 0에서 1로 변경된 이후 다시 findUser 를 누군가 불러주지 않아서
 * label 1 이 수행되지 않는다..
 *
 * 참고 step_2 는 완성된 코드가 아니다.
 * step_1 과 비교하여 어떤게 바뀌었는지 보고 step_3 를 보자
 */
suspend fun main() {
    val service = UserService()
    println(service.findUser(1L))
}

interface MyContinuation {
    // 중단 재개를 위해 가져야할 상태 객체의 인터페이스이다.
}

class UserService {
    private val userProfileRepository = UserProfileRepository()
    private val userImageRepository = UserImageRepository()

    suspend fun findUser(userId: Long) : UserDto {

        val stateMachine = object : MyContinuation { // MyContinuation 을 구현한 익명 클래스
            var label = 0 // 중단 재개 시점에 따른 단계

            // 캡쳐 필요한 객체들 (재개할때 필요한 데이터들은 중단시점에 저장해야한다.)
            var profile: Profile? = null
            var image: Image? = null
        }

        when(stateMachine.label) {
            0 -> { // stateMachine.label 이 0 이면 실행
                stateMachine.label = 1

                myPrint("get profile")
                val profile = userProfileRepository.findProfile(userId)
                stateMachine.profile = profile
            }
            1 -> { // stateMachine.label 이 1 이면 실행
                stateMachine.label = 2

                myPrint("get image")
                val image = userImageRepository.findImage(stateMachine.profile!!)
                stateMachine.image = image
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
    suspend fun findProfile(userId: Long): Profile {
        delay(100L)
        return Profile()
    }
}

class UserImageRepository {
    suspend fun findImage(profile: Profile): Image {
        delay(100L)
        return Image()
    }
}


