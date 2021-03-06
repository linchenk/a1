package game;

import sprites.CleanHallway;
import sprites.Dirt;
import sprites.Dumpster;
import sprites.DustBall;
import sprites.Sprite;
import sprites.Vacuum;
import sprites.Wall;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * A class that represents the basic functionality of the vacuum game.
 * This class is responsible for performing the following operations:
 * 1. At creation, it initializes the instance variables used to store the
 *        current state of the game.
 * 2. When a move is specified, it checks if it is a legal move and makes the
 *        move if it is legal.
 * 3. It reports information about the current state of the game when asked.
 */
public class VacuumGame {

  // a random number generator to move the DustBalls
  private Random random;

  // the grid
  private Grid<Sprite> grid;

  // the first player
  private Vacuum vacuum1;

  /// the second player
  private Vacuum vacuum2;

  // the dirt (both static dirt and mobile dust balls)
  private List<Dirt> dirts;

  // the dumpsters
  private List<Dumpster> dumpsters;

  /**
   * Creates a new VacuumGame that corresponds to the given input text file.
   * Assumes that the input file has one or more lines of equal lengths, and
   * that each character in it (other than newline) is a character that 
   * represents one of the sprites in this game.
   * @param layoutFileName path to the input grid file
   */
  public VacuumGame(String layoutFileName) throws IOException {
    dirts = new ArrayList<Dirt>();
    dumpsters = new ArrayList<Dumpster>();
    random = new Random();

    // open the file, read the contents, and determine 
    // dimensions of the grid
    int[] dimensions = getDimensions(layoutFileName);
    grid = new ArrayGrid<Sprite>(dimensions[0], dimensions[1]);

    // open the file again, read the contents, and store them in grid
    Scanner sc = new Scanner(new File(layoutFileName));

    // INITIALIZE THE GRID HERE
    for (int i = 0; i < dimensions[0]; i++) {
      char[] line = sc.nextLine().toCharArray();
      for (int j = 0; j < line.length; j++) {
        setSprites(i, j, line[j]);
      }
    }

    sc.close();
  }

  /**
   * Returns the dimensions of the grid in the file named layoutFileName.
   * @param layoutFileName path of the input grid file
   * @return an array [numRows, numCols], where numRows is the number
   *     of rows and numCols is the number of columns in the grid that
   *     corresponds to the given input grid file
   * @throws IOException if cannot open file layoutFileName
   */
  private int[] getDimensions(String layoutFileName) throws IOException {       

    Scanner sc = new Scanner(new File(layoutFileName));

    // find the number of columns
    String nextLine = sc.nextLine();
    int numCols = nextLine.length();

    int numRows = 1;

    // find the number of rows
    while (sc.hasNext()) {
      numRows++;
      nextLine = sc.nextLine();
    }

    sc.close();
    return new int[]{numRows, numCols};
  }

  /**
   * return Grid.
   * @return Grid
   */
  public Grid<Sprite> getGrid() {
    return grid;
  }

  /**
   * return Vacuum1.
   * @return Vacuum
   */
  public Vacuum getVacuumOne() {
    return vacuum1;
  }

  /**
   * return Vacuum2.
   * @return Vacuum
   */
  public Vacuum getVacuumTwo() {
    return vacuum2;
  }

  /**
   * return the number of rows.
   * @return int
   */
  public int getNumRows() {
    return grid.getNumRows();
  }

  /**
   * return the number of columns.
   * @return int
   */
  public int getNumColumns() {
    return grid.getNumColumns();
  }

  /**
   * return the object from the target cell.
   * @param row row
   * @param col column
   * @return Sprite
   */
  public Sprite getSprite(int row, int col) {
    return grid.getCell(row, col);
  }

  /**
   * return true if game finished.
   * @return boolean
   */
  public boolean gameOver() {
    return dirts.isEmpty();
  }

  /**
   * return the number of player who win the game, return 0 if tie.
   * @return int
   */
  public int getWinner() {
    if (vacuum1.getScore() > vacuum2.getScore()) {
      return 1;
    } else if (vacuum1.getScore() < vacuum2.getScore()) {
      return 2;
    } else {
      return 0;
    }
  }

  /**
   * return true, if get the correct move character then move players.
   * else return false
   * @param nextMove char of 'wasdijkl'
   * @return boolean
   */
  public boolean move(char nextMove) {
    switch (nextMove) {
      case Constants.P1_LEFT:
        this.moveVacuum(vacuum1, vacuum1.getRow(), vacuum1.getColumn() - 1);
        return true;
      case Constants.P1_RIGHT:
        this.moveVacuum(vacuum1, vacuum1.getRow(), vacuum1.getColumn() + 1);
        return true;
      case Constants.P1_UP:
        this.moveVacuum(vacuum1, vacuum1.getRow() - 1, vacuum1.getColumn());
        return true;
      case Constants.P1_DOWN:
        this.moveVacuum(vacuum1, vacuum1.getRow() + 1, vacuum1.getColumn());
        return true;
      case Constants.P2_LEFT:
        this.moveVacuum(vacuum2, vacuum2.getRow(), vacuum2.getColumn() - 1);
        return true;
      case Constants.P2_RIGHT:
        this.moveVacuum(vacuum2, vacuum2.getRow(), vacuum2.getColumn() + 1);
        return true;
      case Constants.P2_UP:
        this.moveVacuum(vacuum2, vacuum2.getRow() - 1, vacuum2.getColumn());
        return true;
      case Constants.P2_DOWN:
        this.moveVacuum(vacuum2, vacuum2.getRow() + 1, vacuum2.getColumn());
        return true;
      default:
        return false;
    } 
  }

  /**
   * players move vacuum.
   * @param player player
   * @param aimrow aimrow
   * @param aimcol aimcolumn
   */
  private void moveVacuum(Vacuum player, int aimrow, int aimcol) {
    // to get the target cell
    Sprite aimCell = grid.getCell(aimrow, aimcol);
    // if target location can not move
    if (aimCell.getSymbol() == Constants.WALL || aimCell.getSymbol() == Constants.P1
        || aimCell.getSymbol() == Constants.P2) {
      this.moveDustball();
      Sprite tempCell = grid.getCell(player.getRow(), player.getColumn());
      if (tempCell.getSymbol() == player.getSymbol()) { 
      }
      else {
        player.setUnder(tempCell);
      }
      grid.setCell(player.getRow(), player.getColumn(), player);
    }
    else {
      // before moving the vacuum, set the cell
      grid.setCell(player.getRow(), player.getColumn(), player.getUnder()); 
      // move player
      player.moveTo(aimrow, aimcol);
      // if target location is a Dirt
      if (aimCell.getSymbol() == Constants.DIRT) {
        if (player.clean(Constants.DIRT_SCORE)) {
          dirts.remove(aimCell);
          player.setUnder(new CleanHallway(Constants.CLEAN, aimrow, aimcol));
        } else {
          player.setUnder(aimCell);
        }
        grid.setCell(aimrow, aimcol, player);
        this.moveDustball();
      }
      // if target location is a DustBall
      else if (aimCell.getSymbol() == Constants.DUST_BALL) {
        if (player.clean(Constants.DUST_BALL_SCORE)) {
          dirts.remove(aimCell);
          player.setUnder(new CleanHallway(Constants.CLEAN, aimrow, aimcol));
          grid.setCell(aimrow, aimcol, player);
          this.moveDustball();
        } else {
          this.moveDustball();
          Sprite newaimCell = grid.getCell(aimrow, aimcol);
          player.setUnder(newaimCell);
          grid.setCell(aimrow, aimcol, player);
        }
      }
      // if target location is DumpSter
      else if (aimCell.getSymbol() == Constants.DUMPSTER) {
        player.empty();
        player.setUnder(aimCell);
        grid.setCell(aimrow, aimcol, player);
        this.moveDustball();
      }
      // if target location is CleanHallway
      else {
        player.setUnder(aimCell);
        grid.setCell(aimrow, aimcol, player);
        this.moveDustball();
      }
    }
  }

  /**
   * move DustBall by random direction.
   */
  private void moveDustball() {
    // initialize DustBall list
    List<DustBall> dustballs = new ArrayList<DustBall>();
    random = new Random();
    // get DustBal list
    for (Dirt dirt:dirts) {
      if (dirt.getSymbol() == Constants.DUST_BALL) {
        dustballs.add((DustBall)dirt);
      }
    }
    // move DustBall by random direction
    for (DustBall ball:dustballs) {
      int randint = random.nextInt(4);
      switch (randint) {
        case 0:
          this.moveball(ball, ball.getRow(), ball.getColumn() - 1);
          break;
        case 1:
          this.moveball(ball, ball.getRow(), ball.getColumn() + 1);
          break;
        case 2:
          this.moveball(ball, ball.getRow() - 1, ball.getColumn());
          break;
        case 3:
          this.moveball(ball, ball.getRow() + 1, ball.getColumn());
          break;
        default:
          break;
      }
    }
  }

  /**
   * move single DustBall.
   * @param currentball currentball
   * @param aimrow aimrow
   * @param aimcol aimcolumn
   */
  private void moveball(DustBall currentball, int aimrow, int aimcol) {
    // to get the target cell
    Sprite aimCell = grid.getCell(aimrow, aimcol);
    // if target location can not move
    if (aimCell.getSymbol() == Constants.WALL || aimCell.getSymbol() == Constants.P1 
        || aimCell.getSymbol() == Constants.P2 || aimCell.getSymbol() == Constants.DUMPSTER
        || aimCell.getSymbol() == Constants.DUST_BALL) {
    }
    else {
      // before DustBall moving, clean the target cell
      if (aimCell.getSymbol() == Constants.DIRT) {
        dirts.remove(aimCell); 
      }
      // set the new dirt on the grid after DustBall moving
      Dirt newdirt = new Dirt(Constants.DIRT, currentball.getRow(),
          currentball.getColumn(), Constants.DIRT_SCORE);
      dirts.add(newdirt);
      grid.setCell(currentball.getRow(), currentball.getColumn(), newdirt);
      currentball.moveTo(aimrow, aimcol);
      grid.setCell(aimrow, aimcol, currentball);
    }
  }

  /**
   * initialize the grid.
   * @param row row
   * @param col column
   * @param symbol char of symbol
   */
  private void setSprites(int row, int col, char symbol) {
    switch (symbol) {
      case Constants.CLEAN:
        grid.setCell(row, col, new CleanHallway(Constants.CLEAN, row, col));
        break;

      case Constants.WALL:
        grid.setCell(row, col, new Wall(Constants.WALL, row, col));
        break;

      case Constants.DIRT:
        Dirt newDirt = new Dirt(Constants.DIRT, row, col, Constants.DIRT_SCORE);
        grid.setCell(row, col, newDirt);
        dirts.add(newDirt);
        break;

      case Constants.DUST_BALL:
        Dirt newDustball = new DustBall(Constants.DUST_BALL, row, col, Constants.DUST_BALL_SCORE);
        grid.setCell(row, col, newDustball);
        dirts.add(newDustball);
        break;

      case Constants.DUMPSTER:
        Dumpster newDumpster = new Dumpster(Constants.DUMPSTER, row, col);
        grid.setCell(row, col, newDumpster);
        dumpsters.add(newDumpster);
        break;

      case Constants.P1:
        vacuum1 = new Vacuum(Constants.P1, row, col, Constants.CAPACITY);
        grid.setCell(row, col, vacuum1);
        break;

      case Constants.P2:
        vacuum2 = new Vacuum(Constants.P2, row, col, Constants.CAPACITY);
        grid.setCell(row, col, vacuum2);
        break;
      default:
        break;
    }
  }
}
