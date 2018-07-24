package drawing;

import javax.swing.JFrame;

public class PaintApplication {

	public static void main(String[] args) {
		DrawingModel model = new DrawingModel();
		Frame frame = new Frame();
		DrawingController controller = new DrawingController(model,frame);
		
		frame.setController(controller);
		frame.setSize(1200,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getView().setModel(model);
		frame.setVisible(true);
		

	}

}