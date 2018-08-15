package drawing;

import javax.swing.JFrame;


public class PaintApplication {
	//private static final ButtonGroup buttonGroup = new ButtonGroup();

	public static void main(String[] args) {
		
		Frame frame = new Frame();
		DrawingModel model = new DrawingModel();
		
		DrawingController controller = new DrawingController(model,frame);
		
		frame.setController(controller);
		frame.setSize(1200,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		model.addObserver(frame);
		frame.getView().setModel(model);
		frame.setVisible(true);
		

	}
}
