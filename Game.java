
import javax.swing.SwingUtilities;

public class Game{

	private Board board;
	
	public Game(int r, int c, int b){
		board = new Board(r, c, b);
	}
	
	public void gameStart(){

		board.createBoard();
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				new GameWindow(board);
			}
		});
	}
}
