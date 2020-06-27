package minesweeper

import kotlin.random.Random

class MineField(private val size: Int, private val numMines: Int) {
    private var mineField: Array<Array<Cell>> = arrayOf(arrayOf(Cell('?')))
    private var isFinalized = false
    var isExploded = false

    init {
        generateMinefield()
    }

    private fun generateMinefield() {
        var minesPlaced = 0

        // Guarantee specific number of mines in field
        mineField = Array(size) {
            Array(size) {
                if (minesPlaced++ < numMines) {
                    Cell('X')
                } else {
                    Cell('.')
                }
            }
        }

        shuffleField()
    }

    // Randomize mines in field
    private fun shuffleField() {
        var swaps = 0

        loop@ for (i in 0 until size) {
            for (j in 0 until size) {
                swapMinePosition(i, j)
                swaps++

                if (swaps >= numMines) {
                    break@loop
                }
            }
        }
    }

    // Find random cell to swap with mine at (i,j)
    private fun swapMinePosition(i: Int, j: Int) {
        var attempts = 0

        while (true) {
            // Avoid infinite loop
            attempts++
            if (attempts > 100) {
                throw Exception("too many swap attempts")
            }

            val iTarget = Random.nextInt(size)
            val jTarget = Random.nextInt(size)

            // Must swap with empty cell
            if (mineField[iTarget][jTarget].isMine()) {
                continue
            }

            val tempCell = mineField[iTarget][jTarget]
            mineField[iTarget][jTarget] = mineField[i][j]
            mineField[i][j] = tempCell

            // In case one cell was flagged, we must restore the coordinates of the flag
            val tempIsFlagged = mineField[iTarget][jTarget].isFlagged
            mineField[iTarget][jTarget].isFlagged = mineField[i][j].isFlagged
            mineField[i][j].isFlagged = tempIsFlagged

            return
        }
    }

    fun print() {
        // print header
        println(" │123456789│")
        println("—│—————————│")

        for (i in mineField.indices) {
            var printRow = ""
            mineField[i].forEach { cell ->
                printRow += cell.getDisplayChar()
            }

            println("${i + 1}|$printRow|")
        }
        // print footer
        println("—│—————————│")
    }

    fun clickCell(inputX: Int, inputY: Int): Boolean {
        // First click is guaranteed to be empty cell
        if (!isFinalized) {
            finalize(inputY, inputX)
        }

        val clickedCell = mineField[inputY][inputX]

        return if (clickedCell.isClicked) {
            println("Cell is already explored")
            false
        } else {
            clickedCell.click()
            if (clickedCell.isMine()) {
                isExploded = true
            }
            true
        }
    }

    // First 'click' on field cannot be a Mine
    // --> upon first click, move mine if necessary, and set neighbors for all cells
    private fun finalize(yIndex: Int, xIndex: Int) {
        if (mineField[yIndex][xIndex].isMine()) {
            swapMinePosition(yIndex, xIndex)
        }

        // Pass neighbors to each cell
        for (i in 0 until size) {
            for (j in 0 until size) {
                mineField[i][j].neighbors = getNeighbors(i, j)
                mineField[i][j].setCoordinates(j+1, i+1)
            }
        }

        isFinalized = true
    }

    // place/remove flag
    fun toggleFlag(inputX: Int, inputY: Int): Boolean {
        val cell = mineField[inputY][inputX]

        return if(cell.isClicked) {
            println("Cannot mark explored cell!")
            false
        } else {
            cell.isFlagged = !cell.isFlagged
            true
        }
    }

    // Get (up to) 8 neighboring cells for a given cell at (i,j)
    private fun getNeighbors(i: Int, j: Int): MutableList<Cell> {
        val result = mutableListOf<Cell>()

        for (ii in (i - 1)..(i + 1)) {
            if (ii < 0 || ii >= size) {
                continue
            }

            for (jj in (j - 1)..(j + 1)) {
                if (jj < 0 || jj >= size || (ii == i && jj == j)) {
                    continue
                }

                result.add(mineField[ii][jj])
            }
        }

        return result
    }

    fun isGameOver(): Boolean {
        if (isExploded) {
            return true
        }

        var clickedCount = 0

        mineField.forEach {row ->
            row.forEach { cell ->
                if (cell.isClicked) {
                    clickedCount++
                }
            }
        }

        return (clickedCount + numMines) == (size*size)
    }
}