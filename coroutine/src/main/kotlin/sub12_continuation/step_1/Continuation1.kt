package sub12_continuation.step_1

import kotlinx.coroutines.delay
import util.myPrint

/**
 * Continuation 을 알아보며..
 * 코루틴이 어떤 원리로 중단과 재개를 반복하는지 알아본다.
 *
 * step_1 에서는 중단 없이 그냥 한번에 쭉 실행되는 코드이다.
 * 기본 실행 과정이 이렇구나만 보고
 * 중단 재개로 이득볼 구간(IO)만 정해본다.
 */
suspend fun main() {
    val service = UserService()
    println(service.findUser(1L))
}

class UserService {
    private val userProfileRepository = UserProfileRepository()
    private val userImageRepository = UserImageRepository()

    /**
     * findUser() 는 findProfile(), findImage() 를 호출
     */
    suspend fun findUser(userId: Long) : UserDto {

        myPrint("get profile")
        val profile = userProfileRepository.findProfile(userId) // 중단 가능 시점

        myPrint("get image")
        val image = userImageRepository.findImage(profile) // 중단 가능 시점

        return UserDto(profile, image)
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
        delay(100L) // 코루틴을 통해 실제 중단하지만, 여기서는 그냥 blocking 중단이라 생각하자.
        return Profile()
    }
}

class UserImageRepository {
    suspend fun findImage(profile: Profile): Image {
        delay(100L) // 코루틴을 통해 실제 중단하지만, 여기서는 그냥 blocking 중단이라 생각하자.
        return Image()
    }
}


