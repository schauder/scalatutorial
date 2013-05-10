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

trait Money {
    def +(m2: Money): Money
}

object Money {
    def apply(currency: String, amount: Int) = SingleCurrency(currency, amount)
}

case class SingleCurrency(val currency: String, val amount: Int) extends Money {
    def +(m: Money): Money = m match {
        case scm: SingleCurrency if (currency == scm.currency) => Money(currency, amount + scm.amount)
        case _ => throw new IllegalArgumentException("Can't add money of different currencies yet")
    }
}