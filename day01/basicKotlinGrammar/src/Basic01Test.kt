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
                add((30..100).random())
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

class Solution {
    fun solution(lottos: IntArray, win_nums: IntArray) =
        win_nums.count {
            lottos.contains(it)
        }.let { winCount ->
            intArrayOf(
                7 - (lottos.filter { it == 0 }.size + winCount),
                winCount
            ).map { if(it == 7) 6 else it }
        }
}

class Solution2 {
    fun solution(lottos: IntArray, win_nums: IntArray): IntArray {
        val winCount =
            win_nums.count {
                lottos.contains(it)
            }
        var bestCount = 7 - (lottos.filter { it == 0 }.size + winCount)
        var worstCount = 7 - if(winCount == 0) 1 else winCount

        if(bestCount == 7) bestCount = 6
        return intArrayOf(bestCount, worstCount)
    }
}