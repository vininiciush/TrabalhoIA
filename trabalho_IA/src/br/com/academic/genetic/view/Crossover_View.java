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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import br.com.academic.genetic.algorithm.BestResult;
import br.com.academic.genetic.algorithm.crossover.Crossover;
import br.com.academic.genetic.algorithm.fitness.Fitness;
import br.com.academic.genetic.algorithm.selection.Roulette;
import br.com.academic.genetic.algorithm.selection.Selection;
import br.com.academic.genetic.model.Individual;
import br.com.academic.genetic.model.IndividualProduct;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Component;

public class Crossover_View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6140884740674602115L;
	private JPanel contentPane;
	private JTable table;
	private JTable table2;
	private JTable table3;
	private Integer generation;
	private static List<Individual> individuals;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Crossover_View frame = new Crossover_View(0, null);
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
	public Crossover_View(int generation, List<Individual> individuals) {
		this.individuals = new ArrayList<Individual>();
		this.generation = generation;
		this.setTitle("Tela Crossover Geração "+generation);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 613);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		table = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table2 = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table3 = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setRowSelectionAllowed(true);
		table.setBounds(12, 12, 303, 251);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(12, 29, 1226, 152);
		contentPane.add(scroll);
		
		JButton btnIniciar = new JButton("Proximo");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CallMutatuionWindow();
			}
		});
		btnIniciar.setBounds(1127, 557, 111, 25);
		contentPane.add(btnIniciar);
		
		JLabel lblPopulaoOriginal = new JLabel("População Original:");
		lblPopulaoOriginal.setBounds(12, 13, 143, 15);
		contentPane.add(lblPopulaoOriginal);
		
		JLabel lblIndividuosSelecionadosPara = new JLabel("Individuos Selecionados para o Crossover:");
		lblIndividuosSelecionadosPara.setBounds(12, 193, 320, 15);
		contentPane.add(lblIndividuosSelecionadosPara);
		
		JScrollPane scroll_1 = new JScrollPane(table2);
		scroll_1.setBounds(12, 209, 1226, 152);
		contentPane.add(scroll_1);
		
		JLabel lblIndividuosGeradosPelo = new JLabel("Individuos gerados pelo Crossover:");
		lblIndividuosGeradosPelo.setBounds(12, 371, 320, 15);
		contentPane.add(lblIndividuosGeradosPelo);
		
		JScrollPane scroll_1_1 = new JScrollPane(table3);
		scroll_1_1.setBounds(12, 393, 1226, 152);
		contentPane.add(scroll_1_1);
		//Gerando cabeçario das tables
		GenerateTable(table);
		GenerateTable(table2);
		GenerateTable(table3);
		//Preenchendo primeira tabela
		setIndividuals(individuals, table);
		//Selecionando individuos para crossover
		List<Individual> select = (List<Individual>) Selection.selectHalf(individuals, new Roulette<Individual>());
		//Preenchendo individuos selecionados para crossover
		setIndividuals(select, table2);
		//Gerando lista com individuos cruzados
		List<Individual> individuals_cross = Crossover(select);
		//Preenchendo terceira tabela com inviduos gerado pelo crossover
		setIndividuals(individuals_cross, table3);
		//Adicionando individuos que vão para a proxima geração
		List<Individual> selecionados_prox_passo = new ArrayList<Individual>();
		selecionados_prox_passo.addAll(individuals);
		selecionados_prox_passo.addAll(individuals_cross);
		//Selecionando melhores
		this.individuals = (List<Individual>) BestResult.best(selecionados_prox_passo, individuals.size(), false);
		MarcaSelected(table, table3, this.individuals);
		TableColor(table, table3);
	}
	
	public void GenerateTable(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		table.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 9));
		
		String[] columns = {"Geladeira Dako", "Notebook Dell", "Microondas Panasonic", "Notebook Asus", "Iphone 6", "Ventilador Panasonic",
							"Geladeira Brastemp", "Tv 55\'", "Tv 50\'", "Microondas Eletrolux", "Geladeira Consul", "Tv 42\'", "Microondas LG",
							"Notebook Lenovo", "Fitness", "Selected"}; 
		
		model.setColumnIdentifiers(columns);
		
		model.setRowCount(0);
		
		table.setModel(model);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		for(int i = 0; i < table.getColumnCount()-2; i++) {
			table.getColumnModel().getColumn(i).setMaxWidth(82);
			table.getColumnModel().getColumn(i).setMinWidth(82);
		}
		table.getColumnModel().getColumn(14).setMinWidth(75);
		table.getColumnModel().getColumn(14).setMaxWidth(75);
		table.getColumnModel().getColumn(15).setMinWidth(0);
		table.getColumnModel().getColumn(15).setMaxWidth(0);
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
	
	private List<Individual> Crossover(List<Individual> individuals) {
		List<Individual> individualsReturn = new ArrayList<Individual>();
		for(int x = 0; x < individuals.size()-1; x++) {
			for(int i = x+1; i < individuals.size(); i++) {
				Individual son = Crossover.cross(individuals.get(x).getClone(), individuals.get(i).getClone());
				individualsReturn.add(son);
			}
		}
		return individualsReturn;
	}
	
	private void CallMutatuionWindow() {
		Mutation_View mutation_View = new Mutation_View(generation, this.individuals);
		mutation_View.setVisible(true);
		this.setVisible(false);
	}
	
	private void TableColor(JTable table1, JTable table2) {
		
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				
				Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				
				String ok = (String) table.getValueAt(row, 15);
				
				if(ok.equals("1")) {
					cell.setBackground(Color.GREEN);
					cell.setForeground(Color.BLACK);
				}else {
					cell.setBackground(Color.WHITE);
					cell.setForeground(Color.BLACK);
				}
				return this;
			}
		});
		
		table2.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				
				Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				
				String ok = (String) table3.getValueAt(row, 15);
				
				if(ok.equals("1")) {
					cell.setBackground(Color.GREEN);
					cell.setForeground(Color.BLACK);
				}else {
					cell.setBackground(Color.WHITE);
					cell.setForeground(Color.BLACK);
				}
				return this;
			}
		});
	}
	
	private void MarcaSelected(JTable table, JTable table2, List<Individual> individuals) {
		DecimalFormat format = new DecimalFormat("#.##");
		ArrayList<Double> values = new ArrayList<Double>();
		for(Individual individual: individuals) 
			values.add(Double.parseDouble(format.format(individual.getFitnessValue()).replaceAll(",", ".")));
	
		for(int x = 0; x < table.getRowCount(); x++) {
			Double valor = Double.parseDouble(((String)table.getValueAt(x, 14)).replaceAll(",", "."));
			if(values.contains(valor)) {
				table.setValueAt("1", x, 15);
				values.remove(values.indexOf(valor));
			}else {
				table.setValueAt("0", x, 15);
			}
		}
		
		for(int x = 0; x < table2.getRowCount(); x++) {
			Double valor = Double.parseDouble(((String)table2.getValueAt(x, 14)).replaceAll(",", "."));
			if(values.contains(valor)) {
				table2.setValueAt("1", x, 15);
				values.remove(values.indexOf(valor));
			}else {
				table2.setValueAt("0", x, 15);
			}
		}
	}
}
