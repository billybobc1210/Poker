# Poker hand evaluator

This is a program for evaluating a standard five-card poker hand. 

There are choose(52,5), or 2598960, ways to choose 5 cards from a standard
deck of 52 cards.  But, with regards to poker, not all of those hands are 
distinct.  For example the hands:

Ac Ah 8s 7h 2c

and 

As Ad 8c 7c 2d

are of equal rank as a standard five-card poker hand.  In fact, all of those 
2598960 hands can be reduced to 7462 distinct poker rankings.  

This code takes any five-card poker hand and determines its rank in a range
of 0-7461 so that it can be compared to the rank of any other five-card poker
hand to determine which is the better hand.