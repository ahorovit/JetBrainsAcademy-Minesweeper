object PlayingField {
    object Size {
        var width: Int = 0
        var height: Int = 0

        fun changeSize(width: Int, height: Int) {
            this.width = if (width < 0) 0 else width
            this.height = if (height < 0) 0 else height
        }
    }
}