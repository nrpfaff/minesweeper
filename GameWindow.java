import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;

import javax.swing.*;

public class GameWindow implements ActionListener {

	private int diff[] = new int[3];
	private Board board;
	private JFrame jfrm;

	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				new GameWindow(9, 9, 10);
			}
		});
	}

	public GameWindow(int r, int c, int b){

		gameStart(r, c, b);

		jfrm = new JFrame("Minesweeper");

		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jfrm.setLayout(new FlowLayout());

		jfrm.setJMenuBar(menuBarCreation());
		jfrm.add(gameBoardCreation());
		//jfrm.add(tempBoard());

		jfrm.pack();
		jfrm.setVisible(true);

	}
	public JMenuBar menuBarCreation() {

		JMenuBar jmb = new JMenuBar();

		JMenu jmGame = new JMenu("Game");
		JMenuItem jmiNewGame = new JMenuItem("New Game");
		JMenuItem jmDifficulty = new JMenu("Change Difficulty...");
		JMenuItem jmiExit = new JMenuItem("Exit");

		JMenuItem  jmiDiff1 = new JMenuItem("Easy");
		JMenuItem  jmiDiff2 = new JMenuItem("Normal");
		JMenuItem  jmiDiff3 = new JMenuItem("Hard");
		JMenuItem  jmiDiff4 = new JMenuItem("Custom...");

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

		jmiAbout.addActionListener(this);
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

		for (int i = 0; i < board.getBoardRow(); i++){
			for (int j = 0; j < board.getBoardCol(); j++){
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
		
		if(comStr.equals("About")){
			JOptionPane.showMessageDialog(jfrm, 
					"Created by Nolan Pfaff as a Spring 2016 H-Option.\n\n"
					+ "Left clicking on a tile will open it and display the number of mines adjacent to it. Click on a bomb and you lose!\n"
					+ "Right clicking on a tile will flag it and prevent it from being clicked. Use this to mark where you think the mines are!\n"
					+ "Win by clicking every non-mine tile!");
		}
		if(comStr.equals("New Game")){
			close();
			new GameWindow(9, 9, 10);
		}
		if(comStr.equals("Exit")){
			System.exit(0);
		}
		if(comStr.equals("Easy")){
			setDifficulty(9, 9, 10);
			close();
			new GameWindow(9, 9, 10);
		}
		if(comStr.equals("Normal")){
			setDifficulty(16, 16, 40);
			close();
			new GameWindow(16, 16, 40);
		}
		if(comStr.equals("Hard")){
			setDifficulty(16, 30, 99);
			close();
			new GameWindow(16, 30, 99);
			
		}
		if(comStr.equals("Custom...")){

			JTextField rows = new JTextField(5);
			JTextField cols = new JTextField(5);
			JTextField bomb = new JTextField(5);

			JPanel d = new JPanel();

			d.add(new JLabel("Rows: "));
			d.add(rows);
			d.add(Box.createHorizontalStrut(15)); // a spacer
			d.add(new JLabel("Colums: "));
			d.add(cols);
			d.add(new JLabel("Mines: "));
			d.add(bomb);

			int inp = JOptionPane.showConfirmDialog(null, d, "Enter dimensions", JOptionPane.OK_CANCEL_OPTION);
			if (inp == JOptionPane.OK_OPTION) {
				System.out.println("Rows: " + rows.getText());
				System.out.println("Colums: " + cols.getText());
				System.out.println("Mines: " + bomb.getText());

				int r = Integer.parseInt(rows.getText());
				int c = Integer.parseInt(cols.getText());
				int b = Integer.parseInt(bomb.getText());

				if(r*c-b <= 0){
					JOptionPane.showMessageDialog(jfrm, "Unable to create board with those dimensions and number of mines!");
				}
				else if(r <= 0 || c <= 0 || b <= 0){
					JOptionPane.showMessageDialog(jfrm, "Unable to create board with those dimensions and number of mines!");
				}
				else{
					close();
					new GameWindow(r,c,b);
				}
			}
		}	
	}
	public void close(){
		jfrm.dispose();	
	}
	public void gameStart(int r, int c, int b){

		board = new Board(r,c,b);

		board.clearBoard();
		board.placeBombs();
		board.bombLocator();

	}
	public void setDifficulty(int r, int c, int b){
		diff[0] = r;
		diff[1] = c;
		diff[2]	= b;	
	}
}