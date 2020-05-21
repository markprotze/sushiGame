package sushigame.view;

import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.SushiGameModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class SushiGameView extends JPanel implements ActionListener, BeltObserver {
	
	private List<RotationRequestListener> rotation_request_listeners;
	private JLabel controller_messages;
	PlayerChefView player_chef_ui;
	ScoreboardWidget scoreboard;
	
	public SushiGameView(SushiGameModel game_model) {
		setLayout(new BorderLayout());
		
		scoreboard = new ScoreboardWidget(game_model);
		add(scoreboard, BorderLayout.WEST);

		BeltView belt_view = new BeltView(game_model.getBelt());
		add(belt_view, BorderLayout.CENTER);
		
		player_chef_ui = new PlayerChefView();
		add(player_chef_ui, BorderLayout.EAST);
		
		JPanel bottom_panel = new JPanel();
		bottom_panel.setLayout(new BoxLayout(bottom_panel, BoxLayout.X_AXIS));
		
		JButton rotate_button = new JButton("Rotate belt");
		rotate_button.setActionCommand("rotate");
		rotate_button.addActionListener(this);
		
		bottom_panel.add(rotate_button);
		Border bottomConsolePanelBorder = BorderFactory.createLineBorder(java.awt.Color.decode("#C0C0C0"), 1);
		bottom_panel.setBorder(bottomConsolePanelBorder);
		
		Box.Filler consoleWhiteSpace = new Box.Filler(new Dimension(8, 5), new Dimension(8, 5), new Dimension(8, 5));
		bottom_panel.add(consoleWhiteSpace);
		
		controller_messages = new JLabel("Console messages will appear here throughout the game.");
		bottom_panel.add(controller_messages);
		
		add(bottom_panel, BorderLayout.SOUTH);
		
		rotation_request_listeners = new ArrayList<RotationRequestListener>();
		
		game_model.getBelt().registerBeltObserver(this);
	}
	
	public void registerPlayerChefListener(ChefViewListener cl) {
		player_chef_ui.registerChefListener(cl);
	}
	
	public void registerRotationRequestListener(RotationRequestListener rrl) {
		rotation_request_listeners.add(rrl);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("rotate")) {
			for (RotationRequestListener rrl : rotation_request_listeners) {
				rrl.handleRotationRequest();
			}
		}
	}
	
	public void setControllerMessage(String message) {
		controller_messages.setText(message);
	}
	
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			controller_messages.setText("");
		}
	}
	
	public void refreshScoreboard() {
		scoreboard.refresh();
	}
	
}