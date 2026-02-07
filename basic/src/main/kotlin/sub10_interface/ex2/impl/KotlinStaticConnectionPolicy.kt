package sub10_interface.ex2.impl

import sub10_interface.ex2.KotlinConnectionPolicy

class KotlinStaticConnectionPolicy(
    override val host: String,
    override val port: Int,
    override var timeoutMs: Long
) : KotlinConnectionPolicy

/**
 * 생성자에서 override 프로퍼티 제공
 *      host/port/timeoutMs를 실제로 저장(상태 보유)한다. + getter, setter
 *      모두 stored property
 */