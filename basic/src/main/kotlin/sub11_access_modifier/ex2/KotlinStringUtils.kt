package sub11_access_modifier.ex2

fun isEmpty(str: String?): Boolean {
    return str.isNullOrEmpty()
}

/**
 * 코틀린에서는 코틀린 파일에서 함수로 바로 제공하면 된다.
 * 실제로 bytecode 변환 후, decompile 하여 Java 코드로 보면 static 메서드로 보인다.
 */