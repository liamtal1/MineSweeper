import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Game extends JFrame
{
	private GameController controller;
	private View view;
	public Game()// the window that will show up when i launch the game
	{
		this.controller = new GameController();
		this.view = new View(this, this.controller);
		this.controller.setView(view);
		
		this.setSize(800,800);
		this.setVisible(true);
		

	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		final Game game = new Game();
		
		// Running the background music thread
		BackgroundMusicPlayer backgroundMusicPlayer = new BackgroundMusicPlayer();
		backgroundMusicPlayer.start();
		
        game.addWindowListener(new WindowAdapter() //action on closing the window
        {

            @Override
            public void windowClosing(WindowEvent e) {
                if(JOptionPane.showConfirmDialog(game, "Are you sure ?") == JOptionPane.OK_OPTION){
                    game.setVisible(false);
                    game.dispose();
                }
            }
        });


	}
}
