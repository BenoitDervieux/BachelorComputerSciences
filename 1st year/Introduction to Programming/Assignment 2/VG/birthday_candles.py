# --------------------------------------------------------------------------
# Birthday
# File:        borthday_candles.py
# Description:
#   Counts the number of boxes of candles we'll need to
#   buy depending on the age
#
# History:     19 Sept 2022
#
# ---------------------------------------------------------------------------

number_of_candles = 0
# We declare the variable for the number of cadles we need
boxes_to_buy = 0
# we declare the variable for the number of boxes we'll have
# to buy
total_boxes = 0
# we declare the total number of boxes we will need


for age in range(1, 101):
    while number_of_candles < age:
        # Every birthday we check our age and if we have enough
        # candles
        # if we don't have enough candles

        boxes_to_buy += 1
        # we need more boxes

        total_boxes += 1

        number_of_candles += 24
        # each box more is 24 candles more
        # we loop and add up until we have
        # enough candles according to our age

    if number_of_candles >= age and boxes_to_buy > 0:
        # if we have enough candles and bought a box we print it
        # otherwise we continue to loop if we didn't buy a box

        print("Before birthday {0}, buy {1} box(es)".format(age, boxes_to_buy))

        number_of_candles = number_of_candles - age
        # we then substract the number of candles by our age

        boxes_to_buy = 0
        # we start over with the numbers of boxex to buy

    else:
        number_of_candles = number_of_candles - age
        # Otherwise we just substract the number of candles
        # and don't print

print()
# we print out the total number of boxes needed
# and the number of candles remaining
print('Total number of boxes:', total_boxes, end=', ')
print('Remaining candles:', number_of_candles)
