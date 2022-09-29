package cinema
var rowAll: Int = 0
var seatAll: Int = 0
var zal = mutableListOf<MutableList<String>>()
var maxSeats: Int = 0
var numberPurchasedTickets = 0
var purchasedTicketsSum = 0

fun showMenu() {
    println("1. Show the seats \n2. Buy a ticket \n3. Statistics \n0. Exit \n")
}
fun calcPriceTicket(row: Int, seat: Int) {
    print("Ticket price: ")
    maxSeats = rowAll * seatAll
    if (maxSeats <= 60) {
        println("$10")
        purchasedTicketsSum += 10
    }
    else {
        val centr = rowAll / 2
        if (row <= centr) {
            println("$10")
            purchasedTicketsSum += 10
        }
        else {
            println("$8")
            purchasedTicketsSum += 8
        }
    }
    numberPurchasedTickets++
}
fun buyTicket() {
    // 1.
    println()
    println ("Enter a row number:")
    val row  = readln().toInt()
    println("Enter a seat number in that row:")
    val seat = readln().toInt()
    if (row > rowAll || seat > seatAll) {
        println("Wrong input!")
        return
    }
    // 2.
    val isFree = zal[row - 1][seat - 1] != "B"
    // 3.
    if (isFree) {
        calcPriceTicket(row, seat)
        println()
        zal[row - 1][seat - 1] = "B"
    }
    else {
        println("That ticket has already been purchased!")
        buyTicket()
    }
}
fun staticTotalSum(): Int {
    maxSeats = rowAll * seatAll
    var totalSum = 0
    if (maxSeats <= 60) {
        totalSum = maxSeats * 10
        return totalSum
    }
    else {
        val centr = rowAll / 2
        totalSum = (centr * seatAll * 10) + ((rowAll - centr)* seatAll * 8)
        return totalSum
    }
}
fun main() {
    println ("Enter the number of rows:")
    rowAll  = readln().toInt()
    println("Enter the number of seats in each row:")
    seatAll = readln().toInt()
    repeat(rowAll) {
        zal.add(MutableList<String>(seatAll) {"S"})
    }
    println()
    showMenu()
    do {

        var a = readln().toInt()
        when (a) {
            1 -> {            //show cinema
                println()
                println("Cinema:")
                println("  ${(1..seatAll).joinToString(" ")}")
                for (element in zal.withIndex()) {
                    print("${element.index+1} ")
                    println(element.value.joinToString(" "))
                }
            }
            2 -> {           //buy ticket
                buyTicket()
            }
            3 -> {          //static
                println("Number of purchased tickets: $numberPurchasedTickets")
                var totalSum = staticTotalSum()
                var percentage: Double = 0.0
                percentage = numberPurchasedTickets.toDouble() / maxSeats * 100
                val formatPercentage = String.format("%.2f", percentage)
                println("Percentage: ${formatPercentage}%")
                println("Current income: \$$purchasedTicketsSum")
                println("Total income: \$${totalSum}")
            }
        }
        println()
        showMenu()
    } while(a != 0)
}