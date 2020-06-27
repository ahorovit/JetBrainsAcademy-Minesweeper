import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val numCompanies = scanner.nextInt()
    val incomes = IntArray(numCompanies)
    val taxes = IntArray(numCompanies)

    for (i in incomes.indices) {
        incomes[i] = scanner.nextInt()
    }

    for (i in taxes.indices) {
        taxes[i] = scanner.nextInt()
    }

    var maxTax = Int.MIN_VALUE
    var maxCompany: Int = -99
    var tax: Int

    for (i in taxes.indices) {
        tax = incomes[i] * taxes[i]

        if (tax > maxTax) {
            maxTax = tax
            maxCompany = i + 1 // Indexed from 1
        }
    }

    println(maxCompany)
}