// Extended Backus-Naur Form (EBNF):

// | denotes alternative,
// [...] an option (0 or 1),
// {...} a repetition (0 or more).

// Type = SimpleType | FunctionType
// FunctionType = SimpleType '=>' Type | '(' [Types] ')' '=>' Type
// SimpleType = Identifier
// Types = Type {',', Type}

// Another way to put it:
// A type can be:
// A NUMERIC TYPE: Int, Double, (and Byte, Short, Char, Long, Float) - a PRIMITIVE,
// BOOLEAN TYPE with values true and false,
// STRING type,
// FUNCTION type, line Int => Int, (Int, Int) => Int

// EXPRESSION TYPES:

// identifier : x, isGoodEnough, etc.
// literal : 0, 1, 1.0, "abc"
// function application : sqrt(x), isGoodEnough(x), etc.
// operator application : -x, y + x, etc.
// selection : math.abs
// conditional expression : if (x > 0) x else -x
// block : { val x = math.abs(y); x * 2 }
// anonymous function : x => x + 1

// DEFINITIONS:

// Def = FunDef | ValDef
// FunDef = def identifier {'(' [Parameters] ')'} [':' Type] '=' Expression
// ValDef = val ident [':' Type] '=' Expression
// Parameter = identifier ':' [ '=>' ] Type
// Parameters = Parameter {',' Parameter}

// the optional '=>' in parameter makes it CALL BY NAME