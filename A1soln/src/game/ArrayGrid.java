package game;

/**
 * class ArrayGrid.
 * @author chenkailin
 *
 */
public class ArrayGrid<T> implements Grid<T> {
  private Object[][] grid;
  private int numRows;
  private int numColumns;

  /**
   * initialize a grid.
   * @param numRows the number of rows
   * @param numColumns the number of columns
   */
  public ArrayGrid(int numRows, int numColumns) {
    this.numRows = numRows;
    this.numColumns = numColumns;
    this.grid = new Object[numRows][numColumns];

  }

  @Override
  public void setCell(int row, int column, T item) {
    this.grid[row][column] = item;

    // TODO Auto-generated method stub

  }

  @Override
  @SuppressWarnings("unchecked")
  public T getCell(int row, int column) {
    // TODO Auto-generated method stub
    return (T) this.grid[row][column];
  }

  @Override
  public int getNumRows() {
    // TODO Auto-generated method stub
    return numRows;
  }

  @Override
  public int getNumColumns() {
    // TODO Auto-generated method stub
    return numColumns;
  }

  /**
   * return grid in String type.
   * @return String
   */
  public String toString() {
    String str = "";
    for (int i = 0; i < numRows; i++ ) {
      for (int j = 0; j < numColumns; j++) {
        str += grid[i][j].toString();
      }
      str += "\n";
    }
    return str;

  }
}
