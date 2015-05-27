/**
 * Project 6, Poker Game
 * The Card class runs certain methods to use cards in a java GUI Poker Game.
 * April 24, 2015
 * @author Shraddha Rathod
 */
public class Card implements Comparable<Card> {
    /** sets constant symbol for clubs */
    public static final char CLUBS = 'c';
    /** sets constant symbol for diamonds */
    public static final char DIAMONDS = 'd';
    /** sets constant symbol for spades */
    public static final char SPADES = 's';
    /** sets constant symbol for hearts */
    public static final char HEARTS = 'h';
    /** sets constant for lowest card value */
    public static final int LOWEST_VALUE = 2;
    /** sets constant for highest card value */
    public static final char HIGHEST_VALUE = 14;
    
    /** value of the card */
    private int value;
    /** suit of the card */
    private char suit;
  /**
     * This method is used for sorting the cards in a player's hand in a game of
     * Poker. Cards are sorted first by value, then by suit.
     * 
     * @param other
     *            The Card object to which this Card is being compared.
     * @return negative value if this Card should be before the other Card,
     *         positive value if this Card should be after the other Card.
     */
    
    public int compareTo(Card other) {
        if (this.value != other.value) {
            return this.value - other.value;
        } else {
            return this.suit - other.suit;
        }
    }
    /**
     * This is the constructor of the class.  
     * @param value, suit
     *            Value and suit to assign to the instance fields
     * @throw IllegalArgumentException if not in range
     * @throw IllegalArgumentException if suit not valid
     * @return none
     */
    public Card(int value, char suit) {
        if(value < LOWEST_VALUE || value > HIGHEST_VALUE) {
            throw new IllegalArgumentException ("Card value is not in range.");
        }
        if(suit != 'c' && suit != 'd' && suit != 's' && suit != 'h') {     
            throw new IllegalArgumentException ("Card suite is not valid.");      
        }  
        else {
            this.value = value;
            this.suit = suit;
        }
    }
    /**
     * This is the getter method for the instance field that knows the suit of the card. 
     * 
     * @param none
     * @return instance field suit
     */
    public char getSuit() {
        return this.suit;
    
    }
    /**
     * This is the getter method for the instance field that knows the value of the card.      
     * @param none
     * @return instance field value
     */
    public int getValue() {
        return this.value;
        
    }
    /**
     * This method returns the the first letter of the suit (lowercase) followed by 
     * the numeric value of the card.      
     * @param none
     * @return suit and value of card
     */
    public String toString() {
        String v = String.valueOf(this.value);
        return this.suit + v;
    }
    /**
     * This method returns whether this Card and o are equal. If o is not a Card, 
     * the method should return false.     
     * @param none
     * @return true if o and Card are equal; false if o and Card aren't equal 
     */
    public boolean equals(Object o) {
        boolean a = false;
        if (o instanceof Card) {
            Card other = (Card) o;
            if((getSuit() == other.getSuit()) && (getValue() == other.getValue())) {
                a = true;
            }
        } 
        return a;
    }
}
    
    