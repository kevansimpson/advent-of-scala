package org.base.advent._2019.intcode

/** Intcode Virtual Machine. */
object IVM {
  def restoreGravityAssist(codes: Seq[Long], swap1: Long = 12, swap2: Long = 2): Long = {
    val c1: Seq[Long] = codes.updated(1, swap1)
    Program(c1.updated(2, swap2)).run
  }

  def findTargetOutput(codes: Seq[Long]): Long = {
    val nvo = for (noun <- 0L to 99L; verb <- 0L to 99L) yield (noun, verb, restoreGravityAssist(codes, noun, verb))
    nvo.filter(_._3 == 19690720L).map(output => 100L * output._1 + output._2).head
  }
}
