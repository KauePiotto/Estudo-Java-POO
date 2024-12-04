package br.senac.sp.projetopoo.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import br.senac.sp.projetopoo.dao.ConnectionFactory;
import br.senac.sp.projetopoo.dao.EMFactory;
import br.senac.sp.projetopoo.dao.InterfaceDao;
import br.senac.sp.projetopoo.dao.MarcaDAO;
import br.senac.sp.projetopoo.dao.MarcaDaoHib;
import br.senac.sp.projetopoo.modelo.Marca;
import br.senac.sp.projetopoo.tablemodel.MarcaTableModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import br.senac.sp.projetopoo.view.FrameInicio;

public class FrameMarca extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfNome;
	private Marca marca;
	private InterfaceDao<Marca> dao;
	private JFileChooser chooser;
	private FileFilter imageFilter;
	private JLabel lbLogo;
	private File selecionado;
	private JTable tbMarcas;
	private List<Marca> marcas;
	private MarcaTableModel tableModel;
	private JButton btnSalvar;

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
		// dao = new MarcaDAO(ConnectionFactory.getConexao());
		dao = new MarcaDaoHib(EMFactory.getEntityManager());

		try {
			marcas = dao.listar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(FrameMarca.this, "Erro: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		tableModel = new MarcaTableModel(marcas);

		chooser = new JFileChooser();
		imageFilter = new FileNameExtensionFilter("Imagens", ImageIO.getReaderFileSuffixes());

		setTitle("Cadastro de Marcas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 502);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(51, 11, 46, 19);
		contentPane.add(lblNewLabel);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNome.setBounds(10, 45, 46, 17);
		contentPane.add(lblNome);

		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfId.setBounds(73, 11, 51, 20);
		contentPane.add(tfId);
		tfId.setColumns(10);

		tfNome = new JTextField();
		tfNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfNome.setColumns(10);
		tfNome.setBounds(66, 44, 267, 20);
		contentPane.add(tfNome);

		lbLogo = new JLabel("");
		lbLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					chooser.setFileFilter(imageFilter);
					if (chooser.showOpenDialog(FrameMarca.this) == JFileChooser.APPROVE_OPTION) {
						selecionado = chooser.getSelectedFile();
						if (selecionado != null && selecionado.exists() && selecionado.isFile()) {
							try {
								BufferedImage bufImg = ImageIO.read(selecionado);
								if (bufImg != null) {
									Image imagem = bufImg.getScaledInstance(lbLogo.getWidth(), lbLogo.getHeight(),
											Image.SCALE_SMOOTH);
									lbLogo.setIcon(new ImageIcon(imagem));
								} else {
									JOptionPane.showMessageDialog(FrameMarca.this,
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
		lbLogo.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				lbLogo.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				lbLogo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		lbLogo.setBackground(Color.LIGHT_GRAY);
		lbLogo.setBounds(343, 11, 98, 90);
		lbLogo.setOpaque(true);
		contentPane.add(lbLogo);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfNome.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(FrameMarca.this, "Informe o nome", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
					tfNome.requestFocus();
				} else {
					if (marca == null) {
						marca = new Marca();
					}
					marca.setNome(tfNome.getText().trim());
					try {
						if (selecionado != null) {
							byte[] imagemBytes = Files.readAllBytes(selecionado.toPath());
							marca.setLogo(imagemBytes);
						}
						if (marca.getId() == 0) {
							dao.inserir(marca);
						} else {
							dao.alterar(marca);
						}
						marcas = dao.listar();
						tableModel.setLista(marcas);
						tableModel.fireTableDataChanged();
						limpar(); // Limpa o formulário e a imagem
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(FrameMarca.this, e1.getMessage(), "Erro",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			}
		});
		btnSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				btnSalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				btnSalvar.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		btnSalvar.setMnemonic('s');
		btnSalvar.setBounds(10, 72, 96, 29);
		contentPane.add(btnSalvar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (marca != null) {
					if (JOptionPane.showConfirmDialog(FrameMarca.this,
							"Deseja excluir a marca " + marca.getNome()) == JOptionPane.YES_OPTION) {
						try {
							dao.excluir(marca.getId());
							marcas = dao.listar();
							tableModel.setLista(marcas);
							tableModel.fireTableDataChanged();
							limpar();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(FrameMarca.this, "Selecione uma marca para excluí-la");
				}
			}
		});
		btnExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				btnExcluir.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				btnExcluir.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		btnExcluir.setMnemonic('e');
		btnExcluir.setBounds(116, 72, 101, 29);
		contentPane.add(btnExcluir);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				btnLimpar.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				btnLimpar.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		btnLimpar.setMnemonic('l');
		btnLimpar.setBounds(227, 72, 101, 29);
		contentPane.add(btnLimpar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 112, 431, 339);
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
		contentPane.add(scrollPane);

		tbMarcas = new JTable(tableModel);
		tbMarcas.setToolTipText("Selecione um item para alterar ou excluir");
		tbMarcas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbMarcas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int linha = tbMarcas.getSelectedRow();
				if (linha >= 0) {
					marca = marcas.get(linha);
					tfId.setText("" + marca.getId());
					tfNome.setText(marca.getNome());
				}
			}
		});

		scrollPane.setViewportView(tbMarcas);

		JLabel lblVoltar = new JLabel("");
		lblVoltar.setBounds(10, 11, 31, 29);

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
	}

	private void limpar() {
		tfId.setText("");
		tfNome.setText("");
		marca = null;
		tfNome.requestFocus();
		lbLogo.setText("");
	}
}