import javax.swing.JButton;

public class Board {

	private int boardRow;
	private int boardCol;
	private int maxBomb;
	
	private Tile[][] tile;
	
	public Board(int r, int c, int b){
		setBoardRow(r);
		setBoardCol(c);
		setMaxBombs(b);
		
		tile = new Tile[r][c];

	}
	public void clearBoard(){
		for(int i = 0; i < boardRow; i++){
			for(int j =0; j < boardCol; j++){
		    		Tile t = new Tile();
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
				
				btn.setDisabledIcon(tile[i][j].getImage());
				
				tile[i][j].setButton(btn);
			}
		}
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
}
