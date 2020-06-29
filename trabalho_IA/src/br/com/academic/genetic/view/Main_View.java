package br.com.academic.genetic.view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatter;

import br.com.academic.genetic.algorithm.generator.IndividualGenerator;
import br.com.academic.genetic.algorithm.generator.PopulationGenerator;
import br.com.academic.genetic.model.Individual;
import br.com.academic.genetic.model.IndividualProduct;
import br.com.academic.genetic.model.ProductStatus;
import br.com.academic.genetic.service.ProductsFlyWeight;
import main.Session;

import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Main_View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -32660419657504055L;
	private JPanel contentPane;
	private JTable table;
	private static final Integer INDIVIDUOS = 6;
	private JSpinner spinner;
	private JSpinner spinner_1;
	private JSpinner spinner_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_View frame = new Main_View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main_View() {
		this.setTitle("Seleção de individuos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 549);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setRowSelectionAllowed(true);
		table.setBounds(12, 12, 303, 251);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(12, 8, 1097, 504);
		contentPane.add(scroll);
		
		JButton btnIndividuosAleatorios = new JButton("Random");
		btnIndividuosAleatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GerarAleatorios();
			}
		});
		btnIndividuosAleatorios.setBounds(1125, 8, 107, 25);
		contentPane.add(btnIndividuosAleatorios);
		
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CallFitnessWindow();
			}
		});
		btnIniciar.setBounds(1125, 45, 107, 25);
		contentPane.add(btnIniciar);
		
		JLabel lblNIndividuos = new JLabel("Nº Individuos:");
		lblNIndividuos.setBounds(1121, 119, 111, 15);
		contentPane.add(lblNIndividuos);
		
		SpinnerModel sm = new SpinnerNumberModel((int)INDIVIDUOS, 4, 20, 1); //default value,lower bound,upper bound,increment by
		spinner = new JSpinner(sm);
		JComponent comp = spinner.getEditor();
	    JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
	    DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
	    formatter.setCommitsOnValidEdit(true);
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount((int) spinner.getValue());
			}
		});
		spinner.setBounds(1121, 138, 111, 20);
		contentPane.add(spinner);
		
		JLabel lblNGeraes = new JLabel("Nº Gerações:");
		lblNGeraes.setBounds(1121, 170, 111, 15);
		contentPane.add(lblNGeraes);
		
		SpinnerModel snm = new SpinnerNumberModel(10, 1, 100, 1); //default value,lower bound,upper bound,increment by
		spinner_1 = new JSpinner(snm);
		spinner_1.setBounds(1121, 187, 111, 20);
		contentPane.add(spinner_1);
		
		JLabel lblMutaesFrequencai = new JLabel("Freq Mutações:");
		lblMutaesFrequencai.setBounds(1121, 221, 111, 15);
		contentPane.add(lblMutaesFrequencai);
		
		SpinnerModel snn = new SpinnerNumberModel(1, 1, 100, 1); //default value,lower bound,upper bound,increment by
		spinner_2 = new JSpinner(snn);
		spinner_2.setBounds(1121, 238, 111, 20);
		contentPane.add(spinner_2);
		
		JButton btnVerIndividuos = new JButton("Individuos");
		btnVerIndividuos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AllIndividuos_View allIndividuos = new AllIndividuos_View();
				allIndividuos.setVisible(true);
			}
		});
		btnVerIndividuos.setBounds(1125, 82, 107, 25);
		contentPane.add(btnVerIndividuos);
		
		GenerateTable();
	}
	
	public void GenerateTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		table.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 9));
		
		String[] columns = {"Geladeira Dako", "Notebook Dell", "Microondas Panasonic", "Notebook Asus", "Iphone 6", "Ventilador Panasonic",
							"Geladeira Brastemp", "Tv 55\'", "Tv 50\'", "Microondas Eletrolux", "Geladeira Consul", "Tv 42\'", "Microondas LG",
							"Notebook Lenovo"}; 
		
		model.setColumnIdentifiers(columns);
		
		model.setRowCount(INDIVIDUOS);
		
		table.setModel(model);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		for(int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setMinWidth(79);
			table.getColumnModel().getColumn(i).setMaxWidth(79);
		}
	}
	
	public void GerarAleatorios() {
		List<Individual> list = PopulationGenerator.generate((int)spinner.getValue(), new IndividualGenerator());//Busca lista de individuos gerados aleatoriamente
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);//Limpa tabela atual
		
		for(Individual individual : list) {//Percorre os individuos
			
			ArrayList<Byte> individualString = new ArrayList<>();
			
			for(IndividualProduct individualProduct : individual.getProducts())//Percorre os produtos de cada individuo colocando seu estado no ArrayList
				individualString.add(individualProduct.getState().getState());
			
			model.addRow(individualString.toArray());//Coloca na tabela o estado dos produtos
		
		}
		table.setModel(model);
	}
	
	private void CallFitnessWindow() {
		Session session = Session.getInstance();
		session.setNumGenerations((int)spinner_1.getValue());
		session.setMutationFreq((int) spinner_2.getValue());
		List<Individual> individuals = getIndividualList();
		if(individuals != null) {
			Fitness_View fitness_view = new Fitness_View(1,individuals);
			fitness_view.setVisible(true);
			this.setVisible(false);
		}
	}
	
	private List<Individual> getIndividualList(){
		List<Individual> individuals = new ArrayList<Individual>();
		
		for(int x = 0; x < table.getRowCount(); x++) {
			List<IndividualProduct> individualProducts = new ArrayList<IndividualProduct>();
			for(int i = 0; i < table.getColumnCount(); i++) {
				try {
					Byte productStatus = (Byte) table.getValueAt(x, i);
					IndividualProduct individualProduct = new IndividualProduct(ProductsFlyWeight.getProduct(i+1),ProductStatus.fromInt(productStatus));
					individualProducts.add(individualProduct);
				}catch(Exception e) {
					int xi = x+1;
					int ii = i+1;
					JOptionPane.showMessageDialog(null, "Não foi possivel ler a celula "+xi+":"+ii+" corretamente verifique a tabela.");
					return null;
				}
			}
			Individual individual = new Individual(individualProducts);
			individuals.add(individual);
		}
		
		return individuals;
	}
}
