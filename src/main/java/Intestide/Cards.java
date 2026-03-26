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
  public int getValue()
  {
    return value;
  }
  public int getId()
  {
    return id;
  }
  public String getImageFile()
  {
    String name = "";
    if(id/13 == 1)
    {
      name += "CLUBS_";
    }
    else if(id/13 == 2)
    {
      name += "DIAMONDS_";
    }
    else if(id/13 == 3)
    {
      name += "HEARTS_";
    }
    else
    {
      name += "SPADES_";
    }
    name += value;
    return name;
  }
}
