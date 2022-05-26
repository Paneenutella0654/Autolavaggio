package autolavaggio.autolavaggio.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import autolavaggio.autolavaggio.model.Autolavaggio;
import net.miginfocom.swing.MigLayout;

public class GestisciAutolavaggiView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<Autolavaggio> cmbAutolavaggio;
	private JButton btnAssegna;
	private JPanel panel;
	private JLabel lblNomeHeader;
	private JLabel lblNomeBody;
	private JLabel lblTelefonoHeader;
	private JLabel lblPivaBody;
	private JLabel lblTelefonoBody;
	private JLabel lblEmailHeader;
	private JLabel lblEmailBody;
	private JLabel lblIndirizzoHeader;
	private JLabel lblIndirizzoBody;
	private JLabel lblAutoHeader;
	private JLabel lblAutoBody;
	private JLabel lblCodaMaxHeader;
	private JLabel lblCodaMaxBody;



	public GestisciAutolavaggiView() {
		setTitle("Autolavaggio - Gestione autolavaggio");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 768, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[82.00,grow,fill][grow,fill][grow,fill][grow,fill][grow,fill]", "[][][30.00][30.00][30.00][][grow][]"));
		
		JLabel lblAutolavaggio = new JLabel("Seleziona un Autolavaggio");
		contentPane.add(lblAutolavaggio, "cell 0 0 2 1");
		
		cmbAutolavaggio = new JComboBox<>();
		contentPane.add(cmbAutolavaggio, "cell 0 1 4 1,growx");
		
	
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, "cell 0 2 5 3,grow");
		panel.setLayout(new MigLayout("", "[90.00][][210.00][][grow]", "[18.00][18.00][18.00][18.00][18.00]"));
		
		lblNomeHeader = new JLabel("Nome:");
		lblNomeHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblNomeHeader, "cell 1 0");
		
		lblNomeBody = new JLabel("Autolavaggio Irpinia");
		panel.add(lblNomeBody, "cell 2 0");
		
		/*
		lblIconAutolavaggio = new JLabel("");
		ImageIcon imageIcon = new ImageIcon(GestisciAutoView.class.getResource("autolavaggio/autolavaggio/icon/omino.png/img_autola.jpg")); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
		lblIconAutolavaggio.setIcon(imageIcon);
		panel.add(lblIconRisto, "cell 0 0 1 5");
		*/
		
		lblTelefonoHeader = new JLabel("Telefono:");
		lblTelefonoHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblTelefonoHeader, "cell 1 2");
		
		lblTelefonoBody = new JLabel("+393387452169");
		panel.add(lblTelefonoBody, "cell 2 2");
		
		lblEmailHeader = new JLabel("Email:");
		lblEmailHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblEmailHeader, "cell 3 2");
		
		lblEmailBody = new JLabel("antoniamirilia@email.it");
		panel.add(lblEmailBody, "cell 4 2");
		
		lblIndirizzoHeader = new JLabel("Indirizzo:");
		lblIndirizzoHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblIndirizzoHeader, "cell 1 3");
		
		lblIndirizzoBody = new JLabel("Contrada Pescara 83040 Aree Industriali (AV)");
		panel.add(lblIndirizzoBody, "cell 2 3 3 1");
		
		lblAutoHeader = new JLabel("Auto in Coda:");
		lblAutoHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblAutoHeader, "cell 1 4");
		
		lblAutoBody = new JLabel("7");
		panel.add(lblAutoBody, "cell 2 4");
		
		lblCodaMaxHeader = new JLabel("Coda Max:");
		lblCodaMaxHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblCodaMaxHeader, "cell 3 4");
		
		lblCodaMaxBody = new JLabel("30");
		panel.add(lblCodaMaxBody, "cell 4 4");
		
	}

	public JComboBox<Autolavaggio> getCmbAutolavaggio() {
		return cmbAutolavaggio;
	}
	public JButton getBtnAssegna() {
		return btnAssegna;
	}
	public JLabel getLblEmailBody() {
		return lblEmailBody;
	}
	public JLabel getLblCodaMaxBody() {
		return lblCodaMaxBody;
	}
	public JLabel getLblAutoBody() {
		return lblAutoBody;
	}
	public JLabel getLblNomeBody() {
		return lblNomeBody;
	}
	public JLabel getLblPivaBody() {
		return lblPivaBody;
	}
	public JLabel getLblTelefonoBody() {
		return lblTelefonoBody;
	}
	public JLabel getLblIndirizzoBody() {
		return lblIndirizzoBody;
	}

}
