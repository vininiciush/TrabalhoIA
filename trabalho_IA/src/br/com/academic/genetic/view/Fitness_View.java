package br.com.academic.genetic.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.academic.genetic.algorithm.fitness.Fitness;
import br.com.academic.genetic.model.Individual;
import br.com.academic.genetic.model.IndividualProduct;

public class Fitness_View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6396594185887227610L;
	private JPanel contentPane;
	private JTable table;
	private static List<Individual> individuals;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fitness_View frame = new Fitness_View(null);
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
	public Fitness_View(List<Individual> individuals) {
		this.individuals = individuals;
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
		
		JButton btnIniciar = new JButton("Proximo");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnIniciar.setBounds(1377, 8, 111, 25);
		contentPane.add(btnIniciar);
		
		GenerateTable();
		setIndividuals(individuals);
	}
	
	public void GenerateTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		table.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 9));
		
		String[] columns = {"Geladeira Dako", "Notebook Dell", "Microondas Panasonic", "Notebook Asus", "Iphone 6", "Ventilador Panasonic",
							"Geladeira Brastemp", "Tv 55\'", "Tv 50\'", "Microondas Eletrolux", "Geladeira Consul", "Tv 42\'", "Microondas LG",
							"Notebook Lenovo", "Fitness"}; 
		
		model.setColumnIdentifiers(columns);
		
		model.setRowCount(0);
		
		table.setModel(model);
	}
	
	private void setIndividuals(List<Individual> individuals) {
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		Fitness.evaluate((Collection)individuals);
		
		for(Individual individual : individuals) {//Percorre os individuos
			
			ArrayList<String> individualString = new ArrayList<>();
			
			for(IndividualProduct individualProduct : individual.getProducts())//Percorre os produtos de cada individuo colocando seu estado no ArrayList
				individualString.add(""+individualProduct.getState().getState());
			
			individualString.add(""+individual.getFitnessValue());
			
			model.addRow(individualString.toArray());//Coloca na tabela o estado dos produtos
		}
	}

}
