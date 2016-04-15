import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Tile extends JButton{
	
	private static ImageIcon space = new ImageIcon("resources/tile.png");
	private static ImageIcon b1 = new ImageIcon("resources/1.png");
	private static ImageIcon b2 = new ImageIcon("resources/2.png");
	private static ImageIcon b3 = new ImageIcon("resources/3.png");
	private static ImageIcon b4 = new ImageIcon("resources/4.png");
	private static ImageIcon b5 = new ImageIcon("resources/5.png");
	private static ImageIcon b6 = new ImageIcon("resources/6.png");
	private static ImageIcon b7 = new ImageIcon("resources/7.png");
	private static ImageIcon b8 = new ImageIcon ("resources/8.png");
	private static ImageIcon none = new ImageIcon("resources/empty.png");
	private static ImageIcon mine = new ImageIcon("resources/mine.png");
	
	private int value;
	private ImageIcon img;
	private boolean bomb;
	private boolean click;
	private boolean flag;
	
	public Tile(int i, int j){
		this.setValue(0);
		this.setIcon(space);
		this.setBorder(null);
		
		this.setBomb(false);
		this.setClick(false);
		this.setFlag(false);
	}
	
	public void setValue(int v){
		this.value = v;
	}
	public void setBomb(Boolean b){
		bomb = b;
	}
	public void setClick(boolean b){
		click = b;
	}
	public void setFlag(Boolean b){
		flag = b;
	}
	
	public void setImage(int v){
		if(this.isBomb()){
			img = mine;
		}
		else{
			switch(v){
			case 1:{
				img = b1;
				break;
			}
			case 2: {
				img = b2;
				break;
			}
			case 3: {
				img = b3;
				break;
			}
			case 4: {
				img = b4;
				break;
			}
			case 5: {
				img = b5;
				break;
			}
			case 6: {
				img = b6;
				break;
			}
			case 7: {
				img = b7;
				break;
			}
			case 8: {
				img = b8;
				break;
			}

			case 0: {
				img = none;
				break;
			}
			}//end switch
		}
	}
	
	public int getValue(){
		return this.value;
	}
	public ImageIcon getImage(){
		return img;
	}
	public boolean isBomb(){
		return bomb;
	}
	public boolean isSpace(){
		if(this.value == 0){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean isClicked(){
		return click;
	}
	public boolean isFlagged(){
		return flag;
	}
}
