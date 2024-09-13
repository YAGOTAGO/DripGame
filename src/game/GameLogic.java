package game;

import player.Player;
import levels.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.Timer;

public final class GameLogic extends JComponent implements KeyListener {
	private boolean gameWon = false;
	private Player ship;
	private final boolean DEBUG_MODE = true;
	private int score = 0;
	private int[] previousScores = new int[2];
	private boolean start = true;
	private List<Level> levels;
	private Level currLevel;

	public GameLogic() { 
		super();
		Timer timer = new Timer(50, new TimerCallback()); // 50 ms = 0.05 sec

		// levels = new ArrayList<>(){{
		// 	add(new levels.LevelOne(ship));
		// 	add(new levels.LevelTwo(ship));
		// }};

		currLevel = new LevelThree();
		ship = currLevel.getShip();

		addKeyListener(this);
		setFocusable(true);
		timer.start();
	}

    @Override
	public void paintComponent(Graphics g) {
		ship.move();
		currLevel.drawLevel(g, this);

		// score, level and fuel
		Font newFont = new Font("Helvetica", Font.BOLD, 30);
		g.setFont(newFont);
		g.drawString("Score: " + String.valueOf(score), 150, 60);
		g.drawString("Level: ", 155, 160);
		newFont = new Font("Helvetica", Font.BOLD, 14);
		g.setFont(newFont);
		g.drawString("FUEL: " + ship.getFuelAmount(), 55, 37);

		if(DEBUG_MODE){
			g.setColor(Color.RED);
			Rectangle temp = ship.getHitbox();
			g.drawRect(temp.x, temp.y, temp.width, temp.height);
		}
	}

	public void updateScore(int change){
		score += change;
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
			start = true;
		}

		if(!start) { return; }
		
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

		// p key
		if (e.getKeyCode() == 80) {
			if (gameWon) {
				//nextLevel(level);
			}
		}

			// r key
			if (e.getKeyCode() == 82) {
				//resetGame(1);
			}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(!start){return;}
		if ((e.getKeyCode() == KeyEvent.VK_UP) || (e.getKeyCode() == KeyEvent.VK_W)) {
			ship.setThrust(false);
		}
	}

}
