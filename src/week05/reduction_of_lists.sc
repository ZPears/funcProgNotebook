// Reduction of lists

// reduceLeft inserts a given binary operator between adjacent list elements:

// List(x1, ..., xn) reduceList op = (...(x1 op x2) op x3 ... ) op xn

// so for sum:

def sum(xs: List[Int]) = (0 :: xs) reduceLeft ((x, y) => x + y)
def product(xs: List[Int]) = (0 :: xs) reduceLeft ((x, y) => x * y)

// NOTE: underscore notation can be used
// def sum = (0 :: xs) reduceLeft (_ + _)

// FOLDLEFT is the same but takes an accumulator argument so it can handle empty lists

// using foldleft:

def sumFold(xs: List[Int]) = (xs foldLeft 0)(_ + _)
def prodFold(xs: List[Int]) = (xs foldLeft 1)(_ * _)

// also reduceRight and foldRight - they start at last element of the list

// FoldRight and FoldLeft produce the same result IFF the operators are
// associative and commutative

// for example:
def concat[T](xs: List[T], ys: List[T]): List[T] =
(xs foldRight ys) (_ :: _)