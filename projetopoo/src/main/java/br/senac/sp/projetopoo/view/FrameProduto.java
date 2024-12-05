package br.senac.sp.projetopoo.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.NumberFormatter;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import br.senac.sp.projetopoo.dao.EMFactory;
import br.senac.sp.projetopoo.dao.InterfaceDao;
import br.senac.sp.projetopoo.dao.MarcaDaoHib;
import br.senac.sp.projetopoo.modelo.Marca;
import br.senac.sp.projetopoo.modelo.Produto;
import br.senac.sp.projetopoo.tablemodel.ProdutoTableModel;
import javax.swing.JComboBox;
import br.senac.sp.projetopoo.dao.ProdutoDaoHib;
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
	private InterfaceDao<Produto> ProdDao;
	private List<Produto> produtos;
	private Produto produto;
	private ProdutoTableModel tableModel;
	private JPanel contentPane;
	private JTextField txtIdProd;
	private JTextField txtNomeProd;
	private JFormattedTextField txtPrecoProd;
	private JComboBox<Marca> cmbMarcaProd;
	private JTextArea textArea;
	private JTable table;
	private JTable tabProd;
	private JLabel lblLogo;
	private JLabel lblVoltar;
	private ImageIcon icon;
	private Image img;
	private JLabel lblNomeProd;
	private JLabel lblIdProd;
	private JLabel lblMarcaProd;
	private JLabel lblPrecoProd;
	private NumberFormat currencyFormat;
	private NumberFormatter currencyFormatter;
	private JLabel lblDescricaoProd;
	private JScrollPane scrollPaneDescricao;
	private JButton btnSalvarProd;
	private String nome;
	private String descricao;
	private double preco;
	private Marca marca;
	private JButton btnExcluirProd;
	private JButton btnLimparProd;
	private JLabel lblBuscarProd;
	private JButton btnBuscarPros;
	private JScrollPane scrollPane;
	private int linha;
	private ByteArrayInputStream byt;
	private BufferedImage bufImg;
	private Image imagem;
	private InterfaceDao<Marca> marcaDao;
	private List<Marca> marcas;
	private JTextField txtBuscarProd;
	private String nomeBusca;
	private List<Produto> produtosEncontrados;

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
		ProdDao = new ProdutoDaoHib(EMFactory.getEntityManager());

		try {
			produtos = ProdDao.listar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(FrameProduto.this, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}

		tableModel = new ProdutoTableModel(produtos);

		setResizable(false);
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 603);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		lblVoltar = new JLabel("");
		lblVoltar.setBounds(15, 16, 31, 29);

		icon = new ImageIcon("Imagens/botao-voltar.png");
		img = icon.getImage().getScaledInstance(lblVoltar.getWidth(), lblVoltar.getHeight(), Image.SCALE_SMOOTH);
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

		lblNomeProd = new JLabel("Nome:");
		lblNomeProd.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNomeProd.setBounds(15, 56, 56, 20);
		contentPane.add(lblNomeProd);

		lblIdProd = new JLabel("ID:");
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

		lblLogo = new JLabel("");
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

		lblMarcaProd = new JLabel("Marca:");
		lblMarcaProd.setFont(new Font("Arial", Font.PLAIN, 13));
		lblMarcaProd.setBounds(15, 87, 43, 22);
		contentPane.add(lblMarcaProd);

		cmbMarcaProd = new JComboBox();
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
		carregarMarcas();
		contentPane.add(cmbMarcaProd);

		lblPrecoProd = new JLabel("Preço:");
		lblPrecoProd.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPrecoProd.setBounds(182, 87, 46, 22);
		contentPane.add(lblPrecoProd);

		currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		currencyFormat.setMaximumFractionDigits(2);
		currencyFormat.setMinimumFractionDigits(2);

		currencyFormatter = new NumberFormatter(currencyFormat);
		currencyFormatter.setAllowsInvalid(false);
		currencyFormatter.setValueClass(Double.class);

		txtPrecoProd = new JFormattedTextField(currencyFormatter);
		txtPrecoProd.setBounds(225, 87, 116, 20);
		((JFormattedTextField) txtPrecoProd).setValue(0.00);
		contentPane.add(txtPrecoProd);

		lblDescricaoProd = new JLabel("Descrição:");
		lblDescricaoProd.setFont(new Font("Arial", Font.PLAIN, 13));
		lblDescricaoProd.setBounds(15, 120, 66, 14);
		contentPane.add(lblDescricaoProd);

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		scrollPaneDescricao = new JScrollPane(textArea);
		scrollPaneDescricao.setBounds(77, 115, 263, 81);
		contentPane.add(scrollPaneDescricao);

		btnSalvarProd = new JButton("Salvar");
		btnSalvarProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nome = txtNomeProd.getText().trim();
				descricao = textArea.getText();
				preco = ((Number) ((JFormattedTextField) txtPrecoProd).getValue()).doubleValue();
				marca = (Marca) cmbMarcaProd.getSelectedItem();

				if (nome.isEmpty() || descricao.isEmpty() || marca == null || preco <= 0) {
					JOptionPane.showMessageDialog(FrameProduto.this, "Preencha todos os campos corretamente!", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (produto == null) {
						produto = new Produto();
					}
					produto.setNome(nome);
					produto.setDescricao(descricao);
					produto.setPreco(preco);
					produto.setMarca(marca);

					try {
						if (selecionado != null) {
							byte[] imagemBytes = Files.readAllBytes(selecionado.toPath());
							produto.setLogo(imagemBytes);
						}

						if (produto.getId() == 0) {
							ProdDao.inserir(produto);
						} else {
							ProdDao.alterar(produto);
						}

						produtos = ProdDao.listar();
						tableModel.setLista(produtos);
						tableModel.fireTableDataChanged();

						JOptionPane.showMessageDialog(FrameProduto.this, "Produto salvo com sucesso!", "Sucesso",
								JOptionPane.INFORMATION_MESSAGE);

						limparCampos();

						produto = null;
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(FrameProduto.this, e1.getMessage(), "Erro",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
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
		btnSalvarProd.setFont(new Font("Arial", Font.PLAIN, 13));
		btnSalvarProd.setBounds(15, 247, 127, 34);
		contentPane.add(btnSalvarProd);

		btnExcluirProd = new JButton("Excluir");
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
		btnExcluirProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idStr = txtIdProd.getText();

				if (idStr.isEmpty()) {
					JOptionPane.showMessageDialog(FrameProduto.this, "Selecione um produto para excluir.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						int id = Integer.parseInt(idStr);
						ProdDao.excluir(id);
						produtos = ProdDao.listar();
						tableModel.setLista(produtos);
						JOptionPane.showMessageDialog(FrameProduto.this, "Produto excluído com sucesso!", "Sucesso",
								JOptionPane.INFORMATION_MESSAGE);
						limparCampos();
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(FrameProduto.this, "ID inválido.", "Erro",
								JOptionPane.ERROR_MESSAGE);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(FrameProduto.this, "Erro ao excluir produto: " + ex.getMessage(),
								"Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnExcluirProd.setBounds(153, 247, 145, 34);
		contentPane.add(btnExcluirProd);

		btnLimparProd = new JButton("Limpar");
		btnLimparProd.setFont(new Font("Arial", Font.PLAIN, 13));
		btnLimparProd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLimparProd.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnLimparProd.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		btnLimparProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnLimparProd.setBounds(308, 247, 122, 34);
		contentPane.add(btnLimparProd);

		lblBuscarProd = new JLabel("Buscar:");
		lblBuscarProd.setFont(new Font("Arial", Font.PLAIN, 13));
		lblBuscarProd.setBounds(15, 212, 66, 20);
		contentPane.add(lblBuscarProd);

		txtBuscarProd = new JTextField();
		txtBuscarProd.setBounds(66, 212, 276, 20);
		contentPane.add(txtBuscarProd);
		txtBuscarProd.setColumns(10);

		btnBuscarPros = new JButton("Buscar");
		btnBuscarPros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nomeBusca = txtBuscarProd.getText();

				if (nomeBusca.isEmpty()) {
					JOptionPane.showMessageDialog(FrameProduto.this, "Digite o nome do produto para buscar.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						produtosEncontrados = ProdDao.buscar(nomeBusca);
						if (!produtosEncontrados.isEmpty()) {
							tableModel.setLista(produtosEncontrados);
							tabProd.clearSelection();
						} else {
							JOptionPane.showMessageDialog(FrameProduto.this, "Nenhum produto encontrado.", "Informação",
									JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(FrameProduto.this, "Erro ao buscar produto: " + ex.getMessage(),
								"Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
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

		scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 292, 411, 261);
		contentPane.add(scrollPane);

		tabProd = new JTable(tableModel);
		tabProd.setToolTipText("Selecione um item para alterar ou excluir");
		tabProd.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabProd.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				linha = tabProd.getSelectedRow();
				if (linha >= 0) {
					produto = produtos.get(linha);
					txtIdProd.setText(String.valueOf(produto.getId()));
					txtNomeProd.setText(produto.getNome());
					cmbMarcaProd.setSelectedItem(produto.getMarca());
					((JFormattedTextField) txtPrecoProd).setValue(produto.getPreco());
					textArea.setText(produto.getDescricao());

					if (produto.getLogo() != null) {
						try {
							byt = new ByteArrayInputStream(produto.getLogo());
							bufImg = ImageIO.read(byt);
							imagem = bufImg.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(),
									Image.SCALE_SMOOTH);
							lblLogo.setIcon(new ImageIcon(imagem));
						} catch (IOException ex) {
							ex.printStackTrace();
							lblLogo.setIcon(null);
							lblLogo.setText("Erro na imagem");
						}
					} else {
						lblLogo.setIcon(null);
					}
				}
			}
		});
		tabProd.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				scrollPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				scrollPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		scrollPane.setViewportView(tabProd);
		contentPane.revalidate();
		contentPane.repaint();
	}

	private void carregarMarcas() {
		try {
			marcaDao = new MarcaDaoHib(EMFactory.getEntityManager());
			marcas = marcaDao.listar();
			cmbMarcaProd.removeAllItems();
			for (Marca marca : marcas) {
				cmbMarcaProd.addItem(marca);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao carregar marcas: " + e.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limparCampos() {
		txtIdProd.setText(""); // Limpa o campo de ID
		txtNomeProd.setText(""); // Limpa o campo de Nome
		txtPrecoProd.setValue(0.00); // Limpa o campo de Preço
		textArea.setText(""); // Limpa o campo de descrição
		cmbMarcaProd.setSelectedIndex(-1); // Limpa o campo de Marca
		lblLogo.setIcon(null); // Remove a imagem atual
		txtBuscarProd.setText(""); // Limpa o campo buscar
		selecionado = null;
		produto = null;

		try {
			produtos = ProdDao.listar();
			tableModel.setLista(produtos);
			tableModel.fireTableDataChanged();
			table.setModel(tableModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}