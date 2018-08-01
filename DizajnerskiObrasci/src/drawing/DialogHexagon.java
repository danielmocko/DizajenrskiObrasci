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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import geometry.HexagonAdapter;


public class DialogHexagon extends JDialog {

	private Frame frame;
	private final JPanel contentPanel = new JPanel();
	private JButton btnAccept;
	private JButton btnDecline;
	private JTextField txtXCenter;
	private JTextField txtYCenter;

	private Color edgeColorDialog;
	private Color insideColorDialog;
	private JButton btnInsideColor;
	private JButton btnEdgeColor;
	private HexagonAdapter hexagon;
	private boolean accept;
	private JTextField txtRadius;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogHexagon dialog = new DialogHexagon();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogHexagon() {
		setModal(true);
		setBounds(100, 100, 450, 352);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblCircle = new JLabel("Hexagon");
		lblCircle.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblCenter = new JLabel("Center :");
		lblCenter.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblXCoordinate = new JLabel("X coordinate");
		lblXCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JLabel lblYCoordinate = new JLabel("Y coordinate");
		lblYCoordinate.setFont(new Font("Tahoma", Font.PLAIN, 11));

		txtXCenter = new JTextField();
		txtXCenter.setColumns(10);

		txtYCenter = new JTextField();
		txtYCenter.setColumns(10);

		JLabel lblEdgeColor = new JLabel("Edge color");
		lblEdgeColor.setFont(new Font("Tahoma", Font.PLAIN, 12));

		btnEdgeColor = new JButton("");

		JLabel lblInsideColor = new JLabel("Inside color");
		lblInsideColor.setFont(new Font("Tahoma", Font.PLAIN, 12));

		btnInsideColor = new JButton("");

		txtRadius = new JTextField();
		txtRadius.setColumns(10);

		JLabel lblRadius = new JLabel("Radius");
		lblRadius.setFont(new Font("Tahoma", Font.PLAIN, 12));

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel
		.setHorizontalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap(197, Short.MAX_VALUE)
						.addComponent(lblCircle).addGap(186))
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(96).addGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING).addComponent(lblCenter)
						.addGroup(gl_contentPanel.createSequentialGroup().addGroup(gl_contentPanel
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(33)
										.addGroup(
												gl_contentPanel.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblYCoordinate)
												.addComponent(lblXCoordinate))
										.addGap(52)
										.addGroup(gl_contentPanel
												.createParallelGroup(Alignment.TRAILING).addComponent(
														txtXCenter, GroupLayout.DEFAULT_SIZE, 57,
														Short.MAX_VALUE)
												.addComponent(txtYCenter, 0, 0, Short.MAX_VALUE)))
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(lblInsideColor)
												.addComponent(lblEdgeColor)
												.addComponent(lblRadius))
										.addGap(18)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(txtRadius,
														GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
												.addComponent(btnEdgeColor, GroupLayout.DEFAULT_SIZE,
														87, Short.MAX_VALUE)
												.addComponent(btnInsideColor,
														GroupLayout.DEFAULT_SIZE, 87,
														Short.MAX_VALUE))))
								.addGap(19)))
						.addGap(107)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(7).addComponent(lblCircle).addGap(5)
						.addComponent(lblCenter).addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtXCenter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblXCoordinate))
						.addGap(9)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblYCoordinate)
								.addComponent(txtYCenter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRadius))
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addComponent(lblEdgeColor)
								.addComponent(btnEdgeColor, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(
								gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnInsideColor, GroupLayout.PREFERRED_SIZE, 23,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblInsideColor))
						.addGap(32)));
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
			.setVerticalGroup(gl_buttonPane.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
					gl_buttonPane.createSequentialGroup().addContainerGap()
					.addGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING)
							.addComponent(btnAccept).addComponent(btnDecline))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
			buttonPane.setLayout(gl_buttonPane);
			btnEdgeColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					edgeColorDialog = JColorChooser.showDialog(null, "Edge color",edgeColorDialog);
					if(edgeColorDialog!=null) {
						btnEdgeColor.setBackground(edgeColorDialog);
					}
				}
			});
			btnInsideColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					insideColorDialog = JColorChooser.showDialog(null, "Inside color", insideColorDialog);
					if(insideColorDialog!=null) {
						btnInsideColor.setBackground(insideColorDialog);
					}
				}
			});
			btnAccept.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				
					
					int x=Integer.parseInt(getTxtXCenter().getText());
					int y=Integer.parseInt(getTxtYCenter().getText());
					int r=Integer.parseInt(getTxtRadius().getText());
					hexagon = new HexagonAdapter(x,y,r,getBtnEdgeColor().getBackground(),getBtnInsideColor().getBackground());
					setVisible(false);
					setAccept(true);
				}
			});
			btnDecline.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					hexagon = null;
					setAccept(false);
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

	public JTextField getTxtXCenter() {
		return txtXCenter;
	}

	public void setTxtXCenter(JTextField txtXCenter) {
		this.txtXCenter = txtXCenter;
	}

	public JTextField getTxtYCenter() {
		return txtYCenter;
	}

	public void setTxtYCenter(JTextField txtYCenter) {
		this.txtYCenter = txtYCenter;
	}

	public Color getEdgeColorDialog() {
		return edgeColorDialog;
	}

	public void setEdgeColorDialog(Color edgeColorDialog) {
		this.edgeColorDialog = edgeColorDialog;
	}

	public Color getInsideColorDialog() {
		return insideColorDialog;
	}

	public void setInsideColorDialog(Color insideColorDialog) {
		this.insideColorDialog = insideColorDialog;
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

	public HexagonAdapter getHexagon() {
		return hexagon;
	}

	public void setHexagon(HexagonAdapter hexagon) {
		this.hexagon = hexagon;
	}

	public boolean isAccept() {
		return accept;
	}

	public void setAccept(boolean accept) {
		this.accept = accept;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

}
