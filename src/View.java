import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class View extends MouseAdapter {
	private MyButton[][] cellsMatrix;


	public View(Container c, GameController controller)
	{
		c.setLayout(new GridLayout(10,10));// graphics
		this.cellsMatrix = new MyButton[10][10]; //define a matrix for the the game

		Set<Coordinate> set = new HashSet<Coordinate>(); // bomb coordinate
		Random rnd = new Random();
		int counter = 0;// bomb counter

		// Creating a set which will contain the coordinates of all bombs
		while (counter!=10)
		{
			int row = rnd.nextInt(10);
			int col = rnd.nextInt(10);
			Coordinate coord = new Coordinate(row, col);
			if (!set.contains(coord))
			{
				set.add(coord); // add bomb
				counter++;
			}
		}

		for (Coordinate cord : set)
		{
			System.out.println(cord.toString());
		}

		for (int row=0; row<10;row++) // filling the matrix
		{
			for(int col=0;col<10; col++)
			{

				if (set.contains(new Coordinate(row, col)))
				{
					this.cellsMatrix[row][col] = new MyButton(row,col,true,false);	// defining the bomb in th game				
				}
				else
				{
					this.cellsMatrix[row][col] = new MyButton(row,col,false,false);//thats not a bomb
				}
				(this.cellsMatrix[row][col]).addMouseListener(this);// approve my mouse click on every cell in the matrix
				c.add(this.cellsMatrix[row][col]);//add that cell with the click abilities to the matrix
				this.cellsMatrix[row][col].addActionListener(controller);
			}
		}
	}
	public int amountOfBombsAround(int row,int col)// checks that amount of bombs around a certain cell
	{
		int counter=0;
		int amountOfRowsToScan = 3;
		int startRowOffset = -1;
		int finalRowOffset = 1;
		if (row==0)
		{
			startRowOffset=0;
		}

		if (row==this.cellsMatrix[0].length-1)
		{
			finalRowOffset=0;
		}

		int amountOfColsToScan = 3;
		int startColOffset = -1;
		int finalColOffset = 1;
		if (col==0)
		{
			startColOffset=0;
		}

		if (col==this.cellsMatrix[1].length-1)
		{
			finalColOffset=0;
		}	

		for (int tmpRow = row+startRowOffset; tmpRow<=row+finalRowOffset; tmpRow++)
		{
			for (int tmpCol = col+startColOffset; tmpCol <= col+finalColOffset; tmpCol++)
			{
				if(tmpRow!=row||tmpCol!=col)
				{
					if(cellsMatrix[tmpRow][tmpCol].isBomb())
						counter++;
				}
			}
		}
		return counter;
	}
	public int getBoardMaxRow()
	{
		return cellsMatrix[0].length;
	}
	public int getBoardMaxCol()
	{
		return cellsMatrix[1].length;
	}

	public MyButton getButton(int row, int col)
	{
		return cellsMatrix[row][col];
	}
	public int unrevealedButtons()// counts how many unrevealed buttons i have left
	{
		int counter=0;
		for (int row = 0; row < cellsMatrix[0].length; row++) 
		{
			for (int col = 0; col < cellsMatrix[1].length; col++) 
			{
				if(cellsMatrix[row][col].isClicked()==false)
					counter++;
			}
		}
		return counter;
	}
	public boolean isFlagsEqualesToBombs()
	{
		int counter=1;
		for (int row = 0; row < cellsMatrix[0].length; row++) 
		{
			for (int col = 0; col < cellsMatrix[1].length; col++) 
			{
				if(cellsMatrix[row][col].isBomb()==true&&cellsMatrix[row][col].isFlag()==true)
					counter++;
			}
			if(counter==10)
			{
				return true;
			}
		}
		return false;
	}

	public void mousePressed(MouseEvent e)// raises a flag or deleting the flag on right click event
	{
		if(SwingUtilities.isRightMouseButton(e))
		{
			MyButton button = (MyButton) e.getSource();
			if(button.isFlag()==false)
			{
				button.setFlag(true);
				Image flagImage;
				try {
					flagImage = ImageIO.read(getClass().getResource("resources/flag.png"));
					button.setIcon(new ImageIcon(flagImage));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else{

				button.setIcon(null);
				button.setFlag(false);

			}
		}
	}
}

