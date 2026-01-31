package sub10_interface.ex2

class KotlinLocalhostPolicy(
    override var timeoutMs: Long,
) : KotlinConnectionPolicy {

    override val host: String
        get() = "localhost"

    override val port: Int
        get() = 8080

    /**
     * timeoutMs
     *      일반(stored) property
     *      값 저장됨.
     * host, port
     *      계산된(computed) property
     *      custom getter 만 존재
     */
}