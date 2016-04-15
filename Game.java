
import javax.swing.SwingUtilities;

public class Game{

	private int[] difficulty = new int[3];
	private Board board;
	
	public Game(int r, int c, int b){
		setDifficulty(r, c, b);
	}
	
	public void setDifficulty(int r, int c, int b){
		difficulty[0] = r;
		difficulty[1] = c;
		difficulty[2] = b;
	}
	
	public void gameStart(){
		
		board = new Board(difficulty[0], difficulty[1], difficulty[2]);
		board.createBoard();
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				new GameWindow(board);
			}
		});
	}
}
