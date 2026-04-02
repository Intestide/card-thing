 package Intestide;

import java.util.Random;

public class Game {
  private static Cards[] deck = new Cards[52];
  private static Cards[] selected = new Cards[3];
  private static Cards[] grid = new Cards[9];
  private int cardsUsed;

  public Game() {
    shuffle();
    deal();
  }


  //put the first 9 cards in the grid
  private void deal() {
    for (byte i = 0; i < 9; i++) {
      grid[i] = deck[i];
      cardsUsed++;
    }
  }


  public void shuffle() {
    cardsUsed = 0;
    for (byte i = 0; i < 52; i++) {
      deck[i] = new Cards(i);
    }
    Random rand = new Random();
    for (int i = 51; i > 0; i--) {//swap each card with a random card once
      int j = rand.nextInt(i + 1);
      Cards temp = deck[i];
      deck[i] = deck[j];
      deck[j] = temp;
    }
  }

  public boolean check(Cards[] selected) {
    int cardCount = selected.length;
    int sum = 0;
    for (int i = 0; i < selected.length; i++) {
      sum += selected[i].getValue();
    }
    // return (sum == 11 && cardCount == 2) || (sum == -36 && cardCount == 3);//check if the sum is 11 or if the sum is 3 jqk
    return true; //testing thing
  }

  public void fillSpot(int[] indexes) {
    for (int i = 0; i < indexes.length; i++) {
      if (cardsUsed < 52) {
        grid[indexes[i]] = deck[cardsUsed];//set the spots that needs to be filled with the next card
        cardsUsed++;
      } else {
        cardsUsed++;
        grid[indexes[i]] = null;
      }
    }

  }
  
  public boolean isEmpty() {
    return cardsUsed >= 52 + 9; // make sure both the deck and gird is empty, 
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
