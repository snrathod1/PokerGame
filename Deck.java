import java.util.Random;
/**
 * Project 6, Poker Game
 * The Deck class represents a deck of 52 playing cards.
 * April 24, 2015
 * @author Shraddha Rathod
 */
public class Deck {
    
    /** integer value to represent the number of cards in the deck */
    public static final int CARDS_IN_DECK = 52;
    /** integer value to represent the number of times cards should switch positions 
     * during the shuffling of the deck 
     */
    public static final int SHUFFLE_SWAPS = 500;
    
    /** array of Card objects that will be used to manage the 
     * CARDS_IN_DECK cards in the deck 
     */
    private Card[] element;
    /** integer that holds the index of the next card to be dealt from the array 
     * of Cards
     */
    private int nextIndex;
    /** integer variable that maintains the random seed provided for testing */
    private int randomseed;
    /** for random number generator */
    private Random numGen;
    
    /**
     * This is the constructor of the class.  
     * initialize an array of Card objects
     * goes through the array one element at a time and actually creates a Card object
     * all cards are created with no dupilcates
     * @param seed
     *            stored in the instance field for the seed
     * @return none
     */
    public Deck(int seed) {
        nextIndex = 0;
        randomseed = seed;
        
        if(seed == -1) {
            numGen = new Random();
        } else {
            numGen = new Random(randomseed);
        }
        
        element = new Card [CARDS_IN_DECK];
        
        int i = 0;
        for(int j = Card.LOWEST_VALUE; j <= Card.HIGHEST_VALUE; j++) {
            element[i] = new Card(j, Card.CLUBS);   
            i++;
        }
        for(int j = Card.LOWEST_VALUE; j <= Card.HIGHEST_VALUE; j++) {
            element[i] = new Card(j, Card.DIAMONDS);   
            i++;
        }
        for(int j = Card.LOWEST_VALUE; j <= Card.HIGHEST_VALUE; j++) {
            element[i] = new Card(j, Card.HEARTS);   
            i++;
        }
        for(int j = Card.LOWEST_VALUE; j <= Card.HIGHEST_VALUE; j++) {
            element[i] = new Card(j, Card.SPADES); 
            i++;
        }
    }
    
    /**
     * This method handles resetting the deck for a new hand to be played 
     * "shuffles" the deck by using the Random class to generate random numbers and
     * swaps them
     * index used for selecting the next card to be dealt from the deck is reset to 0
     * repeats 500 times
     * @param none
     * @return none
     */
    public void shuffle() {
        for(int i = 0; i <= SHUFFLE_SWAPS; i ++) { 
           int randomCard = numGen.nextInt(51);
           int otherRandom = numGen.nextInt(51);
           Card temp = element[randomCard];
           element[randomCard] = element[otherRandom];
           element[otherRandom] = temp;
        }
        
    }
    
     /**
     * This method returns the next card in the deck based on the instance field that 
     * knows the position within the array where the next card to be dealt is located.
     * @param none
     * @return element[nextIndex++]
     */
    public Card nextCard() {
        //nextIndex ++;
        return element[nextIndex++];
    }
    /**
     * This method returns a String representation of the Deck
     * @param none
     * @return String out
     */
    public String toString() {
    String out = "";
        for(int i = 0; i < CARDS_IN_DECK; i ++) {
            out = out + "card " + i + ": " + element[i].toString() + "\n";
        }
        return out;
    }
    /**
     * This is the getter method for the instance field that knows the element of the
     * array.      
     * @param none
     * @return instance field element
     */
    public Card[] getElement() {
        return this.element;
    }
    
    /**
     * This is the getter method for the instance field that knows the next index
     * of the card.      
     * @param none
     * @return instance field nextIndex
     */
    public int getNextIndex() {
        return this.nextIndex;
    }
    
    /**
     * This is the getter method for the instance field randomseed.      
     * @param none
     * @return instance field randomseed
     */
    public int getRandomSeed() {
        return this.randomseed;
    }
    
   /**
     * This method returns whether this Card and o are equal. If o is not a Card, 
     * the method should return false.     
     * @param none
     * @return true if o and Card are equal; false if o and Card aren't equal 
     */
    public boolean equals(Object o) {
        boolean a = false;
        if (o instanceof Deck) {
            Deck other = (Deck) o;
            for(int i = 0; i < CARDS_IN_DECK; i++) {
                if (element[i].equals(other.getElement()[i]) 
                    && getNextIndex() == other.getNextIndex() 
                    && getRandomSeed() == other.getRandomSeed()) {
                    a = true;
                }
                else {
                    return false;
                    }
            }
        } 
        return a;
    }
    
}