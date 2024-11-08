package controller;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import connectionfactory.connectionfactory;

import javax.swing.JButton;

public class batalha extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					batalha frame = new batalha();
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
	public batalha() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(192, 192, 192), 6));
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 451, 984, 210);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 6));
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(640, 0, 344, 210);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(49, 36, 89, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(216, 36, 89, 23);
		panel_1.add(btnNewButton_1);
		
		
		try {
			Connection conexao = connectionfactory.createConnection();
	        //String sql1 = "SELECT id_inimigo, nome, vida, dano FROM inimigo;";
	        String sql1 = "SELECT nome, vida, arma_id, pocao_id FROM personagem;";
	        String sql2 = "SELECT nome, danoa FROM arma WHERE id_arma = ?;";
	        String sql3 = "SELECT nome, descricao, quantidade, efeito FROM pocao WHERE id_pocao = ?;";
	        
	        
	        //variáveis de personagem
	        PreparedStatement personagem = conexao.prepareStatement(sql1);
	        ResultSet resultado_personagem = personagem.executeQuery();
	        
	        String personagem_nome = resultado_personagem.getString("nome");
	        int personagem_vida = resultado_personagem.getInt("vida");
	        int personagem_arma = resultado_personagem.getInt("arma_id");
	        int personagem_pocao = resultado_personagem.getInt("pocao_id");
	        
	        //variáveis de dano
	        PreparedStatement arma = conexao.prepareStatement(sql2);
	        arma.setInt(1, personagem_arma);
	        ResultSet resultado_arma = arma.executeQuery();
	        
	        String arma_nome = resultado_arma.getString("nome");
	        int arma_dano = resultado_arma.getInt("danoa");
	        
	        
	        //variáveis de poção
	        PreparedStatement pocao = conexao.prepareStatement(sql3);
	        pocao.setInt(1, personagem_pocao);
	        ResultSet resultado_pocao = pocao.executeQuery();
	        
	        String pocao_nome = resultado_pocao.getString("nome");
	        String pocao_descricao = resultado_pocao.getString("descricao");
	        int pocao_quantidade = resultado_pocao.getInt("quantidade");
	        int pocao_efeito = resultado_pocao.getInt("efeito");
	        
	        int inimigo_id = 0;
	        
	        while(personagem_vida > 0 ) {
	        	inimigo_id = inimigo_id + 1;
	        	
	        	String sql4 = "SELECT nome, vida, dano FROM inimigo WHERE id_inimigo = ?;";
	        	PreparedStatement inimigo = conexao.prepareStatement(sql4);
	        	inimigo.setInt(1, inimigo_id);
	        	ResultSet resultado_inimigo = inimigo.executeQuery();
	        	
	        	String inimigo_nome = resultado_inimigo.getString("nome");
	        	int inimigo_vida = resultado_inimigo.getInt("vida");
	        	int inimigo_dano = resultado_inimigo.getInt("dano");
	        	
	        	while(inimigo_vida > 0 ) {
	        		JOptionPane.showMessageDialog(null, "deu");
	        	}
			}
        
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
			
		
	}
}
