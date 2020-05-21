package sushigame.view;

import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.Chef;
import sushigame.model.SushiGameModel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

// This code was garbage. Fix up your code before requesting that I introduce new features
@SuppressWarnings("serial")
public class ScoreboardWidget extends JPanel implements BeltObserver, ActionListener {
	
	private SushiGameModel game_model;
	private JLabel[] chefRows;
	private Chef[] chefArray;
	private String currentSortingMethod = "balance";
	private String[] textArray;
	private JButton BalanceButton, FoodSoldButton, FoodSpoiledButton;
	
	public ScoreboardWidget(SushiGameModel gameModel) {
		game_model = gameModel;
		game_model.getBelt().registerBeltObserver(this);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		buildScoreboardTitlePanel();
		buildScoreboardLayoutButtons();
		BalanceButton.setEnabled(false);
		
		Chef[] opponentChefArray = game_model.getOpponentChefs();
		chefArray = new Chef[opponentChefArray.length  +1];
		chefArray[0] = game_model.getPlayerChef();
		for (int i = 1; i < chefArray.length; i++) {
			chefArray[i] = opponentChefArray[i  -1];
		}
		
		chefRows = new JLabel[chefArray.length];
		textArray = new String[chefArray.length];
		
		Border ScoreboardBorder = BorderFactory.createLineBorder(java.awt.Color.decode("#C0C0C0"), 1);
		this.setBorder(ScoreboardBorder);
		
		intiateChefRows();
	}
	
	private void buildScoreboardTitlePanel() {
		
		JLabel titlePanelLabel = new JLabel("<html>SCOREBOARD</html>");
		titlePanelLabel.setPreferredSize(new Dimension(300, 45));
		titlePanelLabel.setMaximumSize(new Dimension(300, 45));
		titlePanelLabel.setOpaque(true);
		titlePanelLabel.setBackground(java.awt.Color.decode("#8B0000"));
		titlePanelLabel.setForeground(java.awt.Color.decode("#B0C4DE"));
		titlePanelLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		titlePanelLabel.setAlignmentX(CENTER_ALIGNMENT);
		titlePanelLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		add(titlePanelLabel);
		
	}
	
	private void buildScoreboardLayoutButtons() {
		
		JPanel scoreboardHeaderPanel = new JPanel();
		
		JLabel scoreboardHeaderLabel = new JLabel("Select ordering method:");
		scoreboardHeaderLabel.setPreferredSize(new Dimension(285, 15));
		scoreboardHeaderLabel.setOpaque(false);
		scoreboardHeaderLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		scoreboardHeaderLabel.setAlignmentX(CENTER_ALIGNMENT);
		scoreboardHeaderLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		scoreboardHeaderPanel.add(scoreboardHeaderLabel);
		
		scoreboardHeaderPanel.setMaximumSize(scoreboardHeaderPanel.getPreferredSize());
		add(scoreboardHeaderPanel);
		
		JPanel scoreboardSelectButtonPanel = new JPanel();
		
		BalanceButton = new JButton("Balance");
		BalanceButton.setActionCommand("sort_by_balance");
		BalanceButton.addActionListener(this);
		scoreboardSelectButtonPanel.add(BalanceButton);
		
		FoodSoldButton = new JButton("Food sold");
		FoodSoldButton.setActionCommand("sort_by_food_sold");
		FoodSoldButton.addActionListener(this);
		scoreboardSelectButtonPanel.add(FoodSoldButton);
		
		FoodSpoiledButton = new JButton("Food spoiled");
		FoodSpoiledButton.setActionCommand("sort_by_food_spoiled");
		FoodSpoiledButton.addActionListener(this);
		scoreboardSelectButtonPanel.add(FoodSpoiledButton);
		
		scoreboardSelectButtonPanel.setMaximumSize(scoreboardSelectButtonPanel.getPreferredSize());
		
		add(scoreboardSelectButtonPanel);
		
	}
	
	private void intiateChefRows() {
		
		String decentWhiteSpace = "   ";
		
		for (int i = 0; i < chefArray.length; i++) {
			chefRows[i] = new JLabel();
			chefRows[i].setPreferredSize(new Dimension(270, 20));
			chefRows[i].setOpaque(true);
			chefRows[i].setBackground(java.awt.Color.decode("#B8860B"));
			chefRows[i].setAlignmentX(CENTER_ALIGNMENT);
			chefRows[i].setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
			chefRows[i].setFont(new Font("Dialog", Font.BOLD, 12));
			chefRows[i].setText(decentWhiteSpace + chefArray[i].getName());
			chefRows[i].setMaximumSize(chefRows[i].getPreferredSize());
			add(chefRows[i]);
			
			Component whiteSpaceGlue = Box.createVerticalGlue();
			whiteSpaceGlue.setPreferredSize(new Dimension(0, 5));
			whiteSpaceGlue.setMaximumSize(whiteSpaceGlue.getPreferredSize());
			add(whiteSpaceGlue);
		}
		
	}
	
	private void updateScoreboardData() {
		
		String decentWhiteSpace = "   ";
		
		if (currentSortingMethod.equals("balance")) {
			Arrays.sort(chefArray, new HighToLowBalanceComparator());
			for (int i = 0; i < chefArray.length; i ++) {
				textArray[i] = decentWhiteSpace + chefArray[i].getName() + " ($" + Math.round(chefArray[i].getBalance()*100.0)/100.0 + ")";
			}
		}
		if (currentSortingMethod.equals("food sold")) {
			Arrays.sort(chefArray, new HighToLowFoodSoldComparator());
			for (int i = 0; i < chefArray.length; i ++) {
				if (chefArray[i].getTotalFoodConsumed() == 1) {
					textArray[i] = decentWhiteSpace + chefArray[i].getName() + " (1 dish sold)";
				} else {
					textArray[i] = decentWhiteSpace + chefArray[i].getName() + " (" + chefArray[i].getTotalFoodConsumed() + " dishes sold)";
				}			
			}
		}
		if (currentSortingMethod.equals("food spoiled")) {
			Arrays.sort(chefArray, new LowToHighFoodSpoiledComparator());
			for (int i = 0; i < chefArray.length; i ++) {
				if (chefArray[i].getTotalFoodSpoiled() == 1) {
					textArray[i] = decentWhiteSpace + chefArray[i].getName() + " (1 dish spoiled)";
				} else {
					textArray[i] = decentWhiteSpace + chefArray[i].getName() + " (" + chefArray[i].getTotalFoodSpoiled() + " dishes spoiled)";
				}
			}
		}
		
	}
	
	public void actionPerformed(ActionEvent eventOfAction) {
		switch (eventOfAction.getActionCommand()) {
		case "sort_by_balance":
			currentSortingMethod = "balance";
			refresh();
			BalanceButton.setEnabled(false);
			FoodSoldButton.setEnabled(true);
			FoodSpoiledButton.setEnabled(true);
			break;
		case "sort_by_food_sold":
			currentSortingMethod = "food sold";
			refresh();
			FoodSoldButton.setEnabled(false);
			BalanceButton.setEnabled(true);
			FoodSpoiledButton.setEnabled(true);
			break;
		case "sort_by_food_spoiled":
			currentSortingMethod = "food spoiled";
			refresh();
			FoodSpoiledButton.setEnabled(false);
			BalanceButton.setEnabled(true);
			FoodSoldButton.setEnabled(true);
			break;
		}
	}
	
	public void refresh() {
		updateScoreboardData();
		for (int i = 0; i <chefRows.length; i++) {
			chefRows[i].setText(textArray[i]);
		}
	}
	
	public void handleBeltEvent(BeltEvent e) {
		refresh();		
	}
	
}