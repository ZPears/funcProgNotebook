// List Functions

val xList = 1 :: 2 :: 3 :: 4 :: Nil

xList.length

xList.last

xList.init

xList take 2

xList drop 2

xList(2)

// Creating new lists

val yList = 5 :: 6 :: 7 :: 8 :: Nil

xList ++ yList

xList.reverse

// NOTE: because lists are immutable, updated creates a new list
xList updated (3, 6)

xList indexOf 2

xList contains 2

// example implementation of last

def last[T](xs: List[T]): T = xs match {
  case List() => throw new Error("last of empty list")
  case List(x) => x
  case y :: ys => last(ys)
}

// implementation of init
def init[T](xs: List[T]): List[T] = xs match {
  case List() => throw new Error("Cannot manage empty list")
  case List(x) => List()
  case x :: xs => x :: init(xs)
}

// concat implementation
def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
  case List() => ys
  case z :: zs => z :: concat(zs, ys)
}
// needs call for each element of xs - so complexity is |xs|

// reverse implemenation
def reverse[T](xs: List[T]): List[T] = xs match {
  case List() => xs
  case y :: ys => reverse(ys) ++ List(y)
}

// remove implementation
def removeAt[T](n: Int, xs: List[T]): List[T] = (xs take n) ::: (xs drop n+1)