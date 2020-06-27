import java.util.Scanner

enum class Rainbow(val color: String) {
    RED("red"),
    ORANGE("orange"),
    YELLOW("yellow"),
    GREEN("green"),
    BLUE("blue"),
    INDIGO("indigo"),
    VIOLET("violet"),
}

fun main(args: Array<String>) {
    val input = Scanner(System.`in`).next()
    var isInRainbow: Boolean = false

    for (enum in Rainbow.values()) {
        if (input == enum.color) {
            isInRainbow = true
        }
    }

    println(isInRainbow)
}