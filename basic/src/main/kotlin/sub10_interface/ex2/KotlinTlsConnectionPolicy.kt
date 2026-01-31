package sub10_interface.ex2

class KotlinTlsConnectionPolicy(
    override val host: String,
    override val port: Int,
    override var timeoutMs: Long
) : KotlinConnectionPolicy {

    override val endpoint: String
        get() = "https://$host:$port"
}

/**
 * 인터페이스의 default getter(endpoint)를 "재정의" 가능
 *      즉, 인터페이스가 제공한 계산 로직을 구현체에서 바꿀 수 있음
 */