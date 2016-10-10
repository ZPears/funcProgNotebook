//MAPS

// data structure that associates keys of type Key with values of type Value

// type Map[Char, Int]
val romanNumerals = Map('I' -> 1, 'V' -> 5, 'X' -> 10)

// type Map[String, String]
val capitalOfCountry = Map("US" -> "Washington", "Switzerland" -> "Bern")


// NOTE THAT MAPS ARE BOTH FUNCTIONS (of type Key => Value) AND DATA STRUCTURES

// this looks like a function call because it is
capitalOfCountry("US")

// throws an exception
capitalOfCountry("andorra")

// returns none
capitalOfCountry get "andorra"

// returns a "Some" type
capitalOfCountry get "US"


// THE OPTION TYPE

trait OptionExample[+A]
case class SomeExample[+A](value: A) extends OptionExample[A]
object NoneExample extends OptionExample[Nothing]

// can be used to prevent NoSuchElementExceptions where desired

def showCapital(country: String): String = capitalOfCountry get country match {
  case Some(capital) => capital
  case None => "missing data"
}


// Group By and Order By (SQL) abalogues

val fruits = List("apple", "pear", "orange", "pineapple")

fruits sortWith (_.length < _.length)
fruits.sorted

// returns fruits grouped into a Map[Char, List] with first char and List of elements
fruits.groupBy (_.head)



// EXAMPLE: Implementing a polynomial class

// Polynomials can be thought of as Map[Int, Int], where the key int represents
// the exponent and the value int represents the value at that exponent

// I.e., x^3 - 2x + 5:
val expEx = Map(0 -> 5, 1 -> -2, 3 -> 1)

object polynomials {

  class Poly(val terms: Map[Int, Double]) {

    // auxilliary constructor - * means arbitrary number of arguments
    def this(bindings: (Int, Double)*) = this(bindings.toMap)

    def + (other: Poly) = new Poly(terms ++ (other.terms map adjust))

    def adjust(term: (Int, Double)): (Int, Double) = {
      val (exp, coeff) = term
      terms get exp match {
        case Some(coeff1) => exp -> (coeff + coeff1)
        case None => exp -> coeff
      }
    }

    override def toString = {
      (for ((exp, coeff) <- terms.toList.sorted.reverse) yield coeff+"x^"+exp) mkString
    }

    // version using with Default Value
    val terms0 = terms withDefaultValue 0.0

  }

  val p1 = new Poly(Map(1 -> 2.0, 3 -> 4.0, 5 -> 6.2))
  val p2 = new Poly(Map(0 -> 3.0, 3 -> 7.0))
}



// SETTING DEFAULT VALUES OF FUNCTIONS
val cap1 = capitalOfCountry withDefaultValue "not found"


// impelementstaion with foldLeft

def + (other: polynomials.Poly) = new polynomials.Poly( (other.terms foldLeft terms)(addTerm) )

// foldLeft version is more efficient