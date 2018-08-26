package drawing.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

import geometry.Point;

public class DialogPoint extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtXCoordinate;
	private JTextField txtYCoordinate;
	private JLabel lblPoint;
	private JLabel lblXCoordinate;
	private JLabel lblYCoordinate;
	private JLabel lblColor;
	private JButton btnColor;
	private JButton btnAccept;
	private JButton btnDecline;

	private Point dlgPoint;
	private boolean accept;

	private int x;
	private int y;

	private Color color;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogPoint dialog = new DialogPoint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogPoint() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 450, 300);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblPoint = new JLabel("Point ");
			lblPoint.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		{
			lblXCoordinate = new JLabel("X coordinate :");
			lblXCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		{
			txtYCoordinate = new JTextField();
			txtYCoordinate.setColumns(10);
		}
		{
			lblYCoordinate = new JLabel("Y coordinate :");
			lblYCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		{
			txtXCoordinate = new JTextField();
			txtXCoordinate.setColumns(10);
		}
		{
			lblColor = new JLabel("Color ");
			lblColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		{
			btnColor = new JButton("");
			btnColor.setPreferredSize(new Dimension(33, 6));

		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(104)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addComponent(lblYCoordinate)
								.addComponent(lblXCoordinate).addComponent(lblColor, GroupLayout.PREFERRED_SIZE, 61,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18).addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnColor, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE).addGroup(
										gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
												.addComponent(txtXCoordinate).addComponent(txtYCoordinate,
														GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)))
						.addContainerGap(117, Short.MAX_VALUE))
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(170)
						.addComponent(lblPoint, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(179, Short.MAX_VALUE)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(31).addComponent(lblPoint)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtYCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblXCoordinate))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtXCoordinate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblYCoordinate))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblColor, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
								.addComponent(btnColor, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addGap(57)));
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
					.addGroup(gl_buttonPane.createSequentialGroup().addGap(145).addComponent(btnAccept)
							.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnDecline)
							.addContainerGap(145, Short.MAX_VALUE)));
			gl_buttonPane.setVerticalGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup().addGap(7)
							.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE).addComponent(btnAccept)
									.addComponent(btnDecline))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
			buttonPane.setLayout(gl_buttonPane);
			btnColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					color = JColorChooser.showDialog(null, "Choose color", color);
					if (color != null) {
						btnColor.setBackground(color);
					}
				}
			});
			btnAccept.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						dlgPoint = new Point();
						dlgPoint.setX(Integer.parseInt(getTxtXCoordinate().getText()));
						dlgPoint.setY(Integer.parseInt(getTxtYCoordinate().getText()));
						dlgPoint.setBorderColor(btnColor.getBackground());
						setAccept(true);
						setVisible(false);
					} catch (Exception g) {
						JOptionPane.showMessageDialog(null, "Title");
						;
					}
				}
			});
			btnDecline.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dlgPoint = null;
					setAccept(false);
					setVisible(false);
				}
			});
		}
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

	public JLabel getLblPoint() {
		return lblPoint;
	}

	public void setLblPoint(JLabel lblPoint) {
		this.lblPoint = lblPoint;
	}

	public JLabel getLblXCoordinate() {
		return lblXCoordinate;
	}

	public void setLblXCoordinate(JLabel lblXCoordinate) {
		this.lblXCoordinate = lblXCoordinate;
	}

	public JLabel getLblYCoordinate() {
		return lblYCoordinate;
	}

	public void setLblYCoordinate(JLabel lblYCoordinate) {
		this.lblYCoordinate = lblYCoordinate;
	}

	public JLabel getLblColor() {
		return lblColor;
	}

	public void setLblColor(JLabel lblColor) {
		this.lblColor = lblColor;
	}

	public JButton getBtnColor() {
		return btnColor;
	}

	public void setBtnColor(JButton btnColor) {
		this.btnColor = btnColor;
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

	public Point getDlgPoint() {
		return dlgPoint;
	}

	public void setDlgPoint(Point dlgPoint) {
		this.dlgPoint = dlgPoint;
	}

	public boolean isAccept() {
		return accept;
	}

	public void setAccept(boolean accept) {
		this.accept = accept;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

}
