package br.senac.sp.projetopoo.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import br.senac.sp.projetopoo.dao.ConectionFactory;
import br.senac.sp.projetopoo.dao.MarcaDao;
import br.senac.sp.projetopoo.modelo.Marca;
import br.senac.sp.projetopoo.tablemodel.MarcaTableModel;

public class FrameMarca extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtNome;
	private JTable tblMarca;
	private JLabel lblLogo;
	private Marca marca;
	private MarcaDao dao;
	private JFileChooser chooser;
	private FileFilter imageFilter;
	private File selecionado;
	private List<Marca> marcas;
	private MarcaTableModel tableModel;

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

	public FrameMarca() {
		dao = new MarcaDao(ConectionFactory.getConexao());

		try {
			marcas = dao.listar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(FrameMarca.this, "Erro" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		tableModel = new MarcaTableModel(marcas);

		chooser = new JFileChooser();
		imageFilter = new FileNameExtensionFilter("Imagens", ImageIO.getReaderFileSuffixes());

		setTitle("Cadastro Marca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Label Nome
		JLabel lblNome = new JLabel("Nome:");
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
		lblLogo = new JLabel("");
		lblLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					chooser.setFileFilter(imageFilter);
					if (chooser.showOpenDialog(FrameMarca.this) == JFileChooser.APPROVE_OPTION) {
						selecionado = chooser.getSelectedFile();
						try {
							BufferedImage bufImg = ImageIO.read(selecionado);
							Image imagem = bufImg.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(),
									Image.SCALE_SMOOTH);
							ImageIcon imgLabel = new ImageIcon(imagem);
							lblLogo.setIcon(imgLabel);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		lblLogo.setBackground(new Color(192, 192, 192));
		lblLogo.setBounds(349, 11, 75, 75);
		lblLogo.setOpaque(true);
		contentPane.add(lblLogo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 142, 414, 262);
		contentPane.add(scrollPane);

		// Tabela das Marcas
		tblMarca = new JTable(tableModel);
		tblMarca.setToolTipText("Selecione um item para alterar ou excluir");
		tblMarca.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblMarca.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int linha = tblMarca.getSelectedRow();
				if (linha >= 0) {
					marca = marcas.get(linha);
					txtId.setText("" + marca.getId());
					txtNome.setText("" + marca.getNome());
				}
			}
		});
		scrollPane.setViewportView(tblMarca);

       
	
		// Botao Limpar
		JButton btnLimpa = new JButton("Limpar");
		btnLimpa.setMnemonic('l');
		btnLimpa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpa.setBounds(301, 97, 123, 34);
		contentPane.add(btnLimpa);
		
		// Botao Salvar
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNome.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(FrameMarca.this, "Informe o nome", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
					txtNome.requestFocus();
				} else {
					if (marca == null) {
						marca = new Marca();
					}
					marca.setNome(txtNome.getText().trim());
					try {
						if (selecionado != null) {
							byte[] imagemBytes = Files.readAllBytes(selecionado.toPath());
							marca.setLogo(imagemBytes);
						}
						if (marca.getId() == 0) {
							dao.inserir(marca);
						} else {
							dao.Alterar(marca);
						}
						marcas = dao.listar();
						tableModel.setLista(marcas);
						tableModel.fireTableDataChanged();
						limpar();
					} catch (SQLException | IOException e1) {
						JOptionPane.showMessageDialog(FrameMarca.this, e1.getMessage(), "ERRO:",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			}
		});
		btnSalvar.setMnemonic('s');
		btnSalvar.setBounds(10, 97, 131, 34);
		contentPane.add(btnSalvar);
		
		// Botao Excluir
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(marcas != null) {
					if(JOptionPane.showConfirmDialog(FrameMarca.this, "Deseja excluir a marca"+ marca.getNome()) == JOptionPane.YES_NO_OPTION){
						try {
							dao.Excluir(marca.getId());
							marcas = dao.listar();
							tableModel.setLista(marcas);
							tableModel.fireTableDataChanged();
							limpar();
						}catch(SQLException e2) {
							e2.printStackTrace();
						}
					}
				}else {
					JOptionPane.showMessageDialog(FrameMarca.this, "Selecione uma marca para excluir-la");
				}
			}
		});
		btnExcluir.setBounds(151, 97, 140, 34);
		contentPane.add(btnExcluir);
	}

	private void limpar() {
		txtId.setText("");
		txtNome.setText("");
		marca = null;
		txtNome.requestFocus();
	}
}