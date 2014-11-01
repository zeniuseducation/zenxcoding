import Data.List

-- Operators +/-/*///div/mod
-- Boolean operators && ||
-- Algebraic resulting bool ==, /=, <, >

-- identity => (function, value)

my_pi = 3.14

-- function => f(x) = x^2 + x
-- f(x,y) = x^2 + y^2

f x = x^2 + x

square x = x * x

cube x = x * square x

exp4 x = (square x) * (square x)

-- f(x) => (f x)

rumus_kecap a b c = (-b + (sqrt d))/ (2*a)
  where d = (b^2) - (4*a*c)

-- global scope & local/lexical scope

abs' x = if x >= 0
         then x
         else -x

-- function y = | x - 4 |

abs'' x
  | x >= 0 = x
  | otherwise = -x

pita n = [[a,b,c] | a <- [1..n], b <- [1..n],
          c <- [1..n], a^2 + b^2 == c^2]

even' n = 0 == (rem n 2)

faktorial n
  | n == 0 = 1
  | otherwise = n * (faktorial $ pred n)

prime' n
  | n <= 1 = False
  | n == 2 = True
  | even n = False
  | otherwise = null [x| x <- [3,5..miras], mod n x == 0]
  where miras = ceiling $ sqrt $ fromIntegral n





