// List is a fundamental data structure for functional programming.
// Simple examples:

val fruit = List("apples", "oranges", "pears")
val nums = List(1, 2, 3, 4)
val diag3 = List(List(1,0,0), List(0,1,0), List(0,0,1))
val empty = List()

// difference between List and Array:
// Lists are immutable - elements cannot be changed
// Lists are recursive, arrays are flat.

// What you see above - List(a, b, c) is syntactic sugar that removes
// the constructor operation :: (pronounced cons).
// Without the simplification, they would be written:

val other_fruit = "cherries" :: ( "bananas" :: ( "quinces" :: Nil) )
val so_empty = Nil

// double colon associates to the right, so all those parens are redundant:

val moreNums = 5 :: 6 :: 7 :: 8 :: Nil

// NOTE - this means that :: is a method of the RIGHT HAND OPERATOR,
// not the LEFT HAND like everything else. So complier treats the above as:

Nil.::(4).::(3).::(2).::(1)

// So :: is more or less the same as a PREPEND operator for the right hand arg.

// Example pattern matching of List trait:
// 1 :: 2 :: xs // matches lists that start with 1 and then 2
// x :: Nil // matches lists of length 1
// List(x) // same as x :: Nil
// List() // same as Nil
// List(2 :: xs) // a list that contains as only element another list that starts with 2

