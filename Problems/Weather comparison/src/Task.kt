data class City(val name: String) {
    var degrees: Int = 0
        set(value) {
            if (value < -97 || value > 57) {
                field = when (name) {
                    "Dubai" -> 30
                    "Moscow" -> 5
                    "Hanoi" -> 20
                    else -> -9999
                }
            } else {
                field = value
            }
        }
}        

fun main() {
    val first = readLine()!!.toInt()
    val second = readLine()!!.toInt()
    val third = readLine()!!.toInt()
    val firstCity = City("Dubai")
    firstCity.degrees = first
    val secondCity = City("Moscow")
    secondCity.degrees = second
    val thirdCity = City("Hanoi")
    thirdCity.degrees = third

    println(compare(firstCity, secondCity, thirdCity))
}

fun compare(firstCity: City, secondCity: City, thirdCity: City): String {
    var minTemp: Int = firstCity.degrees
    var minCity: City = firstCity
    var minCount: Int = 1

    if (secondCity.degrees == minTemp) {
        minCount++
    } else if (secondCity.degrees < minTemp) {
        minTemp = secondCity.degrees
        minCity = secondCity
    }

    if (thirdCity.degrees == minTemp) {
        minCount++
    } else if (thirdCity.degrees < minTemp) {
        minCity = thirdCity
        minCount = 1
    }

    if (minCount > 1) {
        return "neither"
    } else {
        return minCity.name
    }
}