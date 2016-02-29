import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Tile implements ActionListener {

	private int value;
	private ImageIcon img;
	private JButton btn;
	private boolean bomb;
	
	private ImageIcon space = new ImageIcon("C:/Users/Nolan/workspace/CompSci220/Minesweeper/resources/tile.png");
	private ImageIcon b1 = new ImageIcon("C:/Users/Nolan/workspace/CompSci220/Minesweeper/resources/1.png");
	private ImageIcon b2 = new ImageIcon("C:/Users/Nolan/workspace/CompSci220/Minesweeper/resources/2.png");
	private ImageIcon b3 = new ImageIcon("C:/Users/Nolan/workspace/CompSci220/Minesweeper/resources/3.png");
	private ImageIcon b4 = new ImageIcon("C:/Users/Nolan/workspace/CompSci220/Minesweeper/resources/4.png");
	private ImageIcon b5 = new ImageIcon("C:/Users/Nolan/workspace/CompSci220/Minesweeper/resources/5.png");
	private ImageIcon b6 = new ImageIcon("C:/Users/Nolan/workspace/CompSci220/Minesweeper/resources/6.png");
	private ImageIcon b7 = new ImageIcon("C:/Users/Nolan/workspace/CompSci220/Minesweeper/resources/7.png");
	private ImageIcon b8 = new ImageIcon ("C:/Users/Nolan/workspace/CompSci220/Minesweeper/resources/8.png");
	private ImageIcon none = new ImageIcon("C:/Users/Nolan/workspace/CompSci220/Minesweeper/resources/empty.png");
	private ImageIcon mine = new ImageIcon("C:/Users/Nolan/workspace/CompSci220/Minesweeper/resources/mine.png");
	
	public Tile(){
		this.setValue(0);
		JButton btn = new JButton();
		btn.setIcon(space);
		btn.setBorder(null);
		btn.addActionListener(this);
		this.setButton(btn);
		this.setBomb(false);
		
	}
	public void setValue(int v){
		this.value = v;
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
	public void setButton(JButton b){
	    btn = b;
	}
	public void setBomb(Boolean b){
		bomb = b;
	}
	public int getValue(){
		return this.value;
	}
	public ImageIcon getImage(){
		return img;
	}
	public JButton getButton(){
		return btn;
	}
	public boolean isBomb(){
		return bomb;
	}
	public void actionPerformed(ActionEvent ae) {
		if( ae.getSource() instanceof JButton){
		       ((JButton)ae.getSource()).setIcon(none);
		       System.out.println("Button pressed");
		       ((JButton)ae.getSource()).setEnabled(false);
		}
		
	}
}
