// Sets and Maps

// Sets

val fruit = Set("Apple", "Banana", "Pear")
val s = (1 to 6).toSet

// operations also available in sequence class:
s map (_ + 2)
fruit filter (x => x(0) == 'A')
s.nonEmpty


// DIFFERENCES BETWEEN SETS AND SEQUENCES

// 1. Sets are unordered.
// 2. Sets do not have duplicate elements
// 3. Fundamental operation on sets is CONTAINS, not head/tail (lists) or index (vectors)


// SOLVING N-QUEENS AS AN EXAMPLE

object nqueens {

  def queens(n: Int): Set[List[Int]] = {

    def placeQueens(k: Int): Set[List[Int]] = {
      if (k == 0) Set(List())
      else
        for {
          queens <- placeQueens(k - 1)
          col <- 0 until n
          if isSafe(col, queens)
        } yield col :: queens

    }

    def isSafe(col: Int, queens: List[Int]): Boolean = {
      val row = queens.length
      val queensWithRow = (row - 1 to 0 by -1) zip queens
      queensWithRow forall {
        case (r, c) => col != c && math.abs(col - c) != row - r
      }
    }

    placeQueens(n)
  }

  def show(queens: List[Int]) = {
    val lines =
      for (col <- queens.reverse)
        yield Vector.fill(queens.length)("* ").updated(col, "X ").mkString
    "\n" + (lines mkString "\n")
  }

}

val solution = nqueens.queens(3)
(solution map nqueens.show) mkString "\n"