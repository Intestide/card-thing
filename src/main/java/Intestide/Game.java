package Intestide;

import java.util.Random;

public class Game {
  private static Cards[] deck = new Cards[52];
  private static Cards[] selected = new Cards[3];
  private static Cards[] grid = new Cards[9];
  private int cardsLeft;

  public Game() {
    shuffle();
    deal();
  }

  private void deal() {
    for (byte i = 0; i < 9; i++) {
      grid[i] = deck[i];
      cardsLeft++;
    }
  }

  public void shuffle() {
    cardsLeft = 0;
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

  public boolean check(Cards[] selected) {
    int sum = 0;
    for (int i = 0; i < selected.length; i++) {
      sum += selected[i].getValue();
    }
    return sum == 11 || sum == -36;
  }

  public void fillSpot(int[] indexes) {
    for (int i = 0; i < indexes.length; i++) {
      if (cardsLeft < 52) {
        grid[indexes[i]] = deck[cardsLeft];
        cardsLeft++;
      } else {
        cardsLeft++;
        grid[indexes[i]] = null;
      }
    }

  }
  
  public boolean isEmpty() {
    return cardsLeft >= 52;
  }

  public Cards[] getCards() {
    return grid;
  }

  public int getBoardSize() {
    return grid.length;
  }

  public void restart() {
    shuffle();
    deal();
  }
}
