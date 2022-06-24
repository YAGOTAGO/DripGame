import java.awt.Color;


import javax.swing.JFrame;


@SuppressWarnings("serial")
public class ExecuteGame extends JFrame {
	
	public static int h = 800;
	public static int w = 1400;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("SpaceShip Game");
		FlyingDrone fly = new FlyingDrone();
		
        frame.setSize(w,h);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(fly);
		
	}
	
}
