//	CARLOS ANDRÉS POGO UBILLA

package refactoring;

enum Result {
	WIN,
	LOSE,
	TIE
}

public class Game {

	public static void main(String args[]) {
		Player p1 = new Player();
		Player p2 = new Player();
		boolean gameWon = false;
		int roundsPlayed = 0;    // Number of rounds played

		int draw = 0;
		Choice p1Choice;
		Choice p2Choice;

		// Game Loop
		do {
			System.out.println("***** Round: "
					+ roundsPlayed + " *********************\n");
			System.out.println("Number of Draws: "
					+ draw + "\n");
			p1Choice = p1.playerChoice();
			System.out.println("Player 1: " + p1Choice.getName()
					+ "\t Player 1 Total Wins: " + p1.getWins());
			p2Choice = p2.playerChoice();
			System.out.println("Player 2: " + p2Choice.getName()
					+ "\t Player 2 Total Wins: " + p2.getWins());

			switch (p1Choice.against(p2Choice)) {
				case WIN:
					p1.setWins();
					System.out.println("\nPlayer 1 Wins");
					break;
				case LOSE:
					p2.setWins();
					System.out.println("\nPlayer 2 Wins");
					break;
				default:
					draw++;
					System.out.println("\n\t\t\t Draw \n");
					break;
			}

			roundsPlayed++;
			if ((p1.getWins() >= 3) || (p2.getWins() >= 3)) {
				gameWon = true;
				System.out.println("\nGAME WON");
			}
			System.out.println();
		} while (gameWon != true);
	}

}

class Player {

	int wins = 0;      // # of wins

	/**
	 * Randomly choose rock, paper, or scissors
	 */
	public Choice playerChoice() {

		int c = (int) (Math.random() * 3);
		switch (c) {
			case 0:
				return Rock.getRockChoice();
			case 1:
				return Paper.getPaperChoice();
		}
		return Scissors.getScissorsChoice();

	}

	public void setWins() {
		wins++;
	}

	public int getWins() {
		return (wins);
	}
}

abstract class Choice {

	protected String name;

	protected Choice() {
	}

	protected String getName() {
		return name;
	}

	public abstract Result against(Choice choice);
}

class Rock extends Choice {

	private static Rock rock;

	private Rock() {
		super();
		this.name = "rock\t";
	}

	protected static Choice getRockChoice() {
		if (rock == null) {
			rock = new Rock();
		}
		return rock;
	}

	public Result against(Choice choice) {
		if (choice instanceof Paper) {
			return Result.LOSE;
		}
		if (choice instanceof Scissors) {
			return Result.WIN;
		}
		return Result.TIE;
	}
}

class Paper extends Choice {

	private static Paper paper;

	private Paper() {
		super();
		this.name = "paper\t";
	}

	protected static Choice getPaperChoice() {
		if (paper == null) {
			paper = new Paper();
		}
		return paper;
	}

	public Result against(Choice choice) {
		if (choice instanceof Rock) {
			return Result.WIN;
		}
		if (choice instanceof Scissors) {
			return Result.LOSE;
		}
		return Result.TIE;
	}
}

class Scissors extends Choice {

	private static Scissors scissors;

	private Scissors() {
		super();
		this.name = "scissors";
	}

	protected static Choice getScissorsChoice() {
		if (scissors == null) {
			scissors = new Scissors();
		}
		return scissors;
	}

	public Result against(Choice choice) {
		if (choice instanceof Rock) {
			return Result.LOSE;
		}
		if (choice instanceof Paper) {
			return Result.WIN;
		}
		return Result.TIE;
	}
}
