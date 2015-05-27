/**
 * Outputs first 10 cards from Deck after it's been
 * shuffled using Random number generator created
 * using seed supplied as command line argument
 *
 * @author Suzanne Balik
 */
public class CardsFromDeck {

  public static final int MAX_CARDS = 10;

  /**
   * Outputs first 10 cards from Deck after 
   * creating Deck using seed and shuffling it
   * @param arg[0] seed used to create Deck
   */
  public static void main(String[] args) {
  
    if (args.length != 1) {
      System.out.println("Usage: java CardsFromDeck seed");
      System.exit(1);
    }
    int seed = 0;
    try {
      seed = Integer.parseInt(args[0]);
    }
    catch (NumberFormatException e) {
      System.out.println("seed must be an integer");
    }
    
    Deck deck = new Deck(seed);
    deck.shuffle();
    System.out.print("[");
    for (int i = 1; i <= MAX_CARDS; i++) {
      System.out.print(deck.nextCard().toString());
      if (i < MAX_CARDS) {
        System.out.print(", ");
      }
    }
    System.out.println("]");
  }
}
/* SAMPLE OUTPUT -- YOURS MAY DIFFER!
csc% java CardsFromDeck 1
[h3, s12, s9, d13, d12, s5, h11, c5, d5, d9]      
*/    