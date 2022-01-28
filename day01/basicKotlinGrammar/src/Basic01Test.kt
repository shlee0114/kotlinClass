
fun main() {
    println(test3())
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

fun test3() =
    arrayListOf<Int>()
        .apply {
            for (i in 0..(10..20).random())
                add((30 .. 100).random())
        }.also {
            println(it)
        }.filter {
            it >= 50
        }.map {
            when (it) {
                in 90..100 -> "A"
                in 80 until 90 -> "B"
                in 70 until 80 -> "C"
                else -> "D"
            }
        }