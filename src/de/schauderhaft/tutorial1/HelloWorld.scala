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

        val bag = MoneyBag(Money("Euro", 10), Money("Dollar", 50))
        println("a Bag of money: " + bag)

        println("adding " + (bag + euro))
        println("adding " + (euro + bag))

        println(Money("Euro", 10) + Money("Dollar", 15))
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
        case scm: SingleCurrency => if (currency == scm.currency)
            Money(currency, amount + scm.amount)
        else
            MoneyBag(this, scm)
        case mb: MoneyBag => mb + (this)
        case _ => throw new IllegalArgumentException("still can't add SingleCurrencies with different currency")
    }
}

object MoneyBag {
    def apply(scs: SingleCurrency*) = {
        new MoneyBag(scs.foldLeft(Map[String, Int]())(MoneyBag.add(_, _)))
    }

    private def add(ms: Map[String, Int], sc: SingleCurrency): Map[String, Int] =
        ms + ((sc.currency, sc.amount + ms.getOrElse(sc.currency, 0)))

}

case class MoneyBag(ms: Map[String, Int]) extends Money {

    def +(m: Money) = m match {
        case sc: SingleCurrency => MoneyBag(ms + (sc.currency -> (ms.getOrElse(sc.currency, 0) + sc.amount)))
        case _ => this
    }

}