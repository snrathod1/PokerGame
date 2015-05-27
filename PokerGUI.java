import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Video Poker Game Graphical User Interface
 * @author Dan Longo
 * @author Suzanne Balik
 *
 */
public class PokerGUI extends JFrame implements ActionListener {

  public static final int WIDTH = 420;
  public static final int HEIGHT = 315;
  public static final int X = 100;
  public static final int Y = 100;
  public static final int FONT_SIZE = 15;
  public static final int TEXT_WIDTH = 10;

  /** Displays number of points*/
  private JLabel lblPoints;
  
  /** Displays type of hand*/
  private JTextField txtScore;

  /** Cards to be displayed*/
  private JLabel[] cards;
  
  /** Icons (images) for cards*/
  private ImageIcon[] icons;
  
  /** Replace buttons for cards*/
  private JButton[] btnCards;

  /** Score Hand button*/
  private JButton btnScoreHand;

  /** New Game button*/
  private JButton btnNewGame;

  /** Quit button*/
  private JButton btnQuit;

  /** Poker game model*/
  private PokerModel pm;

  /**
   * Creates instance of PokerGUI class
   * @param seed if -1, a random game is played, otherwise the same game is played, in that
   * the deck will be shuffled the same way, whenever the seed is the same.
   */
  public PokerGUI(int seed) {

    pm = new PokerModel(seed);

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(WIDTH, HEIGHT);
    setLocation(X, Y);
    setTitle("Video Poker");
    Container c = getContentPane();

    JPanel pnlScore = new JPanel();
    pnlScore.setLayout(new GridLayout(1,2));
    lblPoints = new JLabel("Points: "+ pm.getPoints());
    lblPoints.setFont(new Font("SansSerif",Font.BOLD,FONT_SIZE));
    txtScore = new JTextField("",TEXT_WIDTH); 
    txtScore.setFont(new Font("SansSerif",Font.BOLD,FONT_SIZE)); 
    txtScore.setHorizontalAlignment(JTextField.CENTER);
    txtScore.setEditable(false);
    txtScore.setBackground(Color.WHITE);
    txtScore.setText("Press New Game to Start!");
    pnlScore.add(lblPoints);
    pnlScore.add(txtScore);


    JPanel pnlCards = new JPanel();
    pnlCards.setLayout(new GridLayout(2,PokerModel.CARDS_IN_HAND));
    cards = new JLabel[PokerModel.CARDS_IN_HAND];     
    icons = new ImageIcon[PokerModel.CARDS_IN_HAND];
    btnCards = new JButton[PokerModel.CARDS_IN_HAND];  

    for(int i = 0; i < PokerModel.CARDS_IN_HAND; i++) {

      icons[i] = new ImageIcon("cards/h" + (i + 10) + ".gif");
      cards[i] = new JLabel();
      cards[i].setIcon(icons[i]);
      pnlCards.add(cards[i]);

    }

    for(int i = 0; i < PokerModel.CARDS_IN_HAND; i++) {

      btnCards[i] = new JButton("Replace");
      btnCards[i].setBackground(Color.RED);
      btnCards[i].addActionListener (this);
      btnCards[i].setEnabled(false);
      pnlCards.add(btnCards[i]);

    }

    JPanel pnlButtons = new JPanel();
    pnlButtons.setLayout(new GridLayout(1,3));
    btnScoreHand = new JButton("Score Hand");
    btnScoreHand.setFont(new Font("SansSerif",Font.BOLD,FONT_SIZE));
    btnScoreHand.addActionListener(this);
    btnScoreHand.setEnabled(false);
    btnNewGame = new JButton("New Game");
    btnNewGame.setFont(new Font("SansSerif",Font.BOLD,FONT_SIZE));
    btnNewGame.addActionListener(this);
    btnQuit = new JButton("Quit");
    btnQuit.setFont(new Font("SansSerif",Font.BOLD,FONT_SIZE));
    btnQuit.addActionListener (this);
    pnlButtons.add(btnScoreHand);
    pnlButtons.add(btnNewGame);
    pnlButtons.add(btnQuit);

    c.add(pnlScore,BorderLayout.NORTH);
    c.add(pnlCards,BorderLayout.CENTER);
    c.add(pnlButtons,BorderLayout.SOUTH);

    setVisible(true);
  }

  /**
   *  Executes action based on event
   *  @param e event (button press, etc.)
   */
  public void actionPerformed(ActionEvent e) {

    if(e.getSource() == btnNewGame) {

      txtScore.setText("");

      pm.newGame();

      btnScoreHand.setEnabled(true);

      for(int i = 0; i < PokerModel.CARDS_IN_HAND; i++) {
        icons[i] = new ImageIcon(pm.getCardFileName(i));
        cards[i].setIcon(icons[i]);
        btnCards[i].setEnabled(true);
      }

      lblPoints.setText("Points: " + pm.getPoints());
    }

    else if(e.getSource() == btnScoreHand) {
      for(int i = 0; i < PokerModel.CARDS_IN_HAND; i++) {
        btnCards[i].setEnabled(false);
      }

      btnScoreHand.setEnabled(false);

      for(int i = 0; i < 5; i++) {
        icons[i] = new ImageIcon(pm.getCardFileName(i));
        cards[i].setIcon(icons[i]);
      }
      txtScore.setText(pm.scoreHand());
      lblPoints.setText("Points: " + pm.getPoints());
    }
    else if (e.getSource() == btnQuit) {
      System.exit(1);
    }
    else {
      for (int i = 0; i < PokerModel.CARDS_IN_HAND; i++) {
        if(e.getSource() == btnCards[i]) {
          pm.replaceCard(i);
          btnCards[i].setEnabled(false);
        }
      }
    }
  }

  /**
   * Starts up Video Poker game
   * @param args[0] optional seed used for testing which determines how the deck is shuffled
   */
  public static void main(String[] args) {

    if (args.length == 1) {
      try {
        new PokerGUI(Integer.parseInt(args[0]));
      } catch (NumberFormatException e) {
        System.out.println("USAGE: java PokerGUI  seed");
      }
    } else if (args.length == 0) {
      new PokerGUI(PokerModel.RANDOM_GAME);
    } else {
      System.out.println("USAGE: java PokerGUI seed");
    }
  }
}