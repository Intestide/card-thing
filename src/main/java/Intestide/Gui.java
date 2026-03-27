package Intestide;

import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Gui extends JFrame {

  private Game game; // The game board (ElevensBoard)
  private JButton[] cardButtons; // One button per card on the table
  private boolean[] selected; // true = this card is clicked/selected
  private JPanel cardPanel; // The grid that holds the 9 card buttons

  /**
   * Constructor - sets up the entire GUI window Students: You do NOT need to
   * change anything in the constructor.
   */
  public Gui(Game game) {
    this.game = game;
    this.cardButtons = new JButton[game.getBoardSize()];
    this.selected = new boolean[game.getBoardSize()];

    setTitle("Elevens Game");
    setSize(800, 700);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create the 3x3 grid for the cards
    cardPanel = new JPanel();
    cardPanel.setLayout(new GridLayout(3, 3, 10, 10));

    // Create 9 clickable card buttons
    for (int i = 0; i < game.getBoardSize(); i++) {
      cardButtons[i] = createCardButton(i);
      cardPanel.add(cardButtons[i]);
    }

    add(cardPanel, BorderLayout.CENTER);

    // Bottom panel with "Replace Selected" and "Restart Game" buttons
    JPanel controlPanel = new JPanel();
    controlPanel.setLayout(new FlowLayout());

    JButton replaceBtn = new JButton("Replace Selected");
    replaceBtn.addActionListener(e -> replaceCards());
    controlPanel.add(replaceBtn);

    JButton restartBtn = new JButton("Restart Game");
    restartBtn.addActionListener(e -> restartGame());
    controlPanel.add(restartBtn);

    add(controlPanel, BorderLayout.SOUTH);

    updateDisplay(); // Show the first 9 cards
    setVisible(true);
  }

  // =====================================================================
  // HELPER METHOD - creates one clickable card button
  // Students: You do NOT need to change this method.
  // =====================================================================
  private JButton createCardButton(final int index) {
    JButton btn = new JButton();
    btn.setPreferredSize(new Dimension(100, 140));
    btn.addActionListener(e -> toggleSelection(index));
    return btn;
  }

  // =====================================================================
  // STUDENT LOGIC 1: toggleSelection
  // =====================================================================
  private void toggleSelection(int index) {
    // TODO: Student task
    // Think:
    // 1. When a student clicks a card, we should flip its selection state.
    // 2. selected[index] is currently false → make it true (or true → false)
    // 3. After changing it, refresh the screen so the red border
    // appears/disappears.
    //
    // Hint: one line to flip + one line to call updateDisplay()
    selected[index] = !selected[index];
    updateDisplay();
  }

  private void updateDisplay() {
    Cards[] cards = game.getCards();
    for (int i = 0; i < cards.length; i++) {
      if (cards[i] != null) {
        try {
          ImageIcon icon = new ImageIcon(ImageIO.read(
              new File("src/images/" + cards[i].getImageFile()))
              .getScaledInstance(100, 140, Image.SCALE_SMOOTH));
          cardButtons[i].setIcon(icon);
          cardButtons[i].setText("");
        } catch (Exception e) {
          cardButtons[i].setText(cards[i].toString());
          cardButtons[i].setIcon(null);
        }
      } else {
        cardButtons[i].setIcon(null);
        cardButtons[i].setText("Empty");
      }

      // Show red border if selected
      if (selected[i]) {
        cardButtons[i].setBorder(BorderFactory.createLineBorder(Color.RED, 5));
      } else {
        cardButtons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      }
    }
  }

  // =====================================================================
  // STUDENT LOGIC 2: replaceCards (main game logic)
  // =====================================================================
  private void replaceCards() {
    // TODO: Student task - this is the most important method!
    // Think step by step:
    // Step 1: Count how many cards are currently selected
    // (loop through selected[] array and count trues)
    //
    // Step 2: If zero cards selected → show message "Please select cards to
    // replace!" and return
    //
    // Step 3: Create an int[] selectedIndexes that contains only the positions of
    // selected cards
    // (example: if slots 2 and 5 are selected → {2,5})
    //
    // Step 4: Ask the board if this is a legal move:
    List<Cards> selectedCards = new ArrayList<>();
    List<Integer> selectedIndexes = new ArrayList<>();
    for (int i = 0; i < selected.length; i++) {
      if (selected[i]) {
        selectedCards.add(game.getCards()[i]);
        selectedIndexes.add(i);
      }
    }
    if (game.check(selectedCards.toArray(new Cards[selectedCards.size()]))) {
      System.out.println("e");
      for (int i = selectedCards.size() - 1; i >= 0; i--) {
        selected[selectedIndexes.get(i)] = false;
      }
      game.fillSpot(selectedIndexes.stream().mapToInt(Integer::intValue).toArray());
    }else {
      JOptionPane.showMessageDialog(this, "Illegal move");
    }

    updateDisplay();

  }

  // =====================================================================
  // STUDENT LOGIC 3: restartGame
  // =====================================================================
  private void restartGame() {
    // TODO: Student task (only these 2 lines)
    // 1. Create a completely new board (this shuffles and deals new cards)
    // board = new ElevensBoard();
    //
    // 2. Clear all selections

    // =================================================================
    // GUI CODE BELOW IS ALREADY WRITTEN FOR YOU (do NOT change or comment out)
    // =================================================================
    // Rebuild the 9 card buttons with the new board
    game.restart();
    for (int i = 0; i < selected.length; i++) {
      selected[i] = false;
    }
    cardPanel.removeAll();
    for (int i = 0; i < game.getBoardSize(); i++) {
      cardButtons[i] = createCardButton(i);
      cardPanel.add(cardButtons[i]);
    }
    cardPanel.revalidate();
    cardPanel.repaint();

    updateDisplay();
    JOptionPane.showMessageDialog(this, "New Game Started!");
  }
}
