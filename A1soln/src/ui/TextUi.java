package ui;

import game.VacuumGame;

import java.util.Scanner;

public class TextUi implements Ui {
  private VacuumGame game;

  public TextUi(VacuumGame game) {
    this.game = game;
  }

  /**
   * launch game.
   */
  @Override
  public void launchGame() {
    // TODO Auto-generated method stub
    Scanner sc = new Scanner(System.in);
    while (game.gameOver() == false) {
      System.out.println(game.getGrid());
      System.out.println("wasd for player 1");
      System.out.println("ijkl for player 2");
      System.out.println("please input a character of wasdijkl");
      char nextMove = sc.nextLine().charAt(0);
      game.move(nextMove);
    }
    this.displayWinner();
    sc.close();
  }

  /**
   * display the winner.
   */
  @Override
  public void displayWinner() {
    // TODO Auto-generated method stub
    System.out.println("the Winner is player " + game.getWinner());
  }
}
