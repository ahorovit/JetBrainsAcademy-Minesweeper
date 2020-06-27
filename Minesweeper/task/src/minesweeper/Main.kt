package minesweeper

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    print("How many mines do you want on the field?")
    val fieldSize = 9
    val numMines = scanner.nextInt()
    val mineField = MineField(fieldSize, numMines)

    do {
        mineField.print()
        getFlagCoordinates(mineField)
    } while (!mineField.isSolved())

    println("Congratulations! You found all the mines!")
}

fun getFlagCoordinates(mineField: MineField) {
    val scanner = Scanner(System.`in`)
    var inputX: Int
    var inputY: Int
    var isValid: Boolean

    do {
        print("Set/delete min marks (x and y coordinates):")
        inputX = scanner.nextInt() - 1
        inputY = scanner.nextInt() - 1

        isValid = mineField.toggleFlag(inputX, inputY);
    } while (!isValid)
}