package sub8_interface

class Product(val name: String, val price: Int)

interface Wheel {
    fun roll()
}

interface Cart : Wheel {

    var coin: Int  // 인터페이스에서 변수도..가능.. 구체에서 무조건 구현해줘야함.

    val weight: String
        get() {
//            field = "20" // 인터페이스에서는 field 를 사용할 수 없음
            return "20KG"
        }

    fun add(product: Product)

    // java default 메서드 처럼.. interface 에 구현가능
    fun rent() {
        if (coin > 0) {
            println("카트를 대여합니다")
        }
    }

    override fun roll() {
        println("카트가 굴러갑니다")
    }

    fun printId() = println("1234")
}

interface Order {

    fun add(product: Product) {
        println("${product.name} 주문이 완료되었습니다")
    }

    fun printId() = println("5678")

}

class MyCart(override var coin: Int) : Cart, Order {

    override fun add(product: Product) {
        if (coin <= 0) println("코인을 넣어주세요")
        else println("${product.name}이(가) 카트에 추가됐습니다")

        // 주문하기
        super<Order>.add(product)
    }

    override fun printId() {
        super<Cart>.printId()
        super<Order>.printId()
    }

}

fun main() {
    val cart = MyCart(coin = 100)
    cart.rent()
    cart.roll()
    cart.add(Product(name = "장난감", price = 1000))
    cart.printId()
}