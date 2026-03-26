public class Cards {
  private int value;
  private int id;

  public Cards(int id) {
    this.id = id;
    this.value = id % 13 + 1;
    if (this.value > 10) {
      this.value = -1;
    }

  }
}
