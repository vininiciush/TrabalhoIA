package br.com.academic.genetic.view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import br.com.academic.genetic.algorithm.generator.IndividualGenerator;
import br.com.academic.genetic.algorithm.generator.PopulationGenerator;
import br.com.academic.genetic.model.Individual;
import br.com.academic.genetic.model.IndividualProduct;
import br.com.academic.genetic.model.ProductStatus;
import br.com.academic.genetic.service.ProductsFlyWeight;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class Main_View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -32660419657504055L;
	private JPanel contentPane;
	private JTable table;
	private static final Integer INDIVIDUOS = 5;

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
		setBounds(100, 100, 1500, 549);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setBounds(12, 12, 303, 251);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(12, 8, 1353, 504);
		contentPane.add(scroll);
		
		JButton btnIndividuosAleatorios = new JButton("Random");
		btnIndividuosAleatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GerarAleatorios();
			}
		});
		btnIndividuosAleatorios.setBounds(1377, 8, 111, 25);
		contentPane.add(btnIndividuosAleatorios);
		
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CallFitnessWindow();
			}
		});
		btnIniciar.setBounds(1377, 45, 111, 25);
		contentPane.add(btnIniciar);
		
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
	}
	
	public void GerarAleatorios() {
		List<Individual> list = PopulationGenerator.generate(INDIVIDUOS, new IndividualGenerator());//Busca lista de individuos gerados aleatoriamente
		
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
		List<Individual> individuals = getIndividualList();
		if(individuals != null) {
			Fitness_View fitness_view = new Fitness_View(individuals);
			fitness_view.setVisible(true);
			this.setVisible(false);
		}
	}
	
	private List<Individual> getIndividualList(){
		List<Individual> individuals = new ArrayList<Individual>();
		
		for(int x = 0; x < table.getRowCount(); x++) {
			List<IndividualProduct> individualProducts = new ArrayList<IndividualProduct>();
			for(int i = 0; i < table.getColumnCount(); i++) {
				Byte productStatus = (Byte) table.getValueAt(x, i);
				if(productStatus == null) {
					JOptionPane.showMessageDialog(null, "Não foi possivel ler os individuos corretamente verifique a tabela.");
					return null;
				}
				IndividualProduct individualProduct = new IndividualProduct(ProductsFlyWeight.getProduct(i+1),ProductStatus.fromInt(productStatus));
				individualProducts.add(individualProduct);
			}
			Individual individual = new Individual(individualProducts);
			individuals.add(individual);
		}
		
		return individuals;
	}
}
