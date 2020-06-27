import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val input = scanner.nextLine()
    val n = scanner.nextInt()

    println("Symbol # $n of the string \"$input\" is '${input.drop(n - 1).first()}'")
}