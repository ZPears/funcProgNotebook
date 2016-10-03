// Consider the reverse function (inefficient definition):

Nil.reverse = Nil
(x :: xs).reverse = xs.reverse ++ List(x)

// prove the following proposition:

xs.reverse.reverse == xs

// by induction on xs.

// 1. Base case is easy:

Nil.reverse.reverse
= Nil.reverse // by first clause of reverse
= Nil // by first clause of reverse

// 2. Induction step:

(x :: xs).reverse.reverse
= (xs.reverse ++ List(x)).reverse // by 2nd clause of reverse
// this approach doesn't work very well - try using right hand side

x :: xs
= x :: xs.reverse.reverse
// still doesn't work - both sides simplify to different expressions
// still need to show that

(xs.reverse ++ List(x)).reverse = x :: xs.reverse.reverse

// are the same. Create a new lemma by replacing xs.reverses with arbitrary list ys:

(xs.reverse ++ List(x)).reverse = x :: xs.reverse.reverse

(ys ++ List(x)).reverse = x :: ys.reverse

// 1. Base case
(Nil ++ List(x)).reverse = x :: Nil.reverse
= List(x).reverse
= (x :: Nil).reverse
  = Nil.reverse ++ List(x)
= Nil ++ (x :: Nil)
= x :: Nil

// 2. Inductive Step
( (y :: ys) ++ List(x)).reverse
= (y :: (ys ++ List(x))).reverse // by 2nd clause of ++
= (ys ++ List(x)).reverse ++ List(y) // by 2nd clause of reverse
= (x :: ys.reverse) ++ List(y) // by the induction hypothesis
= x :: (ys.reverse ++ List(y)) // by 1st clause of ++
= x :: (y :: ys).reverse  // by 2nd clause of reverse

// CHALLENGE:

// prove the following distribution law:
(xs ++ ys) map f = (xs map f) ++ (ys map f)

// need to use clauses of ++, as well as following clauses for map:
Nil map f = Nil
(x :: xs) map f = f(x) :: (xs map f)