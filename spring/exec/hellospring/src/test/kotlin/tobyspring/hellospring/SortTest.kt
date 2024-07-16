package tobyspring.hellospring

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SortTest {
    private var sort = Sort()

    @BeforeEach
    fun beforeEach() {
        sort = Sort()
    }

    @Test
    fun sort() {
        val sortList = sort.sort(listOf("aa", "b"))

        Assertions.assertThat(sortList).hasSize(2)
        Assertions.assertThat(sortList).isEqualTo(listOf("b", "aa"))
    }

    @Test
    fun sort3Item() {
        val sortList = sort.sort(listOf("aa", "ccc", "b"))

        Assertions.assertThat(sortList).hasSize(3)
        Assertions.assertThat(sortList).isEqualTo(listOf("b", "aa", "ccc"))
    }

    @Test
    fun sortAlreadySorted() {
        val sortList = sort.sort(listOf("b", "aa", "ccc"))

        Assertions.assertThat(sortList).hasSize(3)
        Assertions.assertThat(sortList).isEqualTo(listOf("b", "aa", "ccc"))
    }
}
