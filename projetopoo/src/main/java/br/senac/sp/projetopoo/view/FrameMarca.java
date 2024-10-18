package br.senac.sp.projetopoo.view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 47, 75, 25);
		contentPane.add(lblNome);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 11, 75, 25);
		contentPane.add(lblId);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(45, 13, 75, 23);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setBounds(45, 49, 315, 23);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setMnemonic('e');
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExcluir.setBounds(151, 97, 140, 34);
		contentPane.add(btnExcluir);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setMnemonic('l');
		btnLimpar.setBounds(301, 97, 123, 34);
		contentPane.add(btnLimpar);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBackground(new Color(192, 192, 192));
		lblLogo.setOpaque(true);
		lblLogo.setBounds(370, 11, 54, 61);
		contentPane.add(lblLogo);
		
		tblMarca = new JTable();
		tblMarca.setBounds(10, 142, 414, 108);
		contentPane.add(tblMarca);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtNome.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Informe o nome","Aviso",JOptionPane.INFORMATION_MESSAGE);
					txtNome.requestFocus();
				}
			}
		});
		btnSalvar.setBounds(10, 97, 131, 34);
		contentPane.add(btnSalvar);
	}
}
