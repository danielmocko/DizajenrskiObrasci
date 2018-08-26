package drawing;

import javax.swing.JFrame;

import drawing.mvc.Controller;
import drawing.mvc.Frame;
import drawing.mvc.Model;

import java.awt.GridBagLayout;


public class Application {
	//private static final ButtonGroup buttonGroup = new ButtonGroup();

	public static void main(String[] args) {
		
		Frame frame = new Frame();
		GridBagLayout gridBagLayout = (GridBagLayout) frame.getContentPane().getLayout();
		gridBagLayout.rowHeights = new int[]{0, 338, 154};
		Model model = new Model();
		
		Controller controller = new Controller(model,frame);
		
		frame.setController(controller);
		frame.setSize(1200,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		model.addObserver(frame);
		frame.getView().setModel(model);
		frame.setVisible(true);
		

	}
}
