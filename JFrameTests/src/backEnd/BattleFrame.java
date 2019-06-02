package backEnd;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BattleFrame {
	
	JFrame battleWindow;
	Container con1;
	Font generalFont = new Font ("Times New Roman", Font.PLAIN, 28);

	
	//SCOTT: Player Variables
	
	JPanel enemyStatPanel, enemyPicturePanel, playerConsolePanel, playerActionPanel;
	JLabel hpLabel, hpLabelNumber, enemyLabel, enemyNameLabel, playerHPLabel, playerHPLabelNumber, enemyImgIcon, playerPromptLabel;
	JButton attackButton, defendButton, fleeButton, magicButton;
	ImageIcon enemyImg;
	String enemyName;
	BattleHandler battleHandler = new BattleHandler();
	
	boolean isEnemyDead = false, isPlayerDead = false, isBattleFled = false;
	
	private int playerHp = 100;
	private int playerIp = 100;
	private int playerAttL = 10;
	private int playerAttH = 30;
	private int playerDef = 15;
	private int enemyHPValue = 2000;
	private int enemyAtt = 20;
	private int enemyDef = 10;
	
	public BattleFrame() {

	//SCOTT: Creates Game Title Window
 	battleWindow = new JFrame("Game Window");
 	//SCOTT: Sets dimensions
 	battleWindow.setSize(800,950);
 	//SCOTT: Sets close button action
 	battleWindow.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
 	//SCOTT: Set Background Color
 	battleWindow.getContentPane().setBackground(Color.black);
 	//SCOTT: Makes Window Visable
 	battleWindow.setLayout(null);
 	battleWindow.setVisible(true);
 	//SCOTT: Assigns container to window
	con1 = battleWindow.getContentPane();
		
		enemyStatPanel = new JPanel();
		enemyStatPanel.setBounds(0, 15, 800, 50);
		enemyStatPanel.setBackground(Color.black);
		enemyStatPanel.setLayout(new GridLayout (1,6));
		con1.add(enemyStatPanel);
		
		enemyLabel = new JLabel("Enemy: ");
		enemyLabel.setForeground(Color.white);
		enemyLabel.setFont(generalFont);
		enemyStatPanel.add(enemyLabel);
		
		enemyNameLabel = new JLabel("");
		enemyNameLabel.setForeground(Color.white);
		enemyNameLabel.setFont(generalFont);
		enemyStatPanel.add(enemyNameLabel); 
		
		hpLabel = new JLabel("HP: ");
		hpLabel.setForeground(Color.white);
		hpLabel.setFont(generalFont);
		enemyStatPanel.add(hpLabel);
		
		hpLabelNumber = new JLabel("");
		hpLabelNumber.setForeground(Color.white);
		hpLabelNumber.setFont(generalFont);
		enemyStatPanel.add(hpLabelNumber);
		
		playerHPLabel = new JLabel("Player HP: ");
		playerHPLabel.setForeground(Color.white);
		playerHPLabel.setFont(generalFont);
		enemyStatPanel.add(playerHPLabel);
		
		playerHPLabelNumber = new JLabel("");
		playerHPLabelNumber.setForeground(Color.white);
		playerHPLabelNumber.setFont(generalFont);
		enemyStatPanel.add(playerHPLabelNumber);
		
		enemyPicturePanel = new JPanel();
		enemyPicturePanel.setBounds(50,65,683,535);
		enemyPicturePanel.setBackground(Color.black);
		con1.add(enemyPicturePanel);
		
		enemyImg = new ImageIcon("C:\\Users\\Asus\\eclipse-workspace\\JFrameTests\\src\\backEnd\\dragon.png");
		enemyImgIcon = new JLabel(enemyImg);
		enemyPicturePanel.add(enemyImgIcon);
		
		playerConsolePanel = new JPanel();
		playerConsolePanel.setBounds(50, 600, 683, 270);
		playerConsolePanel.setBackground(Color.black);
		playerConsolePanel.setLayout(new GridLayout (2,1));
		con1.add(playerConsolePanel);
		
		playerPromptLabel = new JLabel("What would you like to do?");
		playerPromptLabel.setForeground(Color.white);
		playerPromptLabel.setFont(generalFont);
		playerConsolePanel.add(playerPromptLabel);
		
		playerActionPanel = new JPanel();
		playerActionPanel.setPreferredSize(new Dimension(683,200));
		playerActionPanel.setBackground(Color.black);
		playerActionPanel.setLayout(new GridLayout (2,2));
		playerConsolePanel.add(playerActionPanel);
		
		attackButton = new JButton("Attack");
		attackButton.setBackground(Color.black);
		attackButton.setForeground(Color.white);
		attackButton.setFont(generalFont);
		attackButton.setFocusPainted(false);
		playerActionPanel.add(attackButton);
		attackButton.addActionListener(battleHandler);
		attackButton.setActionCommand("Attack");
		
		defendButton = new JButton("Defend");
		defendButton.setBackground(Color.black);
		defendButton.setForeground(Color.white);
		defendButton.setFont(generalFont);
		defendButton.setFocusPainted(false);
		playerActionPanel.add(defendButton);
		defendButton.addActionListener(battleHandler);
		defendButton.setActionCommand("Defend");
		
		magicButton = new JButton("Magic");
		magicButton.setBackground(Color.black);
		magicButton.setForeground(Color.white);
		magicButton.setFont(generalFont);
		magicButton.setFocusPainted(false);
		playerActionPanel.add(magicButton);
		magicButton.addActionListener(battleHandler);
		magicButton.setActionCommand("Magic");
		
		fleeButton = new JButton("Flee");
		fleeButton.setBackground(Color.black);
		fleeButton.setForeground(Color.white);
		fleeButton.setFont(generalFont);
		fleeButton.setFocusPainted(false);
		playerActionPanel.add(fleeButton);
		fleeButton.addActionListener(battleHandler);
		fleeButton.setActionCommand("Flee");
		
		
		enemyStatHandler();
		playerStatHandler();
	}
	
	private static int getRandomInteger(int min, int max) {
		if(min >= max) {
			throw new IllegalArgumentException("Max must be greater than min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	public void attack() {
		
		int playerAtt;
		int diceRoll = getRandomInteger(1, 10);
		if(diceRoll >= 9) {
			playerAtt = playerAttH*2;
			playerPromptLabel.setText("You delt a critial hit... It delt " + playerAtt + " damage.");
			if (enemyHPValue<=0) {
				enemyHPValue = 0;
				playerPromptLabel.setText("You have defeated the beast named: " + enemyName);
				isEnemyDead = true;
			}
		} else {
			playerAtt = getRandomInteger(playerAttL,playerAttH);
			playerPromptLabel.setText("You delt " + playerAtt + " damage.");
			if (enemyHPValue<=0) {
				enemyHPValue = 0;
				playerPromptLabel.setText("You have defeated the beast named: " + enemyName);
				isEnemyDead = true;
			}
		}
		
		enemyHPValue = enemyHPValue - playerAtt;
		
		attackButton.setText("Continue");
		attackButton.setActionCommand("ContinueA");
		defendButton.setText("Continue");
		defendButton.setActionCommand("ContinueA");
		magicButton.setText("Continue");
		magicButton.setActionCommand("ContinueA");
		fleeButton.setText("Continue");
		fleeButton.setActionCommand("ContinueA");
		
		enemyStatHandler();
		
	}
	
	public void defend() {
		
		playerDef = playerDef*2;
		playerPromptLabel.setText("You defended against the future attack.");
		
		attackButton.setText("Continue");
		attackButton.setActionCommand("ContinueA");
		defendButton.setText("Continue");
		defendButton.setActionCommand("ContinueA");
		magicButton.setText("Continue");
		magicButton.setActionCommand("ContinueA");
		fleeButton.setText("Continue");
		fleeButton.setActionCommand("ContinueA");
		
	}
	
	public void magic() {
		
		playerPromptLabel.setText("You tried to cast magic. You know no magic spells.");
		
		attackButton.setText("Continue");
		attackButton.setActionCommand("ContinueA");
		defendButton.setText("Continue");
		defendButton.setActionCommand("ContinueA");
		magicButton.setText("Continue");
		magicButton.setActionCommand("ContinueA");
		fleeButton.setText("Continue");
		fleeButton.setActionCommand("ContinueA");
	}
	
	public void flee() {
		
		int fleeHolder = getRandomInteger(1,10);
		if(fleeHolder > 5) {
			playerPromptLabel.setText("You successfully flee from the battle!");
			isBattleFled = true;
		} else {
			playerPromptLabel.setText("You fail to flee from the battle!");
		}
		
		attackButton.setText("Continue");
		attackButton.setActionCommand("ContinueA");
		defendButton.setText("Continue");
		defendButton.setActionCommand("ContinueA");
		magicButton.setText("Continue");
		magicButton.setActionCommand("ContinueA");
		fleeButton.setText("Continue");
		fleeButton.setActionCommand("ContinueA");
		
	}
	
	public void contA() {
		
		if(isBattleFled == true) {
			
			battleWindow.setVisible(false); //you can't see me!
			battleWindow.dispose(); //Destroy the JFrame object
			
		}
		
		else {
			int combatHolder = enemyAtt - playerDef;
			if(combatHolder>0) {
				playerPromptLabel.setText("The enemy delt " + combatHolder + " damage.");
				playerHp = playerHp - combatHolder;
				if(playerHp <= 0) {
					playerStatHandler();
					playerPromptLabel.setText("You have been slain...");
					isPlayerDead = true;
				}
			} else {
				playerPromptLabel.setText("The enemy delt 0 damage.");
			}
			
		}
		
		attackButton.setActionCommand("ContinueB");
		defendButton.setActionCommand("ContinueB");
		magicButton.setActionCommand("ContinueB");
		fleeButton.setActionCommand("ContinueB");
		
	}
	
	public void contB() {
		
		if(isEnemyDead == true || isPlayerDead == true) {
			
			battleWindow.setVisible(false); //you can't see me!
			battleWindow.dispose(); //Destroy the JFrame object
			
		}
		
		else {
		attackButton.setText("Attack");
		attackButton.setActionCommand("Attack");
		defendButton.setText("Defend");
		defendButton.setActionCommand("Defend");
		magicButton.setText("Magic");
		magicButton.setActionCommand("Magic");
		fleeButton.setText("Flee");
		fleeButton.setActionCommand("Flee");
		playerPromptLabel.setText("What would you like to do?");
		
		playerStatHandler();
		
		}
		
	}
	
public class BattleHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent event) {
			
			String playerChoice = event.getActionCommand();
			
			switch(playerChoice) {
			case "Attack": attack(); break;
			case "Defend": defend(); break;
			case "Magic": magic(); break;
			case "Flee": flee(); break;
			case "ContinueA": contA(); break;
			case "ContinueB": contB(); break;
			}
			
		}
		
}

	public void enemyStatHandler() {
		
		enemyName = "Dragon";
		hpLabelNumber.setText("" + enemyHPValue);
		enemyNameLabel.setText(enemyName);
	}
	
	public void playerStatHandler() {
		
		playerDef = 15;
		playerHPLabelNumber.setText("" + playerHp);
		
	}
	
}

