package minesweeper

import java.lang.Exception
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    print("How many mines do you want on the field?")
    val fieldSize = 9
    val numMines = scanner.nextInt()
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
        print("Set/unset mine marks or claim a cell as free:")
        val inputX = scanner.nextInt()
        val inputY = scanner.nextInt()

        val isValid = when (scanner.next()) {
            "free" -> mineField.clickCell(inputX - 1, inputY - 1)
            "mine" -> mineField.toggleFlag(inputX - 1, inputY - 1)
            else -> throw Exception("Invalid command")
        }
    } while (!isValid)
}