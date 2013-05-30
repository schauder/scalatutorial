object SetSeq {

// Set has a + operator with the same argument type as the elements
// Set does not have a :+ operator (prepending/appending doesn't make sens with out an ordering of the elements)
// Seq has a :+ operator which takes any type and returns a Seq with appropriate supertype of existing and new elements
// if no + operator exists and a String is used as an argument the collection is converted to a String and the result concatenated with the argument

  val intSet = Set(1,2,3)                         //> intSet  : scala.collection.immutable.Set[Int] = Set(1, 2, 3)
  val strSet = Set("a","b","c")                   //> strSet  : scala.collection.immutable.Set[java.lang.String] = Set(a, b, c)
  val intSeq = Seq(1,2,3)                         //> intSeq  : Seq[Int] = List(1, 2, 3)
  val strSeq = Seq("a","b","c")                   //> strSeq  : Seq[java.lang.String] = List(a, b, c)
  
  
  // + Int
  intSet + 5                                      //> res0: scala.collection.immutable.Set[Int] = Set(1, 2, 3, 5)
  // strSet + 5 // does not compile
  // intSeq + 5 // does not compile
  // strSeq + 5 // does not compile
  
  
  
  // + String
  intSet + "five"                                 //> res1: java.lang.String = Set(1, 2, 3)five
  strSet + "five"                                 //> res2: scala.collection.immutable.Set[java.lang.String] = Set(a, b, c, five)
                                                  //| 
  intSeq + "five"                                 //> res3: java.lang.String = List(1, 2, 3)five
  strSeq + "five"                                 //> res4: java.lang.String = List(a, b, c)five
 
  // :+ Int
  // intSet :+ 5 // does not compile
  // strSet :+ 5 // does not compile
  intSeq :+ 5                                     //> res5: Seq[Int] = List(1, 2, 3, 5)
  strSeq :+ 5                                     //> res6: Seq[Any] = List(a, b, c, 5)
  
  
  
  // :+ String
  // intSet :+ "five" // does not compile
  // strSet :+ "five" // does not compile
  intSeq :+ "five"                                //> res7: Seq[Any] = List(1, 2, 3, five)
  strSeq :+ "five"                                //> res8: Seq[java.lang.String] = List(a, b, c, five)
 
}