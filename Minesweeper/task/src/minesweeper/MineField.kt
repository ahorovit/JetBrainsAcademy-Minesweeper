package minesweeper

import kotlin.random.Random

class MineField {
    private val size: Int
    private val numMines: Int
    private var mineField: Array<Array<Cell>> = arrayOf(arrayOf(Cell.NULL))
    private val flags: Array<BooleanArray>

    constructor(size: Int, numMines: Int) {
        this.size = size
        this.numMines = numMines
        flags = Array(size) { BooleanArray(size) { false } }
        generateMinefield()
    }

    private fun generateMinefield() {
        var minesPlaced = 0

        mineField = Array(size) {
            Array(size) {
                if (minesPlaced++ < numMines) {
                    Cell.MINE
                } else {
                    Cell.EMPTY
                }
            }
        }

        shuffleField()
//        lookAround()
    }

    private fun shuffleField() {
        var swaps = 0

        loop@ for (i in 0 until size) {
            for (j in 0 until size) {
                swapCell(i, j)
                swaps++

                if (swaps >= numMines) {
                    break@loop
                }
            }
        }
    }

    private fun swapCell(i: Int, j: Int) {
        var attempts = 0

        while (true) {
            attempts++
            if (attempts > 100) {
                throw Exception("too many swap attempts")
            }

            val iTarget = Random.nextInt(size)
            val jTarget = Random.nextInt(size)

            if (mineField[iTarget][jTarget].isMine()) {
                continue
            }

            val temp = mineField[iTarget][jTarget]
            mineField[iTarget][jTarget] = mineField[i][j]
            mineField[i][j] = temp
            return
        }
    }

//    private fun lookAround() {
//        var mineCount: Int
//
//        for (i in 0 until size) {
//            for (j in 0 until size) {
//                if (mineField[i][j] == 'X') {
//                    continue
//                }
//
//                mineCount = 0
//
//                for (ii in (i - 1)..(i + 1)) {
//                    if (ii < 0 || ii >= size) {
//                        continue
//                    }
//
//                    for (jj in (j - 1)..(j + 1)) {
//                        if (jj < 0 || jj >= size) {
//                            continue
//                        }
//
//                        if (mineField[ii][jj] == 'X') {
//                            mineCount++
//                        }
//                    }
//                }
//
//                if (mineCount > 0) {
//                    mineField[i][j] = mineCount.toString()[0]
//                }
//            }
//        }
//    }

    fun print() {
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

    fun isSolved(): Boolean {
        var flagCount = 0
        var minesMarked = 0
        var row: Array<Cell>

        for (i in mineField.indices) {
            row = mineField[i]
            for (j in row.indices) {
                if (flags[i][j]) {
                    flagCount++

                    if (row[j].isMine()) {
                        minesMarked++
                    }
                }
            }
        }

        return (minesMarked == numMines && flagCount == numMines)
    }

    fun toggleFlag(inputX: Int, inputY: Int): Boolean {
        var isSuccessful = true

        if (mineField[inputY][inputX].displayChar == '.' || mineField[inputY][inputX].displayChar == 'X') {
            flags[inputY][inputX] = !flags[inputY][inputX]
        } else {
            println("There is a number here!")
            isSuccessful = false
        }

        return isSuccessful
    }
}