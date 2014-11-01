import Data.List

gerakA t
  | even x = rem (4 * t) 100
  | otherwise = 100 - (rem (4 * t) 100)
  where x = div (4 * t) 100

gerakB t
  | even x = 100 - (rem (6 * t) 100)
  | otherwise = rem (6 * t) 100
  where x = div (6* t) 100

-- lim is the number of ketemuan AB
ketemuan lim = helper 0 []
  where helper i res
          | (length res) == lim = res
          | (gerakA i) == (gerakB i) = helper (succ i) ([(gerakA i),i] :res)
          | otherwise = helper (succ i) res

sp_poli = [[34,16],[43,13],[23,12],[20,10],[12,9],[234,5],[34,3],[54,2]]
poli1 = [[43,34],[54,23],[54,19],[45,16],[5,14],[453,12],[34,8],[54,0]] 

polinom x [] = 0
polinom x ((u:v: []) :ps)
  | null ps = u * (x^v)
  | otherwise = (u * (x^v)) + polinom x ps

turunan_suku (x:y: [])
  | y == 0 = [0,0]
  | otherwise = [x*y, (pred y)]

turunan_pol pol = filter (\x -> 0 /= (head x)) all_elmts
  where all_elmts = map turunan_suku pol

turunan i pol = (iterate turunan_pol pol) !! i

