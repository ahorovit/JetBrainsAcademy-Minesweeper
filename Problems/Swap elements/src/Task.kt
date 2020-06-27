// The array numbers is already exists. Write only exchange actions here.
val tmp: Int = numbers[0]
numbers[0] = numbers.last()
numbers[numbers.lastIndex] = tmp