package Intestide;

import java.util.Random;

public class Game {
  public static Cards[] deck = new Cards[52];
  public static Cards[] selected = new Cards[3];
  public static Cards[] grid = new Cards[9];

  public Game() {
    shuffle();
    deal();
  }

  private void deal() {
    for (byte i = 0; i < 9; i++) {
      grid[i] = deck[i];
    }
  }
  public void shuffle() {
    for (byte i = 0; i < 52; i++) {
      deck[i] = new Cards(i);
    }
    Random rand = new Random();
    for (int i = 51; i > 0; i--) {
      int j = rand.nextInt(i + 1);
      Cards temp = deck[i];
      deck[i] = deck[j];
      deck[j] = temp;
    }
  }

  public Cards[] getCards() {
    return grid;
  }

  public int getBoardSize() {
    return grid.length;
  }
}
