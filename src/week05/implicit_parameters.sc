// making msort work for any type of list

// adding a second argument "lt" that represents the less than function to be used
def msort[T](xs: List[T])(lt: (T,T) => Boolean): List[T] = {
  val n = xs.length/2
  if (n == 0) xs
  else {
    def merge(xs: List[T], ys: List[T]): List[T] = {
      (xs, ys) match {
        case (Nil, ys) => ys
        case (xs, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (lt(x, y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }
    }
    val (fst, snd) = xs splitAt n
    merge(msort(fst)(lt), msort(snd)(lt))
  }
}

// msort with function argument
val nums = List(2, -4, 5, 7, 1, 3, -6, 6, 8, 4)
msort(nums)( (x: Int, y: Int) => x < y)

val fruits = List("apple", "pinapple", "orange", "banana")
msort(fruits)( (x, y) => x.compareTo(y) < 0)

// scala.math.Ordering[T] works for this too
import math.Ordering
// def msort[T](xs: List[T])(ord: Ordering[T]): List[T] = {

// if (ord.lt(x, y))...

// THIS IS ALL CUMBERSOME - you can make it less so with IMPLICIT PARAMETER

// ****
// def msort[T](xs: List[T])(implicit ord: Ordering[T])