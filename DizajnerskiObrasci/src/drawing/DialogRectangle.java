package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;
import geometry.Rectangle;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

public class DialogRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnAccept;
	private JButton btnDecline;
	private JTextField txtXCoordinate;
	private JTextField txtYCoordinate;
	private JTextField txtHeight;
	private Rectangle dlgRectangle;

	private Color edgeColorDlg;
	private Color insideColorDlg;
	private boolean Accept;
	private JButton btnInsideColor;
	private JButton btnEdgeColor;
	private JTextField txtWidth;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogRectangle dialog = new DialogRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogRectangle() {
		setModal(true);
		setBounds(100, 100, 450, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setResizable(false);

		JLabel lblRectangle = new JLabel("Rectangle");
		lblRectangle.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblPointUpLeft = new JLabel("Point upLeft ");
		lblPointUpLeft.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblXCoordinate = new JLabel("X coordinate");

		JLabel lblYCoordinate = new JLabel("Y coordinate");

		txtXCoordinate = new JTextField();
		txtXCoordinate.setColumns(10);

		txtYCoordinate = new JTextField();
		txtYCoordinate.setColumns(10);

		JLabel lblHeight = new JLabel("Height");
		lblHeight.setFont(new Font("Tahoma", Font.PLAIN, 12));

		txtHeight = new JTextField();
		txtHeight.setColumns(10);

		JLabel lblEdgeColor = new JLabel("Edge color");
		lblEdgeColor.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel lblInsideColor = new JLabel("Inside color");
		lblInsideColor.setFont(new Font("Tahoma", Font.PLAIN, 12));

		btnInsideColor = new JButton("");

		btnEdgeColor = new JButton("");

		txtWidth = new JTextField();
		txtWidth.setColumns(10);

		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setFont(new Font("Tahoma", Font.PLAIN, 12));

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel
				.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPanel
						.createSequentialGroup()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel
										.createSequentialGroup()
										.addGap(148).addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblXCoordinate).addComponent(lblYCoordinate))
										.addGap(28)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
												.addComponent(txtXCoordinate, GroupLayout.DEFAULT_SIZE, 51,
														Short.MAX_VALUE)
												.addComponent(txtYCoordinate, Alignment.LEADING,
														GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)))
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(90)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(lblInsideColor)
												.addGroup(gl_contentPanel.createSequentialGroup().addGap(14)
														.addComponent(lblEdgeColor))
												.addGroup(gl_contentPanel.createSequentialGroup().addGap(22)
														.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
																.addComponent(lblWeight).addComponent(lblHeight))))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(txtHeight, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
														96, Short.MAX_VALUE)
												.addComponent(txtWidth, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
														96, Short.MAX_VALUE)
												.addComponent(btnEdgeColor, Alignment.TRAILING,
														GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
												.addGroup(gl_contentPanel.createSequentialGroup()
														.addComponent(btnInsideColor, GroupLayout.DEFAULT_SIZE,
																96, Short.MAX_VALUE)
														.addPreferredGap(ComponentPlacement.RELATED)))))
						.addGap(147))
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(85).addComponent(lblPointUpLeft)
								.addContainerGap(247, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap(167, Short.MAX_VALUE)
								.addComponent(lblRectangle).addGap(147)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap().addComponent(lblRectangle)
						.addGap(11).addComponent(lblPointUpLeft).addGap(11)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblXCoordinate)
								.addComponent(txtXCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtYCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblYCoordinate))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHeight))
						.addGap(14)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblWeight))
						.addGap(18)
						.addGroup(
								gl_contentPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnEdgeColor, GroupLayout.PREFERRED_SIZE, 21,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblEdgeColor))
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblInsideColor).addComponent(btnInsideColor,
										GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(45, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAccept = new JButton("Accept");

				btnAccept.setActionCommand("OK");
				getRootPane().setDefaultButton(btnAccept);
			}
			{
				btnDecline = new JButton("Decline");

				btnDecline.setActionCommand("Cancel");
			}
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup().addGap(143).addComponent(btnAccept).addGap(5)
							.addComponent(btnDecline).addContainerGap(142, Short.MAX_VALUE)));
			gl_buttonPane
					.setVerticalGroup(
							gl_buttonPane.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
									gl_buttonPane.createSequentialGroup()
											.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING)
													.addComponent(btnAccept).addComponent(btnDecline))
											.addContainerGap()));
			buttonPane.setLayout(gl_buttonPane);
			btnEdgeColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					edgeColorDlg = JColorChooser.showDialog(null, "Choose edge color",
							edgeColorDlg);
					if (edgeColorDlg != null) {
						btnEdgeColor.setBackground(edgeColorDlg);
					}
				}
			});
			btnInsideColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					insideColorDlg = JColorChooser.showDialog(null,
							"Choose inside color", insideColorDlg);
					if (insideColorDlg != null) {
						btnInsideColor.setBackground(insideColorDlg);
					}
				}
			});
			btnAccept.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						dlgRectangle = new Rectangle();
						Point upLeft = new Point(Integer.parseInt(getTxtXCoordinate().getText()),
								Integer.parseInt(getTxtYCoordinate().getText()));
						dlgRectangle.setUpLeft(upLeft);
						dlgRectangle.setPageLength(Integer.parseInt(getTxtWidth().getText()));
						dlgRectangle.setHeight(Integer.parseInt(getTxtHeight().getText()));
						dlgRectangle.setEdgeColor(btnEdgeColor.getBackground());
						dlgRectangle.setInsideColor(btnInsideColor.getBackground());
						setAccept(true);
						setVisible(false);
					} catch (Exception h) {
						JOptionPane.showMessageDialog(null, "Title");
					}
				}
			});
			btnDecline.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dlgRectangle = null;
					setAccept(false);
					setVisible(false);

				}
			});
		}
	}

	public JButton getBtnAccept() {
		return btnAccept;
	}

	public void setBtnAccept(JButton btnAccept) {
		this.btnAccept = btnAccept;
	}

	public JButton getBtnDecline() {
		return btnDecline;
	}

	public void setBtnDecline(JButton btnDecline) {
		this.btnDecline = btnDecline;
	}

	public JTextField getTxtXCoordinate() {
		return txtXCoordinate;
	}

	public void setTxtXCoordinate(JTextField txtXCoordinate) {
		this.txtXCoordinate = txtXCoordinate;
	}

	public JTextField getTxtYCoordinate() {
		return txtYCoordinate;
	}

	public void setTxtYCoordinate(JTextField txtYCoordinate) {
		this.txtYCoordinate = txtYCoordinate;
	}

	public JTextField getTxtHeight() {
		return txtHeight;
	}

	public void setTxtHeight(JTextField txtHeight) {
		this.txtHeight = txtHeight;
	}

	public Rectangle getDlgRectangle() {
		return dlgRectangle;
	}

	public void setDlgRectangle(Rectangle dlgRectangle) {
		this.dlgRectangle = dlgRectangle;
	}

	public Color getEdgeColorDlg() {
		return edgeColorDlg;
	}

	public void setEdgeColorDlg(Color edgeColorDlg) {
		this.edgeColorDlg = edgeColorDlg;
	}

	public Color getInsideColorDlg() {
		return insideColorDlg;
	}

	public void setInsideColorDlg(Color insideColorDlg) {
		this.insideColorDlg = insideColorDlg;
	}

	public boolean isAccept() {
		return Accept;
	}

	public void setAccept(boolean accept) {
		Accept = accept;
	}

	public JButton getBtnInsideColor() {
		return btnInsideColor;
	}

	public void setBtnInsideColor(JButton btnInsideColor) {
		this.btnInsideColor = btnInsideColor;
	}

	public JButton getBtnEdgeColor() {
		return btnEdgeColor;
	}

	public void setBtnEdgeColor(JButton btnEdgeColor) {
		this.btnEdgeColor = btnEdgeColor;
	}

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public void setTxtWidth(JTextField txtWidth) {
		this.txtWidth = txtWidth;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}



}
