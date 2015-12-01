package game;

/**
 * interface Grid.
 * @author chenkailin
 *
 */
public interface Grid<T> {
  /**
   * set the item on the target cell.
   * @param row row
   * @param column column
   * @param item sprite
   */
  public void setCell(int row, int column, T item);

  /**
   * return the object on the target cell.
   * @param row row
   * @param column column
   * @return Sprite
   */
  public T getCell(int row, int column);

  /**
   * return the numbers of rows.
   * @return int
   */
  public int getNumRows();

  /**
   * return the numbers of columns.
   * @return int
   */
  public int getNumColumns();

  /**
   * return true if them are same thing.
   * @param other Object
   * @return boolean
   */
  public boolean equals(Object other);

  /**
   * return string.
   * @return string
   */
  public String toString();
}
