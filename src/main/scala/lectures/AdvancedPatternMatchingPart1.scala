package org.joseph.advscala2
package lectures

object AdvancedPatternMatchingPart1 extends App {
  val numbers     = List(1)
  val description =
    numbers match {
      case head :: Nil => println(s"the only element is $head")
      case _           =>
    }

  /*
  Uses / Types of Pattern Matching
  - constants
  - wildcards
  - case classes
  - tuples
  - "magic"
   */

  class Person(val name: String, val age: Int)

  // How do you perform pattern matching with a custom class? Ans: "Unapply" it using a "singleton object"
  object Person {
    def unapply(person: Person): Option[(String, Int)] = Some((person.name, person.age))

    def unapply(age: Int): Option[String] = Some(if(age < 21) "minor" else "major")
  }

  val bob      = new Person("Bob", 25)
  val greeting =
    bob match {
      case Person(n, a) => s"Hi, my name is $n and I am $a y/o."
    }
  println(greeting)

  val legalStatus =
    bob.age match {
      case Person(status) => s"My legal status is $status"
    }
  println(legalStatus)

  /*
  Exercise: Create a custom pattern matching solution for these conditions:
   */
  val n: Int = 45
  val mathProperty =
    n match {
      case x if x < 10 => "single digit"
      case x if x % 2 == 0 => "an even number"
      case _ => "no property"
    }
}
