package de.schauderhaft.tutorial1

object HelloWorld {
    def main(args: Array[String]) {
        println("Hello World")

        val euro = Money("Euro", 100)

        println("Euros: " + euro)

        val someMoreMoney = Money("Euro", 100)

        println("Are they the same? " + (euro == someMoreMoney))
    }
}

case class Money(val currency: String, val amount: Int)