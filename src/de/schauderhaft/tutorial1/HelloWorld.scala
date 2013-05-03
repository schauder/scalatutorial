package de.schauderhaft.tutorial1

object HelloWorld {
    def main(args: Array[String]) {
        println("Hello World")

        val euro = new Money("Euro", 100)

    }
}

class Money(val currency: String, val amount: Int)