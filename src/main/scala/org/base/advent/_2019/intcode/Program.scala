package org.base.advent._2019.intcode

import org.apache.commons.lang3.StringUtils

/** An Intcode program with position. */
class Program(val intcode: Array[Long], var input: Option[Long] = None) {
  var index: Int = 0
  var done: Boolean = false
  var output: Option[Long] = None

  def run: Long = {
    while (!done) step()
    intcode.head
  }

  def at(delta: Int = 0): Long = intcode(index + delta)

  def atAt(delta: Int = 0): Long = intcode(at(delta).toInt)

  def readAt(delta: Int = 0, modeIndex: Int)(implicit modes: Seq[Int]): Long =
    if (modes(modeIndex) == 0) atAt(delta) else at(delta)

  def step(): Unit = {
    if (!done) {
      val instruction = StringUtils.leftPad(at().toString, 5, "0")
      val opCode = instruction.substring(3).toInt
      implicit val parameterModes: Seq[Int] = instruction.substring(0, 3).split("").toSeq.map(_.toInt).reverse

//      println(instruction)
      opCode match {
        case 1 => add()
        case 2 => multiply()
        case 3 => takeInput()
        case 4 => setOutput()
        case 99 =>
          move()
          done = true
      }
    }
  }

  def updateCode(newValue: Long, delta: Int): Unit = {
//    println(s"update $newValue @ $index with delta $delta")
    intcode(at(delta).toInt) = newValue
    move(delta + 1)
  }

  def move(distance: Int = 1): Unit = {
//    println(s"index b4 $index")
    index += distance
//    println(s"index @@ $index")
  }

  // operators            3,225,1,225,6,6,1100,1,238,225,104

  def add()(implicit modes: Seq[Int]): Unit =
    updateCode(readAt(1, 0) + readAt(2, 1), 3)

  def multiply()(implicit modes: Seq[Int]): Unit =
    updateCode(readAt(1, 0) * readAt(2, 1), 3)

  def takeInput(): Unit = {
    updateCode(input.get, 1)
  }

  def setOutput()(implicit modes: Seq[Int]): Unit = {
//    println(output)
    output = Some(readAt(1, 0).toInt)
    if (output.get != 0) println(output)
    move(2)
  }
}

object Program {
  def apply(intcode: Seq[Long], input: Option[Long] = None) = new Program(intcode.toArray, input)
}
