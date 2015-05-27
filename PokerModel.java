/**
 * Project 6, Poker Game
 * The PokerModel class puts together the game.
 * April 24, 2015
 * @author Shraddha Rathod
 */
public class PokerModel {
    
    /** Indicates that a random game should be played, set to -1 */
    public static final int RANDOM_GAME = -1;
    /** The number of cards in a hand, set to 5. */
    public static final int CARDS_IN_HAND = 5;
    /** The number of points that the player has when the game begins, set to 100 */
    public static final int STARTING_POINTS = 100;
    /** The number of points needed to play a new game, set to 10 */
    public static final int POINTS_FOR_NEW_GAME = 10;
    /** The number of points awarded for a Royal Flush, set to 100 */
    public static final int ROYAL_FLUSH = 100;
    /** The number of points awarded for a Straight Flush, set to 60 */
    public static final int STRAIGHT_FLUSH = 60;
    /** The number of points awarded for Four of a Kind, set to 50 */
    public static final int FOUR_OF_A_KIND = 50;
    /** The number of points awarded for a Full House, set to 40 */
    public static final int FULL_HOUSE = 40;
    /** The number of points awarded for a Flush, set to 30 */
    public static final int FLUSH = 30;
    /** The number of points awarded for a Straight, set to 25. */
    public static final int STRAIGHT = 25;
    /** The number of points awarded for Three of a Kind, set to 15 */
    public static final int THREE_OF_A_KIND = 15;
    /** The number of points awarded for Two Pairs, set to 10 */
    public static final int TWO_PAIRS = 10;
    /** The number of points awarded for One Pair, set to 7 */
    public static final int ONE_PAIR = 7;
    
    /** This is the Deck of cards that will be used to play the game */
    private Deck deck;
    /** This is the Hand of Cards in the game */
    private Hand hand;
    /** This is the current number of points */
    private int points;
    
    
    /**
     * This method creates a deck using the seed.
     * Sets points to STARTING_POINTS
     * @param seed
     * @return points
     */
    public PokerModel(int seed) {
        points = STARTING_POINTS;
        deck = new Deck(seed); 
    }
    
     /**
     * This method returns the number of points
     * @param none
     * @return points
     */
    public int getPoints() {
        return points;
    
    }
    
    /**
     * This method gets the name of the image file
     * @param int index
     * @return a : the name of the image file for the Card at the given index in the hand
     */
    public String getCardFileName(int index) {
        String a = "cards/";
        a += hand.getCard(index).toString();
        a += ".gif";
        return a;
    
    }
    
    /**
     * This method gives the card at an index
     * @param int index
     * @return hand.getCard(index) : the card at the given index in the hand
     */
    public Card getCard(int index) {
        return hand.getCard(index);
    
    }
    
    /**
     * This method Subtracts POINTS_FOR_NEW_GAME from points and shuffles the deck. 
     * A new array of CARDS_IN_HAND Cards is created and filled by calling the deck 
     * nextCard() method CARDS_IN_HAND times. The Card array is used to create a new 
     * Hand.
     * @param none
     * @return none
     */
    public void newGame() {
        points = points - POINTS_FOR_NEW_GAME;
        deck.shuffle();
        Card [] array = new Card [CARDS_IN_HAND];
        for (int i = 0; i < CARDS_IN_HAND; i ++) {
            array[i] = deck.nextCard();
        }        
        hand = new Hand(array);
    }
    
    /**
     * This method gets the next Card from the deck and requests the hand to replace 
     * the card at the given index with the new Card
     * @param int index
     * @return none
     */
    public void replaceCard(int index) {
        hand.replace(index, deck.nextCard());
    
    }
    
    /**
     * This method the String with results and adds the correct number of points to the 
     * total points based on the type of hand
     * @param none
     * @return String with results
     */
    public String scoreHand() {
        boolean a = hand.isRoyalFlush();
        boolean b = hand.isStraightFlush();
        boolean c = hand.hasFourOfAKind();
        boolean d = hand.isFullHouse();
        boolean e = hand.isFlush();
        boolean f = hand.isStraight();
        boolean g = hand.hasThreeOfAKind();
        boolean h = hand.hasTwoPairs();
        boolean i = hand.hasOnePair();
        
        String result = "";
        
        if(a == true) {
            points += ROYAL_FLUSH;
            result = "Royal Flush";
        } else if(b == true) {
            points += STRAIGHT_FLUSH;
            result = "Straight Flush";
        } else if(c == true) {
            points += FOUR_OF_A_KIND;
            result = "Four of a Kind";
        } else if(d == true) {
            points += FULL_HOUSE;
            result = "Full House";
        } else if(e == true) {
            points += FLUSH;
            result = "Flush";
        } else if(f == true) {
            points += STRAIGHT;
            result = "Straight";
        } else if(g == true) {
            points += THREE_OF_A_KIND;
            result = "Three of a Kind";
        } else if(h == true) {
            points += TWO_PAIRS;
            result = "Two Pairs";
        } else if(i == true) {
            points += ONE_PAIR;
            result = "One Pair";
        } else {
            result = "No Pair";
        }
        return result;
    }
}