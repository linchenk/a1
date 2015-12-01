package sprites;

/**
 * A class that represents the basic sprite of the grid.
 * @author chenkailin
 */
public abstract class Sprite {

  protected char symbol;
  protected int row;
  protected int column;
  /**
   * initialize Sprite.
   * @param symbol char of symbol
   * @param row row
   * @param column column
   */
  public Sprite(char symbol, int row, int column) {
    this.symbol = symbol;
    this.row = row;
    this.column = column;
  }

  /**
   * return the symbol.
   * @return the symbol
   */
  public char getSymbol() {
    return symbol;
  }

  /**
   * return the num of row.
   * @return the row
   */
  public int getRow() {
    return row;
  }

  /**
   * return the num of column.
   * @return the column
   */
  public int getColumn() {
    return column;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "" + symbol;
  }

}
