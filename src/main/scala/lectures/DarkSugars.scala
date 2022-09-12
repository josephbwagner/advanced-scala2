package org.joseph.advscala2
package lectures

import scala.util.Try

object DarkSugars extends App {
  // sugar 1: single-arg method calls allow for curly brances
  def singleArgMethod(arg: Int): String = s"$arg little ducks"
  val description =
    singleArgMethod {
      // complex code here
      42
    }

  val aTryInstance =
    Try { // reminiscient of Java's try
      throw new RuntimeException
    }

  List(1, 2, 3).map(x => x + 1)

  // sugar 2: single abstract method
  // "types/traits with a single abs. method can be reduced to lambdas"
  trait Action {
    def act(x: Int): Int
  }

  val anInst: Action =
    new Action {
      override def act(x: Int): Int = x + 1
    }

  val aFunkyInst: Action = (x: Int) => x + 1

  // example: Runnables (inst. of Traits/Interfaces passable to Threads)
  val aThread =
    new Thread(new Runnable {
      override def run(): Unit = println("hello scala")
    })

  val aSweeterThread = new Thread(() => println("hey scala"))

  abstract class AnAbstractType {
    def implemented: Int = 23
    def f(a: Int): Unit
  }

  val anAbstractInst: AnAbstractType = (a: Int) => println(s"$a")

  // sugar 3: the `::` and `#::` methods are special
  // "methods with ':' are right-associative, all others are left-associative"
  val prependedList = 2 :: List(3, 4)
  // 2.::(List(3,4))
  // Acutally, compiler sees it as: List(3,4).::(2)
  // Scala specialty: last char decides associativity of method
  val listOne       = 1 :: 2 :: 3 :: List(3, 4)
  val listTwo       = List(4, 5).::(3).::(2).::(1) // how listOne is read by compiler

  class MyStream[T] {
    def -->:(value: T): MyStream[T] = this // not an actual impl.
  }

  val myStream = 1 -->: 2 -->: 3 -->: 4 -->: new MyStream[Int]

  // sugar 4: multi-word method naming
  class Teenager(name: String) {
    def `and then said`(gossip: String): Unit = println(s"$name said $gossip")
  }

  val Ron = new Teenager("Ron Weasley")
  Ron `and then said` "Scala rules!"

  // sugar 5: infix generic types
  class Composite[A, B]
  val composite: Int Composite String = new Composite // dummy impl.

  class -->[A, B]
  val towards: Int --> String = new --> // dummy impl.

  // sugar 6: update() is very special, like apply()
  val anArray = Array(1, 2, 3)
  anArray(2) = 7 // compiler rewrites to: anArray.update(2, 7)
  // used in mutable collections

  // sugar 7: setts for mutable containers
  class Mutable {
    private var internalMember: Int = 0 // private for OO encapsulation
    def member = internalMember // "getter"
    def member_=(value: Int): Unit = internalMember = value // "setter"
  }

  val aMutableContainer = new Mutable
  aMutableContainer.member = 42 // rewritten as: aMutableContainer.member_=(42)
}
