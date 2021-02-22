import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;



public class GameController implements ActionListener{

	private View view;
	public GameController(){}

	public GameController(View view) {
		this.view = view;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public void clickTheUnclicked(int row, int col)// the awesome function where cells who doesnt have bombs around them are clicked by themselves
	{
		if(row <0 || row>=this.view.getBoardMaxRow() || col<0 || col>=this.view.getBoardMaxCol())
		{
			return;
		}
		MyButton button = view.getButton(row, col);

		if (button.isClicked())
		{
			return;
		}

		if(button.isBomb())
		{
			return;
		}

		int amountOfBombsAroumdMe = this.view.amountOfBombsAround( row, col);

		button.setClicked(true);
		button.setEnabled(false);

		if(amountOfBombsAroumdMe!=0)
		{
			Font font = new Font(Font.SANS_SERIF,Font.BOLD, 30);
			button.setFont(font);
			button.setText(String.valueOf(amountOfBombsAroumdMe));
			return;
		}



		clickTheUnclicked(row-1, col);
		clickTheUnclicked(row, col-1);
		clickTheUnclicked(row, col+1);
		clickTheUnclicked(row+1, col);

	}
	@Override
	public void actionPerformed(ActionEvent event) // setting the image of the bomb that was clicked and announce that you have lost the game
	{
		MyButton button = (MyButton) event.getSource();

		int counter = this.view.getBoardMaxCol()*this.view.getBoardMaxRow();
		int amountOfBombsInTheGame = 10;


		if (!button.isClicked())
		{	
			if (button.isBomb())
			{

				Image bombImage;
				try {
					bombImage = ImageIO.read(getClass().getResource("resources/bomb.jpg"));
					button.setIcon(new ImageIcon(bombImage));
					JOptionPane.showMessageDialog(null, "Game over, you're busted !");
					System.exit(0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				clickTheUnclicked(button.getRow(), button.getCol());
			}


		}

		//a private tester
		System.out.println("amountOfBombsInTheGame : "+amountOfBombsInTheGame);
		System.out.println("view.unrevealedButtons() : " + view.unrevealedButtons());
		if(amountOfBombsInTheGame == this.view.unrevealedButtons()||view.isFlagsEqualesToBombs()==true) // if there are only bombs left youre the winner or the flaged buttones are bombs
		{
			JOptionPane.showMessageDialog(null, "you're the best around !");
			System.exit(0);//close the game
		}

	}

}
