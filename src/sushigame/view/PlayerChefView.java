package sushigame.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import core.AvocadoPortion;
import core.CrabPortion;
import core.EelPortion;
import core.IngredientPortion;
import core.Nigiri;
import core.RicePortion;
import core.Roll;
import core.SalmonPortion;
import core.Sashimi;
import core.SeaweedPortion;
import core.ShrimpPortion;
import core.Sushi;
import core.TunaPortion;

@SuppressWarnings({"serial", "rawtypes", "unchecked" })
public class PlayerChefView extends JPanel implements ActionListener, ChangeListener {
	
	private List<ChefViewListener> ChefViewListenersArrayList;
	private Sushi superSushi;
	private JSlider goldPlateSlider;
	private JLabel goldPlateSliderValueLabel;
	private JTextField rollNameTextField;
	private JPanel rollNameTooLongNoticePanel;
	private JPanel rollNameCannotBeNullNoticePanel;
	private JPanel rollCannotBeNullNoticePanel;
	private JButton restartSushiBuilderButton;
	private Component whiteSpaceGlue;
	private double goldPlateFinalValue;
	private String rollNameFinalInput;
	private String sushiType;
	private String sushiDeepType;
	private String plateColor;
	private int positionToBe;
	private int rollNameTooLongNotificationCount;
	private int rollNameCannotBeNullNotificationCount;
	private int rollCannotBeNullNotificationCount;
	private boolean isSimpleSushi;
	private boolean isRoll;
	private boolean hasSushiSelectionBeenMade;
	private boolean hasTypeSelectionBeenMade;
	private boolean hasPlateSelectionBeenMade;
	private boolean hasFinishedAdjustingGoldPlateSlider;
	private boolean hasPositionSelectionBeenMade;
	private boolean hasRollNameBeenEntered;
	private boolean hasFinishedCreatingRollIngredients;
	private boolean hasRestartSushiBuilderButtonBeenDrawn;
	
	private double AvocadoPortionAmount, CrabPortionAmount, EelPortionAmount, RicePortionAmount, SalmonPortionAmount, SeaweedPortionAmount, ShrimpPortionAmount, TunaPortionAmount;
	private JSlider AvocadoSlider, CrabSlider, EelSlider, RiceSlider, SalmonSlider, SeaweedSlider, ShrimpSlider, TunaSlider;
	private JLabel AvocadoSliderValueLabel, CrabSliderValueLabel, EelSliderValueLabel, RiceSliderValueLabel, SalmonSliderValueLabel, SeaweedSliderValueLabel, ShrimpSliderValueLabel, TunaSliderValueLabel;
	private JPanel rollIngredientsHeaderPanel, rollIngredientsAvocadoPanel, rollIngredientsCrabPanel, rollIngredientsEelPanel, rollIngredientsRicePanel, rollIngredientsSalmonPanel, rollIngredientsSeaweedPanel, rollIngredientsShrimpPanel, rollIngredientsTunaPanel, rollIngredientsFooterPanel, rollIngredientsAddendumNoticePanel;
	private JLabel rollIngredientsAddendumNoticeLabel;
	
	public PlayerChefView() {
		ChefViewListenersArrayList = new ArrayList<ChefViewListener>();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Border ChefViewBorder = BorderFactory.createLineBorder(java.awt.Color.decode("#C0C0C0"), 1);
		this.setBorder(ChefViewBorder);
		
		buildChefViewTitlePanel();
		buildSushiSelectionButtons();
		
	}
	
	private void buildChefViewTitlePanel() {
		
		JLabel titlePanelLabel = new JLabel("<html>SUSHI BUILDER</html>");
		titlePanelLabel.setPreferredSize(new Dimension(300, 45));
		titlePanelLabel.setMaximumSize(new Dimension(300, 45));
		titlePanelLabel.setOpaque(true);
		titlePanelLabel.setBackground(java.awt.Color.decode("#0000CD"));
		titlePanelLabel.setForeground(java.awt.Color.decode("#B8860B"));
		titlePanelLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		titlePanelLabel.setAlignmentX(CENTER_ALIGNMENT);
		titlePanelLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		add(titlePanelLabel);
		
	}
	
	private void buildSushiSelectionButtons() {
		
		JPanel sushiSelectionHeaderPanel = new JPanel();
		
		JLabel sushiSelectionHeaderLabel = new JLabel("<html>Select sushi type:</html>");
		sushiSelectionHeaderLabel.setPreferredSize(new Dimension(280, 45));
		sushiSelectionHeaderLabel.setOpaque(true);
		sushiSelectionHeaderLabel.setBackground(java.awt.Color.decode("#FFA500"));
		sushiSelectionHeaderLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		sushiSelectionHeaderLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		sushiSelectionHeaderPanel.add(sushiSelectionHeaderLabel);
		
		JPanel sushiSelectionButtonsPanel = new JPanel();
		
		JButton sashimi_button = new JButton("Sashimi");
		sashimi_button.setActionCommand("sushi_selection_sashimi_button_press");
		sashimi_button.addActionListener(this);
		sushiSelectionButtonsPanel.add(sashimi_button);
		
		JButton nigiri_button = new JButton("Nigiri");
		nigiri_button.setActionCommand("sushi_selection_nigiri_button_press");
		nigiri_button.addActionListener(this);
		sushiSelectionButtonsPanel.add(nigiri_button);
		
		JButton roll_button = new JButton("Roll");
		roll_button.setActionCommand("sushi_selection_roll_button_press");
		roll_button.addActionListener(this);
		sushiSelectionButtonsPanel.add(roll_button);
		
		sushiSelectionHeaderPanel.setMaximumSize(sushiSelectionHeaderPanel.getPreferredSize());
		sushiSelectionButtonsPanel.setMaximumSize(sushiSelectionButtonsPanel.getPreferredSize());
		
		add(sushiSelectionHeaderPanel);
		add(sushiSelectionButtonsPanel);
		
	}
	
	private void buildTypeSelectionButtons() {
		
		JPanel typeSelectionHeaderPanel = new JPanel();
		
		JLabel typeSelectionHeaderLabel = new JLabel("<html>Select " + sushiType + " type:</html>");
		typeSelectionHeaderLabel.setPreferredSize(new Dimension(280, 45));
		typeSelectionHeaderLabel.setOpaque(true);
		typeSelectionHeaderLabel.setBackground(java.awt.Color.decode("#FFA500"));
		typeSelectionHeaderLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		typeSelectionHeaderLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		typeSelectionHeaderPanel.add(typeSelectionHeaderLabel);
		
		JPanel typeSelectionButtonsPanel = new JPanel();
		
		JButton tuna_button = new JButton("Tuna");
		tuna_button.setActionCommand("type_selection_tuna_button_press");
		tuna_button.addActionListener(this);
		typeSelectionButtonsPanel.add(tuna_button);
		
		JButton salmon_button = new JButton("Salmon");
		salmon_button.setActionCommand("type_selection_salmon_button_press");
		salmon_button.addActionListener(this);
		typeSelectionButtonsPanel.add(salmon_button);
		
		JButton eel_button = new JButton("Eel");
		eel_button.setActionCommand("type_selection_eel_button_press");
		eel_button.addActionListener(this);
		typeSelectionButtonsPanel.add(eel_button);
		
		JButton crab_button = new JButton("Crab");
		crab_button.setActionCommand("type_selection_crab_button_press");
		crab_button.addActionListener(this);
		typeSelectionButtonsPanel.add(crab_button);
		
		JButton shrimp_button = new JButton("Shrimp");
		shrimp_button.setActionCommand("type_selection_shrimp_button_press");
		shrimp_button.addActionListener(this);
		typeSelectionButtonsPanel.add(shrimp_button);
		
		typeSelectionHeaderPanel.setMaximumSize(typeSelectionHeaderPanel.getPreferredSize());
		typeSelectionButtonsPanel.setMaximumSize(typeSelectionButtonsPanel.getPreferredSize());
		
		add(typeSelectionHeaderPanel);
		add(typeSelectionButtonsPanel);
		
	}
	
	private void createSushi() {
		
		if (sushiType.equals("sashimi")) {
			if (sushiDeepType.equals("tuna")) {
				superSushi = new Sashimi(Sashimi.SashimiType.TUNA);
			}
			if (sushiDeepType.equals("salmon")) {
				superSushi = new Sashimi(Sashimi.SashimiType.SALMON);
			}
			if (sushiDeepType.equals("eel")) {
				superSushi = new Sashimi(Sashimi.SashimiType.EEL);
			}
			if (sushiDeepType.equals("crab")) {
				superSushi = new Sashimi(Sashimi.SashimiType.CRAB);
			}
			if (sushiDeepType.equals("shrimp")) {
				superSushi = new Sashimi(Sashimi.SashimiType.SHRIMP);
			}
		}
		
		if (sushiType.equals("nigiri")) {
			if (sushiDeepType.equals("tuna")) {
				superSushi = new Nigiri(Nigiri.NigiriType.TUNA);
			}
			if (sushiDeepType.equals("salmon")) {
				superSushi = new Nigiri(Nigiri.NigiriType.SALMON);
			}
			if (sushiDeepType.equals("eel")) {
				superSushi = new Nigiri(Nigiri.NigiriType.EEL);
			}
			if (sushiDeepType.equals("crab")) {
				superSushi = new Nigiri(Nigiri.NigiriType.CRAB);
			}
			if (sushiDeepType.equals("shrimp")) {
				superSushi = new Nigiri(Nigiri.NigiriType.SHRIMP);
			}
		}
		
		if (sushiType.equals("roll")) {
			buildRoll();
		}
		
	}
	
	private void buildPlateChoiceSelectionButtons() {
		
		JPanel plateSelectionHeaderPanel = new JPanel();
		
		JLabel plateSelectionHeaderLabel = new JLabel();
		if (isSimpleSushi) {
			plateSelectionHeaderLabel.setText("<html>Select plate color for " + sushiDeepType + " " + sushiType + ":</html>");
		} else if (isRoll ) {
			plateSelectionHeaderLabel.setText("<html>Select plate color for " + rollNameFinalInput + " " + sushiType + ":</html>");
		}
		plateSelectionHeaderLabel.setPreferredSize(new Dimension(280, 45));
		plateSelectionHeaderLabel.setOpaque(true);
		plateSelectionHeaderLabel.setBackground(java.awt.Color.decode("#FFA500"));
		plateSelectionHeaderLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		plateSelectionHeaderLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		plateSelectionHeaderPanel.add(plateSelectionHeaderLabel);
		
		JPanel plateSelectionButtonsPanel = new JPanel();
		
		JButton red_button = new JButton("Red");
		red_button.setActionCommand("plate_selection_red_button_press");
		red_button.addActionListener(this);
		plateSelectionButtonsPanel.add(red_button);
		
		JButton blue_button = new JButton("Blue");
		blue_button.setActionCommand("plate_selection_blue_button_press");
		blue_button.addActionListener(this);
		plateSelectionButtonsPanel.add(blue_button);
		
		JButton green_button = new JButton("Green");
		green_button.setActionCommand("plate_selection_green_button_press");
		green_button.addActionListener(this);
		plateSelectionButtonsPanel.add(green_button);
		
		JButton gold_button = new JButton("Gold");
		gold_button.setActionCommand("plate_selection_gold_button_press");
		gold_button.addActionListener(this);
		plateSelectionButtonsPanel.add(gold_button);
		
		plateSelectionHeaderPanel.setMaximumSize(plateSelectionHeaderPanel.getPreferredSize());
		plateSelectionButtonsPanel.setMaximumSize(plateSelectionButtonsPanel.getPreferredSize());
		
		add(plateSelectionHeaderPanel);
		add(plateSelectionButtonsPanel);
		
	}
	
	private void buildGoldPlateSlider() {
		
		JPanel goldPlateSliderHeaderPanel = new JPanel();
		
		JLabel goldPlateSliderHeaderLabel = new JLabel("<html>Adjust the price of the gold plate:</html>");
		goldPlateSliderHeaderLabel.setPreferredSize(new Dimension(280, 35));
		goldPlateSliderHeaderLabel.setOpaque(true);
		goldPlateSliderHeaderLabel.setBackground(java.awt.Color.decode("#FFA500"));
		goldPlateSliderHeaderLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		goldPlateSliderHeaderLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JPanel goldPlateSliderPanel = new JPanel();
		
		goldPlateSlider = new JSlider(50,100);
		goldPlateSlider.addChangeListener(this);
		goldPlateSlider.setPaintTicks(true);
		goldPlateSlider.setPaintLabels(true);
		
		Hashtable sliderLabels = new Hashtable();
		sliderLabels.put( new Integer(50), new JLabel("5") );
		sliderLabels.put( new Integer(60), new JLabel("6") );
		sliderLabels.put( new Integer(70), new JLabel("7") );
		sliderLabels.put( new Integer(80), new JLabel("8") );
		sliderLabels.put( new Integer(90), new JLabel("9") );
		sliderLabels.put( new Integer(100), new JLabel("10") );
		goldPlateSlider.setLabelTable(sliderLabels);
		
		JPanel goldPlateValueAndSelectPanel = new JPanel();
		
		goldPlateSliderValueLabel = new JLabel(Double.toString(goldPlateSlider.getValue() / 10.0));
		goldPlateSliderValueLabel.setPreferredSize(new Dimension(35, 35));
		goldPlateSliderValueLabel.setOpaque(false);
		goldPlateSliderValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		goldPlateSliderValueLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		
		JButton goldPlateSelectButton = new JButton("Select");
		goldPlateSelectButton.setActionCommand("finished_adjusting_goldPlate_slider");
		goldPlateSelectButton.addActionListener(this);
		
		goldPlateSliderHeaderLabel.setMaximumSize(goldPlateSliderHeaderLabel.getPreferredSize());
		goldPlateSlider.setMaximumSize(goldPlateSlider.getPreferredSize());
		goldPlateSliderValueLabel.setMaximumSize(goldPlateSliderValueLabel.getPreferredSize());
		goldPlateSelectButton.setMaximumSize(goldPlateSelectButton.getPreferredSize());
		
		goldPlateSliderHeaderPanel.add(goldPlateSliderHeaderLabel);
		goldPlateSliderPanel.add(goldPlateSlider);
		goldPlateValueAndSelectPanel.add(goldPlateSliderValueLabel);
		goldPlateValueAndSelectPanel.add(goldPlateSelectButton);
		
		goldPlateSliderHeaderPanel.setMaximumSize(goldPlateSliderHeaderPanel.getPreferredSize());
		goldPlateSliderPanel.setMaximumSize(goldPlateSliderPanel.getPreferredSize());
		goldPlateValueAndSelectPanel.setMaximumSize(goldPlateValueAndSelectPanel.getPreferredSize());
		
		add(goldPlateSliderHeaderPanel);
		add(goldPlateSliderPanel);
		add(goldPlateValueAndSelectPanel);
		
	}
	
	private void buildPositionChoiceButtons() {
			
		JPanel positionChoiceButtonsHeaderPanel = new JPanel();
		
		JLabel positionChoiceButtonsHeaderLabel = new JLabel();
		if (isSimpleSushi) {
			positionChoiceButtonsHeaderLabel.setText("<html>Select a position to place " + sushiDeepType + " " + sushiType + " on a " + plateColor + " plate nearest to:</html>");
		} else if (isRoll) {
			positionChoiceButtonsHeaderLabel.setText("<html>Select a position to place " + rollNameFinalInput + " on a " + plateColor + " plate nearest to:</html>");
		}
		
		positionChoiceButtonsHeaderLabel.setPreferredSize(new Dimension(280, 45));
		positionChoiceButtonsHeaderLabel.setOpaque(true);
		positionChoiceButtonsHeaderLabel.setBackground(java.awt.Color.decode("#FFA500"));
		positionChoiceButtonsHeaderLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		positionChoiceButtonsHeaderLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		positionChoiceButtonsHeaderPanel.add(positionChoiceButtonsHeaderLabel);
		
		JPanel positionChoiceButtonsPanel_One = new JPanel();
		
		JButton button_1 = new JButton("1");
		button_1.setActionCommand("position_selection_button_1_press");
		button_1.addActionListener(this);
		positionChoiceButtonsPanel_One.add(button_1);
		
		JButton button_2 = new JButton("2");
		button_2.setActionCommand("position_selection_button_2_press");
		button_2.addActionListener(this);
		positionChoiceButtonsPanel_One.add(button_2);
		
		JButton button_3 = new JButton("3");
		button_3.setActionCommand("position_selection_button_3_press");
		button_3.addActionListener(this);
		positionChoiceButtonsPanel_One.add(button_3);
		
		JButton button_4 = new JButton("4");
		button_4.setActionCommand("position_selection_button_4_press");
		button_4.addActionListener(this);
		positionChoiceButtonsPanel_One.add(button_4);
		
		JButton button_5 = new JButton("5");
		button_5.setActionCommand("position_selection_button_5_press");
		button_5.addActionListener(this);
		positionChoiceButtonsPanel_One.add(button_5);
		
		JPanel positionChoiceButtonsPanel_Two = new JPanel();
		
		JButton button_6 = new JButton("6");
		button_6.setActionCommand("position_selection_button_6_press");
		button_6.addActionListener(this);
		positionChoiceButtonsPanel_Two.add(button_6);
		
		JButton button_7 = new JButton("7");
		button_7.setActionCommand("position_selection_button_7_press");
		button_7.addActionListener(this);
		positionChoiceButtonsPanel_Two.add(button_7);
		
		JButton button_8 = new JButton("8");
		button_8.setActionCommand("position_selection_button_8_press");
		button_8.addActionListener(this);
		positionChoiceButtonsPanel_Two.add(button_8);
		
		JButton button_9 = new JButton("9");
		button_9.setActionCommand("position_selection_button_9_press");
		button_9.addActionListener(this);
		positionChoiceButtonsPanel_Two.add(button_9);
		
		JButton button_10 = new JButton("10");
		button_10.setActionCommand("position_selection_button_10_press");
		button_10.addActionListener(this);
		positionChoiceButtonsPanel_Two.add(button_10);
		
		JPanel positionChoiceButtonsPanel_Three = new JPanel();
		
		JButton button_11 = new JButton("11");
		button_11.setActionCommand("position_selection_button_11_press");
		button_11.addActionListener(this);
		positionChoiceButtonsPanel_Three.add(button_11);
		
		JButton button_12 = new JButton("12");
		button_12.setActionCommand("position_selection_button_12_press");
		button_12.addActionListener(this);
		positionChoiceButtonsPanel_Three.add(button_12);
		
		JButton button_13 = new JButton("13");
		button_13.setActionCommand("position_selection_button_13_press");
		button_13.addActionListener(this);
		positionChoiceButtonsPanel_Three.add(button_13);
		
		JButton button_14 = new JButton("14");
		button_14.setActionCommand("position_selection_button_14_press");
		button_14.addActionListener(this);
		positionChoiceButtonsPanel_Three.add(button_14);
		
		JButton button_15 = new JButton("15");
		button_15.setActionCommand("position_selection_button_15_press");
		button_15.addActionListener(this);
		positionChoiceButtonsPanel_Three.add(button_15);
		
		JPanel positionChoiceButtonsPanel_Four = new JPanel();
		
		JButton button_16 = new JButton("16");
		button_16.setActionCommand("position_selection_button_16_press");
		button_16.addActionListener(this);
		positionChoiceButtonsPanel_Four.add(button_16);
		
		JButton button_17 = new JButton("17");
		button_17.setActionCommand("position_selection_button_17_press");
		button_17.addActionListener(this);
		positionChoiceButtonsPanel_Four.add(button_17);
		
		JButton button_18 = new JButton("18");
		button_18.setActionCommand("position_selection_button_18_press");
		button_18.addActionListener(this);
		positionChoiceButtonsPanel_Four.add(button_18);
		
		JButton button_19 = new JButton("19");
		button_19.setActionCommand("position_selection_button_19_press");
		button_19.addActionListener(this);
		positionChoiceButtonsPanel_Four.add(button_19);
		
		JButton button_20 = new JButton("20");
		button_20.setActionCommand("position_selection_button_20_press");
		button_20.addActionListener(this);
		positionChoiceButtonsPanel_Four.add(button_20);
		
		positionChoiceButtonsHeaderPanel.setMaximumSize(positionChoiceButtonsHeaderPanel.getPreferredSize());
		positionChoiceButtonsPanel_One.setMaximumSize(positionChoiceButtonsPanel_One.getPreferredSize());
		positionChoiceButtonsPanel_Two.setMaximumSize(positionChoiceButtonsPanel_Two.getPreferredSize());
		positionChoiceButtonsPanel_Three.setMaximumSize(positionChoiceButtonsPanel_Three.getPreferredSize());
		positionChoiceButtonsPanel_Four.setMaximumSize(positionChoiceButtonsPanel_Four.getPreferredSize());
		
		add(positionChoiceButtonsHeaderPanel);
		add(positionChoiceButtonsPanel_One);
		add(positionChoiceButtonsPanel_Two);
		add(positionChoiceButtonsPanel_Three);
		add(positionChoiceButtonsPanel_Four);
		
	}
	
	private void buildRollNameTextField() {
		
		JPanel rollNameTextFieldHeaderPanel = new JPanel();
		
		JLabel rollNameTextFieldHeaderLabel = new JLabel("<html>Enter the name of your roll:</html>");
		rollNameTextFieldHeaderLabel.setPreferredSize(new Dimension(280, 35));
		rollNameTextFieldHeaderLabel.setOpaque(true);
		rollNameTextFieldHeaderLabel.setBackground(java.awt.Color.decode("#FFA500"));
		rollNameTextFieldHeaderLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		rollNameTextFieldHeaderLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		rollNameTextFieldHeaderPanel.add(rollNameTextFieldHeaderLabel);
		
		JPanel rollNameTextFieldPanel = new JPanel();
		
		rollNameTextField = new JTextField("");
		rollNameTextField.setActionCommand("roll_name_entered");
		rollNameTextField.addActionListener(this);
		rollNameTextField.setColumns(15);
		
		rollNameTextFieldPanel.add(rollNameTextField);
		
		JPanel rollNameTextFieldFooterPanel = new JPanel();
		
		JLabel rollNameTextFieldFooterLabel = new JLabel("<html>Enter to continue</html>");
		rollNameTextFieldFooterLabel.setPreferredSize(new Dimension(180, 25));
		rollNameTextFieldFooterLabel.setOpaque(true);
		rollNameTextFieldFooterLabel.setBackground(java.awt.Color.decode("#DEB887"));
		rollNameTextFieldFooterLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		rollNameTextFieldFooterLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		
		rollNameTextFieldFooterPanel.add(rollNameTextFieldFooterLabel);
		
		rollNameTextFieldHeaderPanel.setMaximumSize(rollNameTextFieldHeaderPanel.getPreferredSize());
		rollNameTextFieldPanel.setMaximumSize(rollNameTextFieldPanel.getPreferredSize());
		rollNameTextFieldFooterPanel.setMaximumSize(rollNameTextFieldFooterPanel.getPreferredSize());
		
		add(rollNameTextFieldHeaderPanel);
		add(rollNameTextFieldPanel);
		add(rollNameTextFieldFooterPanel);
		
	}
	
	private void buildRollIngredientsInterface() {
		
		rollIngredientsHeaderPanel = new JPanel();
		
		JLabel BuildRollIngredientsInfoLabel = new JLabel("<html>Select ingredients to make your roll:</html>");
		BuildRollIngredientsInfoLabel.setPreferredSize(new Dimension(280, 35));
		BuildRollIngredientsInfoLabel.setOpaque(true);
		BuildRollIngredientsInfoLabel.setBackground(java.awt.Color.decode("#FFA500"));
		BuildRollIngredientsInfoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		BuildRollIngredientsInfoLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		rollIngredientsHeaderPanel.add(BuildRollIngredientsInfoLabel);
		
		Box.Filler sliderWhiteSpace = new Box.Filler(new Dimension(5, 5), new Dimension(5, 5), new Dimension(5, 5));
		
		rollIngredientsAvocadoPanel = new JPanel();
		
		AvocadoSliderValueLabel = new JLabel("0");
		AvocadoSliderValueLabel.setText("0.0");
		AvocadoSliderValueLabel.setPreferredSize(new Dimension(35, 35));
		AvocadoSliderValueLabel.setOpaque(false);
		AvocadoSliderValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		AvocadoSliderValueLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		
		JLabel avocadoUnitsLabel = new JLabel("<html>  oz of Avocado:</html>");
		avocadoUnitsLabel.setPreferredSize(new Dimension(100, 20));
		avocadoUnitsLabel.setOpaque(false);
		avocadoUnitsLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		avocadoUnitsLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		
		AvocadoSlider = new JSlider(0, 150, 0);
		AvocadoSlider.addChangeListener(this);
		AvocadoSlider.setPaintTicks(true);
		AvocadoSlider.setPaintLabels(true);
		
		Hashtable avocadoSliderLabels = new Hashtable();
		avocadoSliderLabels.put(new Integer(0), new JLabel("0"));
		avocadoSliderLabels.put(new Integer(50), new JLabel("0.5"));
		avocadoSliderLabels.put(new Integer(100), new JLabel("1.0"));
		avocadoSliderLabels.put(new Integer(150), new JLabel("1.5"));
		AvocadoSlider.setLabelTable(avocadoSliderLabels);
		
		rollIngredientsAvocadoPanel.add(AvocadoSliderValueLabel);
		rollIngredientsAvocadoPanel.add(avocadoUnitsLabel);
		rollIngredientsAvocadoPanel.add(AvocadoSlider);
		rollIngredientsAvocadoPanel.add(sliderWhiteSpace);
		
		rollIngredientsCrabPanel = new JPanel();
		
		CrabSliderValueLabel = new JLabel("0");
		CrabSliderValueLabel.setText("0.0");
		CrabSliderValueLabel.setPreferredSize(new Dimension(35, 35));
		CrabSliderValueLabel.setOpaque(false);
		CrabSliderValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		CrabSliderValueLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		
		JLabel crabUnitsLabel = new JLabel("<html>oz of Crab:</html>");
		crabUnitsLabel.setPreferredSize(new Dimension(100, 20));
		crabUnitsLabel.setOpaque(false);
		crabUnitsLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		crabUnitsLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		
		CrabSlider = new JSlider(0, 150, 0);
		CrabSlider.addChangeListener(this);
		CrabSlider.setPaintTicks(true);
		CrabSlider.setPaintLabels(true);
		
		Hashtable crabSliderLabels = new Hashtable();
		crabSliderLabels.put(new Integer(0), new JLabel("0"));
		crabSliderLabels.put(new Integer(50), new JLabel("0.5"));
		crabSliderLabels.put(new Integer(100), new JLabel("1.0"));
		crabSliderLabels.put(new Integer(150), new JLabel("1.5"));
		CrabSlider.setLabelTable(crabSliderLabels);
		
		rollIngredientsCrabPanel.add(CrabSliderValueLabel);
		rollIngredientsCrabPanel.add(crabUnitsLabel);
		rollIngredientsCrabPanel.add(CrabSlider);
		rollIngredientsCrabPanel.add(sliderWhiteSpace);
		
		rollIngredientsEelPanel = new JPanel();
		
		EelSliderValueLabel = new JLabel("0");
		EelSliderValueLabel.setText("0.0");
		EelSliderValueLabel.setPreferredSize(new Dimension(35, 35));
		EelSliderValueLabel.setOpaque(false);
		EelSliderValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		EelSliderValueLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		
		JLabel eelUnitsLabel = new JLabel("<html>oz of Eel:</html>");
		eelUnitsLabel.setPreferredSize(new Dimension(100, 20));
		eelUnitsLabel.setOpaque(false);
		eelUnitsLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		eelUnitsLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		
		EelSlider = new JSlider(0, 150, 0);
		EelSlider.addChangeListener(this);
		EelSlider.setPaintTicks(true);
		EelSlider.setPaintLabels(true);
		
		Hashtable eelSliderLabels = new Hashtable();
		eelSliderLabels.put(new Integer(0), new JLabel("0"));
		eelSliderLabels.put(new Integer(50), new JLabel("0.5"));
		eelSliderLabels.put(new Integer(100), new JLabel("1.0"));
		eelSliderLabels.put(new Integer(150), new JLabel("1.5"));
		EelSlider.setLabelTable(eelSliderLabels);
		
		rollIngredientsEelPanel.add(EelSliderValueLabel);
		rollIngredientsEelPanel.add(eelUnitsLabel);
		rollIngredientsEelPanel.add(EelSlider);
		rollIngredientsEelPanel.add(sliderWhiteSpace);
		
		rollIngredientsRicePanel = new JPanel();
		
		RiceSliderValueLabel = new JLabel("0");
		RiceSliderValueLabel.setText("0.0");
		RiceSliderValueLabel.setPreferredSize(new Dimension(35, 35));
		RiceSliderValueLabel.setOpaque(false);
		RiceSliderValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		RiceSliderValueLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		
		JLabel riceUnitsLabel = new JLabel("<html>oz of Rice:</html>");
		riceUnitsLabel.setPreferredSize(new Dimension(100, 20));
		riceUnitsLabel.setOpaque(false);
		riceUnitsLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		riceUnitsLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		
		RiceSlider = new JSlider(0, 150, 0);
		RiceSlider.addChangeListener(this);
		RiceSlider.setPaintTicks(true);
		RiceSlider.setPaintLabels(true);
		
		Hashtable riceSliderLabels = new Hashtable();
		riceSliderLabels.put(new Integer(0), new JLabel("0"));
		riceSliderLabels.put(new Integer(50), new JLabel("0.5"));
		riceSliderLabels.put(new Integer(100), new JLabel("1.0"));
		riceSliderLabels.put(new Integer(150), new JLabel("1.5"));
		RiceSlider.setLabelTable(riceSliderLabels);
		
		rollIngredientsRicePanel.add(RiceSliderValueLabel);
		rollIngredientsRicePanel.add(riceUnitsLabel);
		rollIngredientsRicePanel.add(RiceSlider);
		rollIngredientsRicePanel.add(sliderWhiteSpace);
		
		rollIngredientsSalmonPanel = new JPanel();
		
		SalmonSliderValueLabel = new JLabel("0");
		SalmonSliderValueLabel.setText("0.0");
		SalmonSliderValueLabel.setPreferredSize(new Dimension(35, 35));
		SalmonSliderValueLabel.setOpaque(false);
		SalmonSliderValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		SalmonSliderValueLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		
		JLabel salmonUnitsLabel = new JLabel("<html>oz of Salmon:</html>");
		salmonUnitsLabel.setPreferredSize(new Dimension(100, 20));
		salmonUnitsLabel.setOpaque(false);
		salmonUnitsLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		salmonUnitsLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		
		SalmonSlider = new JSlider(0, 150, 0);
		SalmonSlider.addChangeListener(this);
		SalmonSlider.setPaintTicks(true);
		SalmonSlider.setPaintLabels(true);
		
		Hashtable salmonSliderLabels = new Hashtable();
		salmonSliderLabels.put(new Integer(0), new JLabel("0"));
		salmonSliderLabels.put(new Integer(50), new JLabel("0.5"));
		salmonSliderLabels.put(new Integer(100), new JLabel("1.0"));
		salmonSliderLabels.put(new Integer(150), new JLabel("1.5"));
		SalmonSlider.setLabelTable(salmonSliderLabels);
		
		rollIngredientsSalmonPanel.add(SalmonSliderValueLabel);
		rollIngredientsSalmonPanel.add(salmonUnitsLabel);
		rollIngredientsSalmonPanel.add(SalmonSlider);
		rollIngredientsSalmonPanel.add(sliderWhiteSpace);
		
		rollIngredientsSeaweedPanel = new JPanel();
		
		SeaweedSliderValueLabel = new JLabel("0");
		SeaweedSliderValueLabel.setText("0.0");
		SeaweedSliderValueLabel.setPreferredSize(new Dimension(35, 35));
		SeaweedSliderValueLabel.setOpaque(false);
		SeaweedSliderValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		SeaweedSliderValueLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		
		JLabel seaweedUnitsLabel = new JLabel("<html>oz of Seaweed:</html>");
		seaweedUnitsLabel.setPreferredSize(new Dimension(100, 20));
		seaweedUnitsLabel.setOpaque(false);
		seaweedUnitsLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		seaweedUnitsLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		
		SeaweedSlider = new JSlider(0, 150, 0);
		SeaweedSlider.addChangeListener(this);
		SeaweedSlider.setPaintTicks(true);
		SeaweedSlider.setPaintLabels(true);
		
		Hashtable seaweedSliderLabels = new Hashtable();
		seaweedSliderLabels.put(new Integer(0), new JLabel("0"));
		seaweedSliderLabels.put(new Integer(50), new JLabel("0.5"));
		seaweedSliderLabels.put(new Integer(100), new JLabel("1.0"));
		seaweedSliderLabels.put(new Integer(150), new JLabel("1.5"));
		SeaweedSlider.setLabelTable(seaweedSliderLabels);
		
		rollIngredientsSeaweedPanel.add(SeaweedSliderValueLabel);
		rollIngredientsSeaweedPanel.add(seaweedUnitsLabel);
		rollIngredientsSeaweedPanel.add(SeaweedSlider);
		rollIngredientsSeaweedPanel.add(sliderWhiteSpace);
		
		rollIngredientsShrimpPanel = new JPanel();
		
		ShrimpSliderValueLabel = new JLabel("0");
		ShrimpSliderValueLabel.setText("0.0");
		ShrimpSliderValueLabel.setPreferredSize(new Dimension(35, 35));
		ShrimpSliderValueLabel.setOpaque(false);
		ShrimpSliderValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		ShrimpSliderValueLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		
		JLabel shrimpUnitsLabel = new JLabel("<html>oz of Shrimp:</html>");
		shrimpUnitsLabel.setPreferredSize(new Dimension(100, 20));
		shrimpUnitsLabel.setOpaque(false);
		shrimpUnitsLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		shrimpUnitsLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		
		ShrimpSlider = new JSlider(0, 150, 0);
		ShrimpSlider.addChangeListener(this);
		ShrimpSlider.setPaintTicks(true);
		ShrimpSlider.setPaintLabels(true);
		
		Hashtable shrimpSliderLabels = new Hashtable();
		shrimpSliderLabels.put(new Integer(0), new JLabel("0"));
		shrimpSliderLabels.put(new Integer(50), new JLabel("0.5"));
		shrimpSliderLabels.put(new Integer(100), new JLabel("1.0"));
		shrimpSliderLabels.put(new Integer(150), new JLabel("1.5"));
		ShrimpSlider.setLabelTable(shrimpSliderLabels);
		
		rollIngredientsShrimpPanel.add(ShrimpSliderValueLabel);
		rollIngredientsShrimpPanel.add(shrimpUnitsLabel);
		rollIngredientsShrimpPanel.add(ShrimpSlider);
		rollIngredientsShrimpPanel.add(sliderWhiteSpace);
		
		rollIngredientsTunaPanel = new JPanel();
		
		TunaSliderValueLabel = new JLabel("0");
		TunaSliderValueLabel.setText("0.0");
		TunaSliderValueLabel.setPreferredSize(new Dimension(35, 35));
		TunaSliderValueLabel.setOpaque(false);
		TunaSliderValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		TunaSliderValueLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		
		JLabel tunaUnitsLabel = new JLabel("<html>oz of Tuna:</html>");
		tunaUnitsLabel.setPreferredSize(new Dimension(100, 20));
		tunaUnitsLabel.setOpaque(false);
		tunaUnitsLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		tunaUnitsLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		
		TunaSlider = new JSlider(0, 150, 0);
		TunaSlider.addChangeListener(this);
		TunaSlider.setPaintTicks(true);
		TunaSlider.setPaintLabels(true);
		
		Hashtable tunaSliderLabels = new Hashtable();
		tunaSliderLabels.put(new Integer(0), new JLabel("0"));
		tunaSliderLabels.put(new Integer(50), new JLabel("0.5"));
		tunaSliderLabels.put(new Integer(100), new JLabel("1.0"));
		tunaSliderLabels.put(new Integer(150), new JLabel("1.5"));
		TunaSlider.setLabelTable(tunaSliderLabels);
		
		rollIngredientsTunaPanel.add(TunaSliderValueLabel);
		rollIngredientsTunaPanel.add(tunaUnitsLabel);
		rollIngredientsTunaPanel.add(TunaSlider);
		
		rollIngredientsFooterPanel = new JPanel();
		
		JButton rollIngredientFinishButton = new JButton("Ingredient Select");
		rollIngredientFinishButton.setActionCommand("finished_creating_roll_ingredients");
		rollIngredientFinishButton.addActionListener(this);
		
		rollIngredientsFooterPanel.add(rollIngredientFinishButton);
		
		rollIngredientsAddendumNoticePanel = new JPanel();
		
		rollIngredientsAddendumNoticeLabel = new JLabel("<html>The ingredient panel will be removed after selecting ingredients to conserve space.</html>");
		rollIngredientsAddendumNoticeLabel.setPreferredSize(new Dimension(280, 30));
		rollIngredientsAddendumNoticeLabel.setOpaque(true);
		rollIngredientsAddendumNoticeLabel.setBackground(java.awt.Color.decode("#FF0000"));
		rollIngredientsAddendumNoticeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		rollIngredientsAddendumNoticeLabel.setAlignmentX(CENTER_ALIGNMENT);
		rollIngredientsAddendumNoticeLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		rollIngredientsAddendumNoticePanel.add(rollIngredientsAddendumNoticeLabel);
		
		rollIngredientsHeaderPanel.setMaximumSize(rollIngredientsHeaderPanel.getPreferredSize());
		rollIngredientsAvocadoPanel.setMaximumSize(rollIngredientsAvocadoPanel.getPreferredSize());
		rollIngredientsCrabPanel.setMaximumSize(rollIngredientsCrabPanel.getPreferredSize());
		rollIngredientsEelPanel.setMaximumSize(rollIngredientsEelPanel.getPreferredSize());
		rollIngredientsRicePanel.setMaximumSize(rollIngredientsRicePanel.getPreferredSize());
		rollIngredientsSalmonPanel.setMaximumSize(rollIngredientsSalmonPanel.getPreferredSize());
		rollIngredientsSeaweedPanel.setMaximumSize(rollIngredientsSeaweedPanel.getPreferredSize());
		rollIngredientsShrimpPanel.setMaximumSize(rollIngredientsShrimpPanel.getPreferredSize());
		rollIngredientsTunaPanel.setMaximumSize(rollIngredientsTunaPanel.getPreferredSize());
		rollIngredientsFooterPanel.setMaximumSize(rollIngredientsFooterPanel.getPreferredSize());
		rollIngredientsAddendumNoticeLabel.setMaximumSize(rollIngredientsAddendumNoticeLabel.getPreferredSize());
		
		add(rollIngredientsHeaderPanel);
		add(rollIngredientsAvocadoPanel);
		add(rollIngredientsCrabPanel);
		add(rollIngredientsEelPanel);
		add(rollIngredientsRicePanel);
		add(rollIngredientsSalmonPanel);
		add(rollIngredientsSeaweedPanel);
		add(rollIngredientsShrimpPanel);
		add(rollIngredientsTunaPanel);
		add(rollIngredientsFooterPanel);
		add(rollIngredientsAddendumNoticeLabel);
		
	}
	
	private void buildRoll() {
		
		IngredientPortion[] rollIngredientPortionArray = new IngredientPortion[8];
		if (!(AvocadoPortionAmount == 0)) {
			rollIngredientPortionArray[0] = new AvocadoPortion(AvocadoPortionAmount);
		}
		if (!(CrabPortionAmount == 0)) {
			rollIngredientPortionArray[1] = new CrabPortion(CrabPortionAmount);
		}
		if (!(EelPortionAmount == 0)) {
			rollIngredientPortionArray[2] = new EelPortion(EelPortionAmount);
		}
		if (!(RicePortionAmount == 0)) {
			rollIngredientPortionArray[3] = new RicePortion(RicePortionAmount);
		}
		if (!(SalmonPortionAmount == 0)) {
			rollIngredientPortionArray[4] = new SalmonPortion(SalmonPortionAmount);
		}
		if (!(SeaweedPortionAmount == 0)) {
			rollIngredientPortionArray[5] = new SeaweedPortion(SeaweedPortionAmount);
		}
		if (!(ShrimpPortionAmount == 0)) {
			rollIngredientPortionArray[6] = new ShrimpPortion(ShrimpPortionAmount);
		}
		if (!(TunaPortionAmount == 0)) {
			rollIngredientPortionArray[7] = new TunaPortion(TunaPortionAmount);
		}
		
		rollIngredientPortionArray = portionArrayNullEraser(rollIngredientPortionArray);
		
		superSushi = new Roll(rollNameFinalInput, rollIngredientPortionArray);
		
	}
	
	public void actionPerformed(ActionEvent eventOfAction) {
		switch (eventOfAction.getActionCommand()) {
		case "sushi_selection_sashimi_button_press":
			if (!hasSushiSelectionBeenMade) {
				sushiType = "sashimi";
				buildTypeSelectionButtons();
				drawRestartSushiBuilderButton();
				displayChefViewChanges();
				isSimpleSushi = true;
				hasSushiSelectionBeenMade = true;
			}
			break;
		case "sushi_selection_nigiri_button_press":
			if (!hasSushiSelectionBeenMade) {
				sushiType = "nigiri";
				buildTypeSelectionButtons();
				drawRestartSushiBuilderButton();
				displayChefViewChanges();
				isSimpleSushi = true;
				hasSushiSelectionBeenMade = true;
			}
			break;
		case "type_selection_tuna_button_press":
			if (!hasTypeSelectionBeenMade) {
				sushiDeepType = "tuna";
				createSushi();
				removeRestartSushiBuilderButton();
				buildPlateChoiceSelectionButtons();
				drawRestartSushiBuilderButton();
				displayChefViewChanges();
				hasTypeSelectionBeenMade = true;
			}
			break;
		case "type_selection_salmon_button_press":
			if (!hasTypeSelectionBeenMade) {
				sushiDeepType = "salmon";
				createSushi();
				removeRestartSushiBuilderButton();
				buildPlateChoiceSelectionButtons();
				drawRestartSushiBuilderButton();
				displayChefViewChanges();
				hasTypeSelectionBeenMade = true;
			}
			break;
		case "type_selection_eel_button_press":
			if (!hasTypeSelectionBeenMade) {
				sushiDeepType = "eel";
				createSushi();
				removeRestartSushiBuilderButton();
				buildPlateChoiceSelectionButtons();
				drawRestartSushiBuilderButton();
				displayChefViewChanges();
				hasTypeSelectionBeenMade = true;
			}
			break;
		case "type_selection_crab_button_press":
			if (!hasTypeSelectionBeenMade) {
				sushiDeepType = "crab";
				createSushi();
				removeRestartSushiBuilderButton();
				buildPlateChoiceSelectionButtons();
				drawRestartSushiBuilderButton();
				displayChefViewChanges();
				hasTypeSelectionBeenMade = true;
			}
			break;
		case "type_selection_shrimp_button_press":
			if (!hasTypeSelectionBeenMade) {
				sushiDeepType = "shrimp";
				createSushi();
				removeRestartSushiBuilderButton();
				buildPlateChoiceSelectionButtons();
				drawRestartSushiBuilderButton();
				displayChefViewChanges();
				hasTypeSelectionBeenMade = true;
			}
			break;
		case "plate_selection_red_button_press":
			if (!hasPlateSelectionBeenMade) {
				plateColor = "red";
				removeRestartSushiBuilderButton();
				buildPositionChoiceButtons();
				drawRestartSushiBuilderButton();
				displayChefViewChanges();
				hasPlateSelectionBeenMade = true;
			}
			break;
		case "plate_selection_blue_button_press":
			if (!hasPlateSelectionBeenMade) {
				plateColor = "blue";
				removeRestartSushiBuilderButton();
				buildPositionChoiceButtons();
				drawRestartSushiBuilderButton();
				displayChefViewChanges();
				hasPlateSelectionBeenMade = true;
			}
			break;
		case "plate_selection_green_button_press":
			if (!hasPlateSelectionBeenMade) {
				plateColor = "green";
				removeRestartSushiBuilderButton();
				buildPositionChoiceButtons();
				drawRestartSushiBuilderButton();
				displayChefViewChanges();
				hasPlateSelectionBeenMade = true;
			}
			break;
		case "plate_selection_gold_button_press":
			if (!hasPlateSelectionBeenMade) {
				plateColor = "gold";
				removeRestartSushiBuilderButton();
				buildGoldPlateSlider();
				drawRestartSushiBuilderButton();
				displayChefViewChanges();
				hasPlateSelectionBeenMade = true;
			}
			break;
		case "finished_adjusting_goldPlate_slider":
			if (!hasFinishedAdjustingGoldPlateSlider) {
				goldPlateFinalValue = (goldPlateSlider.getValue() / 10.0);
				goldPlateSlider.setEnabled(false);
				removeRestartSushiBuilderButton();
				buildPositionChoiceButtons();
				drawRestartSushiBuilderButton();
				displayChefViewChanges();
				hasFinishedAdjustingGoldPlateSlider = true;
			}
			break;
		case "position_selection_button_1_press":
			if (!hasPositionSelectionBeenMade) {
				positionToBe = 1;
				createRequest();
				hasPositionSelectionBeenMade = true;
			}
			break;
		case "position_selection_button_2_press":
			if (!hasPositionSelectionBeenMade) {
				positionToBe = 2;
				createRequest();
				hasPositionSelectionBeenMade = true;
			}
			break;
		case "position_selection_button_3_press":
			if (!hasPositionSelectionBeenMade) {
				positionToBe = 3;
				createRequest();
				hasPositionSelectionBeenMade = true;
			}
			break;
		case "position_selection_button_4_press":
			if (!hasPositionSelectionBeenMade) {
				positionToBe = 4;
				createRequest();
				hasPositionSelectionBeenMade = true;
			}
			break;
		case "position_selection_button_5_press":
			if (!hasPositionSelectionBeenMade) {
				positionToBe = 5;
				createRequest();
				hasPositionSelectionBeenMade = true;
			}
			break;
		case "position_selection_button_6_press":
			if (!hasPositionSelectionBeenMade) {
				positionToBe = 6;
				createRequest();
				hasPositionSelectionBeenMade = true;
			}
			break;
		case "position_selection_button_7_press":
			if (!hasPositionSelectionBeenMade) {
				positionToBe = 7;
				createRequest();
				hasPositionSelectionBeenMade = true;
			}
			break;
		case "position_selection_button_8_press":
			if (!hasPositionSelectionBeenMade) {
				positionToBe = 8;
				createRequest();
				hasPositionSelectionBeenMade = true;
			}
			break;
		case "position_selection_button_9_press":
			if (!hasPositionSelectionBeenMade) {
				positionToBe = 9;
				createRequest();
				hasPositionSelectionBeenMade = true;
			}
			break;
		case "position_selection_button_10_press":
			if (!hasPositionSelectionBeenMade) {
				positionToBe = 10;
				createRequest();
				hasPositionSelectionBeenMade = true;
			}
			break;
		case "position_selection_button_11_press":
			if (!hasPositionSelectionBeenMade) {
				positionToBe = 11;
				createRequest();
				hasPositionSelectionBeenMade = true;
			}
			break;
		case "position_selection_button_12_press":
			if (!hasPositionSelectionBeenMade) {
				positionToBe = 12;
				createRequest();
				hasPositionSelectionBeenMade = true;
			}
			break;
		case "position_selection_button_13_press":
			if (!hasPositionSelectionBeenMade) {
				positionToBe = 13;
				createRequest();
				hasPositionSelectionBeenMade = true;
			}
			break;
		case "position_selection_button_14_press":
			if (!hasPositionSelectionBeenMade) {
				positionToBe = 14;
				createRequest();
				hasPositionSelectionBeenMade = true;
			}
			break;
		case "position_selection_button_15_press":
			if (!hasPositionSelectionBeenMade) {
				positionToBe = 15;
				createRequest();
				hasPositionSelectionBeenMade = true;
			}
			break;
		case "position_selection_button_16_press":
			if (!hasPositionSelectionBeenMade) {
				positionToBe = 16;
				createRequest();
				hasPositionSelectionBeenMade = true;
			}
			break;
		case "position_selection_button_17_press":
			if (!hasPositionSelectionBeenMade) {
				positionToBe = 17;
				createRequest();
				hasPositionSelectionBeenMade = true;
			}
			break;
		case "position_selection_button_18_press":
			if (!hasPositionSelectionBeenMade) {
				positionToBe = 18;
				createRequest();
				hasPositionSelectionBeenMade = true;
			}
			break;
		case "position_selection_button_19_press":
			if (!hasPositionSelectionBeenMade) {
				positionToBe = 19;
				createRequest();
				hasPositionSelectionBeenMade = true;
			}
			break;
		case "position_selection_button_20_press":
			if (!hasPositionSelectionBeenMade) {
				positionToBe = 20;
				createRequest();
				hasPositionSelectionBeenMade = true;
			}
			break;
		case "sushi_selection_roll_button_press":
			if (!hasSushiSelectionBeenMade) {
				sushiType = "roll";
				buildRollNameTextField();
				drawRestartSushiBuilderButton();
				displayChefViewChanges();
				isRoll = true;
				hasSushiSelectionBeenMade = true;
			}
			break;
		case "roll_name_entered":
			if (!hasRollNameBeenEntered) {
				rollNameFinalInput = rollNameTextField.getText();
				if (rollNameFinalInput.length() == 0) {
					removeRollNameTooLongNotice();
					removeRestartSushiBuilderButton();
					buildRollNameCannotBeNullNotice();
					drawRestartSushiBuilderButton();
					displayChefViewChanges();
				} else if (rollNameFinalInput.length() > 20) {
					removeRollNameCannotBeNullNotice();
					removeRestartSushiBuilderButton();
					buildRollNameTooLongNotice();
					drawRestartSushiBuilderButton();
					displayChefViewChanges();
				} else if (rollNameFinalInput.length() <= 20) {
					removeRollNameTooLongNotice();
					removeRollNameCannotBeNullNotice();
					removeRestartSushiBuilderButton();
					buildRollIngredientsInterface();
					drawRestartSushiBuilderButton();
					displayChefViewChanges();
					rollNameTextField.setEnabled(false);
					hasRollNameBeenEntered = true;
				}
			}
			break;
		case "finished_creating_roll_ingredients":
			if (!hasFinishedCreatingRollIngredients) {
				AvocadoPortionAmount = (AvocadoSlider.getValue() / 100.0);
				CrabPortionAmount = (CrabSlider.getValue() / 100.0);
				EelPortionAmount = (EelSlider.getValue() / 100.0);
				RicePortionAmount = (RiceSlider.getValue() / 100.0);
				SalmonPortionAmount = (SalmonSlider.getValue() / 100.0);
				SeaweedPortionAmount = (SeaweedSlider.getValue() / 100.0);
				ShrimpPortionAmount = (ShrimpSlider.getValue() / 100.0);
				TunaPortionAmount = (TunaSlider.getValue() / 100.0);
				if ((AvocadoPortionAmount == 0) && (CrabPortionAmount == 0) && (EelPortionAmount == 0) && (RicePortionAmount == 0) && (SalmonPortionAmount == 0) && (SeaweedPortionAmount == 0) && (ShrimpPortionAmount == 0) && (TunaPortionAmount == 0)) {
					removeRestartSushiBuilderButton();
					buildNoNullRollNotice();
					drawRestartSushiBuilderButton();
					displayChefViewChanges();
				} else {
					buildRoll();
					removeIngredientInterface();
					removeNoNullRollNotice();
					removeRestartSushiBuilderButton();
					buildPlateChoiceSelectionButtons();
					drawRestartSushiBuilderButton();
					displayChefViewChanges();
				}
			}
			break;
		case "reset_sushi_builder":
			resetSushiBuilder();
			break;
		}
	}
	
	public void stateChanged(ChangeEvent eventChange) {
		JSlider sourceJSlider = (JSlider) eventChange.getSource();
		if (sourceJSlider.equals(goldPlateSlider)) {
			goldPlateSliderValueLabel.setText(Double.toString(sourceJSlider.getValue() / 10.0));
			displayChefViewChanges();
		}
		if (sourceJSlider.equals(AvocadoSlider)) {
			AvocadoSliderValueLabel.setText(Double.toString(sourceJSlider.getValue() / 100.0));
			displayChefViewChanges();
		}
		if (sourceJSlider.equals(CrabSlider)) {
			CrabSliderValueLabel.setText(Double.toString(sourceJSlider.getValue() / 100.0));
			displayChefViewChanges();
		}
		if (sourceJSlider.equals(EelSlider)) {
			EelSliderValueLabel.setText(Double.toString(sourceJSlider.getValue() / 100.0));
			displayChefViewChanges();
		}
		if (sourceJSlider.equals(RiceSlider)) {
			RiceSliderValueLabel.setText(Double.toString(sourceJSlider.getValue() / 100.0));
			displayChefViewChanges();
		}
		if (sourceJSlider.equals(SalmonSlider)) {
			SalmonSliderValueLabel.setText(Double.toString(sourceJSlider.getValue() / 100.0));
			displayChefViewChanges();
		}
		if (sourceJSlider.equals(SeaweedSlider)) {
			SeaweedSliderValueLabel.setText(Double.toString(sourceJSlider.getValue() / 100.0));
			displayChefViewChanges();
		}
		if (sourceJSlider.equals(ShrimpSlider)) {
			ShrimpSliderValueLabel.setText(Double.toString(sourceJSlider.getValue() / 100.0));
			displayChefViewChanges();
		}
		if (sourceJSlider.equals(TunaSlider)) {
			TunaSliderValueLabel.setText(Double.toString(sourceJSlider.getValue() / 100.0));
			displayChefViewChanges();
		}
	}
	
	private void createRequest() {
		
		if (plateColor.equals("red")) {
			makeRedPlateRequest(superSushi, positionToBe);
			drawClearScreenButton();
			displayChefViewChanges();
		}
		if (plateColor.equals("blue")) {
			makeBluePlateRequest(superSushi, positionToBe);
			drawClearScreenButton();
			displayChefViewChanges();
		}
		if (plateColor.equals("green")) {
			makeGreenPlateRequest(superSushi, positionToBe);
			drawClearScreenButton();
			displayChefViewChanges();
		}
		if (plateColor.equals("gold")) {
			makeGoldPlateRequest(superSushi, positionToBe, goldPlateFinalValue);
			drawClearScreenButton();
			displayChefViewChanges();
		}
		
	}
	
	private void drawClearScreenButton() {
		
		removeRestartSushiBuilderButton();
		
		JPanel clearScreenButtonHeaderPanel = new JPanel();
		
		JLabel clearScreenHeaderLabel = new JLabel("<html>Sushi building complete. Check belt or console for status.</html>");
		clearScreenHeaderLabel.setPreferredSize(new Dimension(280, 45));
		clearScreenHeaderLabel.setOpaque(true);
		clearScreenHeaderLabel.setBackground(java.awt.Color.decode("#F0E68C"));
		clearScreenHeaderLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		clearScreenHeaderLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		clearScreenButtonHeaderPanel.add(clearScreenHeaderLabel);
		
		JPanel clearScreenButtonPanel = new JPanel();
		
		JButton clearScreenButton = new JButton("Reset Sushi Builder");
		clearScreenButton.setActionCommand("reset_sushi_builder");
		clearScreenButton.addActionListener(this);
		clearScreenButtonPanel.add(clearScreenButton);
		
		clearScreenButtonHeaderPanel.setMaximumSize(clearScreenButtonHeaderPanel.getPreferredSize());
		clearScreenButtonPanel.setMaximumSize(clearScreenButtonPanel.getPreferredSize());
		
		add(clearScreenButtonHeaderPanel);
		add(clearScreenButtonPanel);
		
	}
	
	private void drawRestartSushiBuilderButton() {
		
		whiteSpaceGlue = Box.createVerticalGlue();
		whiteSpaceGlue.setPreferredSize(new Dimension(0, 100));
		add(whiteSpaceGlue);
		
		restartSushiBuilderButton = new JButton("Restart");
		restartSushiBuilderButton.setActionCommand("reset_sushi_builder");
		restartSushiBuilderButton.addActionListener(this);
		restartSushiBuilderButton.setAlignmentX(CENTER_ALIGNMENT);
		add(restartSushiBuilderButton);
		
		hasRestartSushiBuilderButtonBeenDrawn = true;
		
	}
	
	private void removeRestartSushiBuilderButton() {
		
		if (hasRestartSushiBuilderButtonBeenDrawn) {
			remove(whiteSpaceGlue);
			remove(restartSushiBuilderButton);
			whiteSpaceGlue = null;
			restartSushiBuilderButton = null;
			hasRestartSushiBuilderButtonBeenDrawn = false;
		}
	}
	
	public void removeIngredientInterface() {
		remove(rollIngredientsAvocadoPanel);
		remove(rollIngredientsCrabPanel);
		remove(rollIngredientsEelPanel);
		remove(rollIngredientsRicePanel);
		remove(rollIngredientsSalmonPanel);
		remove(rollIngredientsSeaweedPanel);
		remove(rollIngredientsShrimpPanel);
		remove(rollIngredientsTunaPanel);
		remove(rollIngredientsFooterPanel);
		rollIngredientsAddendumNoticeLabel.setText("<html>The ingredient panel was removed after selecting ingredients to conserve space.</html>");
	}
	
	private void displayChefViewChanges() {
		revalidate();
		repaint();
	}
	
	private void resetSushiBuilder() {
		
		removeAll();
		buildChefViewTitlePanel();
		buildSushiSelectionButtons();
		displayChefViewChanges();
		
		superSushi = null;
		goldPlateSlider = null;
		goldPlateSliderValueLabel = null;
		rollNameTextField = null;
		rollNameTooLongNoticePanel = null;
		rollNameCannotBeNullNoticePanel = null;
		rollCannotBeNullNoticePanel = null;
		goldPlateFinalValue = 0.0;
		rollNameFinalInput = null;
		sushiType = null;
		sushiDeepType = null;
		plateColor = null;
		positionToBe = 0;
		isSimpleSushi = false;
		isRoll = false;
		hasSushiSelectionBeenMade = false;
		hasTypeSelectionBeenMade = false;
		hasPlateSelectionBeenMade = false;
		hasFinishedAdjustingGoldPlateSlider = false;
		hasPositionSelectionBeenMade = false;
		hasRollNameBeenEntered = false;
		hasFinishedCreatingRollIngredients = false;
		hasRestartSushiBuilderButtonBeenDrawn = false;
		
		rollNameTooLongNotificationCount = 0;
		rollNameCannotBeNullNotificationCount = 0;
		rollCannotBeNullNotificationCount = 0;
		
		AvocadoPortionAmount = 0.0;
		CrabPortionAmount = 0.0;
		EelPortionAmount = 0.0;
		RicePortionAmount = 0.0;
		SalmonPortionAmount = 0.0;
		SeaweedPortionAmount = 0.0;
		ShrimpPortionAmount = 0.0;
		TunaPortionAmount = 0.0;
		
		AvocadoSlider = null;
		CrabSlider = null;
		EelSlider = null;
		RiceSlider = null;
		SalmonSlider = null;
		SeaweedSlider = null;
		ShrimpSlider = null;
		TunaSlider = null;
		
		AvocadoSliderValueLabel = null;
		CrabSliderValueLabel = null;
		EelSliderValueLabel = null;
		RiceSliderValueLabel = null;
		SalmonSliderValueLabel = null;
		SeaweedSliderValueLabel = null;
		ShrimpSliderValueLabel = null;
		TunaSliderValueLabel = null;
		
		rollIngredientsHeaderPanel = null;
		rollIngredientsAvocadoPanel = null;
		rollIngredientsCrabPanel = null;
		rollIngredientsEelPanel = null;
		rollIngredientsRicePanel = null;
		rollIngredientsSalmonPanel = null;
		rollIngredientsSeaweedPanel = null;
		rollIngredientsShrimpPanel = null;
		rollIngredientsTunaPanel = null;
		rollIngredientsFooterPanel = null;
		rollIngredientsAddendumNoticePanel = null;
		
		rollIngredientsAddendumNoticeLabel = null;
		
	}
	
	private void buildRollNameTooLongNotice() {
		
		if (rollNameTooLongNotificationCount < 1) {
		
			rollNameTooLongNoticePanel = new JPanel();
			
			JLabel rollNameTooLongNoticeLabel = new JLabel("<html>Roll name too long.</html>");
			rollNameTooLongNoticeLabel.setPreferredSize(new Dimension(180, 25));
			rollNameTooLongNoticeLabel.setOpaque(true);
			rollNameTooLongNoticeLabel.setBackground(java.awt.Color.decode("#FF0000"));
			rollNameTooLongNoticeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			rollNameTooLongNoticeLabel.setAlignmentX(CENTER_ALIGNMENT);
			rollNameTooLongNoticeLabel.setFont(new Font("Dialog", Font.BOLD, 10));
			
			rollNameTooLongNoticePanel.add(rollNameTooLongNoticeLabel);
			rollNameTooLongNoticePanel.setMaximumSize(rollNameTooLongNoticePanel.getPreferredSize());
			add(rollNameTooLongNoticePanel);
			
		}
		
		rollNameTooLongNotificationCount++;
		
	}
	
	private void removeRollNameTooLongNotice() {
		if (rollNameTooLongNotificationCount > 0) {
			remove (rollNameTooLongNoticePanel);
			rollNameTooLongNoticePanel = null;
			rollNameTooLongNotificationCount = 0;
		}
		
	}
	
	private void buildRollNameCannotBeNullNotice() {
		
		if (rollNameCannotBeNullNotificationCount < 1) {
			
			rollNameCannotBeNullNoticePanel = new JPanel();
			
			JLabel rollNameCannotBeNullNoticeLabel = new JLabel("<html>Enter a roll name.</html>");
			rollNameCannotBeNullNoticeLabel.setPreferredSize(new Dimension(180, 25));
			rollNameCannotBeNullNoticeLabel.setOpaque(true);
			rollNameCannotBeNullNoticeLabel.setBackground(java.awt.Color.decode("#FF0000"));
			rollNameCannotBeNullNoticeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			rollNameCannotBeNullNoticeLabel.setAlignmentX(CENTER_ALIGNMENT);
			rollNameCannotBeNullNoticeLabel.setFont(new Font("Dialog", Font.BOLD, 10));
			
			rollNameCannotBeNullNoticePanel.add(rollNameCannotBeNullNoticeLabel);
			rollNameCannotBeNullNoticePanel.setMaximumSize(rollNameCannotBeNullNoticePanel.getPreferredSize());
			add(rollNameCannotBeNullNoticePanel);
			
		}
		
		rollNameCannotBeNullNotificationCount++;
		
	}
	
	private void removeRollNameCannotBeNullNotice() {
		if (rollNameCannotBeNullNotificationCount > 0) {
			remove (rollNameCannotBeNullNoticePanel);
			rollNameCannotBeNullNoticePanel = null;
			rollNameCannotBeNullNotificationCount = 0;
		}
		
	}
	
	private void buildNoNullRollNotice() {
		
		if (rollCannotBeNullNotificationCount < 1) {
			
			rollCannotBeNullNoticePanel = new JPanel();
			
			JLabel rollCannotBeNullNoticeLabel = new JLabel("<html>Roll cannot be empty.</html>");
			rollCannotBeNullNoticeLabel.setPreferredSize(new Dimension(180, 25));
			rollCannotBeNullNoticeLabel.setOpaque(true);
			rollCannotBeNullNoticeLabel.setBackground(java.awt.Color.decode("#FF0000"));
			rollCannotBeNullNoticeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			rollCannotBeNullNoticeLabel.setAlignmentX(CENTER_ALIGNMENT);
			rollCannotBeNullNoticeLabel.setFont(new Font("Dialog", Font.BOLD, 10));
			
			rollCannotBeNullNoticePanel.add(rollCannotBeNullNoticeLabel);
			rollCannotBeNullNoticePanel.setMaximumSize(rollCannotBeNullNoticePanel.getPreferredSize());
			add(rollCannotBeNullNoticePanel);
			
		}
		
		rollCannotBeNullNotificationCount++;
		
	}
	
	private void removeNoNullRollNotice() {
		if (rollCannotBeNullNotificationCount > 0) {
			remove (rollCannotBeNullNoticePanel);
			rollCannotBeNullNoticePanel = null;
			rollCannotBeNullNotificationCount = 0;
		}
		
	}
	
	private IngredientPortion[] portionArrayNullEraser(IngredientPortion[] IngredientPortionArray_ToBeNullErased) {
		int nonNulls = 0;
		int ToBeNullErasedLength = IngredientPortionArray_ToBeNullErased.length;
		for (int i = 0; i < ToBeNullErasedLength; i++) {
			if (!(IngredientPortionArray_ToBeNullErased[i] == null)) {
				nonNulls++;
			}
		}
		
		boolean[] indexTest = new boolean[ToBeNullErasedLength];
		
		for (int i = 0; i < ToBeNullErasedLength; i++) {
			if (IngredientPortionArray_ToBeNullErased[i] == null) {
				indexTest[i] = false;
			} else {
				indexTest[i] = true;
			}
		}
		
		IngredientPortion[] IngredientPortionArray_NullErased = new IngredientPortion[nonNulls];
		int indexIncrementer = 0;
		for (int i = 0; i < nonNulls; i++) {
			if (indexTest[indexIncrementer]) {
				IngredientPortionArray_NullErased[i] = IngredientPortionArray_ToBeNullErased[indexIncrementer];
				indexIncrementer++;
			} else {
				indexIncrementer++;
				i--;
			}
		}
		
		return IngredientPortionArray_NullErased;
	}
	
	public void registerChefListener(ChefViewListener cheflistener) {
		ChefViewListenersArrayList.add(cheflistener);
	}
	
	private void makeRedPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener chefListener : ChefViewListenersArrayList) {
			chefListener.handleRedPlateRequest(plate_sushi, plate_position - 1);
		}
	}
	
	private void makeGreenPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener chefListener : ChefViewListenersArrayList) {
			chefListener.handleGreenPlateRequest(plate_sushi, plate_position - 1);
		}
	}
	
	private void makeBluePlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener chefListener : ChefViewListenersArrayList) {
			chefListener.handleBluePlateRequest(plate_sushi, plate_position - 1);
		}
	}
	
	private void makeGoldPlateRequest(Sushi plate_sushi, int plate_position, double price) {
		for (ChefViewListener chefListener : ChefViewListenersArrayList) {
			chefListener.handleGoldPlateRequest(plate_sushi, plate_position - 1, price);
		}
	}
	
}