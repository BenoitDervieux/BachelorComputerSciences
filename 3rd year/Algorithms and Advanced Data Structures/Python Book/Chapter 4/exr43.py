""" Draw the recursion trace for the computation of power(2,18), using the
repeated squaring algorithm, as implemented in Code Fragment 4.12
"""

""" 
power(2, 18)
    --> power(2, 9)
        --> power(2 , 4)
            --> power(2, 2)
                --> power(2 , 1)
                    --> power(2 , 0)
                        *** Comes back *** - 6 calls
                            --> returns 1
                                power of 1 so result * 2
                                1 * 2 = 2
                                --> return 2
                                    result = 2 * 2 = 4
                                    power of 2
                                    --> return 4
                                        power of 4
                                        so result = 4 * 4 = 16       
                                        --> returns 16
                                            power of 9
                                            result = 16 * 16 = 256
                                            result = 16 * 2 = 512
                                            --> returns 512
                                                power of 18
                                                result = 512 * 512 = 262144
                                                --> returns 262144
"""