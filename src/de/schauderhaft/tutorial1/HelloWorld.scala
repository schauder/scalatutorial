package de.schauderhaft.tutorial1

object HelloWorld {
    def main(args: Array[String]) {
        println("Hello World")

        val euro = Money("Euro", 100)

        println("Euros: " + euro)

        val someMoreMoney = Money("Euro", 100)

        println("Are they the same? " + (euro == someMoreMoney))

        // Let's add some money
        val lots = euro + someMoreMoney

        println("the sum is: " + lots)
    }
}

case class Money(val currency: String, val amount: Int) {
    // + is a valid method name
    def +(m2: Money): Money =
        if (currency == m2.currency)
            Money(currency, amount + m2.amount)
        else
            throw new IllegalArgumentException("Can't add money of different currencies yet")
}