class Flower:
   
    def __init__(self, name, number_of_petals, price):
        self._name = name
        self._number_of_petals = number_of_petals
        self._price = price
    def __get_name__(self):
        return self._name
    def __set_name__(self, name):
        self._name = name
    
    def __get_number_of_petals__(self):
        return self._number_of_petals
    def __set_number_of_petals__(self, number_of_petals):
        self._number_of_petals = number_of_petals
        
    def __get_price__(self):
        return self._price
    def __set_price__(self, price):
        self._price = price


flo = Flower("Habitus", 10, 10.99)
print(flo.__get_name__())
print(flo.__get_number_of_petals__())
print(flo.__get_price__())
flo.__set_name__("Michel")
flo.__set_number_of_petals__(1000000)
flo.__set_price__(800.99)
print(flo.__get_name__())
print(flo.__get_number_of_petals__())
print(flo.__get_price__())
    
    