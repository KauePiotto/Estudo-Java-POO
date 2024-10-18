package br.senac.sp.projetopoo.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.senac.sp.projetopoo.dao.ConectionFactory;
import br.senac.sp.projetopoo.modelo.Marca;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JTable;

public class FrameMarca extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtNome;
	private JTable tblMarca;
	private Marca marca;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameMarca frame = new FrameMarca();
					frame.setVisible(true);
					ConectionFactory.getConexao();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameMarca() {
		setTitle("Cadastro Marca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Label Nome
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 47, 75, 25);
		contentPane.add(lblNome);

		// Label ID
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 11, 75, 25);
		contentPane.add(lblId);

		// Text Field ID
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(45, 11, 58, 25);
		contentPane.add(txtId);
		txtId.setColumns(10);

		// Text Field Nome
		txtNome = new JTextField();
		txtNome.setBounds(45, 49, 294, 23);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		// Label da Logo
		JLabel lblLogo = new JLabel("");
		lblLogo.setBackground(new Color(192, 192, 192));
		lblLogo.setOpaque(true);
		lblLogo.setBounds(349, 11, 75, 75);
		contentPane.add(lblLogo);

		// Tabela das Marcas
		tblMarca = new JTable();
		tblMarca.setBounds(10, 142, 414, 108);
		contentPane.add(tblMarca);

		// Botao Excluir
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnExcluir.setBounds(151, 97, 140, 34);
		contentPane.add(btnExcluir);

		// Botao Salvar
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNome.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(FrameMarca.this, "Informe o nome", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
					txtNome.requestFocus();
				} else {
					marca = new Marca();
					marca.setNome(txtNome.getText().trim());
				}
			}
		});
		btnSalvar.setBounds(10, 97, 131, 34);
		contentPane.add(btnSalvar);

		// Botao Limpar
		JButton btnLimpa = new JButton("Limpar");
		btnLimpa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnLimpa.setBounds(301, 97, 123, 34);
		contentPane.add(btnLimpa);
	}
}
