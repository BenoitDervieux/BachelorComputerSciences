

def number_of_2(n):
    divisions = 0
    while(True):
        n /= 2
        print("N is ", n, " for its divison:", divisions)
        if n < 2:
            break
        else :
            divisions += 1
    print(divisions)
    
number_of_2(1)