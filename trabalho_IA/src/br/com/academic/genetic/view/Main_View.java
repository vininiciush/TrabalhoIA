package br.com.academic.genetic.view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.sun.org.apache.bcel.internal.generic.IDIV;

import br.com.academic.genetic.model.Individual;
import br.com.academic.genetic.model.IndividualProduct;
import br.com.academic.genetic.service.generator.IndividualGenerator;
import br.com.academic.genetic.service.generator.PopulationGenerator;

import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

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
}
