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

The work horse of this program is the class:

PokerHand.kt

In this class, the Kotlin 'init' method gets called statically on the first 
instantiation of the class.  This method then initializes the static hash map variable 
'handRankMap'.  The keys of this map will be a string representation of a
poker hand.  The format of the key is the numeric rank of each card in the 
hand sorted in descending order, with the string 'SUITED' possibly appended, depending
on if all the cards in the hand have the same suit or not.  This string key is then 
mapped to an integer value in the range of 0-7461 representing its poker hand rank.  

Some examples:

handRankMap["7 5 4 3 2"] = 0                 // lowest possible hand in poker: 7 high with 5/4/3/2 kickers  
...  
handRankMap["13 13 13 3 2"] = 5721           // three-of-a-kind: 3 Kings with 3/2 kickers  
...  
handRankMap["9 7 6 3 2 SUITED"] = 5890       // flush: 9 high with 7/6/3/2 kickers  
...  
handRankMap["14 14 14 13 13"] = 7295         // full house: Aces full of Kings  
...  
handRankMap["14 14 14 14 13"] = 7451         // four of a kind: four Aces with a King kicker  
...  
handRankMap["14 13 12 11 10 SUITED] = 7461   // highest possible hand in poker: royal flush  
 
This strategy makes hand-evaluation very fast, since these relative ranks will only ever be 
calculated once up front, and then to calculate the rank of any particular hand all that needs 
to be done is to convert the hand to its string representation and look up it's rank in the 
'handRankMap' map.