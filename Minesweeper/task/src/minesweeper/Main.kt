package minesweeper

import java.lang.Exception
import java.util.*
import kotlin.random.Random

fun main() {
    val scanner = Scanner(System.`in`)
//    print("How many mines do you want on the field?")
    println("How many mines do you want on the field? > 10") // @todo remove
    val fieldSize = 9
//    val numMines = scanner.nextInt()
    val numMines = 10 // @todo remove
    val mineField = MineField(fieldSize, numMines)

    do {
        mineField.print()
        getNextCommand(mineField)
    } while (!mineField.isGameOver())

    mineField.print()

    if (mineField.isExploded) {
        println("You stepped on a mine and failed!")
    } else {
        println("Congratulations! You found all the mines!")
    }
}

fun getNextCommand(mineField: MineField) {
    val scanner = Scanner(System.`in`)

    do {
        // @todo: restore
//        print("Set/unset mine marks or claim a cell as free:")
//        var inputX = scanner.nextInt() - 1
//        var inputY = scanner.nextInt() - 1
//        var command = scanner.next()

        var inputX = Random.nextInt(9)
        var inputY = Random.nextInt(9)
        var command = "free"
        println("Set/unset mine marks or claim a cell as free: $inputX $inputY $command")

        var isValid = when (command) {
            "free" -> mineField.clickCell(inputX, inputY)
            "mine" -> mineField.toggleFlag(inputX, inputY)
            else -> throw Exception("Invalid command")
        }
    } while (!isValid)
}