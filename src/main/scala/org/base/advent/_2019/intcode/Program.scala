package org.base.advent._2019.intcode

/** An Intcode program with position. */
class Program(val intcode: Array[Long], var index: Int = 0, var done: Boolean = false) {

  def run: Long = {
    while (!done) step()
    intcode.head
  }

  def at(delta: Int = 0): Long = intcode(index + delta)

  def atAt(delta: Int = 0): Long = intcode(at(delta).toInt)

  def step(): Unit = {
    if (!done) {
      val opCode = at()
      opCode match {
        case 1 => add()
        case 2 => multiply()
        case 99 =>
          move()
          done = true
      }
    }
  }

  def update(newValue: Long, delta: Int): Unit = {
    move(delta)
//    println(s"update $newValue @ $index with delta $delta")
    intcode.update(at().toInt, newValue)
    move()
  }

  def move(distance: Int = 1): Unit = index += distance

  // operators
  def add(): Unit = update(atAt(1) + atAt(2), 3)
  def multiply(): Unit = update(atAt(1) * atAt(2), 3)
}

object Program {
  def apply(intcode: Seq[Long], index: Int = 0, done: Boolean = false) = new Program(intcode.toArray, index, done)
}
