fun main() {
    test1()
}

fun test1() {
    for(i in 0..8) {
        for(j in 0 .. 12) {
            print(
                if(i + j == 6 || j - i == 6 || i == 6 || i == 2 || i - j == 2 || i + j == 14) {
                    "*"
                } else {
                    " "
                }
            )
        }
        println()
    }
}