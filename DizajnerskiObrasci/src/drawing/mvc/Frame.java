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

import javax.imageio.ImageIO;
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
	private JPanel panel;



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

		Icon open = new ImageIcon("res/Icons/open.png");
		
		Icon save = new ImageIcon("res/Icons/save.png");

		Icon load = new ImageIcon("res/Icons/load.png");

		Icon undo = new ImageIcon("res/Icons/undo.png");

		Icon redo = new ImageIcon("res/Icons/redo.png");

		Icon select = new ImageIcon("res/Icons/select.png");

		Icon toBack= new ImageIcon("res/Icons/goToBack.png");

		Icon toFront = new ImageIcon("res/Icons/goToFront.png");

		Icon bringToBack = new ImageIcon("res/Icons/bringToBack.png");

		Icon bringToFront = new ImageIcon("res/Icons/bringToFront.png");

		Icon modify = new ImageIcon("res/Icons/modify.png");

		Icon delete = new ImageIcon("res/Icons/delete.png");

		Icon point = new ImageIcon("res/Icons/point.png");

		Icon line = new ImageIcon("res/Icons/line.png");

		Icon square = new ImageIcon("res/Icons/square.png");

		Icon rectangle = new ImageIcon("res/Icons/rectangle.png");

		Icon circle = new ImageIcon("res/Icons/circle.png");

		Icon hexagon = new ImageIcon("res/Icons/Hexagon.png");

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
		logList.disable();

		//panelNort = new JPanel();
		//panelNort.setBackground(Color.WHITE);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		getContentPane().add(scrollPane, gbc_scrollPane);
		scrollPane.setViewportView(logList);
		GridBagLayout gbl_panelNorth = new GridBagLayout();
		gbl_panelNorth.columnWidths = new int[]{0, 31, 31, 31, 33, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 19, 26, 25, 57, 62, 0};
		gbl_panelNorth.rowHeights = new int[]{20, 24, 0, 0};
		gbl_panelNorth.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelNorth.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		panelNorth.setLayout(gbl_panelNorth);
		
		btnUndo = new JButton(undo);
		btnUndo.setBorder(null);
		btnUndo.setEnabled(false);
		btnUndo.setBackground(Color.decode(color));
		btnUndo.setBorderPainted(false);
		btnUndo.setToolTipText("Undo");

		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undo();
			}
		});
		btnSave = new JButton(save);
		btnSave.setBorder(null);
		btnSave.setBackground(Color.decode(color));
		btnSave.setBorderPainted(false);
		btnSave.setToolTipText("Save file");

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saving(e);

			}
		});

		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 19;
		gbc_panel.gridy = 0;
		panelNorth.add(panel, gbc_panel);

		JLabel lblBorderColor = new JLabel("Border color:");

		btnInsideColor = new JButton();
		btnInsideColor.setBackground(Color.WHITE);
		btnInsideColor.setToolTipText("Area color");

		btnInsideColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.insideColor(e);
			}
		});

		lblAreaColor = new JLabel("Area color:");

		btnEdgeColor = new JButton();
		btnEdgeColor.setBackground(Color.BLACK);
		btnEdgeColor.setToolTipText("Broder color");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblAreaColor, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
							.addGap(3))
						.addComponent(lblBorderColor, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnInsideColor, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
						.addComponent(btnEdgeColor, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblBorderColor, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
						.addComponent(btnEdgeColor, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnInsideColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblAreaColor, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);

		btnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.edgeColor(e);
			}
		});
		btnOpen = new JButton(open);
		btnOpen.setBorder(null);
		btnOpen.setBackground(Color.decode(color));
		btnOpen.setBorderPainted(false);
		btnOpen.setToolTipText("Open file");
		
		btnOpen.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				try {
					controller.openFiles();
				} catch (FileNotFoundException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnOpen = new GridBagConstraints();
		gbc_btnOpen.gridheight = 2;
		gbc_btnOpen.fill = GridBagConstraints.BOTH;
		gbc_btnOpen.insets = new Insets(0, 0, 5, 5);
		gbc_btnOpen.gridx = 1;
		gbc_btnOpen.gridy = 0;
		panelNorth.add(btnOpen, gbc_btnOpen);
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.gridheight = 2;
		gbc_btnSave.fill = GridBagConstraints.BOTH;
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 2;
		gbc_btnSave.gridy = 0;
		panelNorth.add(btnSave, gbc_btnSave);
		
		btnLoad = new JButton(load);
		btnLoad.setBorder(null);
		btnLoad.setBackground(Color.decode(color));
		btnLoad.setBorderPainted(false);
		btnLoad.setEnabled(false);
		btnLoad.setToolTipText("Load log file");

		btnLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.loadData();

			}
		});
		GridBagConstraints gbc_btnLoad = new GridBagConstraints();
		gbc_btnLoad.gridheight = 2;
		gbc_btnLoad.fill = GridBagConstraints.BOTH;
		gbc_btnLoad.insets = new Insets(0, 0, 5, 5);
		gbc_btnLoad.gridx = 3;
		gbc_btnLoad.gridy = 0;
		panelNorth.add(btnLoad, gbc_btnLoad);
		GridBagConstraints gbc_btnUndo = new GridBagConstraints();
		gbc_btnUndo.gridheight = 2;
		gbc_btnUndo.fill = GridBagConstraints.BOTH;
		gbc_btnUndo.insets = new Insets(0, 0, 5, 5);
		gbc_btnUndo.gridx = 4;
		gbc_btnUndo.gridy = 0;
		panelNorth.add(btnUndo, gbc_btnUndo);
		btnBringToBack = new JButton(bringToBack);
		btnBringToBack.setBorder(null);
		btnBringToBack.setBackground(Color.decode(color));
		btnBringToBack.setBorderPainted(false);
		btnBringToBack.setToolTipText("Bring to back");
		
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack(e);
			}
		});
		btnToBack = new JButton(toBack);
		btnToBack.setBorder(null);
		btnToBack.setBackground(Color.decode(color));
		btnToBack.setBorderPainted(false);
		btnToBack.setToolTipText("Go to back");

		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBack(e);
			}
		});
		btnModify = new JButton(modify);
		btnModify.setBorder(null);
		btnModify.setBackground(Color.decode(color));
		btnModify.setBorderPainted(false);
		btnModify.setToolTipText("Modify");

		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				controller.modify(e);
			}
		});
		btnRedo = new JButton(redo);
		btnRedo.setBorder(null);
		btnRedo.setEnabled(false);
		btnRedo.setBackground(Color.decode(color));
		btnRedo.setBorderPainted(false);
		btnRedo.setToolTipText("Redo");

		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();

			}
		});
		GridBagConstraints gbc_btnRedo = new GridBagConstraints();
		gbc_btnRedo.gridheight = 2;
		gbc_btnRedo.fill = GridBagConstraints.BOTH;
		gbc_btnRedo.insets = new Insets(0, 0, 5, 5);
		gbc_btnRedo.gridx = 5;
		gbc_btnRedo.gridy = 0;
		panelNorth.add(btnRedo, gbc_btnRedo);
		
		tglbtnSelect = new JToggleButton(select);
		tglbtnSelect.setBorder(null);
		tglbtnSelect.setBackground(Color.decode(color));
		tglbtnSelect.setBorderPainted(false);
		buttonGroup.add(getTglbtnSelect());
		tglbtnSelect.setToolTipText("Select");
		
		GridBagConstraints gbc_tglbtnSelect = new GridBagConstraints();
		gbc_tglbtnSelect.gridheight = 2;
		gbc_tglbtnSelect.fill = GridBagConstraints.BOTH;
		gbc_tglbtnSelect.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnSelect.gridx = 6;
		gbc_tglbtnSelect.gridy = 0;
		panelNorth.add(tglbtnSelect, gbc_tglbtnSelect);
		GridBagConstraints gbc_btnModify = new GridBagConstraints();
		gbc_btnModify.gridheight = 2;
		gbc_btnModify.fill = GridBagConstraints.BOTH;
		gbc_btnModify.insets = new Insets(0, 0, 5, 5);
		gbc_btnModify.gridx = 7;
		gbc_btnModify.gridy = 0;
		panelNorth.add(btnModify, gbc_btnModify);
		
		btnDelete = new JButton(delete);
		btnDelete.setBorder(null);
		btnDelete.setBackground(Color.decode(color));
		btnDelete.setBorderPainted(false);
		btnDelete.setToolTipText("Delete");

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.delete(e);
			}
		});
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.gridheight = 2;
		gbc_btnDelete.fill = GridBagConstraints.BOTH;
		gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete.gridx = 8;
		gbc_btnDelete.gridy = 0;
		panelNorth.add(btnDelete, gbc_btnDelete);
		GridBagConstraints gbc_btnToBack = new GridBagConstraints();
		gbc_btnToBack.gridheight = 2;
		gbc_btnToBack.fill = GridBagConstraints.BOTH;
		gbc_btnToBack.insets = new Insets(0, 0, 5, 5);
		gbc_btnToBack.gridx = 9;
		gbc_btnToBack.gridy = 0;
		panelNorth.add(btnToBack, gbc_btnToBack);
		
		btnToFront = new JButton(toFront);
		btnToFront.setBorder(null);
		btnToFront.setBackground(Color.decode(color));
		btnToFront.setBorderPainted(false);
		btnToFront.setToolTipText("Go to front");

		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toFront();
			}
		});
		GridBagConstraints gbc_btnToFront = new GridBagConstraints();
		gbc_btnToFront.gridheight = 2;
		gbc_btnToFront.fill = GridBagConstraints.BOTH;
		gbc_btnToFront.insets = new Insets(0, 0, 5, 5);
		gbc_btnToFront.gridx = 10;
		gbc_btnToFront.gridy = 0;
		panelNorth.add(btnToFront, gbc_btnToFront);
		GridBagConstraints gbc_btnBringToBack = new GridBagConstraints();
		gbc_btnBringToBack.gridheight = 2;
		gbc_btnBringToBack.fill = GridBagConstraints.BOTH;
		gbc_btnBringToBack.insets = new Insets(0, 0, 5, 5);
		gbc_btnBringToBack.gridx = 11;
		gbc_btnBringToBack.gridy = 0;
		panelNorth.add(btnBringToBack, gbc_btnBringToBack);
		
		btnBringToFront = new JButton(bringToFront);
		btnBringToFront.setBorder(null);
		btnBringToFront.setBackground(Color.decode(color));
		btnBringToFront.setBorderPainted(false);
		btnBringToFront.setToolTipText("Bring to front");

		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront(e);
			}
		});
		
		GridBagConstraints gbc_btnBringToFront = new GridBagConstraints();
		gbc_btnBringToFront.gridheight = 2;
		gbc_btnBringToFront.fill = GridBagConstraints.BOTH;
		gbc_btnBringToFront.insets = new Insets(0, 0, 5, 5);
		gbc_btnBringToFront.gridx = 12;
		gbc_btnBringToFront.gridy = 0;
		panelNorth.add(btnBringToFront, gbc_btnBringToFront);
		
		tglbtnPoint = new JToggleButton(point);
		tglbtnPoint.setBorder(null);
		buttonGroup.add(tglbtnPoint);
		tglbtnPoint.setBackground(Color.decode(color));
		tglbtnPoint.setBorderPainted(false);
		buttonGroup.add(getTglbtnPoint());
		tglbtnPoint.setToolTipText("Point");
		
		GridBagConstraints gbc_tglbtnPoint = new GridBagConstraints();
		gbc_tglbtnPoint.gridheight = 2;
		gbc_tglbtnPoint.fill = GridBagConstraints.BOTH;
		gbc_tglbtnPoint.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnPoint.gridx = 13;
		gbc_tglbtnPoint.gridy = 0;
		panelNorth.add(tglbtnPoint, gbc_tglbtnPoint);
		
		tglbtnLine = new JToggleButton(line);
		tglbtnLine.setBorder(null);
		buttonGroup.add(tglbtnLine);
		tglbtnLine.setBackground(Color.decode(color));
		tglbtnLine.setBorderPainted(false);
		tglbtnLine.setToolTipText("Line");
		buttonGroup.add(getTglbtnLine());
		
		
		GridBagConstraints gbc_tglbtnLine = new GridBagConstraints();
		gbc_tglbtnLine.gridheight = 2;
		gbc_tglbtnLine.fill = GridBagConstraints.BOTH;
		gbc_tglbtnLine.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnLine.gridx = 14;
		gbc_tglbtnLine.gridy = 0;
		panelNorth.add(tglbtnLine, gbc_tglbtnLine);
		
		tglbtnSquare = new JToggleButton(square);
		tglbtnSquare.setBorder(null);
		buttonGroup.add(tglbtnSquare);
		tglbtnSquare.setBackground(Color.decode(color));
		tglbtnSquare.setBorderPainted(false);
		tglbtnSquare.setToolTipText("Square");
		buttonGroup.add(getTglbtnSquare());
		
		GridBagConstraints gbc_tglbtnSquare = new GridBagConstraints();
		gbc_tglbtnSquare.gridheight = 2;
		gbc_tglbtnSquare.fill = GridBagConstraints.BOTH;
		gbc_tglbtnSquare.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnSquare.gridx = 15;
		gbc_tglbtnSquare.gridy = 0;
		panelNorth.add(tglbtnSquare, gbc_tglbtnSquare);
		
		tglbtnRectangle = new JToggleButton(rectangle);
		tglbtnRectangle.setBorder(null);
		buttonGroup.add(tglbtnRectangle);
		tglbtnRectangle.setBackground(Color.decode(color));
		tglbtnRectangle.setBorderPainted(false);
		tglbtnRectangle.setToolTipText("Rectangle");
		buttonGroup.add(getTglbtnRectangle());
		
		GridBagConstraints gbc_tglbtnRectangle = new GridBagConstraints();
		gbc_tglbtnRectangle.gridheight = 2;
		gbc_tglbtnRectangle.fill = GridBagConstraints.BOTH;
		gbc_tglbtnRectangle.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnRectangle.gridx = 16;
		gbc_tglbtnRectangle.gridy = 0;
		panelNorth.add(tglbtnRectangle, gbc_tglbtnRectangle);
		
		tglbtnCircle = new JToggleButton(circle);
		tglbtnCircle.setBorder(null);
		buttonGroup.add(tglbtnCircle);
		tglbtnCircle.setBackground(Color.decode(color));
		tglbtnCircle.setBorderPainted(false);
		tglbtnCircle.setToolTipText("Circle");
		buttonGroup.add(getTglbtnCircle());
		
		GridBagConstraints gbc_tglbtnCircle = new GridBagConstraints();
		gbc_tglbtnCircle.gridheight = 2;
		gbc_tglbtnCircle.fill = GridBagConstraints.BOTH;
		gbc_tglbtnCircle.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnCircle.gridx = 17;
		gbc_tglbtnCircle.gridy = 0;
		panelNorth.add(tglbtnCircle, gbc_tglbtnCircle);
		tglbtnHexagon = new JToggleButton(hexagon);
		tglbtnHexagon.setBorder(null);
		buttonGroup.add(tglbtnHexagon);
		tglbtnHexagon.setBackground(Color.decode(color));
		tglbtnHexagon.setBorderPainted(false);
		tglbtnHexagon.setToolTipText("Hexagon");



		buttonGroup.add(getTglbtnHexagon());
		GridBagConstraints gbc_tglbtnHexagon = new GridBagConstraints();
		gbc_tglbtnHexagon.fill = GridBagConstraints.BOTH;
		gbc_tglbtnHexagon.insets = new Insets(0, 0, 0, 5);
		gbc_tglbtnHexagon.gridheight = 2;
		gbc_tglbtnHexagon.gridx = 18;
		gbc_tglbtnHexagon.gridy = 0;
		panelNorth.add(tglbtnHexagon, gbc_tglbtnHexagon);

		updateView(0, 0, 0);

	}


	public void updateView(int numberSelectedObjects, int flag,int size) {
		this.numberSelectedObjects=numberSelectedObjects;	
		if(size==0) {
			this.btnSave.setEnabled(false);
		}
		else if(size>0) {
			this.btnSave.setEnabled(true);
		}

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
