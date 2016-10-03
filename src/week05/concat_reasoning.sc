// what would it mean to say that a definition of "concat" is "correct"?

// 1. That it is associative:

// (xs ++ ys) ++ zs = xs ++ (ys ++ zs)
// xs ++ Nil = xs
// Nil ++ xs = xs

// How can we prove? With STRUCTURAL INDUCTION

// Reminder: Natural Induction

// To show that property P holds for P(n) for all integers n >= b:
// Show that we have P(b) (base case),
// Then for all ints n >= b show the induction step:
// if one has P(n), then one also has P(n + 1)

// for example, with factorial:

def factorial(n: Int): Int =
  if (n == 0) 1
  else n * factorial(n-1)

// Show that for all n >= 4:
// factorial(n) >=  2^n
// factorial(4) = 24 >= 16 = power(2, 4)
// factorial(n) >= 2^n
// factorial(n +1) == (n + 1) * factorial(n)
// 2 * factorial(n) >= 2 * power(2, n)
// power(2, n + 1)

// STRUCTURAL INDUCTION

// prove that P(xs) for all lists xs:
// show that P(Nil) holds (base case),
// for a list xs and some element x, show the induction step:
// if P(xs) holds, then P(x :: xs) also holds.

// PROVING ASSOCIATIVITY:
// 1. Start with base case:
(Nil ++ ys) ++ zs
ys ++ zs // works

Nil ++ (ys ++ zs)
ys ++ zs // works

// 2. Move to induction step
( (x :: xs) ++ ys) ++ zs
= (x :: (xs ++ ys)) ++ zs
= x :: ((xs ++ ys) ++ zs)
= x :: (xs ++ (ys ++ zs))

(x :: xs) ++ (ys ++ zs)
= x :: (xs ++ (ys ++ zs))


//