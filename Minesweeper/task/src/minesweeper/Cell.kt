package minesweeper

class Cell(val cellValue: Char) {
    var isClicked = false
    var isFlagged = false
    var xCoord: Int = -1
    var yCoord: Int = -1
    var neighboringMines = 0
    var neighbors: MutableList<Cell> = mutableListOf()
        set(neighbors) {
            field = neighbors

            neighbors.forEach {
                neighbor -> if(neighbor.isMine()) neighboringMines++
            }
        }

    companion object {
        val cellsToClick = mutableListOf<Cell>()
    }

    fun setCoordinates(xCoord: Int, yCoord: Int) {
        this.xCoord = xCoord
        this.yCoord = yCoord
    }

    fun getDisplayChar(): Char {
        return if (isClicked) {
            when (isMine()) {
                true -> cellValue
                else -> {
                    if (neighboringMines > 0) {
                        "$neighboringMines"[0] // Convert decimal (1-8) to char (not ASCII)
                    } else {
                        '/'
                    }
                }
            }
        } else if (isFlagged) {
            '*'
        } else {
            '.'
        }
    }

    fun isMine(): Boolean {
        return cellValue == 'X'
    }

    fun click() {
        // Cell may be added to cellsToClick by multiple neighbors
        if (isClicked) {
            return
        }

        isClicked = true

        // Game over
        if (isMine()) {
            return
        }

        // clicking on empty cells must propagate to neighboring empty cells
        if (neighboringMines == 0) {
            neighbors.forEach { neighbor ->
                if (!neighbor.isMine()) {
                    if (neighbor.neighboringMines == 0 && !neighbor.isClicked) {
                        cellsToClick.add(neighbor)
                    } else {
                        neighbor.isClicked = true
                    }
                }
            }
        }

        while (!cellsToClick.isEmpty()) {
            cellsToClick.removeAt(0).click()
        }
    }
}