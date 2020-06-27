fun Fridge.take(productName: String): Product {
    this.open()
    val found = this.find(productName)
    this.close()
    return found
}