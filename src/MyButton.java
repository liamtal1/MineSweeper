import javax.swing.JButton;


public class MyButton extends JButton{
	private int row;
	private int col;
	private boolean isClicked;
	
	private boolean isBomb;
	private boolean isFlag;
	
	public MyButton(int row, int col, boolean isBomb, boolean isFlag) {
		this.row = row;
		this.col = col;
		this.isBomb = isBomb;
		this.isClicked = false;
		this.isFlag = false;
	}
	public boolean isFlag() {
		return isFlag;
	}
	public void setFlag(boolean isFlag) {
		this.isFlag = isFlag;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public boolean isBomb() {
		return isBomb;
	}
	public void setBomb(boolean isBomb) {
		this.isBomb = isBomb;
	}
	public boolean isClicked() {
		return isClicked;
	}
	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}	
	
}
