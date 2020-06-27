import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val n = scanner.nextInt()
    val numbers = IntArray(n)

    for (i in 0 until n) {
        numbers[i] = scanner.nextInt()
    }

    val target = scanner.nextInt()
    var matchCount = 0

    for (value in numbers) {
        if (value == target) {
            matchCount++
        }
    }

    println(matchCount)
}