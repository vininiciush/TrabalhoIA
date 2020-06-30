package br.com.academic.genetic.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import br.com.academic.genetic.algorithm.fitness.Fitness;
import br.com.academic.genetic.algorithm.mutation.Mutation;
import br.com.academic.genetic.model.Individual;
import br.com.academic.genetic.model.IndividualProduct;
import main.Session;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Mutation_View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5684146745489996118L;
	private JPanel contentPane;
	private List<Individual> individuals;
	private int generation;
	private JTable table;
	private JTable table2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mutation_View frame = new Mutation_View(0,null);
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
	public Mutation_View(int generation, List<Individual> individuals) {
		this.individuals = individuals;
		this.generation = generation;
		this.setTitle("Tela Mutação Geração "+generation);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1255, 621);
		contentPane = new JPanel();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
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
		scroll.setBounds(12, 31, 1226, 233);
		contentPane.add(scroll);
		
		table2 = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		
		JButton btnIniciar = new JButton("Proximo");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CallFitnessWindow();
			}
		});
		btnIniciar.setBounds(1127, 554, 111, 25);
		contentPane.add(btnIniciar);
		
		JScrollPane scroll_1 = new JScrollPane(table2);
		scroll_1.setBounds(12, 303, 1226, 239);
		contentPane.add(scroll_1);
		
		JLabel lblIdividuosAtuais = new JLabel("Idividuos Atuais:");
		lblIdividuosAtuais.setBounds(12, 13, 123, 15);
		contentPane.add(lblIdividuosAtuais);
		
		JLabel lblIndividuosMutados = new JLabel("Individuos Mutados:");
		lblIndividuosMutados.setBounds(12, 276, 150, 15);
		contentPane.add(lblIndividuosMutados);
		
		GenerateTable(table);
		GenerateTable(table2);
		setIndividuals(individuals,table);
		if(generation %Session.getInstance().getMutationFreq() == 0)
			Mutate(individuals,3);
		setIndividuals(individuals, table2);
		TableColor(table, table2);
		
		JButton btnExecutarMutao = new JButton("Executar Mutação");
		btnExecutarMutao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CompareTables(table, table2)) {
					Mutate(individuals,3);
					setIndividuals(individuals, table2);
				}else {
					JOptionPane.showMessageDialog(null, "Uma mutação já ocorreu");
					
				}
			}
		});
		btnExecutarMutao.setBounds(955, 554, 160, 25);
		contentPane.add(btnExecutarMutao);
	}
	
	public void GenerateTable(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		table.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 9));
		
		String[] columns = {"Geladeira Dako", "Notebook Dell", "Microondas Panasonic", "Notebook Asus", "Iphone 6", "Ventilador Panasonic",
							"Geladeira Brastemp", "Tv 55\'", "Tv 50\'", "Microondas Eletrolux", "Geladeira Consul", "Tv 42\'", "Microondas LG",
							"Notebook Lenovo", "Fitness"}; 
		
		model.setColumnIdentifiers(columns);
		
		model.setRowCount(0);
		
		table.getTableHeader().setReorderingAllowed(false);
		
		table.setModel(model);
	}
	
	private void setIndividuals(List<Individual> individuals, JTable table) {
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		DecimalFormat format = new DecimalFormat("#.##");
		Fitness.evaluate((Collection)individuals);
		model.setRowCount(0);
		
		for(Individual individual : individuals) {//Percorre os individuos
			ArrayList<String> individualString = new ArrayList<>();
			
			for(IndividualProduct individualProduct : individual.getProducts())//Percorre os produtos de cada individuo colocando seu estado no ArrayList
				individualString.add(""+individualProduct.getState().getState());
			
			individualString.add(""+format.format(individual.getFitnessValue()));
			
			model.addRow(individualString.toArray());//Coloca na tabela o estado dos produtos
		}
	}
	
	private void Mutate(List<Individual> individuals, int numMutate) {
		Random random = new Random();
		for(int x = 0; x < numMutate; x++) {
			Mutation.mutate(individuals.get(random.nextInt(individuals.size())), random.nextInt(3));
		}
	}
	
	private void CallFitnessWindow() {
		Session session = Session.getInstance();
		if(session.getNumGenerations() != generation) {
			Fitness_View fitness_View = new Fitness_View(generation+1,this.individuals);
			fitness_View.setVisible(true);
		}
		else {
			Final_View final_View = new Final_View(this.individuals, generation);
			final_View.setVisible(true);
		}
		this.dispose();
	}
	
	private void TableColor(JTable table1, JTable table2) {
		
		table2.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				
				Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				
				String valorT1 = (String) table1.getValueAt(row, column);
				String valorT2 = (String) table2.getValueAt(row, column);
				
				if(!valorT1.equals(valorT2) && column != 14) {
					cell.setBackground(Color.RED);
					cell.setForeground(Color.BLACK);
				}
				else {
					cell.setBackground(Color.WHITE);
					cell.setForeground(Color.BLACK);
				}
				return this;
			}
		});
	}
	
	private Boolean CompareTables(JTable table1, JTable table2) {
		if(!(table1.getColumnCount() == table2.getColumnCount()) || !(table1.getRowCount() == table2.getRowCount())) 
			return false;
		
		for(int i = 0; i < table1.getRowCount(); i++) {
			for(int x = 0; x < table1.getColumnCount(); x++) {
				String value1 = (String) table1.getValueAt(i, x);
				String value2 = (String) table2.getValueAt(i, x);
				if(!value1.equals(value2)) {
					return false;
				}
			}
		}	
		return true;	
	}
}
