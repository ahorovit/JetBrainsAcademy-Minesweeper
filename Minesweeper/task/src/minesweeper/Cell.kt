package minesweeper

enum class Cell(val cellValue: Char) {
    MINE('X'),
    EMPTY('.'),
    NULL('?');

    val neighbors: MutableList<Cell> = mutableListOf()
    var displayChar: Char = cellValue

    fun isMine(): Boolean {
        return cellValue == 'X'
    }


    fun addNeighbor(neighbor: Cell) {
        neighbors.add(neighbor)

//        if (this.cellValue != 'X')
    }



}