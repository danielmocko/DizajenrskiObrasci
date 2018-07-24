package drawing;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JToggleButton;
import java.awt.Color;

public class Frame extends JFrame{
	private DrawingView view = new DrawingView();
	private DrawingController controller ;
	
	
	
	public Frame() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorth = new JPanel();
		getContentPane().add(panelNorth, BorderLayout.NORTH);
		
		JButton btnSave = new JButton("O");
		
		JButton btnOpen = new JButton("S");
		
		JButton btnLoad = new JButton("L");
		
		JButton btnUndo = new JButton("U");
		
		JButton btnRedo = new JButton("R");
		
		JToggleButton tglbtnSelect = new JToggleButton("Sel");
		
		JToggleButton tglbtnModify = new JToggleButton("Mod");
		
		JToggleButton tglbtnDelete = new JToggleButton("Del");
		
		JButton btnGoToBack = new JButton("GTB");
		
		JButton btnGoToFront = new JButton("GTF");
		
		JButton btnBringToBack = new JButton("BTB");
		
		JButton btnBringToFront = new JButton("BTF");
		
		JToggleButton tglbtnPoint = new JToggleButton("Pt");
		
		JToggleButton tglbtnLine = new JToggleButton("Ln");
		
		JToggleButton tglbtnSquare = new JToggleButton("Sq");
		
		JToggleButton tglbtnRectangle = new JToggleButton("Rc");
		
		JToggleButton tglbtnCircle = new JToggleButton("Cr");
		
		JToggleButton tglbtnHexagon = new JToggleButton("Hx");
		
		JButton btnEdgeColor = new JButton("Eg");
		
		JButton btnInsideColor = new JButton("Ic");
		GroupLayout gl_panelNorth = new GroupLayout(panelNorth);
		gl_panelNorth.setHorizontalGroup(
			gl_panelNorth.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelNorth.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnSave)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnOpen)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLoad)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnUndo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRedo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tglbtnSelect)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tglbtnModify)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tglbtnDelete)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnGoToBack)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnGoToFront)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBringToBack)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBringToFront)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tglbtnPoint)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tglbtnLine)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tglbtnSquare)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tglbtnRectangle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tglbtnCircle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tglbtnHexagon)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEdgeColor)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnInsideColor)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panelNorth.setVerticalGroup(
			gl_panelNorth.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelNorth.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panelNorth.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnOpen)
						.addComponent(btnLoad)
						.addComponent(btnUndo)
						.addComponent(btnRedo)
						.addComponent(tglbtnSelect)
						.addComponent(tglbtnModify)
						.addComponent(tglbtnDelete)
						.addComponent(btnGoToBack)
						.addComponent(btnGoToFront)
						.addComponent(btnBringToBack)
						.addComponent(btnBringToFront)
						.addComponent(tglbtnPoint)
						.addComponent(tglbtnLine)
						.addComponent(tglbtnSquare)
						.addComponent(tglbtnRectangle)
						.addComponent(tglbtnCircle)
						.addComponent(tglbtnHexagon)
						.addComponent(btnEdgeColor)
						.addComponent(btnInsideColor)))
		);
		panelNorth.setLayout(gl_panelNorth);
		
		JPanel panelSouth = new JPanel();
		getContentPane().add(panelSouth, BorderLayout.SOUTH);
		
		JPanel DrawingView = new JPanel();
		getContentPane().add(DrawingView, BorderLayout.CENTER);
	}

	public DrawingView getView() {
		return view;
	}

	public void setView(DrawingView view) {
		this.view = view;
	}

	public DrawingController getController() {
		return controller;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}
	
	
}
