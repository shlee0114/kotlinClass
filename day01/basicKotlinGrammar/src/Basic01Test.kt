fun main() {
}

fun test1() {
    for (i in 0..8) {
        for (j in 0..12) {
            print(
                if (i + j == 6 || j - i == 6 || i == 6 || i == 2 || i - j == 2 || i + j == 14) {
                    "*"
                } else {
                    " "
                }
            )
        }
        println()
    }
}

fun test2() =
    arrayListOf(1, 2, 3, 4, 5, 6)
        .also {
            println(it)
        }.apply {
            add(7)
            add(8)
        }.map {
            it + 3
        }.filter {
            it > 5
        }.run {
            get(2)
        }