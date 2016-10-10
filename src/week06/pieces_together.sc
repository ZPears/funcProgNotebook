// Example:

// assume you are given a dictionary with a list of words

// design a method "translate" such that translate(phone number)
// returns the list of words that could serve as mnemonics for the number

import scala.io.Source

object x {
  val in = Source.fromURL("http://lamp.epfl.ch/files/content/sites/lamp/files/teaching/progfun/linuxwords.txt")

  val words = in.getLines.toList filter (word => word forall (chr => chr.isLetter))

  // phone keys and their letter values

  val mnemonics = Map(
    '2' -> "ABC", '3' -> "DEF", '4' -> "GHI", '5' -> "JKL",
    '6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ"
  )

  val charCode: Map[Char, Char] =
    for ( (digit, str) <- mnemonics; ltr <- str ) yield ltr -> digit

  def wordCode(word: String): String =
    word.toUpperCase map charCode

  wordCode("Java")

  // now go the other direction:

  val wordsForNum: Map[String, Seq[String]] =
    words groupBy wordCode withDefaultValue Seq()

  def encode(number: String): Set[List[String]] =

    // boundary case first
    if (number.isEmpty) Set(List())
    else {
      for {
        split <- 1 to number.length
        word <- wordsForNum(number take split)
        rest <- encode(number drop split)
      } yield word :: rest
    }.toSet

  encode("7225247386")

  def translate(number: String): Set[String] =
    encode(number) map (_ mkString " ")

  translate("7225247386")

}