class Application(val name: String) {

    fun run(strings: Array<String>) {
        println(name)

        for (string in strings) {
            println(string)
        }
    }
}