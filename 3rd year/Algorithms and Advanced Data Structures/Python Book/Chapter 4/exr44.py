""" 
Draw the recursion trace for the execution of function reverse(S, 0, 5)
(Code Fragment 4.10) on S = [4, 3, 6, 2, 6]
"""
"""
 reverse([4,3,6,2,6])
    start = 0 < stop = 5 - 1 = 4
    it reverses, new list = [6,3,6,2,4]
    reverse([6,3,6,2,4], 1, 4)
        start = 1 < stop = 4 - 1 = 3
        it reverses, new list = [6,2,6,3,4]
        reverse([6,2,6,3,4], 2, 3)
            start = 2, stop - 1 = 2
            return [6,2,6,3,4]
"""
