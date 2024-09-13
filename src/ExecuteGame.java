import java.awt.Color;
import javax.swing.JFrame;

public final class ExecuteGame extends JFrame {

	private static ExecuteGame instance;

	public static final int SCREENHEIGHT = 800;
	public static final int SCREENWIDTH = 1400;
	
	private GameLogic logic;

	public static void main(String[] args) {
		ExecuteGame.getInstance();
	}

	private ExecuteGame(){
		JFrame frame = new JFrame("SpaceShip Game");
		logic = new GameLogic();
		
		frame.setIconImage(ImageHelper.getImage("ship", "spaceshipFlame.png"));
        frame.setSize(SCREENWIDTH, SCREENHEIGHT);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(logic);
	}
		
	 // Public static method to provide access to the singleton instance
	 public static ExecuteGame getInstance() {
        if (instance == null) {
            // Create the instance if it doesn't exist
            instance = new ExecuteGame();
        }
        return instance;
    }

	public GameLogic getLogic(){
		return logic;
	}

	
}
