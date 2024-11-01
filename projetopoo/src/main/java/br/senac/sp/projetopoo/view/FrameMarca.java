package br.senac.sp.projetopoo.view;

import java.awt.EventQueue;
import java.awt.ScrollPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.senac.sp.projetopoo.dao.ConectionFactory;
import br.senac.sp.projetopoo.dao.MarcaDao;
import br.senac.sp.projetopoo.modelo.Marca;
import br.senac.sp.projetopoo.tablemodel.MarcaTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileFilter;
import java.util.List;
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
	private MarcaDao dao;
	private JFileChooser chooser;
	private FileFilter iamgeFilter;
	private File selecionado;
	private List<Marca> marcas;
	private MarcaTableModel tableModel;
	
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

	public FrameMarca() {
		dao = new MarcaDao(ConectionFactory.getConexao());
		
		try {
			marcas = dao.listar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(FrameMarca.this, "Erro"+e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		tableModel = new MarcaTableModel(marcas);
		
		chooser = new JFileChooser();
		//iamgeFilter = new FileNameExtensionFilter("Imagens", ImageIO.getReaderFileSuffixes());
		
		setTitle("Cadastro Marca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 360);
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
		tblMarca = new JTable(tableModel);
		tblMarca.setBounds(10, 142, 414, 168);
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
	
	private void limpar() {
		tfid.setText("");
		tfNome.setText("");
		marca = null;
		tfNome.resquestFocus();
	}
}