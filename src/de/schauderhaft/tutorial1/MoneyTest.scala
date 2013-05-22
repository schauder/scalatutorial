package de.schauderhaft.tutorial1

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MoneyTest extends FunSuite {
    // declaring a class with simple fields
    test("money has a currency and an amount") {
        Money(23, "Euro").currency should be("Euro")
        Money(23, "Euro").amount should be(23)
    }

    // equals and hashcode
    test("money of same value and currency is equal") {
        Money(42, "Euro") should be(Money(42, "Euro"))

        Money(23, "Euro") should not be (Money(42, "Euro"))
        Money(42, "USD") should not be (Money(42, "Euro"))
        Money(23, "USD") should not be (Money(42, "Euro"))
    }

    //refactor to case class

    // overwrite toString
    test("money has a nice String representation") {
        Money(23, "Euro").toString should be("23Euro")
    }

    // objects / import statements
    test("US Dollars can be created using nice methods") {
        import Currencies._

        US(100) should be(Money(100, "USD"))
    }

    test("Euros can be created using nice methods") {
        import Currencies._

        Euro(100) should be(Money(100, "Euro"))
    }

    // if / exceptions / Nothing
    test("Money of same currency can be added") {
        import Currencies._
        Euro(23).plus(Euro(19)) should be(Euro(42))
    }

    //weird method names
    test("Money of same currency can be added with the + operator") {
        import Currencies._
        Euro(23) + Euro(19) should be(Euro(42))
    }

    test("a money bag should be independent of order of elements") {
        import Currencies._
        MoneyBag(Euro(12), US(100)) should be(MoneyBag(US(100), Euro(12)))
    }

    // groupby
    test("a money bag should add elements of same currency") {
        import Currencies._
        MoneyBag(Euro(12), US(100), Euro(23)) should be(MoneyBag(US(100), Euro(35)))
    }

    // create a trait with the + method
    test("Money of different currency can be added and results in a MoneyBag") {
        import Currencies._
        Euro(23) + US(19) should be(MoneyBag(Euro(23), US(19)))
    }

    test("adding MoneyBags") {
        import Currencies._
        MoneyBag(Euro(23), US(19)) + MoneyBag(Euro(24), Yen(190)) should be(
            MoneyBag(Euro(47), US(19), Yen(190)))
    }

}