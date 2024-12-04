package br.senac.sp.projetopoo.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameInicio extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameInicio frame = new FrameInicio();
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
	public FrameInicio() {
		setType(Type.POPUP);
		setResizable(false);
		setTitle("Tela Inicial");
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 296, 186);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(27, 27, 27));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnCadastroMarca = new JButton("Cadastro Marca");
		btnCadastroMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameMarca marca = new FrameMarca();
				marca.setVisible(true);
				dispose();
			}
		});
		btnCadastroMarca.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				btnCadastroMarca.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				btnCadastroMarca.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		btnCadastroMarca.setForeground(new Color(255, 255, 255));
		btnCadastroMarca.setBackground(new Color(128, 128, 128));
		btnCadastroMarca.setBounds(56, 11, 178, 47);
		contentPane.add(btnCadastroMarca);

		JButton btnCadastroProduto = new JButton("Cadastro Produto");
		btnCadastroProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameProduto produto = new FrameProduto();
				produto.setVisible(true);
				dispose();
			}
		});
		btnCadastroProduto.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				btnCadastroProduto.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				btnCadastroProduto.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		btnCadastroProduto.setForeground(new Color(255, 255, 255));
		btnCadastroProduto.setBackground(new Color(128, 128, 128));
		btnCadastroProduto.setBounds(53, 82, 181, 47);
		contentPane.add(btnCadastroProduto);
	}
}