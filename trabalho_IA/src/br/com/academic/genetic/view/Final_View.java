package br.com.academic.genetic.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.academic.genetic.algorithm.BestResult;
import br.com.academic.genetic.algorithm.fitness.Fitness;
import br.com.academic.genetic.model.Individual;
import br.com.academic.genetic.model.IndividualProduct;

public class Final_View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6261979328255278090L;
	private JPanel contentPane;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Final_View frame = new Final_View(null, 0);
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
	public Final_View(List<Individual> individuals, int generation) {
		this.setTitle("Tela Final Geração: "+generation);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 587);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setRowSelectionAllowed(true);
		table.setBounds(12, 12, 303, 251);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(12, 24, 1226, 488);
		contentPane.add(scroll);
		
		JButton btnIniciar = new JButton("Finalizar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Exit();
			}
		});
		btnIniciar.setBounds(1127, 524, 111, 25);
		contentPane.add(btnIniciar);
		
		JLabel lblIndividuos = new JLabel("Individuos:");
		lblIndividuos.setBounds(12, 8, 83, 15);
		contentPane.add(lblIndividuos);
		
		GenerateTable(table);
		List<Individual> best_Individual = new ArrayList<Individual>();
		best_Individual.add(BestResult.best(individuals, false));
		setIndividuals(best_Individual,table);
		
		JButton btnReiniciar = new JButton("Reiniciar");
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CallMainWindow();
			}
		});
		btnReiniciar.setBounds(1004, 524, 111, 25);
		contentPane.add(btnReiniciar);
	}
	
	public void GenerateTable(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		table.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 9));
		
		String[] columns = {"Geladeira Dako", "Notebook Dell", "Microondas Panasonic", "Notebook Asus", "Iphone 6", "Ventilador Panasonic",
							"Geladeira Brastemp", "Tv 55\'", "Tv 50\'", "Microondas Eletrolux", "Geladeira Consul", "Tv 42\'", "Microondas LG",
							"Notebook Lenovo", "Fitness"}; 
		
		model.setColumnIdentifiers(columns);
		
		model.setRowCount(0);
		
		table.setModel(model);
	}
	
	private void setIndividuals(List<Individual> individuals, JTable table) {
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		DecimalFormat format = new DecimalFormat("#.##");
		Fitness.evaluate((Collection)individuals);
		
		for(Individual individual : individuals) {//Percorre os individuos
			
			ArrayList<String> individualString = new ArrayList<>();
			
			for(IndividualProduct individualProduct : individual.getProducts())//Percorre os produtos de cada individuo colocando seu estado no ArrayList
				individualString.add(""+individualProduct.getState().getState());
			
			individualString.add(""+format.format(individual.getFitnessValue()));
			
			model.addRow(individualString.toArray());//Coloca na tabela o estado dos produtos
		}
	}
	
	private void Exit() {
		System.exit(0);
	}
	
	private void CallMainWindow() {
		Main_View main_View = new Main_View();
		main_View.setVisible(true);
		this.setVisible(false);
	}
}
