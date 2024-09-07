#exercise 2.5
#exercise 2.6
#exercise 2.7

class CreditCard:
    # ”””A consumer credit card.”””

    def __init__(self, customer, bank, acnt, limit, balance = 0):
        """Create a new credit card instance.
            The initial balance is zero.
            customer the name of the customer (e.g., John Bowman )
            bank the name of the bank (e.g., California Savings )
            acnt the acount identifier (e.g., 5391 0375 9387 5309 )
            limit credit limit (measured in dollars)"""
        self.customer = customer
        self.bank = bank
        self.account = acnt
        self.limit = limit
        self.balance = balance
    
    def get_customer(self):
        "”””Return name of the customer.”””"
        return self.customer

    def get_bank(self):
        "”””Return the bank s name.”””"
        return self.bank

    def get_account(self):
        "”””Return the card identifying number (typically stored as a string).”””"
        return self.account

    def get_limit(self):
        "”””Return current credit limit.”””"
        return self.limit

    def get_balance(self):
    # ”””Return current balance.”””
        return self.balance
    def charge(self, price):
        """”””Charge given price to the card, assuming sufficient credit limit.

        Return True if charge was processed; False if charge was denied.
        ”””"""
        if not isinstance(price, (int, float)):
            raise ValueError('Parameter should be a number')
        if price + self. balance > self. limit: # if charge would exceed limit,
            print("Sorry can't charge")
            return False # cannot accept charge
        else:
            self.balance += price
        return True

    def make_payment(self, amount):
        "”””Process customer payment that reduces balance.”””"
        if not isinstance(amount, (int, float)):
            raise ValueError('Parameter should be a number')
        if amount < 0:
            raise ValueError('Amount must be positive')
        self.balance -= amount

credit1 = CreditCard("Benoit", "LBP", '8954 4586 4586 7458', 1000)
# credit1.charge('aloirs')
# credit1.make_payment('alors')
# credit1.make_payment(-54)
print('Balance 1', credit1.get_balance())
credit2 = CreditCard("Benoit", "LBP", '8954 4586 4586 7458', 1000, 2500)
print('Balance 2', credit2.get_balance())