package minesweeper

import java.util.*
import kotlin.random.Random

fun main() {
    val scanner = Scanner(System.`in`)
    print("How many mines do you want on the field?")
    val fieldSize = 9
    val numMines = scanner.nextInt()
    val mineField = generateMineField(fieldSize, numMines)
    val flags = Array(fieldSize) { BooleanArray(fieldSize) { false } }

    do {
        printMineField(mineField, flags)
        getFlagCoordinates(mineField, flags)
    } while (!isSolved(mineField, flags, numMines))

    println("Congratulations! You found all the mines!")
}

//fun generateMineField(size: Int, numMines: Int): Array<CharArray> {
//    val result = Array(size) { CharArray(size) }
//    var minesPlaced = 0
//    var cell: Char
//
//    for (i in 0 until size) {
//        for (j in 0 until size) {
//
//            if (minesPlaced < numMines) {
//                cell = 'X'
//                minesPlaced++
//            } else {
//                cell = '.'
//            }
//
//            result[i][j] = cell
//        }
//    }
//
//    shuffleArray(result, size)
//    lookAround(result, size)
//
//    return result
//}

//fun shuffleArray(mineField: Array<CharArray>, size: Int) {
//    var i1: Int
//    var j1: Int
//    var i2: Int
//    var j2: Int
//    var temp: Char
//    var swaps = 0
//
//    do {
//        i1 = Random.nextInt(size)
//        j1 = Random.nextInt(size)
//        i2 = Random.nextInt(size)
//        j2 = Random.nextInt(size)
//
//        if (mineField[i1][j1] == mineField[i2][j2]) {
//            continue;
//        }
//
//        temp = mineField[i1][j1]
//        mineField[i1][j1] = mineField[i2][j2]
//        mineField[i2][j2] = temp
//        swaps++
//    } while (swaps < 20)
//}
//
//fun lookAround(mineField: Array<CharArray>, size: Int) {
//    var mineCount: Int
//
//    for (i in 0 until size) {
//        for (j in 0 until size) {
//            if (mineField[i][j] == 'X') {
//                continue
//            }
//
//            mineCount = 0
//
//            for (ii in (i - 1)..(i + 1)) {
//                if (ii < 0 || ii >= size) {
//                    continue
//                }
//
//                for (jj in (j - 1)..(j + 1)) {
//                    if (jj < 0 || jj >= size) {
//                        continue
//                    }
//
//                    if (mineField[ii][jj] == 'X') {
//                        mineCount++
//                    }
//                }
//            }
//
//            if (mineCount > 0) {
//                mineField[i][j] = mineCount.toString()[0]
//            }
//        }
//    }
//}

fun printMineField(mineField: Array<CharArray>, flags: Array<BooleanArray>) {
    var printRow: String

    // print header
    println(" │123456789│")
    println("—│—————————│")

    for (i in mineField.indices) {
        printRow = mineField[i].joinToString(separator = "").replace('X', '.')

        // print left margin
        print("${i + 1}|")

        for (j in printRow.indices) {
            if (flags[i][j])
                print('*')
            else
                print(printRow[j])
        }

        // print right margin
        println("|")
    }

    // print footer
    println("—│—————————│")
}

fun getFlagCoordinates(mineField: Array<CharArray>, flags: Array<BooleanArray>) {
    val scanner = Scanner(System.`in`)
    var inputX: Int
    var inputY: Int
    var isValid = false

    do {
        print("Set/delete min marks (x and y coordinates):")
        inputX = scanner.nextInt() - 1
        inputY = scanner.nextInt() - 1

        if (mineField[inputY][inputX] == '.' || mineField[inputY][inputX] == 'X') {
            flags[inputY][inputX] = !flags[inputY][inputX]
            isValid = true
        } else {
            println("There is a number here!")
        }
    } while (!isValid)
}

fun isSolved(mineField: Array<CharArray>, flags: Array<BooleanArray>, numMines: Int): Boolean {
    var flagCount = 0
    var minesMarked = 0
    var row: CharArray

    for (i in mineField.indices) {
        row = mineField[i]
        for (j in row.indices) {
            if (flags[i][j]) {
                flagCount++

                if (row[j] == 'X') {
                    minesMarked++
                }
            }
        }
    }

    return (minesMarked == numMines && flagCount == numMines)
}