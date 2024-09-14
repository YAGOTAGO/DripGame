import player.Player;
import levels.*;
import utils.ImageHelper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.Timer;

public final class GameLogic extends JComponent implements KeyListener {
	private Player ship;
	private final Image START_BACKGROUND = ImageHelper.getImage("screen","startBackground.png");
	private final Image GAME_LOST = ImageHelper.getImage("screen", "gameOver.png");
	private final Image GAME_WON = ImageHelper.getImage("screen", "victoryBackground.png");
	private int score = 0;
	private boolean start = false;
	private boolean gameWon = false;
	private int level;
	private Level currLevel;

	public GameLogic() { 
		super();
		Timer timer = new Timer(40, new TimerCallback()); // 50 ms = 0.05 sec
		timer.start();
		addKeyListener(this);
		setFocusable(true);
	}

    @Override
	public void paintComponent(Graphics g) {
		if(!start){ //start screen
			g.drawImage(START_BACKGROUND, 0, 0, this);
			return;
		}
		if(gameWon){ //win screen
			g.drawImage(GAME_WON, 0, 0, this);
			g.setColor(Color.WHITE);
			Font font = new Font("Helvetica", Font.BOLD, 60);
			g.setFont(font);
			g.drawString("Total Score: " + score, 400, 400);
			return;
		}
		if(ship.isGameLost()){ //lost screen
			g.drawImage(GAME_LOST, 0, 0, this);
			return;
		}
		//move to next level
		if(ship.isNextLevel()){
			score += ship.getScore();
			setLevel(++level);
		}
		ship.move();

		//paints and handles all level logic
		currLevel.drawLevel(g, this);
	}

	public void setLevel(int level) {
		switch(level){
			case 1 -> currLevel = new LevelOne();
			case 2 -> currLevel = new LevelTwo();
			case 3 -> currLevel = new LevelThree();
			default -> gameWon = true;
		}
		ship = currLevel.getShip();
	}

	public void resetGame() {
		setLevel(1);
	}

	protected class TimerCallback implements ActionListener {
        @Override
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		// s for start game key
		if (e.getKeyCode() ==  KeyEvent.VK_S) {
			setLevel(level = 1);
			start = true;
		}
		if(!start || gameWon) { return; }

		if(ship.isGameLost()){
			// r key
			if (e.getKeyCode() == KeyEvent.VK_R) {
				resetGame();
			}
			return;
		}

		// up key
		if ((e.getKeyCode() == KeyEvent.VK_UP) || (e.getKeyCode() == KeyEvent.VK_W)) {
			ship.setThrust(true);
		}

		// right key
		if ((e.getKeyCode() == KeyEvent.VK_RIGHT) || (e.getKeyCode() == KeyEvent.VK_D)) {
			ship.changeAngle(true);
		}
		// left key
		if (e.getKeyCode() == KeyEvent.VK_LEFT ||  e.getKeyCode() == KeyEvent.VK_A) {
			ship.changeAngle(false);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(!start || gameWon){return;}
		if ((e.getKeyCode() == KeyEvent.VK_UP) || (e.getKeyCode() == KeyEvent.VK_W)) {
			ship.setThrust(false);
		}
	}

}
