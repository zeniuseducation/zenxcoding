import Data.List

square x = x * x

div' a m = 0 == (rem a m)

fibolist 1 = [1]
fibolist n = helper 2 [1,1]
  where helper i (x:y:xs)
          | i == n = (x:y:xs)
          | otherwise = helper (succ i) ((x+y) :x:y:xs)




