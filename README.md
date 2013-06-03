Scala Tutorial
=============

## Setup ##
Jeder Teilnehmer benötigt:

* Einen Rechner, sich einen Rechner mit seinem Nachbarn zu teilen ist eine **sehr gute** Idee.
* Auf diesem Rechner eine [installierte Scala IDE](http://scala-ide.org/) (dies beinhaltet, dass auch ein JDK installiert sein muss). Beim start prüft die Scala IDE, ob sie geeignet konfiguriert ist. Insbesondere die Speichereinstellungen sollten großzügig angepasst, werden. Ansonsten leidet die Performance.
* Gute Grundlagen in OOP und einer entsprechenden Programmiersprache. Java ist toll, C# tut es auch. 
* Eine aktuelle Version von [JUnit](http://junit.org)
* Eine aktuelle Version von [ScalaTest](http://scalatest.org)

## Was ist Scala und warum sollte man das lernen? ##

Scala ist eine Programmiersprache für die JVM. Erdacht wurde sie von Martin Odersky, einem Schweizer Professor an der EPFL. Martin Odersky hatte zuvor schon an Vorläufern von Java 1.5 und an `javac` mitgearbeitet. Man kann also davon ausgehen, dass er nicht nur die Theorie, sondern auch die Praxis von Programmiersprachen im Griff hat.

Sie besticht durch die folgenden Features:

* Objektorientierung
* Funktionales programmieren
* Starkes Typsystem
* Leistungsfähige Typinferenz (Typen müssen nur selten hingeschrieben werden)
* Gute Unterstützung für immutable Datentypen
* Kompakte Syntax

Scala erlaubt unterschiedliche Zugänge zur Sprache:

* als besseres Java
* als funktionale Sprache auf der JVM
* als eigenständige Sprache mit einer ganz eigenen Kombination von Featuren

**Scala ist einfach**: Es enthält weniger Sprachkonstrukte als Java (aber wesentlich mehr als Lisp)

**Scala ist schwierig**: Es enthält Abstraktionsmöglichkeiten, die man als Java Entwickler nicht kennt und die man erstmal lernen muss. Die Syntax ist sehr kompakt, dadurch können Fehlermeldungen schon mal irritierend werden, wenn man durch einen Fehler etwas anderes geschrieben hat als man eigentlich wollte.

**Scala macht Spaß**, dennn man kann eine Menge damit lernen und es sogar praktisch einsetzen.

## Die REPL und einfache Datentypen ##

1. Neues Projekt in der IDE anlegen
2. Den Scala Interpreter View in Eclipse öffnen. (Dieses Tutorial verwendet den Interpreter in der IDE um nicht zwischen unterschiedlichen Tools wechseln zu müssen. Scala bringt seine eigenen Kommandozeilen basierte REPL mit)
3. Verschieden Ausdrücke ausprobieren. z.B.
    * Addition zweier ganzer Zahlen
    * zwei Dezimalzahlen durch einander teilen
    * Strings aneinander hängen
    * Boolsche Ausdrücke
    
Es funktionieren die aus Java bekannten Literale. Strings können auch wie folgt geschrieben werden:

    """Dies ist ein "String" der doppelte Anführungszeichen enthält"""
    
Solche Strings können auch über mehrere Zeilen gehen.

Ergebnisse von Ausdrücken werden in der REPL Values / Werten zugeordnet, die in folgenden Ausdrücken wieder verwendet werden können.

    > 3+ 5
    res0: Int = 8


    > 3.4 / 2
    res1: Double = 1.7

    > res0 * res1
    res2: Double = 13.6

Dies kann mit dem Schlüsselwort `val` auch explizit geschehen, um nettere Namen zu erhalten:

    > val ageInDays = 42*365
    ageInDays: Int = 15330

Es gibt keine Unterscheidung zwischen Objekten und Primitiven in Scala. Operatoren sind einfach nur eigenartig benannte Methoden:

    > 23.+(5)
    res1: Double = 28.0
	
## Typannotationen ##

Man beachte, dass wir keinen einzigen Datentypen angegeben haben, Scala aber dennoch "weiß" welchen Datentypen die Werte haben sollen. Wenn gewünscht können Datentypen nach dem Valuenamen durch einen Doppelpunkt getrennt angegeben werden. 

    > val name : String = "Jens Schauder"
    name: String = Jens Schauder

Inkompatible Datentypen führen dabei zu einem Fehler

    > val age : Int = "Jens Schauder"
    <console>:7: error: type mismatch;
     found   : java.lang.String("Jens Schauder")
     required: Int
           val age : Int = "Jens Schauder"
                           ^

## Collections##

Die Collections API von Scala ist ein Wunderwerk der Technik und besteht us vielen Dutzend Klassen. Wir schauen hier nur die wichtigsten immutable Varianten an. 

    > val simpsons = Seq("Homer", "Marge", "Bart", "Lisa", "Maggie")
    simpsons: List[java.lang.String] = List(Homer, Marge, Bart, Lis, Maggie)

Wie man sieht muss man keine Typen angeben.

Collections sind unveränderlich

    > simpsons + "Grand Pa"
    res2: List[java.lang.String] = List(Homer, Marge, Bart, Lisa, Maggie, Grand Pa)

    > simpsons
    res3: List[java.lang.String] = List(Homer, Marge, Bart, Lisa, Maggie)

	
    > simpsons :+ List()
    res4: List[java.lang.Object] = List(Homer, Marge, Bart, Lisa, Maggie, List())
	
## Funktionen ##

Scala als Hybrid Sprache hat eine gute Unterstützung für Funktionen. Ein einfaches Beispiel für die Verwendung von Funktionen
 
    > simpsons.map(_ + " Simpson")
    res8: List[java.lang.String] = List(Homer Simpson, Marge Simpson, Bart Simpson, Lisa Simpson, Maggie Simpson)

Der Unterstrich ist dabei das Argument zur Funktion. Der Unterstrich kann verwendet werden, wenn jedes Argument, genau einmal verwendet wird und in genau der Reihenfolge, in der sie auch in der Parameterliste stehen. Die vollständigere Schreibweise ist die mit expliziter deklaration der Argumente:

    > simpsons.map( (n) => n + " Simpson") 
    res11: List[java.lang.String] = List(Homer Simpson, Marge Simpson, Bart Simpson, Lisa Simpson, Maggie Simpson)
	
Natürlich kann man auch Typparameter angeben

    > simpsons.map( (n : String) => n + " Simpson") 
	res11: List[java.lang.String] = List(Homer Simpson, Marge Simpson, Bart Simpson, Lisa Simpson, Maggie Simpson)

Funktionen sind ganz normale Objekte in Skala und können auch einem Value zugeordnet werden:

    > val addSurname = (n : String) => n + " Simpson"
	addSurname: String => java.lang.String = <function1>
	> simpsons.map(addSurname)
	res11: List[java.lang.String] = List(Homer Simpson, Marge Simpson, Bart Simpson, Lisa Simpson, Maggie Simpson)
	
Der Typ einer Funktion wird  mit einem Doppelpfeil geschrieben: 

    Int => Int
    (Int, Int) => String
    () => String

Diese Notation ist equivalent zu einer normalen Klassen basierten Notation:

    Function1[Int, Int]
    Function2[Int, Int, String]
    Function0[String]

Wir können natürlich auch selbst Funktionen schreiben die Funktionen als Argumente bekommen.
	
	> val twice :(String => String) => (String => String) = f => x => f(f(x))

Wir lernen: zu viele in einander verschachtelte Funktionsdefinitionen und Deklaration können ganz schön verwirrend sein.
	
### Aufgabe ###

1. Die Methode `filter` auf Listen nimmt einen Funktion vom Typ der Listenelemente auf Boolean und liefert eine Liste mit den Listenelementen für die, die Funktion 'true' ergibt. Erzeuge damit eine Liste der Simpsons deren Namen länger als 4 Buchstaben sind.

2. Die Methode `groupBy` nimmt eine Funktion vom Listenelementtypen auf einen beliebigen anderen Typen. Was liefert diese Methode als Ergebnis? Versuche es zum Beispiel mit der Funktion die die Länge des Namens zurückgibt, oder mit der Funktion aus der vorrigen Aufgabe.
	
3. Die Methode `to` ist auf Integern definiert und liefert eine Liste (genauer einen `Range`) von der Zahl bis zum Argument:

        > val numbers = 1 to 4
        numbers: scala.collection.immutable.Range.Inclusive = Range(1, 2, 3, 4)

    Was ist das erwartete Ergebnis von `numbers.map(_ to 4)`?
	
	Vergleiche es mit dem Ergebnis von `numbers.flatMap(_ to 4)`?
	
## Option ##

In Java und anderen Sprachen wird meist ´null´ verwendet oder eine Exception geworfen, wenn ein Wert nicht existiert. In Scals gibt es einen eigenen Typ dafür: `Option`, mit dem Singleton Wert `None` und der Klasse `Some`

    > simpsons.head
    res2: java.lang.String = Homer
	
    > Seq().head
    java.util.NoSuchElementException: head of empty list

    > Seq().headOption
    res9: Option[Nothing] = None	

	> simpsons.headOption
	res3: Option[java.lang.String] = Some(Homer)

    > Some(23)
    res4: Some[Int] = Some(23)

    > None
    res5: None.type = None

	> val nn : String = null
    nn: String = null
	
	> Option(nn)
    res6: Option[String] = None
    
    > Option("hello")
    res7: Option[java.lang.String] = Some(hello)
    
In vielerlei Hinsicht verhält sich Option wie eine Liste, die nur 0 oder 1 Element haben kann. Insbesondere besitzt `Option` die Methoden `map´ und ´flatMap`
	
## Tupel ##

Of will man nicht einen sondern mehrere Werte zurückgeben. Natürlich kann man sich eine eigene Klasse dazu schreiben. Scala bietet aber schon vorgefertigte Klassen: Tupel. Tupel werden geschrieben wie in der Mathematik:

    > (3, "Lara", Seq("Tochter", "schlau","süß", "selbstbewusst"))
    res2: (Int, java.lang.String, Seq[java.lang.String]) = (3,Lara,List(Tochter, schlau, süß, selbstbewusst))
	
Wie man sieht sieht der Typ praktisch wie eine Instanz aus. 

Tupel gibt es von 2 - 22 Elemente. Auf die einzelnen Element kann man einbasiert über `_<index>` zugreifen
    
    > res2._2
    res3: java.lang.String = Lara
	
Warnung, in einigen Situationen möchte man ein Tupel schreiben, Scala interpretiert die Klammern aber als Klammern eines anderen Konstrukts: Klammern um Typdeklarationen, um eine Parameterliste oder ähnliches. Dann benötigt man doppelte Klammern: `(("blah", 23, true))`

## Sets ##

Neben Listen gibt es natürlich auch `Set`s. 

    > Set()
    res10: scala.collection.immutable.Set[Nothing] = Set()
    
    > Set(1,2,3,5,7,11)
    res11: scala.collection.immutable.Set[Int] = Set(5, 1, 2, 7, 3, 11)
    
    > res11 + 23
    res12: scala.collection.immutable.Set[Int] = Set(5, 1, 2, 7, 3, 11, 23)
    
    > res12.filter(_ % 2 == 0)
    res13: scala.collection.immutable.Set[Int] = Set(2)
    
    > res12.toSeq
    res14: Seq[Int] = ArrayBuffer(5, 1, 2, 7, 3, 11, 23)

    > res12.toSet[Any]
    res15: scala.collection.immutable.Set[Any] = Set(5, 1, 2, 7, 3, 11, 23)
    
    > res12.toSet[String]
    <console>:10: error: type arguments [String] do not conform to method toSet's type parameter bounds [B >: Int]
              res12.toSet[String]
			  
	> Set("alpha", "beta") + 3
    <console>:8: error: type mismatch;
     found   : Int(3)
     required: java.lang.String
                  Set("alpha", "beta") + 3
                                     ^		
    > Set("alpha", "beta")("gamma")
    res18: Boolean = false									 

## Maps ##	
    
	
    > val letters = Map("a" -> "alpha", "b" -> "beta", "g" -> "gamma")
    letters: scala.collection.immutable.Map[java.lang.String,java.lang.String] = Map(a -> alpha, b -> beta, g -> gamma)
    
Der Pfeil ist nur eine Alternative Schreibweise für Tupel
	
    > "x" -> 23
    res0: (java.lang.String, Int) = (x,23)
    
    > letters("b")
    res1: java.lang.String = beta
    
    > letters.get("b")
    res3: Option[java.lang.String] = Some(beta)
    
    > letters.toSeq
    res5: Seq[(java.lang.String, java.lang.String)] = ArrayBuffer((a,alpha), (b,beta), (g,gamma))
    
    
    > letters.get("x")
    res6: Option[java.lang.String] = None
    
Es lassen sich einfach Default Funktionen oder Default Werte definieren
	
    > letters.withDefault(identity)("x")
    res15: java.lang.String = x
    
    > letters.withDefaultValue("unknown")("x")
    res17: java.lang.String = unknown

Bezüglich neuer Einträge verhält sich eine Map wie ein Set von Tupel
	
    > letters + (("e","eta"))
    res19: scala.collection.immutable.Map[java.lang.String,java.lang.String] = Map(a -> alpha, b -> beta, g -> gamma, e -> eta)
    	
    
    > letters + ("e"->"eta")
    res21: scala.collection.immutable.Map[java.lang.String,java.lang.String] = Map(a -> alpha, b -> beta, g -> gamma, e -> eta)	
	
## Klassen ##

Klassen werden in der folgenden Form definiert:

    class Name {
	    //
	}

## `val` vs `var` ##
## Objekte ##
## Case Classes ##


## for Comprehension ##

Die Kombination von `map´, ´flatMap´ und ´filter´ ist ziemlich mächtig und wird sehr häufig benötigt. Leider leidet die Lesbarkeit, wenn man davon zu viele aneinander hängt. Dafür gibt es for Comprehensions. Diese sehen aus wie eine For Schleife auf Steroiden. Es ist aber keine Schleife! Es sollten keine relevanten Seiteneffekte verwendet werden!


## Lügen, Halbwarheiten und Ergänzungen ##

1. Scala gibt es nicht nur für die JVM, sondern auch für .Net ... in welchem Zustand es dort ist, muss jeder den es interessiert selber rausfinden.
2. Es gibt auch mutable collections, aber die ignorieren wir hier, da immutable Collections in einem gewissen Sinne viel mächtiger sind.
3. Seq erzeugt einfach verkettete Listen. Der :+ Operator sollte auf solchen Listen vermieden werden, da er sehr ineffizient ist. Man verwendet besser +: (prepend) oder die Klasse Vector
4. `to` ist nicht wirklich auf Integer definiert. In Wirklichkeit werden durch eine implizite Konversion aus einem Integer eine Klasse gemacht, die die `to` Methode enthält. Aber das ist Material für den 2. Tag.