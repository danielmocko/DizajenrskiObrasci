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
		
		Icon open = new ImageIcon("Icons/open.png");

		Icon save = new ImageIcon("Icons/save.png");

		Icon load = new ImageIcon("Icons/load.png");

		Icon undo = new ImageIcon("Icons/undo.png");

		Icon redo = new ImageIcon("Icons/redo.png");
		
		Icon select = new ImageIcon("Icons/select.png");

		Icon toBack= new ImageIcon("Icons/goToBack.png");

		Icon toFront = new ImageIcon("Icons/goToFront.png");

		Icon bringToBack = new ImageIcon("Icons/bringToBack.png");
		
		Icon bringToFront = new ImageIcon("Icons/bringToFront.png");
		
		Icon modify = new ImageIcon("Icons/modify.png");
		
		Icon delete = new ImageIcon("Icons/delete.png");

		Icon point = new ImageIcon("Icons/point.png");

		Icon line = new ImageIcon("Icons/line.png");

		Icon square = new ImageIcon("Icons/square.png");

		Icon rectangle = new ImageIcon("Icons/rectangle.png");

		Icon circle = new ImageIcon("Icons/circle.png");

		Icon hexagon = new ImageIcon("Icons/Hexagon.png");

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
		
				btnUndo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.undo();
		
					}
				});
				btnSave = new JButton(save);
				btnSave.setBorder(null);
				btnSave.setBackground(Color.decode(color));
				btnSave.setBorderPainted(false);
				
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
						GridBagLayout gbl_panel = new GridBagLayout();
						gbl_panel.columnWidths = new int[]{0, 0, 0};
						gbl_panel.rowHeights = new int[]{0, 0, 0};
						gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
						gbl_panel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
						panel.setLayout(gbl_panel);
												
												JLabel lblBorderColor = new JLabel("Border color:");
												GridBagConstraints gbc_lblBorderColor = new GridBagConstraints();
												gbc_lblBorderColor.fill = GridBagConstraints.BOTH;
												gbc_lblBorderColor.insets = new Insets(0, 0, 5, 5);
												gbc_lblBorderColor.gridx = 0;
												gbc_lblBorderColor.gridy = 0;
												panel.add(lblBorderColor, gbc_lblBorderColor);
												
														btnInsideColor = new JButton();
														GridBagConstraints gbc_btnInsideColor = new GridBagConstraints();
														gbc_btnInsideColor.fill = GridBagConstraints.BOTH;
														gbc_btnInsideColor.insets = new Insets(0, 0, 5, 0);
														gbc_btnInsideColor.gridx = 1;
														gbc_btnInsideColor.gridy = 0;
														panel.add(btnInsideColor, gbc_btnInsideColor);
														btnInsideColor.setBackground(Color.WHITE);
														
														btnInsideColor.addActionListener(new ActionListener() {
															public void actionPerformed(ActionEvent e) {
																controller.insideColor(e);
															}
														});
												
												lblAreaColor = new JLabel("Area color:");
												GridBagConstraints gbc_lblAreaColor = new GridBagConstraints();
												gbc_lblAreaColor.fill = GridBagConstraints.BOTH;
												gbc_lblAreaColor.insets = new Insets(0, 0, 0, 5);
												gbc_lblAreaColor.gridx = 0;
												gbc_lblAreaColor.gridy = 1;
												panel.add(lblAreaColor, gbc_lblAreaColor);
												
														btnEdgeColor = new JButton();
														GridBagConstraints gbc_btnEdgeColor = new GridBagConstraints();
														gbc_btnEdgeColor.fill = GridBagConstraints.BOTH;
														gbc_btnEdgeColor.gridx = 1;
														gbc_btnEdgeColor.gridy = 1;
														panel.add(btnEdgeColor, gbc_btnEdgeColor);
														btnEdgeColor.setBackground(Color.BLACK);
												
												btnEdgeColor.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent e) {
														controller.edgeColor(e);
													}
												});
						btnOpen = new JButton(open);
						btnOpen.setBorder(null);
						btnOpen.setBackground(Color.decode(color));
						btnOpen.setBorderPainted(false);
						
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
								gbc_btnOpen.anchor = GridBagConstraints.NORTH;
								gbc_btnOpen.fill = GridBagConstraints.HORIZONTAL;
								gbc_btnOpen.insets = new Insets(0, 0, 5, 5);
								gbc_btnOpen.gridx = 1;
								gbc_btnOpen.gridy = 1;
								panelNorth.add(btnOpen, gbc_btnOpen);
						GridBagConstraints gbc_btnSave = new GridBagConstraints();
						gbc_btnSave.anchor = GridBagConstraints.NORTH;
						gbc_btnSave.fill = GridBagConstraints.HORIZONTAL;
						gbc_btnSave.insets = new Insets(0, 0, 5, 5);
						gbc_btnSave.gridx = 2;
						gbc_btnSave.gridy = 1;
						panelNorth.add(btnSave, gbc_btnSave);
				btnLoad = new JButton(load);
				btnLoad.setBorder(null);
				btnLoad.setBackground(Color.decode(color));
				btnLoad.setBorderPainted(false);
				btnLoad.setEnabled(false);
				
						btnLoad.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								controller.loadData();
				
							}
						});
						GridBagConstraints gbc_btnLoad = new GridBagConstraints();
						gbc_btnLoad.anchor = GridBagConstraints.NORTH;
						gbc_btnLoad.fill = GridBagConstraints.HORIZONTAL;
						gbc_btnLoad.insets = new Insets(0, 0, 5, 5);
						gbc_btnLoad.gridx = 3;
						gbc_btnLoad.gridy = 1;
						panelNorth.add(btnLoad, gbc_btnLoad);
				GridBagConstraints gbc_btnUndo = new GridBagConstraints();
				gbc_btnUndo.anchor = GridBagConstraints.NORTH;
				gbc_btnUndo.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnUndo.insets = new Insets(0, 0, 5, 5);
				gbc_btnUndo.gridx = 4;
				gbc_btnUndo.gridy = 1;
				panelNorth.add(btnUndo, gbc_btnUndo);
		btnBringToBack = new JButton(bringToBack);
		btnBringToBack.setBorder(null);
		btnBringToBack.setBackground(Color.decode(color));
		btnBringToBack.setBorderPainted(false);
		
				btnBringToBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.bringToBack(e);
					}
				});
				btnToBack = new JButton(toBack);
				btnToBack.setBorder(null);
				btnToBack.setBackground(Color.decode(color));
				btnToBack.setBorderPainted(false);
				
						btnToBack.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								controller.toBack(e);
							}
						});
						btnModify = new JButton(modify);
						btnModify.setBorder(null);
						btnModify.setBackground(Color.decode(color));
						btnModify.setBorderPainted(false);
						
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
								
										btnRedo.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												controller.redo();
								
											}
										});
										GridBagConstraints gbc_btnRedo = new GridBagConstraints();
										gbc_btnRedo.anchor = GridBagConstraints.NORTH;
										gbc_btnRedo.fill = GridBagConstraints.HORIZONTAL;
										gbc_btnRedo.insets = new Insets(0, 0, 5, 5);
										gbc_btnRedo.gridx = 5;
										gbc_btnRedo.gridy = 1;
										panelNorth.add(btnRedo, gbc_btnRedo);
								tglbtnSelect = new JToggleButton(select);
								tglbtnSelect.setBorder(null);
								tglbtnSelect.setBackground(Color.decode(color));
								tglbtnSelect.setBorderPainted(false);
								buttonGroup.add(getTglbtnSelect());
								GridBagConstraints gbc_tglbtnSelect = new GridBagConstraints();
								gbc_tglbtnSelect.anchor = GridBagConstraints.NORTH;
								gbc_tglbtnSelect.fill = GridBagConstraints.HORIZONTAL;
								gbc_tglbtnSelect.insets = new Insets(0, 0, 5, 5);
								gbc_tglbtnSelect.gridx = 6;
								gbc_tglbtnSelect.gridy = 1;
								panelNorth.add(tglbtnSelect, gbc_tglbtnSelect);
								GridBagConstraints gbc_btnModify = new GridBagConstraints();
								gbc_btnModify.anchor = GridBagConstraints.NORTH;
								gbc_btnModify.fill = GridBagConstraints.HORIZONTAL;
								gbc_btnModify.insets = new Insets(0, 0, 5, 5);
								gbc_btnModify.gridx = 7;
								gbc_btnModify.gridy = 1;
								panelNorth.add(btnModify, gbc_btnModify);
						btnDelete = new JButton(delete);
						btnDelete.setBorder(null);
						btnDelete.setBackground(Color.decode(color));
						btnDelete.setBorderPainted(false);
						
								btnDelete.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										controller.delete(e);
									}
								});
								GridBagConstraints gbc_btnDelete = new GridBagConstraints();
								gbc_btnDelete.anchor = GridBagConstraints.NORTH;
								gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
								gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
								gbc_btnDelete.gridx = 8;
								gbc_btnDelete.gridy = 1;
								panelNorth.add(btnDelete, gbc_btnDelete);
						GridBagConstraints gbc_btnToBack = new GridBagConstraints();
						gbc_btnToBack.anchor = GridBagConstraints.NORTH;
						gbc_btnToBack.fill = GridBagConstraints.HORIZONTAL;
						gbc_btnToBack.insets = new Insets(0, 0, 5, 5);
						gbc_btnToBack.gridx = 9;
						gbc_btnToBack.gridy = 1;
						panelNorth.add(btnToBack, gbc_btnToBack);
				btnToFront = new JButton(toFront);
				btnToFront.setBorder(null);
				btnToFront.setBackground(Color.decode(color));
				btnToFront.setBorderPainted(false);
				
						btnToFront.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								controller.toFront();
							}
						});
						GridBagConstraints gbc_btnToFront = new GridBagConstraints();
						gbc_btnToFront.anchor = GridBagConstraints.NORTH;
						gbc_btnToFront.fill = GridBagConstraints.HORIZONTAL;
						gbc_btnToFront.insets = new Insets(0, 0, 5, 5);
						gbc_btnToFront.gridx = 10;
						gbc_btnToFront.gridy = 1;
						panelNorth.add(btnToFront, gbc_btnToFront);
				GridBagConstraints gbc_btnBringToBack = new GridBagConstraints();
				gbc_btnBringToBack.anchor = GridBagConstraints.NORTH;
				gbc_btnBringToBack.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnBringToBack.insets = new Insets(0, 0, 5, 5);
				gbc_btnBringToBack.gridx = 11;
				gbc_btnBringToBack.gridy = 1;
				panelNorth.add(btnBringToBack, gbc_btnBringToBack);
		btnBringToFront = new JButton(bringToFront);
		btnBringToFront.setBorder(null);
		btnBringToFront.setBackground(Color.decode(color));
		btnBringToFront.setBorderPainted(false);
		
				btnBringToFront.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.bringToFront(e);
					}
				});
				GridBagConstraints gbc_btnBringToFront = new GridBagConstraints();
				gbc_btnBringToFront.anchor = GridBagConstraints.NORTH;
				gbc_btnBringToFront.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnBringToFront.insets = new Insets(0, 0, 5, 5);
				gbc_btnBringToFront.gridx = 12;
				gbc_btnBringToFront.gridy = 1;
				panelNorth.add(btnBringToFront, gbc_btnBringToFront);
		tglbtnPoint = new JToggleButton(point);
		tglbtnPoint.setBorder(null);
		buttonGroup.add(tglbtnPoint);
		tglbtnPoint.setBackground(Color.decode(color));
		tglbtnPoint.setBorderPainted(false);
		buttonGroup.add(getTglbtnPoint());
		GridBagConstraints gbc_tglbtnPoint = new GridBagConstraints();
		gbc_tglbtnPoint.anchor = GridBagConstraints.NORTH;
		gbc_tglbtnPoint.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnPoint.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnPoint.gridx = 13;
		gbc_tglbtnPoint.gridy = 1;
		panelNorth.add(tglbtnPoint, gbc_tglbtnPoint);
		tglbtnLine = new JToggleButton(line);
		tglbtnLine.setBorder(null);
		buttonGroup.add(tglbtnLine);
		tglbtnLine.setBackground(Color.decode(color));
		tglbtnLine.setBorderPainted(false);
		buttonGroup.add(getTglbtnLine());
		GridBagConstraints gbc_tglbtnLine = new GridBagConstraints();
		gbc_tglbtnLine.anchor = GridBagConstraints.NORTH;
		gbc_tglbtnLine.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnLine.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnLine.gridx = 14;
		gbc_tglbtnLine.gridy = 1;
		panelNorth.add(tglbtnLine, gbc_tglbtnLine);
		tglbtnSquare = new JToggleButton(square);
		tglbtnSquare.setBorder(null);
		buttonGroup.add(tglbtnSquare);
		tglbtnSquare.setBackground(Color.decode(color));
		tglbtnSquare.setBorderPainted(false);
		buttonGroup.add(getTglbtnSquare());
		GridBagConstraints gbc_tglbtnSquare = new GridBagConstraints();
		gbc_tglbtnSquare.anchor = GridBagConstraints.NORTH;
		gbc_tglbtnSquare.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnSquare.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnSquare.gridx = 15;
		gbc_tglbtnSquare.gridy = 1;
		panelNorth.add(tglbtnSquare, gbc_tglbtnSquare);
		tglbtnRectangle = new JToggleButton(rectangle);
		tglbtnRectangle.setBorder(null);
		buttonGroup.add(tglbtnRectangle);
		tglbtnRectangle.setBackground(Color.decode(color));
		tglbtnRectangle.setBorderPainted(false);
		buttonGroup.add(getTglbtnRectangle());
		GridBagConstraints gbc_tglbtnRectangle = new GridBagConstraints();
		gbc_tglbtnRectangle.anchor = GridBagConstraints.NORTH;
		gbc_tglbtnRectangle.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnRectangle.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnRectangle.gridx = 16;
		gbc_tglbtnRectangle.gridy = 1;
		panelNorth.add(tglbtnRectangle, gbc_tglbtnRectangle);
		tglbtnCircle = new JToggleButton(circle);
		tglbtnCircle.setBorder(null);
		buttonGroup.add(tglbtnCircle);
		tglbtnCircle.setBackground(Color.decode(color));
		tglbtnCircle.setBorderPainted(false);
		buttonGroup.add(getTglbtnCircle());
		GridBagConstraints gbc_tglbtnCircle = new GridBagConstraints();
		gbc_tglbtnCircle.anchor = GridBagConstraints.NORTH;
		gbc_tglbtnCircle.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnCircle.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnCircle.gridx = 17;
		gbc_tglbtnCircle.gridy = 1;
		panelNorth.add(tglbtnCircle, gbc_tglbtnCircle);
		tglbtnHexagon = new JToggleButton(hexagon);
		tglbtnHexagon.setBorder(null);
		buttonGroup.add(tglbtnHexagon);
		tglbtnHexagon.setBackground(Color.decode(color));
		tglbtnHexagon.setBorderPainted(false);
		
		
		
				buttonGroup.add(getTglbtnHexagon());
				GridBagConstraints gbc_tglbtnHexagon = new GridBagConstraints();
				gbc_tglbtnHexagon.anchor = GridBagConstraints.NORTH;
				gbc_tglbtnHexagon.fill = GridBagConstraints.HORIZONTAL;
				gbc_tglbtnHexagon.insets = new Insets(0, 0, 0, 5);
				gbc_tglbtnHexagon.gridheight = 2;
				gbc_tglbtnHexagon.gridx = 18;
				gbc_tglbtnHexagon.gridy = 1;
				panelNorth.add(tglbtnHexagon, gbc_tglbtnHexagon);

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
