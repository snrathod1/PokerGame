import java.util.*;

/**
 * Project 6, Poker Game
 * The Card class runs certain methods to use cards in a java GUI Poker Game.
 * April 24, 2015
 * @author Shraddha Rathod
 */
 
public class Hand {

    /** the number of cards a Hand holds in their hand at a time */
    public static final int CARDS_IN_HAND = 5;

    /* array of Card objects that represents a player's hand
     * Each element in the array is a single Card object
     */
    private Card[] hand;

  /**
   * Counts the number of cards with each value in the hand
   * @return tally array containing number of cards of each value from 2 to 14.
   */
    public int[] getCounts() {
        int[] counts = new int[Card.HIGHEST_VALUE + 1];
        for (int i = 0; i < hand.length; i++) {
            counts[hand[i].getValue()]++;
        }
        return counts;
    }

  /**
   * Creates a copy of the hand sorted first by value, then by suit
   * @return copy of the hand sorted first by value, then by suit
   */
    public Card[] getSortedHand() {
        Card[] sortedHand = Arrays.copyOf(hand, hand.length);
        Arrays.sort(sortedHand);
        return sortedHand;
    }   
      
     /**
     * This is the constructor of the class.  
     * passed an array of Cards to be used as the hand
     * @param Card [] hand
     *            array of cards
     * @throws IllegalArgument Exception if not right number of cards
     * @return none
     */    
    public Hand(Card[] hand) {
        if(hand.length != CARDS_IN_HAND) {
            throw new IllegalArgumentException ("Don't have the right number of cards.");
        }
        for(int i = 0; i < CARDS_IN_HAND; i ++) {
            if(hand[i] == null) {
            throw new NullPointerException();
            }
        }
        this.hand = hand;
    }
    
    /**
     * method returns the card from the hand array at the index specified by the parameter     
     * @param index
     *            position in array
     * @throws IllegalArgument Exception if if index is less than 0 or greater than 
     *                                   or equal to CARDS_IN_HAND
     * @return hand[index]
     */ 
    public Card getCard(int index) {
        if((index < 0) || (index >= CARDS_IN_HAND)) {
            throw new IllegalArgumentException();
        }
        return hand[index];
    }
    
    /**
     * method replaces the card at the given index in the hand array with the card passed 
     * to the method     
     * @param index, card
     *            position and card
     * @throws IllegalArgument Exception if if index is less than 0 or greater than 
     *                                   or equal to CARDS_IN_HAND
     * @throws NullPointerException if card is null
     * @return none
     */
    public void replace(int index, Card card) {
        if((index < 0) || (index >= CARDS_IN_HAND)) {
            throw new IllegalArgumentException();
        }
        if(card == null){
            throw new NullPointerException();
        }
        hand[index] = card;
    }
  
    /**
     * method creates a String representation of the hand     
     * @param none
     * @return String a
     */
    public String toString() {
        String a = "[";
        for(int i = 0; i < CARDS_IN_HAND; i ++) {
            if ( i == (CARDS_IN_HAND - 1)) {
                a += hand[i].toString();
            } else{
                a += hand[i].toString() + ", ";
            }
        }
        a += "]";
        return a;
    }
    
    /**
     * This method returns whether this Card and o are equal. If o is not a Card, 
     * the method should return false.     
     * @param none
     * @return true if o and Card are equal; false if o and Card aren't equal 
     */
    public boolean equals(Object o) {
        boolean a = false;
        getSortedHand();
        int y = 0;
        Card [] sortedHand = getSortedHand();
        if (o instanceof Hand) {
            Hand other = (Hand) o;
            Card [] sortedO = other.getSortedHand();
            for(int i = 0; i < CARDS_IN_HAND; i ++) {
                if(sortedHand[i] == sortedO[i]) {
                    y ++;
                }
            }
        }
        if(y == 5) {
            a = true;
        }  
        return a;  
    }
 
    /**
     * This method sees if all the cards have the same suit
     * @param none
     * @return true if all the cards in the hand have the same suit; false otherwise 
     */
    public boolean isFlush() {
        boolean a  = false;
        char x = hand[0].getSuit();
        int y = 0;
        for(int i = 1; i < CARDS_IN_HAND; i ++) {
            if(hand[i].getSuit() == x) {
                y++;
            }
        }
        if(y == 4) {
            a = true;
        }
        return a;
    
    }
    
    /**
     * This method sees if the values are in sequence
     * @param none
     * @return true if the values of the cards in the hand are in sequence, 
     *         for example, 6, 7, 8, 9 10; false otherwise 
     */
    public boolean isStraight() {  
        boolean a = false;
        Card [] sortedHand = getSortedHand();
        int b = sortedHand[0].getValue();
        int y = 0;
        for(int i = 1; i < CARDS_IN_HAND; i ++) {
            if(sortedHand[i].getValue() == (i + b)) {
                y++;
            }
        }
        if(y == 4) {
            a = true;
        }   
        return a;
        
    }

    /**
     * This method sees if the cards are same suit and in sequence
     * @param none
     * @return true if the cards in the hand have the same suit and are in sequence; 
     *         false otherwise
     */
    public boolean isStraightFlush() {
        boolean a = false;
        boolean b = isFlush();
        boolean c = isStraight();
        if( b == true && c == true) {
            a = true;
        }
        return a;
    }
    
     /**
     * This method sees if the cards in the hand have the same suit and the values 
     * 10, 11, 12, 13, 14
     * @param none
     * @return true if the cards in the hand have the same suit and the values 
     *         10, 11, 12, 13, 14; false otherwise.
     */
    public boolean isRoyalFlush() {
        boolean a = false;
        Card [] sortedHand = getSortedHand();
        boolean b = isFlush();
        if(b == true) {
            if(sortedHand[0].getValue() == 10 
            && sortedHand[1].getValue() == 11 
            && sortedHand[2].getValue() == 12 
            && sortedHand[3].getValue() == 13
            && sortedHand[4].getValue() == 14) {
                a = true;
            }
        }
        return a;
    }
    
    /**
     * This method sees if the hand has four cards with the same value 
     * @param none
     * @return true if the hand has four cards with the same value; false otherwise
     */
    public boolean hasFourOfAKind() {
    
        for (int i = 0; i < CARDS_IN_HAND; i ++) {
            int x = hand[i].getValue();
            int y = 0;
            for (int j = 0; j < CARDS_IN_HAND; j ++) {
                if (hand[j].getValue() == x) {
                    y++;
                }
            } 
            if (y >= 4) {
                return true;
            }
        }
        
        return false;
    
    }
    
    /**
     * This method sees if the hand has exactly three cards with the same value 
     * @param none
     * @return true if the hand has exactly three cards with the same value; 
     *         false otherwise
     */
    public boolean hasThreeOfAKind() {
       for (int i = 0; i < CARDS_IN_HAND; i ++) {
            int x = hand[i].getValue();
            int y = 0;
            for (int j = 0; j < CARDS_IN_HAND; j ++) {
                if (hand[j].getValue() == x) {
                    y++;
                }
            } 
            if (y == 3) {
                return true;
            }
        }
        
        return false;
    }
     
     /**
     * This method sees if the hand has exactly two pairs
     * @param none
     * @return true if the hand has exactly two pairs (two cards with the same value, 
     *         two cards with the same, but a different value from the first pair, and 
     *         a card whose value is not the same as that of either pair); 
     *         false otherwise.
     */
    public boolean hasTwoPairs() {
        boolean a = false;
        int [] counts = getCounts();
        int y = 0;
            for(int i = 0; i < counts.length; i ++) {
                if(counts[i] == 2) {
                    y++;
                }
                if(counts[i] > 2) {
                    y += counts[i]/2;
                }
            }
        if(y == 2) {
            a = true;
        }
        return a;
    }
    
    /**
     * This method sees if the hand contains exactly one pair
     * @param none
     * @return true if the hand contains exactly one pair (two cards with the same value);
     *         false otherwise
     */
    public boolean hasOnePair() {
        boolean a = false;
        int [] counts = getCounts();
        int y = 0;
            for(int i = 0; i < counts.length; i ++) {
                if(counts[i] == 2) {
                    y++;
                }
                if(counts[i] > 2) {
                    y += counts[i]/2;
                }
            }
        if(y == 1) {
            a = true;
        }
        return a;
    
    }
    
    /**
     * This method sees if the hand contains three cards with the same value and two 
     * other cards with the same value
     * @param none
     * @return true if the hand contains three cards with the same value and two other 
     *         cards with the same value, but different from that of the three cards, 
     *         for example, [c2, d2, c5, d5, h5]; false otherwise.
     */
    public boolean isFullHouse() {
        boolean a = false;
        int [] counts = getCounts();
        int y = 0;
        int z = 0;
            for(int i = 0; i < counts.length; i ++) {
                if(counts[i] == 2) {
                    y++;
                }
            }
            for(int i = 0; i < counts.length; i ++) {
                if(counts[i] == 3) {
                    z++;
                }
            }
        if(y == 1 && z == 1) {
            a = true;
        }
        return a;
    
    }

}