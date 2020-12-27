package org.base.advent._2020

import org.base.advent.Reader._

/**
  * <b>Part 1</b>
  * You arrive at the airport only to realize that you grabbed your North Pole Credentials instead of your passport.
  * While these documents are extremely similar, North Pole Credentials aren't issued by a country and therefore aren't
  * actually valid documentation for travel in most of the world.
  *
  * It seems like you're not the only one having problems, though; a very long line has formed for the automatic
  * passport scanners, and the delay could upset your travel itinerary.
  *
  * Due to some questionable network security, you realize you might be able to solve both of these problems at the
  * same time.
  *
  * The automatic passport scanners are slow because they're having trouble detecting which passports have all required
  * fields. The expected fields are as follows:
  * <ul>
  *     <li>byr (Birth Year)</li>
  *     <li>iyr (Issue Year)</li>
  *     <li>eyr (Expiration Year)</li>
  *     <li>hgt (Height)</li>
  *     <li>hcl (Hair Color)</li>
  *     <li>ecl (Eye Color)</li>
  *     <li>pid (Passport ID)</li>
  *     <li>cid (Country ID)</li>
  * </ul>
  * Passport data is validated in batch files (your puzzle input). Each passport is represented as a sequence of
  * key:value pairs separated by spaces or newlines. Passports are separated by blank lines.
  *
  * Here is an example batch file containing four passports:
  * <pre>
  * ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
  * byr:1937 iyr:2017 cid:147 hgt:183cm
  *
  * iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
  * hcl:#cfa07d byr:1929
  *
  * hcl:#ae17e1 iyr:2013
  * eyr:2024
  * ecl:brn pid:760753108 byr:1931
  * hgt:179cm
  *
  * hcl:#cfa07d eyr:2025 pid:166559648
  * iyr:2011 ecl:brn hgt:59in
  * </pre>
  *
  * The first passport is valid - all eight fields are present. The second passport is invalid - it is missing hgt
  * (the Height field).
  *
  * The third passport is interesting; the only missing field is cid, so it looks like data from North Pole Credentials,
  * not a passport at all! Surely, nobody would mind if you made the system temporarily ignore missing cid fields.
  * Treat this "passport" as valid.
  *
  * The fourth passport is missing two fields, cid and byr. Missing cid is fine, but missing any other field is not, so
  * this passport is invalid.
  *
  * According to the above rules, your improved system would report 2 valid passports.
  *
  * Count the number of valid passports - those that have all required fields. Treat cid as optional. In your batch
  * file, how many passports are valid?
  *
  * <b>Part 2</b>
  * The line is moving more quickly now, but you overhear airport security talking about how passports with invalid data
  * are getting through. Better add some data validation, quick!
  *
  * You can continue to ignore the cid field, but each other field has strict rules about what values are valid for
  * automatic validation:
  * <ul>
  *     <li>byr (Birth Year) - four digits; at least 1920 and at most 2002.</li>
  *     <li>iyr (Issue Year) - four digits; at least 2010 and at most 2020.</li>
  *     <li>eyr (Expiration Year) - four digits; at least 2020 and at most 2030.</li>
  *     <li>hgt (Height) - a number followed by either cm or in:</li>
  *     <li>If cm, the number must be at least 150 and at most 193.</li>
  *     <li>If in, the number must be at least 59 and at most 76.</li>
  *     <li>hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.</li>
  *     <li>ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.</li>
  *     <li>pid (Passport ID) - a nine-digit number, including leading zeroes.</li>
  *     <li>cid (Country ID) - ignored, missing or not.</li>
  * </ul>
  *
  * Your job is to count the passports where all required fields are both present and valid according to the above rules.
  * Here are some example values:
  * <pre>
  * byr valid:   2002
  * byr invalid: 2003
  *
  * hgt valid:   60in
  * hgt valid:   190cm
  * hgt invalid: 190in
  * hgt invalid: 190
  *
  * hcl valid:   #123abc
  * hcl invalid: #123abz
  * hcl invalid: 123abc
  *
  * ecl valid:   brn
  * ecl invalid: wat
  *
  * pid valid:   000000001
  * pid invalid: 0123456789
  * </pre>
  *
  * Here are some invalid passports:
  * <pre>
  * eyr:1972 cid:100
  * hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926
  *
  * iyr:2019
  * hcl:#602927 eyr:1967 hgt:170cm
  * ecl:grn pid:012533040 byr:1946
  *
  * hcl:dab227 iyr:2012
  * ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277
  *
  * hgt:59cm ecl:zzz
  * eyr:2038 hcl:74454a iyr:2023
  * pid:3556412378 byr:2007
  * </pre>
  *
  * Here are some valid passports:
  * <pre>
  * pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980
  * hcl:#623a2f
  *
  * eyr:2029 ecl:blu cid:129 byr:1989
  * iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm
  *
  * hcl:#888785
  * hgt:164cm byr:2001 iyr:2015 cid:88
  * pid:545766238 ecl:hzl
  * eyr:2022
  *
  * iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719
  * </pre>
  *
  * Count the number of valid passports - those that have all required fields and valid values. Continue to treat cid as
  * optional. In your batch file, how many passports are valid?
  */
class Day04 {
  private lazy val input = readLines("/2020/input04.txt")

  type Validator = Map[String, String] => Boolean

  def countValidPasswords(lines: Seq[String], validator: Validator): Long = {
    if (lines.isEmpty) 0L
    else {
      val span = lines.span(_.contains(":"))
      parsePasswords(span._1, validator) + countValidPasswords(span._2.drop(1), validator)
    }
  }

  private final val KEYS = Seq("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid")
  private final val YEAR = "(\\d{4})".r
  private final val HEIGHT = "(\\d+)(cm|in)".r
  private final val HAIR_COLOR = "#([0-9a-f]{6})".r
  private final val EYE_COLORS = Seq("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
  private final val PASSPORT = "(\\d{9})".r

  def validate(password: Map[String, String]): Boolean = {
    KEYS.dropRight(1).forall(password.contains)
  }

  def validateComplex(password: Map[String, String]): Boolean = {
    KEYS
      .dropRight(1)
      .forall(_ match {
        case "byr" => validYear(password.get("byr").getOrElse(""), 1920, 2002)
        case "iyr" => validYear(password.get("iyr").getOrElse(""), 2010, 2020)
        case "eyr" => validYear(password.get("eyr").getOrElse(""), 2020, 2030)
        case "hgt" =>
          password.get("hgt").getOrElse("") match {
            case HEIGHT(ht, unit) if "cm".equals(unit) => ht.toInt >= 150 && ht.toInt <= 193
            case HEIGHT(ht, unit) if "in".equals(unit) => ht.toInt >= 59 && ht.toInt <= 76
            case _ => false
          }
        case "hcl" =>
          password.get("hcl").getOrElse("") match {
            case HAIR_COLOR(hcl) => hcl.length == 6
            case _ => false
          }
        case "ecl" => EYE_COLORS.contains(password.get("ecl").getOrElse(""))
        case "pid" => PASSPORT.matches(password.get("pid").getOrElse(""))
        case _ => true
      })
  }

  def validYear(string: String, min: Int, max: Int): Boolean = string match {
    case YEAR(year) => year.toInt >= min && year.toInt <= max
    case _ => false
  }

  def parsePasswords(lines: Seq[String], validator: Validator): Long = {
    if (validator(lines.foldLeft(Map[String, String]())(_ ++ password2map(_)))) 1L else 0L
  }

  def password2map(password: String): Map[String, String] = {
    password.split(" ").map(kvp => kvp.split(":")).map(kvp => kvp(0) -> kvp(1)).toMap
  }

  def solvePart1: Long = countValidPasswords(input, validate)

  def solvePart2: Long = countValidPasswords(input, validateComplex)
}
