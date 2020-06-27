package minesweeper

import kotlin.random.Random

class MineField(val size: Int, val numMines: Int) {
    val field: Array<CharArray> = generateMinefield()

    private fun generateMinefield(): Array<CharArray> {
        val result = Array(size) { CharArray(size) }
        var minesPlaced = 0
        var cell: Char

        for (i in 0 until size) {
            for (j in 0 until size) {

                if (minesPlaced < numMines) {
                    cell = 'X'
                    minesPlaced++
                } else {
                    cell = '.'
                }

                result[i][j] = cell
            }
        }

        shuffleArray(result)
        lookAround(result)

        return result
    }

    private fun shuffleArray(mineField: Array<CharArray>) {
        var i1: Int
        var j1: Int
        var i2: Int
        var j2: Int
        var temp: Char
        var swaps = 0

        do {
            i1 = Random.nextInt(size)
            j1 = Random.nextInt(size)
            i2 = Random.nextInt(size)
            j2 = Random.nextInt(size)

            if (mineField[i1][j1] == mineField[i2][j2]) {
                continue
            }

            temp = mineField[i1][j1]
            mineField[i1][j1] = mineField[i2][j2]
            mineField[i2][j2] = temp
            swaps++
        } while (swaps < 20)
    }

    private fun lookAround(mineField: Array<CharArray>) {
        var mineCount: Int

        for (i in 0 until size) {
            for (j in 0 until size) {
                if (mineField[i][j] == 'X') {
                    continue
                }

                mineCount = 0

                for (ii in (i - 1)..(i + 1)) {
                    if (ii < 0 || ii >= size) {
                        continue
                    }

                    for (jj in (j - 1)..(j + 1)) {
                        if (jj < 0 || jj >= size) {
                            continue
                        }

                        if (mineField[ii][jj] == 'X') {
                            mineCount++
                        }
                    }
                }

                if (mineCount > 0) {
                    mineField[i][j] = mineCount.toString()[0]
                }
            }
        }
    }

}