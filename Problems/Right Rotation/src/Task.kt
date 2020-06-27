import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    val n = scanner.nextInt()
    val numbers = IntArray(n)

    for (i in 0 until n) {
        numbers[i] = scanner.nextInt()
    }

    val numShifts = scanner.nextInt()
    var tmp: Int

    repeat(numShifts % numbers.size) {
        tmp = numbers.last()

        for (j in numbers.lastIndex downTo 1) {
            numbers[j] = numbers[j - 1]
        }
        
        numbers[0] = tmp
    }

    println(numbers.joinToString(separator = " "))
}
