package sprites;

/**
 * class Sprite.
 * @author chenkailin
 *
 */
public class Dirt extends Sprite {

  protected int value;


  /**
   * initialize Dirt. 
   * @param symbol char of symbol
   * @param row row
   * @param column column
   * @param value value
   */
  public Dirt(char symbol, int row, int column, int value) {
    super(symbol, row, column);
    this.value = value;
    // TODO Auto-generated constructor stub
  }


  /**
   * return the value.
   * @return the value
   */
  public int getValue() {
    return value;
  }

}
