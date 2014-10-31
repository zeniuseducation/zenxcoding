import Data.List

gerakA t
  | even x = rem (4 * t) 100
  | otherwise = 100 - (rem (4 * t) 100)
  where x = div (4 * t) 100

gerakB t
  | even x = 100 - (rem (6 * t) 100)
  | otherwise = rem (6 * t) 100
  where x = div (6* t) 100

ketemuan lim = helper 0 []
  where helper i res
          | (length res) == lim = res
          | (gerakA i) == (gerakB i) = helper (succ i) ([(gerakA i),i] :res)
          | otherwise = helper (succ i) res

sp_poli = [[34,16],[43,13],[23,12],[20,10],[12,9],[234,5],[34,3],[54,2]]

polinom x [] = 0
polinom x ((u:v: []) :ps)
  | null ps = u * (x^v)
  | otherwise = (u * (x^v)) + polinom x ps


