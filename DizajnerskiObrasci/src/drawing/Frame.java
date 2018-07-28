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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

public class Frame extends JFrame{
	private DrawingView view = new DrawingView();
	private DrawingController controller ;
	
	private JButton btnSave;
	private JButton btnOpen;
	private JButton btnLoad;
	private JButton btnUndo;
	private JButton btnRedo;
	private JToggleButton tglbtnSelect;
	private JToggleButton tglbtnModify;
	private JToggleButton tglbtnDelete;
	private JButton btnGoToBack;
	private JButton btnGoToFront;
	private JButton btnBringToBack;
	private JButton btnBringToFront;
	private JToggleButton tglbtnPoint;
	private JToggleButton tglbtnLine;
	private JToggleButton tglbtnSquare;
	private JToggleButton tglbtnRectangle;
	private JToggleButton tglbtnCircle;
	private JToggleButton tglbtnHexagon;
	private JButton btnEdgeColor;
	private JButton btnInsideColor;
	private JPanel panel;
	private JPanel panelView;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	
	
	public Frame() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1034, 0};
		gridBagLayout.rowHeights = new int[]{53, 288, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panelNorth = new JPanel();
		GridBagConstraints gbc_panelNorth = new GridBagConstraints();
		gbc_panelNorth.insets = new Insets(0, 0, 5, 0);
		gbc_panelNorth.anchor = GridBagConstraints.NORTH;
		gbc_panelNorth.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelNorth.gridx = 0;
		gbc_panelNorth.gridy = 0;
		getContentPane().add(panelNorth, gbc_panelNorth);
		
		btnSave = new JButton("O");
		
		btnOpen = new JButton("S");
		
		btnLoad = new JButton("L");
		
		btnUndo = new JButton("U");
		
		btnRedo = new JButton("R");
		
		tglbtnSelect = new JToggleButton("Sel");
		
		tglbtnModify = new JToggleButton("Mod");
		
		tglbtnDelete = new JToggleButton("Del");
		
		btnGoToBack = new JButton("GTB");
		
		btnGoToFront = new JButton("GTF");
		
		btnBringToBack = new JButton("BTB");
		
		btnBringToFront = new JButton("BTF");
		
		tglbtnPoint = new JToggleButton("Pt");
		buttonGroup.add(tglbtnPoint);
		
		tglbtnLine = new JToggleButton("Ln");
		buttonGroup.add(tglbtnLine);
		
		tglbtnSquare = new JToggleButton("Sq");
		buttonGroup.add(tglbtnSquare);
		
		tglbtnRectangle = new JToggleButton("Rc");
		buttonGroup.add(tglbtnRectangle);
		
		tglbtnCircle = new JToggleButton("Cr");
		buttonGroup.add(tglbtnCircle);
		
		tglbtnHexagon = new JToggleButton("Hx");
		buttonGroup.add(tglbtnHexagon);
		
		btnEdgeColor = new JButton("Eg");
		btnEdgeColor.setBackground(Color.BLACK);
		btnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.edgeColor(e);
			}
		});
		
		btnInsideColor = new JButton("Ic");
		btnInsideColor.setBackground(Color.WHITE);
		btnInsideColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.insideColor(e);
			}
		});
		
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
		
		panelView = new JPanel();
		panelView.setBackground(Color.WHITE);
		panelView.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				controller.panelClick(e);
			}
		});
		GridBagConstraints gbc_panelView = new GridBagConstraints();
		gbc_panelView.insets = new Insets(0, 0, 5, 0);
		gbc_panelView.fill = GridBagConstraints.BOTH;
		gbc_panelView.gridx = 0;
		gbc_panelView.gridy = 1;
		getContentPane().add(panelView, gbc_panelView);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		getContentPane().add(panel, gbc_panel);
		
		
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

	public JButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}

	public JButton getBtnOpen() {
		return btnOpen;
	}

	public void setBtnOpen(JButton btnOpen) {
		this.btnOpen = btnOpen;
	}

	public JButton getBtnLoad() {
		return btnLoad;
	}

	public void setBtnLoad(JButton btnLoad) {
		this.btnLoad = btnLoad;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public void setBtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public void setBtnRedo(JButton btnRedo) {
		this.btnRedo = btnRedo;
	}

	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}

	public void setTglbtnSelect(JToggleButton tglbtnSelect) {
		this.tglbtnSelect = tglbtnSelect;
	}

	public JToggleButton getTglbtnModify() {
		return tglbtnModify;
	}

	public void setTglbtnModify(JToggleButton tglbtnModify) {
		this.tglbtnModify = tglbtnModify;
	}

	public JToggleButton getTglbtnDelete() {
		return tglbtnDelete;
	}

	public void setTglbtnDelete(JToggleButton tglbtnDelete) {
		this.tglbtnDelete = tglbtnDelete;
	}

	public JButton getBtnGoToBack() {
		return btnGoToBack;
	}

	public void setBtnGoToBack(JButton btnGoToBack) {
		this.btnGoToBack = btnGoToBack;
	}

	public JButton getBtnGoToFront() {
		return btnGoToFront;
	}

	public void setBtnGoToFront(JButton btnGoToFront) {
		this.btnGoToFront = btnGoToFront;
	}

	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}

	public void setBtnBringToBack(JButton btnBringToBack) {
		this.btnBringToBack = btnBringToBack;
	}

	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public void setBtnBringToFront(JButton btnBringToFront) {
		this.btnBringToFront = btnBringToFront;
	}

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public void setTglbtnPoint(JToggleButton tglbtnPoint) {
		this.tglbtnPoint = tglbtnPoint;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public void setTglbtnLine(JToggleButton tglbtnLine) {
		this.tglbtnLine = tglbtnLine;
	}

	public JToggleButton getTglbtnSquare() {
		return tglbtnSquare;
	}

	public void setTglbtnSquare(JToggleButton tglbtnSquare) {
		this.tglbtnSquare = tglbtnSquare;
	}

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public void setTglbtnRectangle(JToggleButton tglbtnRectangle) {
		this.tglbtnRectangle = tglbtnRectangle;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public void setTglbtnCircle(JToggleButton tglbtnCircle) {
		this.tglbtnCircle = tglbtnCircle;
	}

	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}

	public void setTglbtnHexagon(JToggleButton tglbtnHexagon) {
		this.tglbtnHexagon = tglbtnHexagon;
	}

	public JButton getBtnEdgeColor() {
		return btnEdgeColor;
	}

	public void setBtnEdgeColor(JButton btnEdgeColor) {
		this.btnEdgeColor = btnEdgeColor;
	}

	public JButton getBtnInsideColor() {
		return btnInsideColor;
	}

	public void setBtnInsideColor(JButton btnInsideColor) {
		this.btnInsideColor = btnInsideColor;
	}

	public JPanel getPanelView() {
		return panelView;
	}

	public void setPanelView(JPanel panelView) {
		this.panelView = panelView;
	}
	
	
}
