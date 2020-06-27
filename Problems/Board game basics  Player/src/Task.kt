data class Player(val id: Int, val name: String, val hp: Int) {

    companion object {
        const val defaultHp = 100
        var nextId = 1

        fun create(name: String): Player {
            return Player(nextId++, name, defaultHp)
        }
    }
}