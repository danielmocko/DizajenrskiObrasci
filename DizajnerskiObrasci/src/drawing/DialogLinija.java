package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import geometry.*;


public class DialogLinija extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblStartX;
	private JLabel lblLine;
	private JTextField txtXCoordinateStartPoint;
	private JTextField txtYCoordinateStartPoint;
	private JTextField txtXCoordinateEndPoint;
	private JTextField txtYCoordinateEndPoint;
	private JButton btnAccept;
	private JButton btnDecline;
	private boolean accept = false;

	private Color colorDlg;

	private Line dlgLine;
	private JButton btnColorDlg;
	private Point pStart;
	private Point pEnd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogLinija dialog = new DialogLinija();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogLinija() {
		setModal(true);
		setMinimumSize(new Dimension(430, 340));
		setBounds(100, 100, 456, 380);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		{
			lblStartX = new JLabel("X coordinate");
		}
		{
			lblLine = new JLabel("Line");
			lblLine.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		setResizable(false);
		JLabel lblYkoordinata = new JLabel("Y coordinate");

		JLabel lblXkoordinatatKrajnja = new JLabel("X coordinate");

		JLabel lblYkoordinataTKrajnja = new JLabel("Y coordinate");

		JLabel lblBojaIvice = new JLabel("Color");

		txtXCoordinateStartPoint = new JTextField();
		txtXCoordinateStartPoint.setColumns(10);

		txtYCoordinateStartPoint = new JTextField();
		txtYCoordinateStartPoint.setColumns(10);

		txtXCoordinateEndPoint = new JTextField();
		txtXCoordinateEndPoint.setColumns(10);

		txtYCoordinateEndPoint = new JTextField();
		txtYCoordinateEndPoint.setColumns(10);

		btnColorDlg = new JButton("");

		JLabel lblPocetnaTacka = new JLabel("Start point");
		lblPocetnaTacka.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblKrajnjaTacka = new JLabel("End point");
		lblKrajnjaTacka.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(187)
						.addComponent(lblLine).addContainerGap(205, Short.MAX_VALUE))
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addGroup(gl_contentPanel
								.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel.createSequentialGroup()
										.addContainerGap(29, Short.MAX_VALUE)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblStartX).addComponent(lblYkoordinata))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
												.addComponent(txtYCoordinateStartPoint, 0, 0, Short.MAX_VALUE)
												.addComponent(txtXCoordinateStartPoint, GroupLayout.DEFAULT_SIZE, 67,
														Short.MAX_VALUE)
												.addComponent(lblBojaIvice, Alignment.TRAILING))
										.addGap(59))
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(42)
										.addComponent(lblPocetnaTacka).addGap(77)))
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
								.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblXkoordinatatKrajnja)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(txtXCoordinateEndPoint, GroupLayout.PREFERRED_SIZE, 67,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblYkoordinataTKrajnja)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(txtYCoordinateEndPoint, 0, 0, Short.MAX_VALUE)))
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(23)
										.addComponent(lblKrajnjaTacka))
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnColorDlg,
												GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)))
						.addGap(78)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(14).addComponent(lblLine).addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblPocetnaTacka)
								.addComponent(lblKrajnjaTacka))
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblStartX, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblXkoordinatatKrajnja)
								.addComponent(txtXCoordinateStartPoint, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtXCoordinateEndPoint, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblYkoordinata)
								.addComponent(txtYCoordinateStartPoint, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblYkoordinataTKrajnja).addComponent(txtYCoordinateEndPoint,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(50)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addComponent(lblBojaIvice)
								.addComponent(btnColorDlg, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(50, Short.MAX_VALUE)));
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
					.addGroup(gl_buttonPane.createSequentialGroup().addGap(149).addComponent(btnAccept)
							.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnDecline).addContainerGap(149,
									Short.MAX_VALUE)));
			gl_buttonPane.setVerticalGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup().addContainerGap()
							.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE).addComponent(btnAccept)
									.addComponent(btnDecline))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
			btnColorDlg.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					colorDlg = JColorChooser.showDialog(null, "Choose color", colorDlg);
					if (colorDlg != null) {
						btnColorDlg.setBackground(colorDlg);
					}
				}
			});
			buttonPane.setLayout(gl_buttonPane);
			btnAccept.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						dlgLine = new Line();
						pStart = new Point(Integer.parseInt(getTxtXCoordinateStartPoint().getText()),
								Integer.parseInt(getTxtYCoordinateStartPoint().getText()));
						pEnd = new Point(Integer.parseInt(getTxtXCoordinateEndPoint().getText()),
								Integer.parseInt(getTxtYCoordinateEndPoint().getText()));

						// linijaDijalog.gettPocetna().setX(Integer.parseInt(getTxtXkoordinataTPocetna().getText()));
						// linijaDijalog.gettPocetna().setY(Integer.parseInt(getTxtYkoordinataTPocetna().getText()));
						// linijaDijalog.gettKrajnja().setX(Integer.parseInt(getTxtXkoordinataTKrajnja().getText()));
						// linijaDijalog.gettKrajnja().setY(Integer.parseInt(getTxtYkoordinataTKrajnja().getText()));
						dlgLine.setpStart(pStart);
						dlgLine.setpEnd(pEnd);
						dlgLine.setEdgeColor(btnColorDlg.getBackground());
						// JOptionPane.showMessageDialog(null, tPocetna);
						setAccept(true);
						setVisible(false);
					} catch (Exception g) {
						JOptionPane.showMessageDialog(null, "Title");
					}

				}
			});
			btnDecline.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dlgLine = null;
					setAccept(false);
					setVisible(false);
				}
			});
		}
	}

	public JLabel getLblStartX() {
		return lblStartX;
	}

	public void setLblStartX(JLabel lblStartX) {
		this.lblStartX = lblStartX;
	}

	public JLabel getLblLine() {
		return lblLine;
	}

	public void setLblLine(JLabel lblLine) {
		this.lblLine = lblLine;
	}

	public JTextField getTxtXCoordinateStartPoint() {
		return txtXCoordinateStartPoint;
	}

	public void setTxtXCoordinateStartPoint(JTextField txtXCoordinateStartPoint) {
		this.txtXCoordinateStartPoint = txtXCoordinateStartPoint;
	}

	public JTextField getTxtYCoordinateStartPoint() {
		return txtYCoordinateStartPoint;
	}

	public void setTxtYCoordinateStartPoint(JTextField txtYCoordinateStartPoint) {
		this.txtYCoordinateStartPoint = txtYCoordinateStartPoint;
	}

	public JTextField getTxtXCoordinateEndPoint() {
		return txtXCoordinateEndPoint;
	}

	public void setTxtXCoordinateEndPoint(JTextField txtXCoordinateEndPoint) {
		this.txtXCoordinateEndPoint = txtXCoordinateEndPoint;
	}

	public JTextField getTxtYCoordinateEndPoint() {
		return txtYCoordinateEndPoint;
	}

	public void setTxtYCoordinateEndPoint(JTextField txtYCoordinateEndPoint) {
		this.txtYCoordinateEndPoint = txtYCoordinateEndPoint;
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

	public boolean isAccept() {
		return accept;
	}

	public void setAccept(boolean accept) {
		this.accept = accept;
	}

	public Color getColorDlg() {
		return colorDlg;
	}

	public void setColorDlg(Color colorDlg) {
		this.colorDlg = colorDlg;
	}

	public Line getDlgLine() {
		return dlgLine;
	}

	public void setDlgLine(Line dlgLine) {
		this.dlgLine = dlgLine;
	}

	public JButton getBtnColorDlg() {
		return btnColorDlg;
	}

	public void setBtnColorDlg(JButton btnColorDlg) {
		this.btnColorDlg = btnColorDlg;
	}

	public Point getpStart() {
		return pStart;
	}

	public void setpStart(Point pStart) {
		this.pStart = pStart;
	}

	public Point getpEnd() {
		return pEnd;
	}

	public void setpEnd(Point pEnd) {
		this.pEnd = pEnd;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

}
