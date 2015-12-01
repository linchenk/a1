package sprites;

import game.Constants;

public class Vacuum extends Sprite implements Moveable {
  private int score;
  private int capacity;
  private int fullness;
  private Sprite under;

  /**
   * initialize Vacuum.
   * @param symbol char of symbol
   * @param row row
   * @param column column
   */
  public Vacuum(char symbol, int row, int column, int capacity) {
    super(symbol, row, column);
    this.capacity = capacity;
    this.fullness = Constants.EMPTY;
    this.score = Constants.INIT_SCORE;
    this.under = new CleanHallway(Constants.CLEAN, row, column);

    // TODO Auto-generated constructor stub
  }

  /**
   * return true if clean the dirt, else return false.
   * @param score score
   * @return boolean, clean the dirt or dustball and add score
   */
  public boolean clean(int score) {
    if (fullness == capacity) {
      return false;
    }
    else {
      this.fullness += Constants.FULLNESS_INC;
      this.score += score;
      return true;
    }
  }

  /**
   * initialize the fullness.
   * reset this vacuum's fullness
   */
  public void empty() {
    this.fullness = Constants.EMPTY;
  }


  /**
   * return score.
   * @return the score
   */
  public int getScore() {
    return score;
  }

  /**
   * get the under.
   * @return the under
   */
  public Sprite getUnder() {
    return under;
  }

  /**
   * set the under.
   * @param under the under to set
   */
  public void setUnder(Sprite under) {
    this.under = under;
  }

  @Override
  public void moveTo(int row, int column) {
    // TODO Auto-generated method stub
    this.row = row;
    this.column = column;
  }
}
