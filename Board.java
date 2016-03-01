import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Board implements ActionListener, MouseListener {

	private int boardRow;
	private int boardCol;
	private int maxBomb;
	private int maxClicks;
	private int numClicks;
	
	private ImageIcon flag = new ImageIcon("resources/flag.png");
	private ImageIcon mineex = new ImageIcon("resources/mineex.png");
	
	private Tile[][] tile;
	
	public Board(int r, int c, int b){
		setBoardRow(r);
		setBoardCol(c);
		setMaxBombs(b);
		maxClicks = boardRow * boardCol - maxBomb;
		tile = new Tile[r][c];
	}
	
	public void setBoardRow(int r){
		boardRow = r;
	}
	public void setBoardCol(int c){
		boardCol = c;
	}
	public void setMaxBombs(int b){
		maxBomb = b;
	}
	public int getBoardRow(){
		return boardRow;
	}
	public int getBoardCol(){
		return boardCol;
	}
	public int getMaxBombs(){
		return maxBomb;
	}
	public Tile getTile(int i, int j){
		return tile[i][j];
	}
	
	public void clearBoard(){
		for(int i = 0; i < boardRow; i++){
			for(int j =0; j < boardCol; j++){
		    		Tile t = new Tile(i, j);
					tile[i][j] = t;
			}
		}
	}
	public void placeBombs(){
		int nBomb = 0;

		loop:	while(nBomb < maxBomb){
			for(int i = 0; i < boardRow; i++){
				for(int j =0; j < boardCol; j++){
					int rand = (int)Math.ceil(Math.random()*1000);
					if(rand > 950){
						tile[i][j].setBomb(true);
						tile[i][j].setValue(9);
						nBomb += 1;
						if(nBomb == maxBomb){
							break loop;
						}
					}
				}
			}
		}//end loop
	}
	public void bombLocator(){
		for(int i = 0; i < boardRow; i++){ //loop for row transversal
			for(int j = 0; j < boardCol; j++){ //loop for column transversal
				if(tile[i][j].isBomb()){ //if there is a bomb at this location
					for(int r = i-1; r < i+2; r++){//goes to the previous row, 3 long
						for(int s = j-1; s < j+2; s++){//goes to the previous column, 3 long
							try{
								if(!tile[r][s].isBomb()){
									tile[r][s].setValue(tile[r][s].getValue()+ 1);
								}	
							} catch(IndexOutOfBoundsException e){}
						}
					}
				}
			}
		}
		imageAssign();
	}
	public void imageAssign(){
		for(int i = 0; i < boardRow; i++){ 
			for(int j = 0; j < boardCol; j++){
				tile[i][j].setImage(tile[i][j].getValue());
				JButton btn = tile[i][j].getButton();
				btn.addMouseListener(this);
				btn.addActionListener(this);
				btn.setActionCommand(i+" "+j);
				btn.setDisabledIcon(tile[i][j].getImage());
				
				tile[i][j].setButton(btn);
			}
		}
	}
		
	public void clickEmpty(int i, int j){
		for(int r = i-1; r < i+2; r++){
			for(int s = j-1; s < j+2; s++){
				try{
					if(!tile[r][s].isClicked()){
						JButton btn = tile[r][s].getButton();
						btn.setEnabled(false);
						tile[r][s].setButton(btn);
						tile[r][s].setClick(true);
						numClicks++;
						if(tile[r][s].getValue() == 0){
							clickEmpty(r, s);
						}
					}
				}catch(IndexOutOfBoundsException e){}
			}
		}	
	}
	public void gameEnd(){
		for(int i = 0; i < boardRow; i++){
			for(int j =0; j < boardCol; j++){
					tile[i][j].setClick(true);
					if(tile[i][j].isBomb()){
						JButton btn = tile[i][j].getButton();
						btn.setEnabled(false);
						tile[i][j].setButton(btn);
					}
			}
		}
	}
	
	
	public void mouseClicked(MouseEvent e) {
		String action =((JButton)e.getSource()).getActionCommand();
		String[] a = action.split(" ");
		int x = Integer.parseInt(a[0]);
		int y = Integer.parseInt(a[1]);

		if (SwingUtilities.isRightMouseButton(e)){
			System.out.println("Right mouse clicked.");
			if(!tile[x][y].isClicked()){ //if button is not clicked
				if(!tile[x][y].isFlagged()){ //if button is not flagged
					((JButton)e.getSource()).setDisabledIcon(flag);
					((JButton)e.getSource()).setEnabled(false);
					tile[x][y].setFlag(true);
				}
				else if(tile[x][y].isFlagged()){ //if button is flagged
					((JButton)e.getSource()).setDisabledIcon(tile[x][y].getImage());
					((JButton)e.getSource()).setEnabled(true);
					tile[x][y].setFlag(false);
				}
			}
		}
		if (SwingUtilities.isLeftMouseButton(e)) {
			System.out.println("Left mouse clicked.");
			if(!tile[x][y].isFlagged() && !tile[x][y].isClicked()){
				tile[x][y].setClick(true);
				if(tile[x][y].isBomb()){
					JButton btn = tile[x][y].getButton();
					btn.setDisabledIcon(mineex);
					tile[x][y].setButton(btn);
					gameEnd();
					System.out.println("Game over.");
				}
				if(tile[x][y].isSpace()){
					clickEmpty(x, y);
				}
				if( e.getSource() instanceof JButton){
					numClicks++;
					System.out.println(numClicks + " buttons pressed");
					((JButton)e.getSource()).setEnabled(false);
				}
				if(numClicks  == maxClicks){
					System.out.println("Game won!");
					gameEnd();	
				}
			}
		}
	}
	
	/**
	 * @EVERYTHING BELOW IS UNUSED
	 */
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {	
	}
	public void actionPerformed(ActionEvent ae) {
	}
}
