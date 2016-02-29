import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameWindow implements ActionListener {

	private Board board;

	public GameWindow(){
		
		board = new Board(9, 9, 10);
		
		board.clearBoard();
		board.placeBombs();
		board.bombLocator();
		
		JFrame jfrm = new JFrame("Minesweeper");
		
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jfrm.setLayout(new FlowLayout());
		
		jfrm.setJMenuBar(menuBarCreation());
		jfrm.add(gameBoardCreation());
		jfrm.add(tempBoard());
		
		jfrm.pack();
		jfrm.setVisible(true);
	}
	public JMenuBar menuBarCreation() {
		
		JMenuBar jmb = new JMenuBar();
		
		JMenu jmGame = new JMenu("Game");
		JMenuItem jmiNewGame = new JMenuItem("New Game");
		JMenuItem jmDifficulty = new JMenu("Change Difficulty...");
		JMenuItem jmiExit = new JMenuItem("Exit");
		
		JRadioButtonMenuItem  jmiDiff1 = new JRadioButtonMenuItem("Beginner", true);
		JRadioButtonMenuItem  jmiDiff2 = new JRadioButtonMenuItem("Normal");
		JRadioButtonMenuItem  jmiDiff3 = new JRadioButtonMenuItem("Hard");
		JRadioButtonMenuItem  jmiDiff4 = new JRadioButtonMenuItem("Expert");
		
		ButtonGroup bg = new ButtonGroup();
		
		bg.add(jmiDiff1);
		bg.add(jmiDiff2);
		bg.add(jmiDiff3);
		bg.add(jmiDiff4);
		
		jmDifficulty.add(jmiDiff1);
		jmDifficulty.add(jmiDiff2);
		jmDifficulty.add(jmiDiff3);
		jmDifficulty.add(jmiDiff4);
	
		jmGame.add(jmiNewGame);
		jmGame.add(jmDifficulty);
		jmGame.add(jmiExit);

		JMenu jmHelp = new JMenu("Help");
		JMenuItem jmiAbout = new JMenuItem("About");
		
		jmHelp.add(jmiAbout);
		
		jmb.add(jmGame);	//add to menu bar
		jmb.add(jmHelp);	//
		
		jmiNewGame.addActionListener(this);
		jmiExit.addActionListener(this);
		jmiDiff1.addActionListener(this);
		jmiDiff2.addActionListener(this);
		jmiDiff3.addActionListener(this);
		jmiDiff4.addActionListener(this);
			
		return jmb; //add menu bar to window
	}
	public JPanel tempBoard(){
		
		JPanel table = new JPanel(new GridLayout(board.getBoardRow(), board.getBoardCol()));
		
	    for (int i = 0; i < board.getBoardRow(); i++) {
	        for (int j = 0; j < board.getBoardCol(); j++) {
	        	
	        	table.add(new JLabel(board.getTile(i, j).getImage(), JLabel.CENTER));
	        	
	        	
	        }
	    }
		return table;
	}
	public JLabel bombCounter(){
		return null;
	}
	public JPanel gameBoardCreation(){

		JPanel table = new JPanel(new GridLayout(board.getBoardRow(), board.getBoardCol()));
		
		for (int i = 0; i < board.getBoardRow(); i++) {
	        for (int j = 0; j < board.getBoardCol(); j++) {
	        	table.add(board.getTile(i, j).getButton());
	        }
		}

		return table;
	}
	public void actionPerformed(ActionEvent ae){
		
		String comStr = ae.getActionCommand();
	
		if(comStr.equals("New Game")){
		}
		if(comStr.equals("Exit")){
			System.exit(0);
		}
		if(comStr.equals("Beginner")){
			board.setBoardRow(9);
			board.setBoardCol(9);
			board.setMaxBombs(10);
			
		}
		if(comStr.equals("Normal")){
			board.setBoardRow(11);
			board.setBoardCol(11);
			board.setMaxBombs(20);
			
		}
		if(comStr.equals("Hard")){
			board.setBoardRow(13);
			board.setBoardCol(22);
			board.setMaxBombs(50);
			
		}
		if(comStr.equals("Expert")){
			board.setBoardRow(16);
			board.setBoardCol(30);
			board.setMaxBombs(99);
		}	
	}
}
