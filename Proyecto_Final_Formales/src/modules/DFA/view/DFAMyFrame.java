package modules.DFA.view;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import modules.DFA.controller.DFAController;
import modules.DFA.model.DFAEstado;

public class DFAMyFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private GridBagConstraints gbc;
	private JComboBox<String> comboestadoInicial;
	private JComboBox<String> comboestadoFinal;
	private int withBorderPanels = 650;
	private JButton agregarAlfabeto;
	private JButton compararLenguaje;
	private JButton agregarEstado;
	private JButton agregarFuncion;
	private JTextField textAlfabeto;
	private JTextField textEstado;
	private CheckboxGroup cbg;
	private Checkbox inicial;
	private Checkbox estadofinal;
	private Checkbox nada;
	private JTextField textFuncion;
	private JTextField textCompararLenguaje;
	private DFAPanelGrafo grafo;
	private JPanel derechaJPanel;

	private int x;
	private int y;
	
	public DFAMyFrame(DFAController control, ArrayList<DFAEstado> arrayList) {
		super("Automata: ");
		x = 75;
		y = 25;
		gbc = new GridBagConstraints();
		setLayout(new GridLayout(1, 2));
		setExtendedState(MAXIMIZED_BOTH);
		
		grafo = new DFAPanelGrafo(arrayList);
		
		derechaJPanel = new JPanel();
		derechaJPanel.setLayout(new GridBagLayout());
		panelTipoAutomata();
		
		panelEstado(control);

		panelFuncion(control);

		panelComparar(control);

		add(derechaJPanel);
		add(grafo);
		setMinimumSize(new Dimension(1080, 720));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void panelTipoAutomata() {
		JPanel seleccionarTipo = new JPanel();
		seleccionarTipo.setBorder(new LineBorder(Color.BLACK));
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.insets = new Insets(10, 10, 10, 10);
		derechaJPanel.add(seleccionarTipo, gbc);
	}

	private void panelComparar(DFAController control) {
		JPanel panelcomparar = new JPanel();
		panelcomparar.setPreferredSize(new Dimension(withBorderPanels, 150));
		panelcomparar.setBorder(new LineBorder(Color.BLACK));

		panelcomparar.setLayout(new GridBagLayout());
		gbc.gridwidth = GridBagConstraints.RELATIVE;

		JLabel lblCompararLenguaje = new JLabel("Validar palabra: ");
		lblCompararLenguaje.setFont(new Font("Arial", Font.PLAIN, 20));
		panelcomparar.add(lblCompararLenguaje, gbc);

		textCompararLenguaje = new JTextField();
		textCompararLenguaje.setPreferredSize(new Dimension(300, 30));
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		panelcomparar.add(textCompararLenguaje, gbc);

		compararLenguaje = new JButton("Validar");
		compararLenguaje.setPreferredSize(new Dimension(150, 30));
		compararLenguaje.addActionListener(control);
		compararLenguaje.setActionCommand("COMPARAR");
		panelcomparar.add(compararLenguaje, gbc);

		gbc.insets = new Insets(10, 50, 10, 50);
		derechaJPanel.add(panelcomparar, gbc);
	}

	private void panelFuncion(DFAController control) {
		JPanel panelfuncion = new JPanel();
		panelfuncion.setPreferredSize(new Dimension(withBorderPanels, 300));
		panelfuncion.setBorder(new LineBorder(Color.BLACK));

		panelfuncion.setLayout(new GridBagLayout());
		gbc.gridwidth = GridBagConstraints.RELATIVE;

		JLabel lblfuncion = new JLabel("Funcion: ");
		lblfuncion.setFont(new Font("Arial", Font.PLAIN, 20));
		panelfuncion.add(lblfuncion, gbc);

		textFuncion = new JTextField();
		textFuncion.setPreferredSize(new Dimension(300, 30));
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		panelfuncion.add(textFuncion, gbc);

		JLabel lblincial = new JLabel("Estado Inical: ");
		lblfuncion.setFont(new Font("Arial", Font.PLAIN, 20));
		panelfuncion.add(lblincial, gbc);

		comboestadoInicial = new JComboBox<String>();
		comboestadoInicial.setBounds(10, 10, 80, 20);
		panelfuncion.add(comboestadoInicial, gbc);

		JLabel lblfinal = new JLabel("Estado Final: ");
		lblfuncion.setFont(new Font("Arial", Font.PLAIN, 20));
		panelfuncion.add(lblfinal, gbc);

		comboestadoFinal = new JComboBox<String>();
		comboestadoFinal.setBounds(10, 10, 80, 20);
		panelfuncion.add(comboestadoFinal, gbc);

		agregarFuncion = new JButton("Agregar Funcion");
		agregarFuncion.setPreferredSize(new Dimension(150, 30));
		agregarFuncion.addActionListener(control);
		agregarFuncion.setActionCommand("ADD_FUNCION");
		panelfuncion.add(agregarFuncion, gbc);

		gbc.insets = new Insets(10, 50, 10, 50);
		derechaJPanel.add(panelfuncion, gbc);
	}

	private void panelEstado(DFAController control) {
		JPanel panelEstado = new JPanel();
		panelEstado.setPreferredSize(new Dimension(withBorderPanels, 250));
		panelEstado.setBorder(new LineBorder(Color.BLACK));

		panelEstado.setLayout(new GridBagLayout());
		gbc.gridwidth = GridBagConstraints.RELATIVE;

		JLabel lblestado = new JLabel("Estado: ");
		lblestado.setFont(new Font("Arial", Font.PLAIN, 20));
		panelEstado.add(lblestado, gbc);

		textEstado = new JTextField();
		textEstado.setPreferredSize(new Dimension(300, 30));
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		panelEstado.add(textEstado, gbc);

		cbg = new CheckboxGroup();
		inicial = new Checkbox("Estado Inicial", cbg, false);
		estadofinal = new Checkbox("Estado Final", cbg, false);
		nada = new Checkbox("No es inicial ni final", cbg, true);
		panelEstado.add(inicial, gbc);
		panelEstado.add(estadofinal, gbc);
		panelEstado.add(nada, gbc);

		agregarEstado = new JButton("Agregar Estado");
		agregarEstado.setPreferredSize(new Dimension(150, 30));
		agregarEstado.addActionListener(control);
		agregarEstado.setActionCommand("ADD_ESTADO");
		panelEstado.add(agregarEstado, gbc);

		gbc.insets = new Insets(10, 50, 10, 50);
		derechaJPanel.add(panelEstado, gbc);
	}


	public void disableAlfabetoAgregado(boolean disable) {
		agregarAlfabeto.setEnabled(disable);
	}

	public void disablePanelComparar(boolean disable) {
		compararLenguaje.setEnabled(disable);
	}

	public void disablePaneEstado(boolean disable) {
		agregarEstado.setEnabled(disable);
	}

	public void disablePanelfuncion(boolean disable) {
		agregarFuncion.setEnabled(disable);
	}

	public String getAlfabeto() {
		return textAlfabeto.getText();
	}

	public void fillEstadosInicialesCheck(ArrayList<DFAEstado> arrayList) {
		comboestadoInicial.removeAllItems();
		comboestadoFinal.removeAllItems();

		for (DFAEstado estadiIni : arrayList) {
			comboestadoInicial.addItem(estadiIni.getNombre());
			comboestadoFinal.addItem(estadiIni.getNombre());
		}
	}

	public DFAEstado getEstado() {
		if (textEstado.getText().isEmpty()) {
			this.showDialog("Texto del estado vacio, no se puede agregar");
			return null;
		}

		if (cbg.getSelectedCheckbox().equals(inicial)) {
			x = x+100;
			return new DFAEstado(textEstado.getText(), true, false, x, y);
		} else if (cbg.getSelectedCheckbox().equals(estadofinal)) {
			x = x+100;
			return new DFAEstado(textEstado.getText(), false, true, x, y);
		} else {
			x = x+100;
			return new DFAEstado(textEstado.getText(), false, false, x, y);
		}
		
	}
	
	public String getTextFuncion() {
		if(textFuncion.getText().isEmpty()) {
			this.showDialog("No se puede agregar una funcion vacia");
		} else if (textFuncion.getText().length() > 1) {
			this.showDialog("No se puede agregar mas de un caracter");
			return "";
		}
		return textFuncion.getText();
	}
	
	
	public String getEstadoInitFuncion() {
		if(comboestadoInicial.getSelectedItem() == null) {
			this.showDialog("No se encuentran estados seleccionados, revise su selecci�n");
			return null;
		}
		return comboestadoInicial.getSelectedItem().toString();
	}
	
	public String getEstadoFinishFuncion() {
		if(comboestadoFinal.getSelectedItem() == null) {
			this.showDialog("No se encuentran estados seleccionados, revise su selecci�n");
			return null;
		}
		return comboestadoFinal.getSelectedItem().toString();
	}
	
	public String getComprar() {
		return textCompararLenguaje.getText();
	}
	
	public void updateGrafo(ArrayList<DFAEstado> estados) {
		grafo.update(estados);
	}
	
	public void showDialog(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
}
