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
        case 5 => jumpYes()
        case 6 => jumpNo()
        case 7 => lessThan()
        case 8 => equalTo()
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

  def move(distance: Int = 1): Unit = index += distance

  // operators

  def add()(implicit modes: Seq[Int]): Unit =
    updateCode(readAt(1, 0) + readAt(2, 1), 3)

  def multiply()(implicit modes: Seq[Int]): Unit =
    updateCode(readAt(1, 0) * readAt(2, 1), 3)

  def takeInput(): Unit = updateCode(input.get, 1)

  def setOutput()(implicit modes: Seq[Int]): Unit = {
    output = Some(readAt(1, 0).toInt)
    move(2)
  }

  def jumpYes()(implicit modes: Seq[Int]): Unit = {
    if (readAt(1, 0) != 0) index = readAt(2, 1).toInt
    else move(3)
  }

  def jumpNo()(implicit modes: Seq[Int]): Unit = {
    if (readAt(1, 0) == 0) index = readAt(2, 1).toInt
    else move(3)
  }

  def lessThan()(implicit modes: Seq[Int]): Unit = {
    updateCode(if (readAt(1, 0) < readAt(2, 1)) 1 else 0, 3)
  }

  def equalTo()(implicit modes: Seq[Int]): Unit = {
    updateCode(if (readAt(1, 0) == readAt(2, 1)) 1 else 0, 3)
  }
}

object Program {
  def apply(intcode: Seq[Long], input: Option[Long] = None) = new Program(intcode.toArray, input)
}
