"""Draw the recursion trace for the computation of power(2,5), using the
traditional function implemented in Code Fragment 4.11.
"""

"""" For power(2, 5)

power(2, 5)
  --> power(2, 4)
    --> power(2, 3)
        --> power(2, 2)
            --> power(2, 1)
                ***comes back***
                    --> return 2 * 1
                        --> return 2 * 2
                            --> return 2 * 4
                                --> return 2 * 8
                                    --> return 2 * 16        
"""