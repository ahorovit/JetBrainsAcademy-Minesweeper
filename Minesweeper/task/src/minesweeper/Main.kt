package minesweeper

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
        getFlagCoordinates(mineField)
    } while (!mineField.isSolved())

    println("Congratulations! You found all the mines!")
}

fun getFlagCoordinates(mineField: MineField) {
    val scanner = Scanner(System.`in`)

    do {

        // @todo: restore
//        print("Set/delete min marks (x and y coordinates):")
//        var inputX = scanner.nextInt() - 1
//        var inputY = scanner.nextInt() - 1
        var inputX = Random.nextInt(9)
        var inputY = Random.nextInt(9)
        println("Set/delete min marks (x and y coordinates): $inputX $inputY")

        var isValid = mineField.toggleFlag(inputX, inputY)
    } while (!isValid)
}