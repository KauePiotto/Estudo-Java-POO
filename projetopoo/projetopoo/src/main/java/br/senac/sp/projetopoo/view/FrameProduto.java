package br.senac.sp.projetopoo.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import br.senac.sp.projetopoo.dao.EMFactory;
import br.senac.sp.projetopoo.dao.InterfaceDao;
import br.senac.sp.projetopoo.dao.MarcaDaoHib;
import br.senac.sp.projetopoo.modelo.Marca;
import br.senac.sp.projetopoo.modelo.Produto;
import br.senac.sp.projetopoo.tablemodel.MarcaTableModel;
import br.senac.sp.projetopoo.tablemodel.ProdutoTableModel;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FrameProduto extends JFrame {

	private static final long serialVersionUID = 1L;
	private File selecionado;
	private JFileChooser chooser;
	private FileFilter imageFilter;
	private JPanel contentPane;
	private JTextField txtIdProd;
	private JTextField txtNomeProd;
	private JTextField txtPrecoProd;
	private JTextField txtBuscarProd;
	private JTable table;
	private InterfaceDao<Produto> dao;
	private Produto produto;
	private ProdutoTableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameProduto frame = new FrameProduto();
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
	public FrameProduto() {
		setResizable(false);
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 603);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		JLabel lblVoltar = new JLabel("");
		lblVoltar.setBounds(15, 16, 31, 29);

		ImageIcon icon = new ImageIcon("Imagens/botao-voltar.png");
		Image img = icon.getImage().getScaledInstance(lblVoltar.getWidth(), lblVoltar.getHeight(), Image.SCALE_SMOOTH);
		lblVoltar.setIcon(new ImageIcon(img));
		lblVoltar.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				lblVoltar.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {

				FrameInicio inicio = new FrameInicio();
				inicio.setVisible(true);
				dispose();
			}

		});
		contentPane.setLayout(null);
		contentPane.add(lblVoltar);

		JLabel lblNomeProd = new JLabel("Nome:");
		lblNomeProd.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNomeProd.setBounds(15, 56, 56, 20);
		contentPane.add(lblNomeProd);

		JLabel lblIdProd = new JLabel("ID:");
		lblIdProd.setFont(new Font("Arial", Font.PLAIN, 13));
		lblIdProd.setBounds(56, 25, 31, 20);
		contentPane.add(lblIdProd);

		txtIdProd = new JTextField();
		txtIdProd.setBounds(77, 25, 86, 20);
		txtIdProd.setEditable(false);
		contentPane.add(txtIdProd);
		txtIdProd.setColumns(10);

		txtNomeProd = new JTextField();
		txtNomeProd.setBounds(56, 56, 285, 20);
		contentPane.add(txtNomeProd);
		txtNomeProd.setColumns(10);

		chooser = new JFileChooser();
		imageFilter = new FileNameExtensionFilter("Imagens", ImageIO.getReaderFileSuffixes());

		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(345, 16, 85, 93);
		lblLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					chooser.setFileFilter(imageFilter);
					if (chooser.showOpenDialog(FrameProduto.this) == JFileChooser.APPROVE_OPTION) {
						selecionado = chooser.getSelectedFile();
						if (selecionado != null && selecionado.exists() && selecionado.isFile()) {
							try {
								BufferedImage bufImg = ImageIO.read(selecionado);
								if (bufImg != null) {
									Image imagem = bufImg.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(),
											Image.SCALE_SMOOTH);
									lblLogo.setIcon(new ImageIcon(imagem));
								} else {
									JOptionPane.showMessageDialog(FrameProduto.this,
											"Arquivo selecionado não é uma imagem válida.", "Erro",
											JOptionPane.ERROR_MESSAGE);
								}
							} catch (IOException ex) {
								ex.printStackTrace();
							}
						}
					}
				}
			}
		});
		lblLogo.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblLogo.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				lblLogo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lblLogo.setBackground(Color.LIGHT_GRAY);
		lblLogo.setOpaque(true);
		contentPane.add(lblLogo);

		JLabel lblMarcaProd = new JLabel("Marca:");
		lblMarcaProd.setFont(new Font("Arial", Font.PLAIN, 13));
		lblMarcaProd.setBounds(15, 87, 43, 22);
		contentPane.add(lblMarcaProd);

		JComboBox cmbMarcaProd = new JComboBox();
		cmbMarcaProd.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				cmbMarcaProd.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				cmbMarcaProd.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		cmbMarcaProd.setBounds(56, 87, 122, 22);
		contentPane.add(cmbMarcaProd);

		JLabel lblPrecoProd = new JLabel("Preço:");
		lblPrecoProd.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPrecoProd.setBounds(182, 87, 46, 22);
		contentPane.add(lblPrecoProd);

		txtPrecoProd = new JTextField();
		txtPrecoProd.setBounds(225, 87, 116, 20);
		contentPane.add(txtPrecoProd);
		txtPrecoProd.setColumns(10);

		JLabel lblDescricaoProd = new JLabel("Descrição:");
		lblDescricaoProd.setFont(new Font("Arial", Font.PLAIN, 13));
		lblDescricaoProd.setBounds(15, 120, 66, 14);
		contentPane.add(lblDescricaoProd);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(77, 115, 263, 81);
		contentPane.add(textArea);

		JButton btnSalvarProd = new JButton("Salvar");
		btnSalvarProd.setFont(new Font("Arial", Font.PLAIN, 13));
		btnSalvarProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSalvarProd.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				btnSalvarProd.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				btnSalvarProd.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		btnSalvarProd.setBounds(15, 247, 127, 34);
		contentPane.add(btnSalvarProd);

		JButton btnExcluirProd = new JButton("Excluir");
		btnExcluirProd.setFont(new Font("Arial", Font.PLAIN, 13));
		btnExcluirProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExcluirProd.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				btnExcluirProd.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				btnExcluirProd.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		btnExcluirProd.setBounds(153, 247, 145, 34);
		contentPane.add(btnExcluirProd);

		JButton btnLimparProd = new JButton("Limpar");
		btnLimparProd.setFont(new Font("Arial", Font.PLAIN, 13));
		btnLimparProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLimparProd.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				btnLimparProd.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				btnLimparProd.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		btnLimparProd.setBounds(308, 247, 122, 34);
		contentPane.add(btnLimparProd);

		JLabel lblBuscarProd = new JLabel("Buscar:");
		lblBuscarProd.setFont(new Font("Arial", Font.PLAIN, 13));
		lblBuscarProd.setBounds(15, 212, 66, 20);
		contentPane.add(lblBuscarProd);

		txtBuscarProd = new JTextField();
		txtBuscarProd.setBounds(66, 212, 276, 20);
		contentPane.add(txtBuscarProd);
		txtBuscarProd.setColumns(10);

		JButton btnBuscarPros = new JButton("Buscar");
		btnBuscarPros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBuscarPros.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				btnBuscarPros.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				btnBuscarPros.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		btnBuscarPros.setBounds(351, 212, 79, 23);
		contentPane.add(btnBuscarPros);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				scrollPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				scrollPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		scrollPane.setBounds(15, 292, 411, 261);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
	}
}