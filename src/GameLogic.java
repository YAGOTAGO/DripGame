import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.Timer;

public class GameLogic extends JComponent implements KeyListener {
	
	protected Timer timer;
	int h = ExecuteGame.SCREENHEIGHT;
	int w = ExecuteGame.SCREENWIDTH;
	private int initialY = 300;
	private int initialX = 50;
	private int x = initialX;
	private int y = initialY;
	private double initialTheta = Math.PI / 2;
	private double theta = initialTheta;
	private int xBoundary = 0;
	private int numLives = 3;
	private int topH = h / 4;
	private int topW = w / 3;
	private int botH = h - (h / 4);
	int botW = w / 6;
	int bot2H = h - (h / 2);
	private Rectangle ship = null;
	private Rectangle rectFuel;
	public static int score = 0;
	private int initialFuel = 400;
	private int fuel = initialFuel;
	private boolean gameWon = false;
	private int level = 1;
	private int[] previousScores = new int[2];
	private int i = 0;
	private int fuelIntersect = 0;
	private int explosionX;
	private int explosionY;
	private boolean explosionON = false;
	private int explosionTimer = 0;
	// c is for chest animation
	private double c = 0;
	private int chestIntersection = 0;
	// m is for landing animation
	private double m = 0;
	private int platformX = 1177;
	private int platformY = 620;

	// t is coins frame variable
	public static double t = 0;
	private boolean start = false;

	// y value of drip which used to be a rock
	private int yDrip = 75;
	private double yDripVel = 0;
	private boolean flameOn = false;
	private boolean puddle = false;
	private int initialFuelY = 0;
	private int fuelY = initialFuelY;

	public static int dripGuyY = 120;
	private int dripGuyX = 910;
	private boolean dripGuyGoingDown = true;

	// values that affect gravity
	private double xVelocity = 0;
	private double yVelocity = 0;
	private double coefficientFriction = .06;
	private double damping = 1 - coefficientFriction;
	private double gravity = .3;
	private double yVelCoefficient = .4;
	private double xVelCoefficient = .4;
	
	private boolean numberOn = false;
	private int numberTimer = 0;

	public static int initialDripBallX = 925;
	public static int dripBallX = initialDripBallX;
	public static int dripBallY = dripGuyY + 20;

	DripBalls ball1 = new DripBalls(dripBallX, dripBallY);

	// all images by Mina Stevens
	private Image fuelPic = ImageHelper.getImage("UI", "fuel.png");
	private Image startBackground = ImageHelper.getImage("screen", "startBackground.png");
	private Image spaceBackground = ImageHelper.getImage("screen", "spaceBackground.png");
	private Image victoryBackground = ImageHelper.getImage("screen", "victoryBackground.png");
	private Image drip = ImageHelper.getImage("drip", "drip.png");
	private Image metal = ImageHelper.getImage("UI", "metal.png");
	private Image cliffBot = ImageHelper.getImage("enviornment", "cliffBot.png");
	private Image cliffTop = ImageHelper.getImage("enviornment", "cliffTop.png");
	private Image background = ImageHelper.getImage("screen", "background.png");
	private Image dripFlat = ImageHelper.getImage("drip", "dripFlat.png");
	private Image gameOver = ImageHelper.getImage("screen", "gameOver.png");
	private Image fuelCanister = ImageHelper.getImage("UI", "fuelCanister.png");
	private Image dripGuy = ImageHelper.getImage("drip", "dripGuy.png");
	private Image dripBall = ImageHelper.getImage("drip", "dripBall.png");

	private GameObject heart1;
	private GameObject heart2;
	private GameObject heart3;

	//Animated stuff
	List<IAnimated> animations;
	List<Collectible> collectibles;
	
	/**
	 * Program is a spaceship video game
	 * 
	 * @author Tiago Davies
	 * @artist Mina Stevens
	 */
	public GameLogic() {
		super();
		timer = new Timer(50, new TimerCallback()); // 100 ms = 0.1 sec
		timer.start();
		
		animations = new ArrayList<>();
		collectibles = new ArrayList<>();
		ship = new Rectangle(200, 200, 100, 100);
		rectFuel = new Rectangle(200, 200, 50, 50);
		Coin coin1 = new Coin(250, 400);
		Coin coin2 = new Coin(500, 400);
		animations.add(coin1);
		animations.add(coin2);
		collectibles.add(coin1);
		collectibles.add(coin2);

		addKeyListener(this);
		setFocusable(true);
	}

    @Override
	public void paintComponent(Graphics g) {

		// start screen
		if (start == false) {
			g.drawImage(startBackground, 0, 0, this);
		} else {

			// main background
			g.drawImage(background, 0, 0, this);

			// bottom cliff
			g.drawImage(cliffBot, -10, -30, this);

			// metal background where score is
			g.drawImage(metal, 0, 0, this);

			// score, level and fuel
			Font newFont = new Font("Helvetica", Font.BOLD, 30);
			g.setFont(newFont);
			g.drawString("Score: " + String.valueOf(score), 150, 60);
			g.drawString("Level " + level, 155, 160);
			newFont = new Font("Helvetica", Font.BOLD, 14);
			g.setFont(newFont);
			g.drawString("FUEL: " + fuel, 55, 37);
			
			if (explosionON == true) {
				//g.drawImage(explosion[(int) explosionTimer], explosionX - 50, explosionY - 20, this);
			}
			// System.out.println("X cord: " + explosionX + " Y cord: " + explosionY);

			// spaceship image
			BufferedImage in = null;
			try {
				if (flameOn == true && fuel > 0) {
					in = ImageIO.read(new File("images\\ship\\spaceshipFlame.png"));
					yVelocity = yVelocity - yVelCoefficient * Math.sin(theta);
					xVelocity = xVelocity + xVelCoefficient * Math.cos(theta);
					x = x + (int) xVelocity;
					y = y + (int) yVelocity;
					fuel = fuel - 1;
					fuelY = fuelY + 1;
				} else {
					in = ImageIO.read(new File("images\\ship\\spaceship.png"));
				}
			} catch (IOException e) {
				System.out.println("didnt work: " + e);
			}

			BufferedImage newImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);

			if (theta == Math.PI / 2) {
				g.drawImage(in, (int) x - 13, (int) y - 3, this);
			}
			double rotationRequired = Math.PI / 2 - theta;
			double locationX = newImage.getWidth() / 2;
			double locationY = newImage.getHeight() / 2;
			AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

			// Drawing the rotated image at the required drawing locations
			g.drawImage(op.filter(in, null), (int) x - 13, (int) y - 3, this);

			ship = new Rectangle(x, y, 45, 55);
			// commented out but would should hitbox
			// g2.setColor(Color.RED);
			// g2.draw(at.createTransformedShape(rect));

			//score += (100 * coinCollection.hitCoins(ship)); //increase value per hit coin
			for (IAnimated e : animations) {
				g.drawImage(e.GetFrame(), e.getX(), e.getY(), this);
			}

			if (level != 3) {
				// fuel icon interaction
				if (!touchFuel() && fuelIntersect == 0) {
					g.drawImage(fuelPic, 910, 550, this);
					rectFuel.setRect(910, 550, 50, 50);
					// g2.draw(rectFuel);
				}
			} else {
				g.drawImage(dripGuy, dripGuyX, dripGuyY, this);
				g.drawImage(dripBall, dripBallX, dripBallY, this);

				// g2.draw(setRectBounds(dripBallX,dripBallY,30,30));

				if (chestIntersection == 0) {
					//g.drawImage(chest[(int) c], 870, 524, this);
					if (touchRect(setRectBounds(870, 524, 125, 125))) {
						numberOn = true;
						score += 10000;
						fuel += 100;
						fuelY -= 100;
						chestIntersection += 1;

					}
				}

				if (numberOn == true) {
					newFont = new Font("Helvetica", Font.BOLD, 22);
					g.setFont(newFont);
					g.setColor(Color.YELLOW);
					g.drawString("+10000", 920, 560);
				}
				// coordinate and width adjusted for fair hitbox
				if (touchRect(setRectBounds(dripGuyX + 10, dripGuyY + 10, 75, 85))) {
					respawn();
				}
				if (touchRect(setRectBounds(dripBallX, dripBallY, 30, 30))) {
					ball1.respawnBalls(initialDripBallX, dripGuyY + 20);
					respawn();
				}

			}

			// drip
			if (level > 1) {
				g.drawImage(drip, 740, yDrip, this);
				g.drawImage(drip, 1100, yDrip, this);
				// see bounds of drop
				// g2.draw(fallingBounds(740+10,yRock+10));

				if (puddle == true && yDrip < 120) {
					g.drawImage(dripFlat, 720, 295, this);
					g.drawImage(dripFlat, 1070, 295, this);
				}
			}

			// landing platform
			//g.drawImage(platform[(int) m], platformX, platformY, this);

			// Draws fuel and cannister
			Color purple = new Color(145, 0, 255);
			Color fuelRED = new Color(255, 0, 0);
			g.setColor(purple);
			if (fuel < initialFuel / 2) {
				g.setColor(fuelRED);
			}
			if (level == 1) {
				// the numbers added after fuelY are to adjust position on screen
				g.fillRect(70, (fuelY / 4) + 50, 30, (fuel / 4));
			} else {
				g.fillRect(70, (fuelY / 4) + 69, 30, (fuel / 4));
			}

			g.drawImage(fuelCanister, 60, 42, this);
			// g.fillRect(45, 35, (w/3)/6 , 2*fuel/3);

			// top cliff
			g.drawImage(cliffTop, -10, -30, this);

			// draws hearts on the screen and end screen
			switch (numLives) {
			case 3 -> {
            //                g.drawImage(heart, 150, 80, this);
            //                g.drawImage(heart, 200, 80, this);
            //                g.drawImage(heart, 250, 80, this);
                        }
			case 2 -> {
            //                g.drawImage(heart, 150, 80, this);
            //                g.drawImage(heart, 200, 80, this);
                        }
			//case 1 -> g.drawImage(heart, 150, 80, this);
			case 0 -> g.drawImage(gameOver, 0, 0, this);
						
			}	
			// makes the screen before the next level
			if (gameWon == true) {
				if (level != 3) {
					g.drawImage(spaceBackground, 0, 0, this);
					g.setColor(Color.WHITE);
					newFont = new Font("Helvetica", Font.BOLD, 80);
					g.setFont(newFont);
					g.drawString("Level " + level + " score: " + score, 350, 300);
					g.drawString("Press [p] for next level", 300, 500);
					if (i > 0) {
						g.drawString("Total Scores: " + String.valueOf(arraySum(previousScores) + score), 350, 400);
					}
				}
				// makes the final victory screen
				if (level >= 3) {
					g.drawImage(victoryBackground, 0, 0, this);
					g.setColor(Color.WHITE);
					newFont = new Font("Helvetica", Font.BOLD, 60);
					g.setFont(newFont);
					g.drawString("Total Score: " + String.valueOf(arraySum(previousScores) + score), 400, 400);
				}
			}
		}
	}

	public Rectangle setRectBounds(int x, int y, int w, int h) {
		return new Rectangle(x, y, w, h);
	}

	// if rect (aka Ship) intersects another rectangle
	public boolean touchRect(Rectangle a) {
    	return ship.intersects(a);
	}

	// detects if fuel icon is touched
	public boolean touchFuel() {
		if (ship.intersects(920, 550, 50, 50) && fuelIntersect == 0) {
			fuel = fuel + 120;
			fuelY = fuelY - 120;
			fuelIntersect += 1;

			return true;
		}
		return false;
	}

	// method that checks if a bounding rectangle intersects with the background
	// rectangles
	public boolean isTouchBound(Rectangle rect) {
		if (rect.intersects(10 * w / 12, h - (h / 6), 240, 50) && Math.sin(theta) != 1)
			return true;
		else if (rect.intersects(xBoundary, 0, 4 * w / 12, topH))
			return true;
		else if (rect.intersects(topW, 0, 8 * w / 12, topH / 2))
			return true;
		else if (rect.intersects(xBoundary, botH, 2 * w / 12, 20))
			return true;
		else if (rect.intersects(xBoundary, 0, 1, h))
			return true;
		else if (rect.intersects(w - 1, 0, 1, h))
			return true;
		else if (rect.intersects(w / 6, h - (4 * h / 10), 250, 170))
			return true;
		else if (rect.intersects(w / 4, bot2H, 125, 130))
			return true;
		else if (rect.intersects(w / 3, h - (4 * h / 10), 300, 100))
			return true;
		else if (rect.intersects(w / 2, h - (6 * h / 10), 125, 400))
			return true;
		else if (rect.intersects(7 * w / 12, h - (h / 6), 250, 50))
			return true;
		else if (rect.intersects(9 * w / 12, h - (6 * h / 10), 125, 400))
			return true;
		else if (rect.intersects(9 * w / 12, h - (6 * h / 10), 125, 400))
			return true;
		else if (rect.intersects(750, yDrip + 10, 30, 40) && level >= 2)
			return true;
		else return rect.intersects(1110, yDrip + 10, 30, 40) && level >= 2;
		
	}

	public boolean haveWon() {

		if (ship.intersects(10 * w / 12, h - (h / 6), 240, 50) && Math.sin(theta) == 1)
			return true;
		else
			return false;

	}

	public void respawn() {
		numLives = numLives - 1;
		xVelocity = 0;
		yVelocity = 0;
		x = initialX;
		y = initialY;
		theta = initialTheta;
		fuel = fuelLevel(level);
		score = score - 100;
		fuelIntersect = 0;
		fuelY = initialFuelY;
		repaint();

	}

	public void resetGame(int level) {

		//coinCollection.resetCoins();

		xVelocity = 0;
		yVelocity = 0;
		x = initialX;
		y = initialY;
		theta = initialTheta;
		this.level = level;
		fuel = fuelLevel(this.level);
		score = 0;
		numLives = 3;
		gameWon = false;
		fuelIntersect = 0;
		fuelY = initialFuelY;

	}

	public int fuelLevel(int a) {
		switch (a) {
		case 1 -> {
                    return initialFuel;
                }

		case 2 -> {
                    return initialFuel - 100;
                }

		case 3 -> {
                    return initialFuel - 100;
                }
		}
		return 0;
	}

	public void nextLevel(int level) {
		resetGame(level);

		switch (level) {
		case 2 -> {
                    // level =2;
                    numLives = 3;
                    fuel = initialFuel - 100;
                }
		case 3 -> {
                    // level = 3;
                    numLives = 3;
                    fuel = initialFuel - 100;
                }

		}
		repaint();
	}

	protected class TimerCallback implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// System.out.println("xVel: " + xVelocity + " yVel: " + yVelocity);

			for(IAnimated a : animations){
				a.updateAnimationStep();
			}

			if (explosionON == true) {
				explosionTimer = explosionTimer + 1;
			}
			if (explosionTimer == 6) {
				explosionON = false;
				explosionTimer = 0;
			}

			// c is used for chest animation
			if (c > 8)
				c = 0;
			else
				c = c + .5;
			// m is used for landing platform animation
			if (m > 3) {
				m = 0;
			} else {
				m = m + .3;
			}
			// t is used for the coin animation
			if (t > 9) {
				t = 0;
			} else {
				t = t + .5;
			}

			// to ensure gravity is only in effect when game starts
			if (numLives > 0 && start == true) {
				if (haveWon() == true) {
					gameWon = true;
				}
				// constant fall of gravity
				yVelocity = damping * yVelocity + gravity;
				xVelocity = xVelocity * damping;
				y = y + (int) yVelocity;
				x = x + (int) xVelocity;

				// drip fall after level 1
				if (level > 1) {
					yDripVel = yDripVel + .2;
					yDrip = yDrip + (int) yDripVel;
					if (yDrip >= 280) {
						puddle = true;
						yDrip = 75;
						yDripVel = 0;
					}
				}
				if (level == 3) {
					if (numberOn == true) {
						numberTimer += 1;
					}
					if (numberTimer == 18) {
						numberTimer = 0;
						numberOn = false;
					}
					dripBallX -= 5;
					if (dripGuyY == 402) {
						dripGuyGoingDown = false;
					} else if (dripGuyY == 120) {
						dripGuyGoingDown = true;
					}
					if (dripGuyGoingDown) {
						dripGuyY = dripGuyY + 3;
						dripBallY += 3;
					} else {
						dripGuyY = dripGuyY - 3;
						dripBallX -= 3;
					}
				}
				repaint();

				if (isTouchBound(ship) == true) {
					explosionX = (int) x;
					explosionY = (int) y;
					explosionON = true;
					respawn();

				}
				if (isTouchBound(setRectBounds(dripBallX, dripBallY, 30, 30)) == true) {
					ball1.respawnBalls(initialDripBallX, dripGuyY + 20);
				}

			}
		}
	}

	// to total the scores in array
	public int arraySum(int[] a) {
		int sum = 0;
		for (int j = 0; j < a.length; j++) {
			sum = sum + a[j];
		}
		return sum;
	}

	@Override
	public void keyPressed(KeyEvent e) {

		// s for start game key
		if (e.getKeyCode() == 83) {
			start = true;
		}

		if (numLives > 0) {

			// up key
			if (e.getKeyCode() == 38) {
				if (fuel > 0) {
					flameOn = true;

				}
				repaint();

			}
			// right key
			if (e.getKeyCode() == 39) {
				theta = theta - Math.PI / 8;
				repaint();
			}
			// left key
			if (e.getKeyCode() == 37) {
				theta = theta + Math.PI / 8;
				repaint();
			}
			// p key
			if (e.getKeyCode() == 80) {
				if (gameWon == true) {
					previousScores[i] = score;
					if (i < 3) {
						i++;
					}
					//coinCollection.resetCoins();
					level = level + 1;
					nextLevel(level);
				}
			}
		} else {
			// r key
			if (e.getKeyCode() == 82) {
				resetGame(1);
				i = 0;
			}
		}
		// space key pauses for 5 seconds
		if (e.getKeyCode() == 32) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {

				e1.printStackTrace();
			}
		}
		// System.out.println("CanvasWithKeyListener.keyPressed: " + e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// System.out.println("CanvasWithKeyListener.keyTyped: " + e.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// up key
		if (e.getKeyCode() == 38) {
			flameOn = false;
		}
		// System.out.println("CanvasWithKeyListener.keyReleased: " + e.getKeyCode());

	}

}
