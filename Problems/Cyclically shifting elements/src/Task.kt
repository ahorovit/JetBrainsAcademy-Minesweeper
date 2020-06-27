import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val n = scanner.nextInt()
    val numbers = IntArray(n)

    for (i in 0 until n) {
        numbers[i] = scanner.nextInt()
    }

    var prev = numbers.last()
    var tmp: Int

    for (j in numbers.indices) {
        tmp = numbers[j]
        numbers[j] = prev
        prev = tmp
    }

    println(numbers.joinToString(separator = " "))
}