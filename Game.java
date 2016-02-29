import javax.swing.SwingUtilities;

public class Game {
	
	private int[] diff;
	
	public Game(){
	
	}
	public boolean gameStart(){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				new GameWindow();
			}
		});
		return false;
	}
	public void setDifficulty(){
		int diff[] = {9, 9, 10};
	}
	public int[] getDifficulty(){
		return diff;
	}	
}
