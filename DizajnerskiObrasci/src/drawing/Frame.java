package drawing;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.sun.jmx.mbeanserver.SunJmxMBeanServer;

import javax.swing.JToggleButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;

public class Frame extends JFrame implements Observer{
	private DrawingView view = new DrawingView();
	private DrawingController controller ;

	private JButton btnOpen;
	private JButton btnSave;
	private JButton btnLoad;
	private JButton btnUndo;
	private JButton btnRedo;
	private JToggleButton tglbtnSelect;
	private JButton btnToBack;
	private JButton btnToFront;
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
	private JPanel panelNort;
	private JPanel panelView;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnDelete;
	private JButton btnModify;

	private DefaultListModel<String> dlmList = new DefaultListModel<String>();

	private int numberSelectedObjects=0;
	private JScrollPane scrollPane;
	private JList<String> logList;



	public Frame() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1034, 0};
		gridBagLayout.rowHeights = new int[]{55, 288, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);

		JPanel panelNorth = new JPanel();
		GridBagConstraints gbc_panelNorth = new GridBagConstraints();
		gbc_panelNorth.insets = new Insets(0, 0, 5, 0);
		gbc_panelNorth.anchor = GridBagConstraints.NORTH;
		gbc_panelNorth.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelNorth.gridx = 0;
		gbc_panelNorth.gridy = 0;
		getContentPane().add(panelNorth, gbc_panelNorth);

		btnOpen = new JButton("O");

		btnSave = new JButton("S");

		btnLoad = new JButton("L");

		btnUndo = new JButton("U");

		btnRedo = new JButton("R");

		tglbtnSelect = new JToggleButton("Sel");

		btnToBack = new JButton("TB");

		btnToFront = new JButton("TF");

		btnBringToBack = new JButton("BTB");

		btnBringToFront = new JButton("BTF");

		btnModify = new JButton("Mod");
		//btnModify.setEnabled(false);

		btnDelete = new JButton("Del");
		//btnDelete.setEnabled(false);

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

		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.modify(e);
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.delete(e);
			}
		});

		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toFront(e);
			}
		});

		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBack(e);
			}
		});

		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack(e);
			}
		});

		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront(e);
			}
		});

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saving(e);

			}
		});

		btnOpen.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				controller.openFiles(e);
			}
		});
		
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undo();
				
			}
		});
		
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
				
			}
		});

		GroupLayout gl_panelNorth = new GroupLayout(panelNorth);
		gl_panelNorth.setHorizontalGroup(
				gl_panelNorth.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelNorth.createSequentialGroup()
						.addContainerGap()
						.addComponent(btnOpen)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnSave)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnLoad)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnUndo)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnRedo)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(tglbtnSelect)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnModify)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnDelete)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnToBack)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnToFront)
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
								.addComponent(btnOpen)
								.addComponent(btnSave)
								.addComponent(btnLoad)
								.addComponent(btnUndo)
								.addComponent(btnRedo)
								.addComponent(tglbtnSelect)
								.addComponent(btnToBack)
								.addComponent(btnToFront)
								.addComponent(btnBringToBack)
								.addComponent(btnBringToFront)
								.addComponent(tglbtnPoint)
								.addComponent(tglbtnLine)
								.addComponent(tglbtnSquare)
								.addComponent(tglbtnRectangle)
								.addComponent(tglbtnCircle)
								.addComponent(tglbtnHexagon)
								.addComponent(btnEdgeColor)
								.addComponent(btnInsideColor)
								.addComponent(btnModify)
								.addComponent(btnDelete)))
				);
		panelNorth.setLayout(gl_panelNorth);

		panelView = new JPanel();
		view.setBackground(Color.WHITE);//panelView
		view.addMouseListener(new MouseAdapter() {//panelView
			public void mousePressed(MouseEvent e) {
				controller.panelClick(e);
			}
		});
		GridBagConstraints gbc_panelView = new GridBagConstraints();
		gbc_panelView.insets = new Insets(0, 0, 5, 0);
		gbc_panelView.fill = GridBagConstraints.BOTH;
		gbc_panelView.gridx = 0;
		gbc_panelView.gridy = 1;
		getContentPane().add(view, gbc_panelView);//panelView

		scrollPane = new JScrollPane();
		logList = new JList<String>(dlmList);

		//panelNort = new JPanel();
		//panelNort.setBackground(Color.WHITE);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		getContentPane().add(scrollPane, gbc_scrollPane);
		scrollPane.setViewportView(logList);



		buttonGroup.add(getTglbtnHexagon());
		buttonGroup.add(getTglbtnCircle());
		buttonGroup.add(getTglbtnRectangle());
		buttonGroup.add(getTglbtnSquare());
		buttonGroup.add(getTglbtnLine());
		buttonGroup.add(getTglbtnPoint());
		buttonGroup.add(getTglbtnSelect());

		setButton();
	}


	public void updateView(int numberSelectedObjects) {
		this.numberSelectedObjects=numberSelectedObjects;	
		setButton();
	}

	public void setButton() {
		if(this.numberSelectedObjects==0) {
			this.btnModify.setEnabled(false);
			this.btnDelete.setEnabled(false);
			this.btnBringToBack.setEnabled(false);
			this.btnBringToFront.setEnabled(false);
			this.btnToFront.setEnabled(false);
			this.btnToBack.setEnabled(false);
		}
		else if(this.numberSelectedObjects==1) {
			this.btnModify.setEnabled(true);
			this.btnDelete.setEnabled(true);
			this.btnBringToBack.setEnabled(true);
			this.btnBringToFront.setEnabled(true);
			this.btnToFront.setEnabled(true);
			this.btnToBack.setEnabled(true);
		}
		else {
			this.btnModify.setEnabled(false);
			this.btnDelete.setEnabled(true);
			this.btnBringToBack.setEnabled(false);
			this.btnBringToFront.setEnabled(false);
			this.btnToFront.setEnabled(false);
			this.btnToBack.setEnabled(false);
		}
	}


	public void updateLog(String logList) {
		getDlmList().add(0, logList);
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
		return btnOpen;
	}

	public void setBtnSave(JButton btnSave) {
		this.btnOpen = btnSave;
	}

	public JButton getBtnOpen() {
		return btnSave;
	}

	public void setBtnOpen(JButton btnOpen) {
		this.btnSave = btnOpen;
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

	public JButton getBtnGoToBack() {
		return btnToBack;
	}

	public void setBtnGoToBack(JButton btnGoToBack) {
		this.btnToBack = btnGoToBack;
	}

	public JButton getBtnGoToFront() {
		return btnToFront;
	}

	public void setBtnGoToFront(JButton btnGoToFront) {
		this.btnToFront = btnGoToFront;
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

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public JButton getBtnModify() {
		return btnModify;
	}

	public void setBtnModify(JButton btnModify) {
		this.btnModify = btnModify;
	}

	public DefaultListModel<String> getDlmList() {
		return dlmList;
	}

	public void setDlmList(DefaultListModel<String> dlmList) {
		this.dlmList = dlmList;
	}

	public JList<String> getLogList() {
		return logList;
	}

	public void setLogList(JList<String> logList) {
		this.logList = logList;
	}







}
