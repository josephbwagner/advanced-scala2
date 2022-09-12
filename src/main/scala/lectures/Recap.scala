package org.joseph.advscala2
package lectures

import scala.annotation.tailrec

object Recap extends App {
  val aCondition: Boolean = false
  val aConditionedVal = if(aCondition) 42 else 65
  // instructions vs exprs:

  // compiler infers types for us
  val aCodeBlock = {
    if(aCondition) 54
    56
  }

  // Unit
  val theUnit = println("hello, scala")

  // funcs
  def aFunction(x: Int): Int = x + 1

  // recursion: stack and tail
  @tailrec
  def factorial(n: Int, accumulator: Int): Int =
    if(n <= 0) accumulator
    else factorial(n - 1, n * accumulator)

  // OOP
  class Animal
  class Dog extends Animal
  val aDog: Animal = new Dog // subtyping polymorphism

  trait Carnivore {
    def eat(a: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore {
    override def eat(a: Animal): Unit = println("crunch!")
  }

  // method notations
  val aCroc = new Crocodile
  aCroc.eat(aDog) // ...is the same as
  aCroc eat aDog

  // anonymous classes
  val aCarnivore =
    new Carnivore {
      override def eat(a: Animal): Unit = println("roar!")
    }

  // generics, co/contravariance
  abstract class MyList[+A]
  // singletons and companions
  object MyList

  // case classes
  case class Person(name: String, age: Int)

  // exceptions and try/catch/finally
  // val throwsException   = throw new RuntimeException // Nothing
  val aPotentialFailure =
    try throw new RuntimeException
    catch {
      case _: Exception => "I caught an exception"
    } finally println("some logs")

  // packaging and imports

  // functional programming
  val incrementer =
    new Function1[Int, Int] {
      override def apply(v1: Int): Int = v1 + 1
    }

  incrementer(1)

  val anonymousIncrementer = (x: Int) => x + 1 // anonymous func
  List(1, 2, 3).map(anonymousIncrementer) // higher-order func

  // for comprehension
  val pairs =
    for {
      num  <- List(1, 2, 3)
      char <- List('a', 'b', 'c')
    } yield (num + "-" + char)

  // Scala collections
  val aMap =
    Map(
      "Daniel" -> 789,
      "John"   -> 345,
    )

  // Scala's "other" collections: Option, Try
  val anOption = Some(2)

  // pattern matching
  val x     = 2
  val order =
    x match {
      case 1 => "first"
      case 2 => "second"
      case 3 => "third"
      case _ => "value is not 1,2,3"
    }

  val bob      = Person("Bob", 22)
  val greeting =
    bob match {
      case Person(n, _) => s"Hi, my name is $n"
    }
}
