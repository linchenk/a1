package sprites;

/**
 * class Dustball.
 * @author chenkailin
 *
 */
public class DustBall extends Dirt implements Moveable {

  /**
   * initialize Dustball.
   * @param symbol char of symbol
   * @param row row
   * @param column column
   * @param value value
   */
  public DustBall(char symbol, int row, int column, int value) {
    super(symbol, row, column, value);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void moveTo(int row, int column) {
    // TODO Auto-generated method stub
    this.row = row;
    this.column = column;
  }

}
