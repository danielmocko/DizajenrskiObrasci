package drawing.mvc;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.sun.jmx.mbeanserver.SunJmxMBeanServer;

import drawing.observer.Observer;

import javax.swing.JToggleButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
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
import javax.swing.JLabel;

public class Frame extends JFrame implements Observer{
	private View view = new View();
	private Controller controller ;

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
	private String color = "#f0f0f0";
	private DefaultListModel<String> dlmList = new DefaultListModel<String>();

	private int numberSelectedObjects=0;
	private JScrollPane scrollPane;
	private JList<String> logList;
	private JLabel lblAreaColor;



	public Frame() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1034, 0};
		gridBagLayout.rowHeights = new int[]{55, 448, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);

		JPanel panelNorth = new JPanel();
		GridBagConstraints gbc_panelNorth = new GridBagConstraints();
		gbc_panelNorth.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelNorth.insets = new Insets(0, 0, 5, 0);
		gbc_panelNorth.anchor = GridBagConstraints.NORTH;
		gbc_panelNorth.gridx = 0;
		gbc_panelNorth.gridy = 0;
		getContentPane().add(panelNorth, gbc_panelNorth);
		
		Icon open = new ImageIcon("Icons/open.png");
		btnOpen = new JButton(open);
		btnOpen.setBorder(null);
		btnOpen.setBackground(Color.decode(color));
		btnOpen.setBorderPainted(false);

		Icon save = new ImageIcon("Icons/save.png");
		btnSave = new JButton(save);
		btnSave.setBorder(null);
		btnSave.setBackground(Color.decode(color));
		btnSave.setBorderPainted(false);

		Icon load = new ImageIcon("Icons/load.png");
		btnLoad = new JButton(load);
		btnLoad.setBorder(null);
		btnLoad.setBackground(Color.decode(color));
		btnLoad.setBorderPainted(false);

		Icon undo = new ImageIcon("Icons/undo.png");
		btnUndo = new JButton(undo);
		btnUndo.setBorder(null);
		btnUndo.setEnabled(false);
		btnUndo.setBackground(Color.decode(color));
		btnUndo.setBorderPainted(false);

		Icon redo = new ImageIcon("Icons/redo.png");
		btnRedo = new JButton(redo);
		btnRedo.setBorder(null);
		btnRedo.setEnabled(false);
		btnRedo.setBackground(Color.decode(color));
		btnRedo.setBorderPainted(false);
		
		Icon select = new ImageIcon("Icons/select.png");
		tglbtnSelect = new JToggleButton(select);
		tglbtnSelect.setBorder(null);
		tglbtnSelect.setBackground(Color.decode(color));
		tglbtnSelect.setBorderPainted(false);

		Icon toBack= new ImageIcon("Icons/goToBack.png");
		btnToBack = new JButton(toBack);
		btnToBack.setBorder(null);
		btnToBack.setBackground(Color.decode(color));
		btnToBack.setBorderPainted(false);

		Icon toFront = new ImageIcon("Icons/goToFront.png");
		btnToFront = new JButton(toFront);
		btnToFront.setBorder(null);
		btnToFront.setBackground(Color.decode(color));
		btnToFront.setBorderPainted(false);

		Icon bringToBack = new ImageIcon("Icons/bringToBack.png");
		btnBringToBack = new JButton(bringToBack);
		btnBringToBack.setBorder(null);
		btnBringToBack.setBackground(Color.decode(color));
		btnBringToBack.setBorderPainted(false);
		
		Icon bringToFront = new ImageIcon("Icons/bringToFront.png");
		btnBringToFront = new JButton(bringToFront);
		btnBringToFront.setBorder(null);
		btnBringToFront.setBackground(Color.decode(color));
		btnBringToFront.setBorderPainted(false);
		
		Icon modify = new ImageIcon("Icons/modify.png");
		btnModify = new JButton(modify);
		btnModify.setBorder(null);
		btnModify.setBackground(Color.decode(color));
		btnModify.setBorderPainted(false);
		
		Icon delete = new ImageIcon("Icons/delete.png");
		btnDelete = new JButton(delete);
		btnDelete.setBorder(null);
		btnDelete.setBackground(Color.decode(color));
		btnDelete.setBorderPainted(false);

		Icon point = new ImageIcon("Icons/point.png");
		tglbtnPoint = new JToggleButton(point);
		tglbtnPoint.setBorder(null);
		buttonGroup.add(tglbtnPoint);
		tglbtnPoint.setBackground(Color.decode(color));
		tglbtnPoint.setBorderPainted(false);

		Icon line = new ImageIcon("Icons/line.png");
		tglbtnLine = new JToggleButton(line);
		tglbtnLine.setBorder(null);
		buttonGroup.add(tglbtnLine);
		tglbtnLine.setBackground(Color.decode(color));
		tglbtnLine.setBorderPainted(false);

		Icon square = new ImageIcon("Icons/square.png");
		tglbtnSquare = new JToggleButton(square);
		tglbtnSquare.setBorder(null);
		buttonGroup.add(tglbtnSquare);
		tglbtnSquare.setBackground(Color.decode(color));
		tglbtnSquare.setBorderPainted(false);

		Icon rectangle = new ImageIcon("Icons/rectangle.png");
		tglbtnRectangle = new JToggleButton(rectangle);
		tglbtnRectangle.setBorder(null);
		buttonGroup.add(tglbtnRectangle);
		tglbtnRectangle.setBackground(Color.decode(color));
		tglbtnRectangle.setBorderPainted(false);

		Icon circle = new ImageIcon("Icons/circle.png");
		tglbtnCircle = new JToggleButton(circle);
		tglbtnCircle.setBorder(null);
		buttonGroup.add(tglbtnCircle);
		tglbtnCircle.setBackground(Color.decode(color));
		tglbtnCircle.setBorderPainted(false);

		Icon hexagon = new ImageIcon("Icons/Hexagon.png");
		tglbtnHexagon = new JToggleButton(hexagon);
		//tglbtnHexagon.setBorder(null);
		buttonGroup.add(tglbtnHexagon);
		tglbtnHexagon.setBackground(Color.decode(color));
		tglbtnHexagon.setBorderPainted(false);

		btnEdgeColor = new JButton();
		btnEdgeColor.setBackground(Color.BLACK);
		
		btnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.edgeColor(e);
			}
		});

		btnInsideColor = new JButton();
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
				controller.toFront();
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
				try {
					controller.openFiles();
				} catch (FileNotFoundException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}
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

		btnLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.loadData();

			}
		});
		
		JLabel lblBorderColor = new JLabel("Border color:");
		
		lblAreaColor = new JLabel("Area color");

		GroupLayout gl_panelNorth = new GroupLayout(panelNorth);
		gl_panelNorth.setHorizontalGroup(
			gl_panelNorth.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelNorth.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelNorth.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelNorth.createSequentialGroup()
							.addComponent(btnOpen, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLoad, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnUndo, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRedo, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tglbtnSelect, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnModify, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnToBack, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnToFront, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBringToBack, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBringToFront, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tglbtnPoint, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tglbtnLine, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tglbtnSquare, GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tglbtnRectangle, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tglbtnCircle, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tglbtnHexagon, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
							.addGap(31)
							.addComponent(lblAreaColor))
						.addGroup(gl_panelNorth.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 683, Short.MAX_VALUE)
							.addComponent(lblBorderColor)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelNorth.createParallelGroup(Alignment.LEADING)
						.addComponent(btnEdgeColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnInsideColor, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
					.addGap(12))
		);
		gl_panelNorth.setVerticalGroup(
			gl_panelNorth.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelNorth.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_panelNorth.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelNorth.createSequentialGroup()
							.addGap(13)
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
								.addComponent(btnModify)
								.addComponent(btnDelete)))
						.addGroup(gl_panelNorth.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelNorth.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBorderColor)
								.addComponent(btnEdgeColor))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelNorth.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnInsideColor)
								.addComponent(lblAreaColor))))
					.addGap(25))
		);
		panelNorth.setLayout(gl_panelNorth);

		panelView = new JPanel();
		panelView.setBackground(Color.WHITE);//panelView
		panelView.addMouseListener(new MouseAdapter() {//panelView
			public void mousePressed(MouseEvent e) {
				controller.panelClick(e);
			}
		});
		GridBagConstraints gbc_panelView = new GridBagConstraints();
		gbc_panelView.insets = new Insets(0, 0, 5, 0);
		gbc_panelView.fill = GridBagConstraints.BOTH;
		gbc_panelView.gridx = 0;
		gbc_panelView.gridy = 1;
		getContentPane().add(panelView, gbc_panelView);//panelView

		scrollPane = new JScrollPane();
		logList = new JList<String>(dlmList);

		//panelNort = new JPanel();
		//panelNort.setBackground(Color.WHITE);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
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

		updateView(0, 0, 0);
		
		if(btnModify.isSelected()) {
			setBackground(Color.decode("#f00ff0"));
		}
		else {
			setBackground(Color.decode("#f0f0f0"));
		}
		
		
		
	}


	public void updateView(int numberSelectedObjects, int flag,int size) {
		this.numberSelectedObjects=numberSelectedObjects;	

		if(this.numberSelectedObjects==0) {
			this.btnModify.setEnabled(false);
			this.btnDelete.setEnabled(false);
			this.btnBringToBack.setEnabled(false);
			this.btnBringToFront.setEnabled(false);
			this.btnToFront.setEnabled(false);
			this.btnToBack.setEnabled(false);
		}
		else if(this.numberSelectedObjects==1 ) {
			this.btnModify.setEnabled(true);
			this.btnDelete.setEnabled(true);
			if(size==1 ) {
				this.btnBringToBack.setEnabled(false);
				this.btnToBack.setEnabled(false);
				this.btnBringToFront.setEnabled(false);
				this.btnToFront.setEnabled(false);
			}
			else if(size>1 && flag==2) {
				this.btnBringToBack.setEnabled(true);
				this.btnToBack.setEnabled(true);
				this.btnBringToFront.setEnabled(false);
				this.btnToFront.setEnabled(false);
			}
			else if(size>1 && flag==1) {
				this.btnBringToBack.setEnabled(false);
				this.btnToBack.setEnabled(false);
				this.btnBringToFront.setEnabled(true);
				this.btnToFront.setEnabled(true);
			}
			else {
				this.btnBringToBack.setEnabled(true);
				this.btnToBack.setEnabled(true);
				this.btnBringToFront.setEnabled(true);
				this.btnToFront.setEnabled(true);
			}
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


	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
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
