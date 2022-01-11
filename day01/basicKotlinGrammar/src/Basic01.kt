fun main() {
}

fun forTest(startNum: Int, loopCount: Int): Int {
    var start = startNum

    for (i in 0..loopCount) {
        start += startNum
        println("$i : $start")
    }

    start = startNum

    for (i in 0 until loopCount) {
        start += startNum
        println("$i : $start")
    }

    start = startNum

    for(i in loopCount downTo 0) {
        start += startNum
        println("$i : $start")
    }

    return start
}

fun forTestStep(startNum: Int, loopCount: Int, step: Int) {
    var start = startNum

    for(i in 0 .. loopCount step(step)) {
        start += startNum
        println("$i : $start")
    }
}

fun forTest2(array: Array<Int>) {
    for(i in array) {
        println(i)
    }

    for(i in array.indices) {
        println(i)
    }

    array.forEach {
        println(it)
    }

    array.forEachIndexed { index, i ->
        println("$index $i")
    }

    array.forEachIndexed { index, _ ->
        println(index)
    }
}

fun whileTest(startNum: Int, loopCount: Int): Int {
    var start = startNum
    var index = 0

    while (index < loopCount) {
        index++
        start += startNum
        println("$index : $start")
    }

    return start
}

fun ifTest(testA: Int, testB: Int) = testA == testB

fun ifTest2(testA: Int, testB: Int) = if (testA == testB) 1 else 2

fun whenTest(test: Int) {
    when (test) {
        0 -> println("0")
        1 -> println("1")
        2 -> println("2")
        3, 4 -> println("3,4")
        in 5..10 -> println("5 .. 10")
        in 11 until 15 -> println("11 until 15")
        else -> println("else")
    }
}

fun whenReturnTest(test: Int) =
    when (test) {
        0 -> "0"
        1 -> "1"
        2 -> "2"
        3, 4 -> "3,4"
        in 5..10 -> "5 .. 10"
        in 11 until 15 -> "11 until 15"
        else -> "else"
    }

fun whenTestFinal(test: Int) =
    println(
        whenReturnTest(test)
    )

fun listTest() {
    //고정 길이
    arrayOf(1,32,1,55,23)

    //가변길이
    arrayListOf(1,432,13).apply {
        add(12)
        set(0, 12)
    }.forEach {
        println(it)
    }

    println()

    mutableListOf(1,32,53,63).apply {
        set(0, 32)
        add(34)
    }.forEach {
        println(it)
    }
}

fun scopeFunction() {
    val test = arrayListOf(1, 2, 3, 4)
    test.run {
        forEach {
            println(it)
        }
    }

    println()

    println(
        test.let {
            it.forEach { value ->
                println(value)
            }
            println()
            "test"
        }
    )

    test.also {
        it.add(5)
    }.apply {
        add(6)
    }
}