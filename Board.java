import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Board extends JPanel implements ActionListener, MouseListener{

	private int boardRow;
	private int boardCol;
	private int maxBomb;
	private int maxClicks;
	private int numClicks;
	private boolean bombClick;
	
	private Tile[][] tiles;
	
	private static ImageIcon flag = new ImageIcon("resources/flag.png");
	private static ImageIcon mineex = new ImageIcon("resources/mineex.png");
	
	public Board(int r, int c, int b){
		boardRow = r;
		boardCol = c;
		maxBomb = b;
		maxClicks = boardRow * boardCol - maxBomb;
		numClicks = 0;
		bombClick = false;
		tiles = new Tile[r][c];
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

	public void createBoard() {
		initBoard();
		placeBombs();
		bombLocator();
		imageAssign();
		
		this.setLayout(new GridLayout(boardRow, boardCol));

		for (int i = 0; i < boardRow; i++) {
			for (int j = 0; j < boardCol; j++) {
				this.add(tiles[i][j]);
			}
		}
	}
	
	public void initBoard(){
		for(int i = 0; i < boardRow; i++){
			for(int j =0; j < boardCol; j++){
					tiles[i][j] = new Tile();;
			}
		}
	}
	
	public void placeBombs(){
		int nBomb = 0;

		loop:	while(nBomb < maxBomb){
			for(int i = 0; i < boardRow; i++){
				for(int j =0; j < boardCol; j++){
					int rand = (int)Math.ceil(Math.random()*1000);
					if(rand > 985){
						tiles[i][j].setBomb(true);
						tiles[i][j].setValue(9);
						if(++nBomb == maxBomb){
							break loop;
						}
					}
				}
			}
		}
	}
	
	public void bombLocator(){
		for(int i = 0; i < boardRow; i++){ //loop for row transversal
			for(int j = 0; j < boardCol; j++){ //loop for column transversal
				if(tiles[i][j].isBomb()){ //if there is a bomb at this location
					for(int r = i-1; r < i+2; r++){//goes to the previous row, checks only 3 rows
						for(int s = j-1; s < j+2; s++){//goes to the previous column, checks only 3 columns
							try{
								if(!tiles[r][s].isBomb()){
									tiles[r][s].setValue(tiles[r][s].getValue()+ 1);
								}	
							} catch(IndexOutOfBoundsException e){} //
						}
					}
				}
			}
		}
	}
	
	public void imageAssign(){
		for(int i = 0; i < boardRow; i++){ 
			for(int j = 0; j < boardCol; j++){
				tiles[i][j].setImage(tiles[i][j].getValue());
				tiles[i][j].addMouseListener(this);
				tiles[i][j].addActionListener(this);
				tiles[i][j].setActionCommand(i+" "+j);
				tiles[i][j].setDisabledIcon(tiles[i][j].getImage());
			}
		}
	}
	
	public void clickEmpty(int i, int j){
		for(int r = i-1; r < i+2; r++){
			for(int s = j-1; s < j+2; s++){
				try{
					if(!tiles[r][s].isClicked()){
						tiles[r][s].setEnabled(false);
						tiles[r][s].setClick(true);
						numClicks++;
						if(tiles[r][s].getValue() == 0){
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
				tiles[i][j].setClick(true);
				if(tiles[i][j].isBomb() && bombClick){
					tiles[i][j].setEnabled(false);
				}
			}
		}
		if(bombClick){
			JOptionPane.showMessageDialog(null, "Game over! You clicked a bomb!");
		}
		else{
			JOptionPane.showMessageDialog(null, "Game won! Congratulations!");
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		String action =((JButton)e.getSource()).getActionCommand();
		String[] a = action.split(" ");
		int x = Integer.parseInt(a[0]);
		int y = Integer.parseInt(a[1]);

		if (SwingUtilities.isRightMouseButton(e)){
			if(!tiles[x][y].isClicked()){ 
				if(!tiles[x][y].isFlagged()){
					tiles[x][y].setDisabledIcon(flag);
					tiles[x][y].setEnabled(false);
					tiles[x][y].setFlag(true);
				}
				else if(tiles[x][y].isFlagged()){
					tiles[x][y].setDisabledIcon(tiles[x][y].getImage());
					tiles[x][y].setEnabled(true);
					tiles[x][y].setFlag(false);
				}
			}
		}
		if (SwingUtilities.isLeftMouseButton(e)) {
			if(!tiles[x][y].isFlagged() && !tiles[x][y].isClicked()){
				tiles[x][y].setClick(true);
				if(tiles[x][y].isBomb()){
					tiles[x][y].setDisabledIcon(mineex);
					bombClick = true;
					gameEnd();
				}
				if(tiles[x][y].isSpace()){
					clickEmpty(x, y);
				}
				if( e.getSource() instanceof JButton){
					numClicks++;
					tiles[x][y].setEnabled(false);
				}
				if(numClicks  == maxClicks){
					gameEnd();	
				}
			}
		}
	}
	
	/**
	 * @EVERYTHING BELOW IS UNUSED
	 */

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}
}