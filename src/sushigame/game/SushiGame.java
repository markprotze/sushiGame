package sushigame.game;

import java.awt.Dimension;
import javax.swing.JFrame;
import sushigame.controller.SushiGameController;
import sushigame.model.SushiGameModel;
import sushigame.view.SushiGameView;

public class SushiGame {
	
	public static void main(String[] args) {
		
		SushiGameModel game_model = new SushiGameModel(20, 5, 4);
		SushiGameView game_view = new SushiGameView(game_model);
		@SuppressWarnings("unused")
		SushiGameController game_controller = new SushiGameController(game_model, game_view);
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Sushi Battle Royale");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		main_frame.setContentPane(game_view);
		
		main_frame.pack();
		main_frame.setVisible(true);
		
		main_frame.setSize(new Dimension(1500, 900));
	}
	
}