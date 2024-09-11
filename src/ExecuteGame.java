import java.awt.Color;
import javax.swing.JFrame;

public class ExecuteGame extends JFrame {
	
	public static final int SCREENHEIGHT = 800;
	public static final int SCREENWIDTH = 1400;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("SpaceShip Game");
		FlyingDrone fly = new FlyingDrone();
		
        frame.setSize(SCREENWIDTH,SCREENHEIGHT);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(fly);
		
	}
	
}
