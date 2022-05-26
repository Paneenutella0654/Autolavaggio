package autolavaggio.autolavaggio.view;

import autolavaggio.autolavaggio.model.Dipendente;


import autolavaggio.autolavaggio.model.ValutazioneTableModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableRowSorter;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JTable;

public class DipendenteReportView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableValutazioni;
	private JTable tableSocieta;
	private ValutazioneTableModel modelValutazione;
	private ValutazioneTableModel modelValutazioniBasse;
	private JLabel lblDisponibilitaBody;
	private JLabel lblCognomeBody;
	private JLabel lblCfBody;
	private JLabel lblValutazionemediaBody;
	private JList<Dipendente> lstDipendente;
	private JLabel lblEmailBody;
	private JLabel lblTelefonoBody;
	private JLabel lblNumImpieghiBody;
	private JLabel lblNumValutazioniBody;
	private JLabel lblNomeBody;
	private JTable tableValutazioniBasse;

	public DipendenteReportView() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 860, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[190.00][105.00,grow]", "[][grow][grow][grow]"));
		
		JPanel panelDipendente = new JPanel();
		panelDipendente.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Seleziona Il Dipendente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panelDipendente, "cell 0 0 1 4,grow");
		panelDipendente.setLayout(new MigLayout("", "[190.00]", "[grow][grow]"));
		
		JScrollPane scrollPane = new JScrollPane();
		panelDipendente.add(scrollPane, "flowy,cell 0 0 1 2,growy");
		
		lstDipendente = new JList<>();
		scrollPane.setViewportView(lstDipendente);
		
		JPanel panelDatiDipendente = new JPanel();
		panelDatiDipendente.setBackground(Color.WHITE);
		panelDatiDipendente.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Dati Dipendente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panelDatiDipendente, "cell 1 0,grow");
		panelDatiDipendente.setLayout(new MigLayout("", "[105.00:105.00][][grow][][grow]", "[][][][][][][]"));
		
		/*
		JLabel lblIcon = new JLabel("");
		ImageIcon imageIcon = new ImageIcon(GestisciAutoView.class.getResource("autolavaggio/autolavaggio/icon/omino.png")); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
		lblIcon.setIcon(imageIcon);
		panelDatiDipendente.add(lblIcon, "cell 0 0 1 7,alignx center,growy");
		*/
		
		JLabel lblNomeHeader = new JLabel("Nome:");
		lblNomeHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelDatiDipendente.add(lblNomeHeader, "cell 1 0");
		
		lblNomeBody = new JLabel("Angelo");
		panelDatiDipendente.add(lblNomeBody, "cell 2 0");
		
		JLabel lblCognomeHeader = new JLabel("Cognome:");
		lblCognomeHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelDatiDipendente.add(lblCognomeHeader, "cell 3 0,alignx left");
		
		lblCognomeBody = new JLabel("Carbone");
		panelDatiDipendente.add(lblCognomeBody, "cell 4 0");
		
		JLabel lblCfHeader = new JLabel("Codice Fiscale:");
		lblCfHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelDatiDipendente.add(lblCfHeader, "cell 1 1");
		
		lblCfBody = new JLabel("CRBNGL96L04F839T");
		panelDatiDipendente.add(lblCfBody, "cell 2 1");
		
		JLabel lblTelefonoHeader = new JLabel("Telefono:");
		lblTelefonoHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelDatiDipendente.add(lblTelefonoHeader, "cell 1 2");
		
		lblTelefonoBody = new JLabel("+393664587426");
		panelDatiDipendente.add(lblTelefonoBody, "cell 2 2");
		
		JLabel lblEmailHeader = new JLabel("Email:");
		lblEmailHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelDatiDipendente.add(lblEmailHeader, "cell 3 2");
		
		lblEmailBody = new JLabel("angelocarbone96@studenti.unisa.it");
		panelDatiDipendente.add(lblEmailBody, "cell 4 2");
		
		JLabel lblValutazionemediaHeader = new JLabel("Valutazione Media:");
		lblValutazionemediaHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelDatiDipendente.add(lblValutazionemediaHeader, "cell 1 3");
		
		lblValutazionemediaBody = new JLabel("4.26");
		panelDatiDipendente.add(lblValutazionemediaBody, "cell 2 3");
		
		JLabel lblNumValutazioniHeader = new JLabel("Valutazioni:");
		lblNumValutazioniHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelDatiDipendente.add(lblNumValutazioniHeader, "cell 3 3");
		
		lblNumValutazioniBody = new JLabel("26");
		panelDatiDipendente.add(lblNumValutazioniBody, "cell 4 3");
		
		JLabel lblNumImpieghiHeader = new JLabel("Numero Impieghi:");
		lblNumImpieghiHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelDatiDipendente.add(lblNumImpieghiHeader, "cell 3 4");
		
		lblNumImpieghiBody = new JLabel("9");
		panelDatiDipendente.add(lblNumImpieghiBody, "cell 4 4");
		
		JLabel lblDisponibilitaHeader = new JLabel("Disponibile:");
		lblDisponibilitaHeader.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelDatiDipendente.add(lblDisponibilitaHeader, "cell 3 5");
		
		lblDisponibilitaBody = new JLabel("Si");
		panelDatiDipendente.add(lblDisponibilitaBody, "cell 4 5");
		
		
		JPanel panelValutazioni = new JPanel();
		panelValutazioni.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Valutazioni", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panelValutazioni, "cell 1 1,grow");
		panelValutazioni.setLayout(new MigLayout("", "[105.00,grow][426.00]", "[grow]"));
		
		JScrollPane scrollPaneValutazioni = new JScrollPane();
		panelValutazioni.add(scrollPaneValutazioni, "cell 0 0 2 1,grow");
		
		modelValutazione = new ValutazioneTableModel();
		tableValutazioni = new JTable(modelValutazione);
		scrollPaneValutazioni.setViewportView(tableValutazioni);
		tableValutazioni.setShowGrid(false);
		tableValutazioni.setShowHorizontalLines(false);
		tableValutazioni.setShowVerticalLines(false);
		tableValutazioni.setRowMargin(0);
		tableValutazioni.setIntercellSpacing(new Dimension(0, 0));
		tableValutazioni.setFillsViewportHeight(true);
		TableRowSorter<ValutazioneTableModel> sotValutazioni = new TableRowSorter<>(getModelValutazione());
		tableValutazioni.setRowSorter(sotValutazioni);
		
		JPanel panelValutazioniBasse = new JPanel();
		panelValutazioniBasse.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Valutazioni minori dello score medio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panelValutazioniBasse, "cell 1 3,grow");
		panelValutazioniBasse.setLayout(new MigLayout("", "[105.00,grow]", "[grow]"));
		
		JScrollPane scrollPanelValutazioniBasse = new JScrollPane();
		panelValutazioniBasse.add(scrollPanelValutazioniBasse, "cell 0 0,grow");
		
		modelValutazioniBasse = new ValutazioneTableModel();
		tableValutazioniBasse = new JTable(modelValutazioniBasse);
		scrollPanelValutazioniBasse.setViewportView(tableValutazioniBasse);
		tableValutazioniBasse.setShowGrid(false);
		tableValutazioniBasse.setShowHorizontalLines(false);
		tableValutazioniBasse.setShowVerticalLines(false);
		tableValutazioniBasse.setRowMargin(0);
		tableValutazioniBasse.setIntercellSpacing(new Dimension(0, 0));
		tableValutazioniBasse.setFillsViewportHeight(true);
		TableRowSorter<ValutazioneTableModel> sorterValutazioniBasse = new TableRowSorter<>(modelValutazioniBasse);
		tableValutazioniBasse.setRowSorter(sorterValutazioniBasse);
	}

	public JLabel getLblDisponibilitaBody() {
		return lblDisponibilitaBody;
	}
	public JLabel getLblCognomeBody() {
		return lblCognomeBody;
	}
	public JLabel getLblCfBody() {
		return lblCfBody;
	}
	public JLabel getLblValutazionemediaBody() {
		return lblValutazionemediaBody;
	}
	public JList<Dipendente> getLstDipendente() {
		return lstDipendente;
	}
	public JLabel getLblEmailBody() {
		return lblEmailBody;
	}
	public JLabel getLblTelefonoBody() {
		return lblTelefonoBody;
	}
	public JLabel getLblNumImpieghiBody() {
		return lblNumImpieghiBody;
	}
	public JLabel getLblNumValutazioniBody() {
		return lblNumValutazioniBody;
	}
	public JLabel getLblNomeBody() {
		return lblNomeBody;
	}
	public JTable getTableValutazioni() {
		return tableValutazioni;
	}
	public JTable getTableSocieta() {
		return tableSocieta;
	}
	public ValutazioneTableModel getModelValutazione() {
		return modelValutazione;
	}
	public ValutazioneTableModel getModelValutazioniBasse() {
		return modelValutazioniBasse;
	}
}
