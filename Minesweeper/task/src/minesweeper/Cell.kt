package minesweeper

class Cell(val cellValue: Char) {
    var isClicked = false
    var isFlagged = false
    var neighboringMines = 0
    var neighbors: MutableList<Cell> = mutableListOf()
        set(neighbors) {
            field = neighbors

            neighbors.forEach {
                neighbor -> if(neighbor.isMine()) neighboringMines++
            }
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
}