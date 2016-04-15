import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameWindow extends JFrame implements ActionListener{
	
	Board board;

	public GameWindow(Board b){
		
		board = b;
		
		this.setTitle("Minesweeper");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.setJMenuBar(menuBarCreation());
		this.add(b);
		
		this.pack();
		this.setVisible(true);
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

		jmb.add(jmGame);	
		jmb.add(jmHelp);	

		jmiAbout.addActionListener(this);
		jmiNewGame.addActionListener(this);
		jmiExit.addActionListener(this);
		jmiDiff1.addActionListener(this);
		jmiDiff2.addActionListener(this);
		jmiDiff3.addActionListener(this);
		jmiDiff4.addActionListener(this);

		return jmb; //add menu bar to window
	}
	
	public void actionPerformed(ActionEvent ae){

		String comStr = ae.getActionCommand();
		
		if(comStr.equals("About")){
			System.out.println("About clicked");
			JOptionPane.showMessageDialog(this, 
					"Created by Nolan Pfaff as a Spring 2016 H-Option. \n\n"
					+ "Left clicking on a tile will open it and display the number of mines adjacent to it. Click on a bomb and you lose!\n"
					+ "Right clicking on a tile will flag it and prevent it from being clicked. Use this to mark where you think the mines are!\n"
					+ "Win by clicking every non-mine tile!");
		}
		if(comStr.equals("New Game")){
			this.dispose();
			Game g = new Game(board.getBoardRow(), board.getBoardCol(), board.getMaxBombs());
			g.gameStart();
		}
		if(comStr.equals("Exit")){
			System.exit(0);
		}
		if(comStr.equals("Easy")){
			this.dispose();
			Game g = new Game(9, 9, 10);
			g.gameStart();
		}
		if(comStr.equals("Normal")){
			this.dispose();
			Game g = new Game(16, 16, 40);
			g.gameStart();
		}
		if(comStr.equals("Hard")){
			this.dispose();
			Game g = new Game(16, 30, 99);
			g.gameStart();
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
				
				int r = 0;
				int c = 0;
				int b = 0;
				
				try{
					r = Integer.parseInt(rows.getText());
					c = Integer.parseInt(cols.getText());
					b = Integer.parseInt(bomb.getText());
				}catch(Exception e){}
				
				if((r-1)*(c-1) < b){
					JOptionPane.showMessageDialog(this, "Unable to create board with those dimensions and number of mines!");
				}
				else if(r <= 0 || c <= 0 || b <= 0){
					JOptionPane.showMessageDialog(this, "Unable to create board with those dimensions and number of mines!");
				}
				else{
					this.dispose();
					Game g = new Game(r,c,b);
					g.gameStart();
				}
			}
		}	
	}
}