package de.schauderhaft.tutorial1

object HelloWorld {
    def main(args: Array[String]) {
        println("Hello World")

        val euro = Money(100, "Euro")

        println("Euros: " + euro)

        val someMoreMoney = Money(100, "Euro")

        println("Are they the same? " + (euro == someMoreMoney))

        // Let's add some money
        val lots = euro + someMoreMoney

        println("the sum is: " + lots)

        val bag = MoneyBag(Money(10, "Euro"), Money(50, "Dollar"))
        println("a Bag of money: " + bag)

        println("adding " + (bag + euro))
        println("adding " + (euro + bag))

        println(Money(10, "Euro") + Money(15, "Dollar"))
    }
}

trait Money {
    def +(m2: Money): Money
    def plus(m: Money): Money = this.+(m)
}

object Money {
    def apply(amount: Int, currency: String) = SingleCurrency(currency, amount)
}

case class SingleCurrency(val currency: String, val amount: Int) extends Money {
    def +(m: Money): Money = m match {
        case scm: SingleCurrency => if (currency == scm.currency)
            Money(amount + scm.amount, currency)
        else
            MoneyBag(this, scm)
        case mb: MoneyBag => mb + (this)
        case _ => throw new IllegalArgumentException("still can't add SingleCurrencies with different currency")
    }

    override def toString() = amount + currency
}

object MoneyBag {
    def apply(scs: SingleCurrency*) = {
        new MoneyBag(scs.foldLeft(Map[String, Int]())(MoneyBag.add(_, _)))
    }

    private def add(ms: Map[String, Int], sc: SingleCurrency): Map[String, Int] =
        ms + ((sc.currency, sc.amount + ms.getOrElse(sc.currency, 0)))

    private def pair2SingleCurrency(t: (String, Int)) = SingleCurrency(t._1, t._2)
}

case class MoneyBag(ms: Map[String, Int]) extends Money {

    def +(m: Money) = m match {
        case sc: SingleCurrency => MoneyBag(ms + (sc.currency -> (ms.getOrElse(sc.currency, 0) + sc.amount)))
        case mb: MoneyBag => MoneyBag((ms.map(MoneyBag.pair2SingleCurrency(_)).toSeq ++ mb.ms.map(MoneyBag.pair2SingleCurrency(_)): _*))
        case _ => this
    }

}

object Currencies {
    def US(amount: Int) = Money(amount, "USD")
    def Euro(amount: Int) = Money(amount, "Euro")
    def Yen(amount: Int) = Money(amount, "Yen")
}