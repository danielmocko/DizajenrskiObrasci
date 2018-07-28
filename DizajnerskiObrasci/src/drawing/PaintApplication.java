package drawing;

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

public class PaintApplication {
	private static final ButtonGroup buttonGroup = new ButtonGroup();

	public static void main(String[] args) {
		DrawingModel model = new DrawingModel();
		Frame frame = new Frame();
		buttonGroup.add(frame.getTglbtnHexagon());
		buttonGroup.add(frame.getTglbtnCircle());
		buttonGroup.add(frame.getTglbtnRectangle());
		buttonGroup.add(frame.getTglbtnSquare());
		buttonGroup.add(frame.getTglbtnLine());
		buttonGroup.add(frame.getTglbtnPoint());
		buttonGroup.add(frame.getTglbtnDelete());
		buttonGroup.add(frame.getTglbtnModify());
		buttonGroup.add(frame.getTglbtnSelect());
		
		//DrawingView view = new DrawingView();
		DrawingController controller = new DrawingController(model,frame);
		//DrawingController controller = new DrawingController(model,view,frame);
		
		frame.setController(controller);
		frame.setSize(1200,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getView().setModel(model);
		frame.setVisible(true);
		

	}
}
