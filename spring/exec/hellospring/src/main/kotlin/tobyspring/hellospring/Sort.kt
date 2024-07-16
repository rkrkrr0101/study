package tobyspring.hellospring

class Sort {
    fun sort(list: List<String>): List<String> {
        return list.sortedBy { it.length }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val list = mutableListOf(1, 2, 566, 343, 12)
            list.sort()
            list.forEach { println(it) }
        }
    }
}
