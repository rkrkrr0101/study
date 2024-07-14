package tobyspring.hellospring

class Sort {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val list = mutableListOf(1, 2, 566, 343, 12)
            list.sort()
            list.forEach { println(it) }
        }
    }
}
