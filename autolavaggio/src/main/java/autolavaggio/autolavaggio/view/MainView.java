package autolavaggio.autolavaggio.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnInserisciAuto;
	private JButton btnGestisciAuto;
	private JButton btnFiltraAuto;
	private JButton btnValutaDipendente;
	private JButton btnDipendenteReport;
	private JButton btnAssumiDipendente;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JButton btnGestisciAutolavaggio;
	private JButton btnPunto1;
	private JButton btnPunto2;
	private JButton btnPunto3;
	private JButton btnPunto4;
	private JButton btnPunto5;
	private JButton btnPunto6;
	private JButton btnPunto7;
	private JButton btnPunto8;
	private JButton btnPunto9;
	private JButton btnPunto10;
	private JPanel panel_1;

	public MainView() {
		setTitle("Autolavaggio - Pagina Principale");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 239));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[61.00][][]"));
		
		lblNewLabel = new JLabel("Autolavaggio");
		lblNewLabel.setBackground(new Color(255, 255, 239));
		lblNewLabel.setFont(new Font("Tw Cen MT", Font.PLAIN, 40));
		contentPane.add(lblNewLabel, "cell 0 0,alignx center,aligny center");
		
		panel = new JPanel();
		panel.setBackground(new Color(220, 255, 218));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Seleziona un'operazione", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel, "cell 0 1,grow");
		panel.setLayout(new MigLayout("", "[grow][grow]", "[][][][][]"));
		
		btnGestisciAuto = new JButton("Gestisci Auto");
		btnGestisciAuto.setBackground(new Color(255, 255, 204));
		btnGestisciAuto.setForeground(new Color(0, 0, 51));
		panel.add(btnGestisciAuto, "cell 0 0,growx");
		
		setBtnInserisciAuto(new JButton("Inserisci un Auto"));
		
		btnGestisciAutolavaggio = new JButton("Gestisci Autolavaggio");
		panel.add(btnGestisciAutolavaggio, "cell 0 1 2 1,growx");
		
		btnFiltraAuto = new JButton("Filtra Auto");
		btnFiltraAuto.setBackground(new Color(255, 255, 204));
		btnFiltraAuto.setForeground(new Color(0, 0, 51));
		panel.add(btnFiltraAuto, "cell 0 2,grow");
		
		btnValutaDipendente = new JButton("Valuta un Dipendente");
		btnValutaDipendente.setBackground(new Color(255, 255, 204));
		btnValutaDipendente.setForeground(new Color(0, 0, 51));
		panel.add(btnValutaDipendente, "cell 0 3,grow");
		
		btnDipendenteReport = new JButton("Report di un Dipendente");
		btnDipendenteReport.setBackground(new Color(255, 255, 204));
		btnDipendenteReport.setForeground(new Color(0, 0, 51));
		panel.add(btnDipendenteReport, "cell 1 3,grow");
		
		
		btnAssumiDipendente = new JButton("Assumi Dipendente");
		btnAssumiDipendente.setBackground(new Color(255, 255, 204));
		btnAssumiDipendente.setForeground(new Color(0, 0, 51));
		panel.add(btnAssumiDipendente, "cell 1 2,grow");
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(220, 255, 218));
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Operazioni", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel_1, "cell 0 2,grow");
		panel_1.setLayout(new MigLayout("", "[grow]", "[][][][][][][][][][][][][][][]"));

		
		
		btnPunto1 = new JButton("1. Registrazione di un Auto");
		panel_1.add(btnPunto1, "cell 0 0,grow");
		btnPunto1.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnPunto2 = new JButton("2. Lavaggio di un auto");
		panel_1.add(btnPunto2, "cell 0 1,growx");
		btnPunto2.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnPunto3 = new JButton("3. Valutazione di un Dipendente");
		panel_1.add(btnPunto3, "cell 0 2,growx");
		btnPunto3.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnPunto4 = new JButton("4. Visualizzazione dei clienti che hanno consegnate delle auto per il lavaggio");
		panel_1.add(btnPunto4, "cell 0 3,growx");
		btnPunto4.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnPunto5 = new JButton("5. Assunzione di un nuovo dipendente");
		panel_1.add(btnPunto5, "cell 0 4,growx");
		btnPunto5.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnPunto6 = new JButton("6.Visualizzazione delle auto lavate da dipendente ancora non valutati");
		panel_1.add(btnPunto6, "cell 0 5,growx");
		btnPunto6.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnPunto7 = new JButton("7. Cancellazione di un auto");
		panel_1.add(btnPunto7, "cell 0 6,growx");
		btnPunto7.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnPunto8 = new JButton("8. Stampa di un report che mostri i dati degli autolavaggi, inclusa la coda delle auto");
		panel_1.add(btnPunto8, "cell 0 7,growx");
		btnPunto8.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnPunto9 = new JButton("9. Stampa settimanale di un report che mostri i dati dei dipendente, inclusa la valutazione media ottenuta");
		panel_1.add(btnPunto9, "cell 0 8,growx");
		btnPunto9.setHorizontalAlignment(SwingConstants.LEFT);
		
		btnPunto10 = new JButton("10. Stampa settimanale dei clienti che nell’ultima settimana abbiano effettuato almeno una valutazione abbassando le medie dei dipendenti");
		panel_1.add(btnPunto10, "cell 0 9,growx");
		btnPunto10.setHorizontalAlignment(SwingConstants.LEFT);
		
	}

	public JButton getBtnInserisciOrdine() {
		return btnInserisciAuto;
	}

	public void setBtnInserisciAuto(JButton btnInserisciAuto) {
		this.btnInserisciAuto = btnInserisciAuto;
		btnInserisciAuto.setForeground(new Color(0, 0, 51));
		btnInserisciAuto.setBackground(new Color(255, 255, 204));
		panel.add(btnInserisciAuto, "cell 1 0,growx");
	}
	public JButton getBtnGestisciAuto() {
		return btnGestisciAuto;
	}
	public JButton getBtnFiltraAuto() {
		return btnFiltraAuto;
	}
	public JButton getBtnValutaDipendente() {
		return btnValutaDipendente;
	}
	public JButton getBtnDipendeteReport() {
		return btnDipendenteReport;
	}
	public JButton getBtnAssumiDipendente() {
		return btnAssumiDipendente;
	}
	public JButton getBtnGestisciAutolavaggio() {
		return btnGestisciAutolavaggio;
	}
	public JButton getBtnDipendenteReport() {
		return btnDipendenteReport;
	}
	public JButton getBtnPunto1() {
		return btnPunto1;
	}
	public JButton getBtnPunto2() {
		return btnPunto2;
	}
	public JButton getBtnPunto3() {
		return btnPunto3;
	}
	public JButton getBtnPunto4() {
		return btnPunto4;
	}
	public JButton getBtnPunto5() {
		return btnPunto5;
	}
	public JButton getBtnPunto6() {
		return btnPunto6;
	}
	public JButton getBtnPunto7() {
		return btnPunto7;
	}
	public JButton getBtnPunto8() {
		return btnPunto8;
	}
	public JButton getBtnPunto9() {
		return btnPunto9;
	}
	public JButton getBtnPunto10() {
		return btnPunto10;
	}
}
