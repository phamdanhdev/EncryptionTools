package phamdanh.swing.atbm;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import phamdanh.swing.AES.AES;
import phamdanh.swing.AES.AESwithFile;
import phamdanh.swing.AESAndRSA.AESAndRSA;
import phamdanh.swing.AESAndRSA.AESAndRSAWithFile;
import phamdanh.swing.ARCFOUR.ARCFOUR;
import phamdanh.swing.ARCFOUR.ARCFOURWithFile;
import phamdanh.swing.DES.DES;
import phamdanh.swing.DES.DESWithFile;
import phamdanh.swing.MyAlgorithms.Affine;
import phamdanh.swing.MyAlgorithms.DichChuyen;
import phamdanh.swing.MyAlgorithms.ThayThe;
import phamdanh.swing.RC2.RC2;
import phamdanh.swing.RC2.RC2WithFile;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.SwingConstants;
import javax.swing.DropMode;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTextField;

public class EncryptWithDecrypt extends JFrame {

	private JPanel contentPane;
	private JButton btnDES;
	private JButton btnAES;
	private JButton btnClear;
	private JTextArea textAreaInputText;
	private JTextArea textAreaCipherText;
	private JTextArea textAreaPlainText;
	private JLabel lbThongBao;
	private JComboBox comboBoxLength;
	private JComboBox comboBoxLength2;
	private JButton btnOkEncryptFile;
	private JButton btnOkText;
	private JFileChooser fileChooser;
	private JFileChooser fileChooser2;
	private JFileChooser fileChooser3;
	private JFileChooser fileChooser4;
	private JFileChooser fileChooser5 = new JFileChooser();
	private JFileChooser fileChooser6 = new JFileChooser();
	private JFileChooser fileChooser7 = new JFileChooser();
	private JFileChooser fileChooser8 = new JFileChooser();
	private JFileChooser fileChooser9 = new JFileChooser();
	private String folderPath;
	private String filePath;
	private JTextField textFieldFilePath;
	private JTextField textFieldFolderPath;
	private JComboBox comboBox2;
	private JLabel lbThongBao2;
	private JTextField textFieldSecretKeyPath;
	private JTextField textFieldEncryptPath;
	private JButton btnSelectFileSecretKey;
	private JButton btnSelectFileEncrypt;
	private JPanel panel_2;
	private JPanel panel_5 = new JPanel();
	private JPanel panel_6 = new JPanel();
	private JTextField textFieldPublicKey;
	private JLabel lbPrivateKey;
	private JButton btnPrivateKey;
	private JLabel lbSuccess;
	private JTextField textFieldSign;
	private JPanel panel_8;
	private JTextField textFieldOriginalFile;
	private JTextField textFieldSignedFile;
	private JTextField textFieldPublicKeyVerify;
	private JButton btnOriginalFilePath;
	private JButton btnSignedFilePath;
	private JButton btnPublicKeyVerifyFilePath;
	private JButton btnVerify;
	private JLabel lbVerifyResult;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EncryptWithDecrypt frame = new EncryptWithDecrypt();
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
	public EncryptWithDecrypt() {
		setTitle("M\u00E3 ho\u00E1 & Gi\u1EA3i m\u00E3");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "TEXT", TitledBorder.LEFT,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 24, 380, 526);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Input text:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 188, 68, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Cipher text:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 273, 68, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Plain text:\r\n");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(10, 360, 68, 14);
		panel.add(lblNewLabel_2);

		btnClear = new JButton("CLEAR ALL\r\n");
		btnClear.setBounds(20, 455, 150, 42);
		btnClear.setBackground(Color.WHITE);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaInputText.setText("");
				textAreaCipherText.setText("");
				textAreaPlainText.setText("");
			}
		});
		panel.add(btnClear);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 298, 360, 49);
		panel.add(scrollPane);

		textAreaCipherText = new JTextArea();
		textAreaCipherText.setEditable(false);
		scrollPane.setViewportView(textAreaCipherText);
		textAreaCipherText.setBackground(Color.WHITE);
		textAreaCipherText.setText("  ");

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 213, 360, 49);
		panel.add(scrollPane_1);

		textAreaInputText = new JTextArea();
		scrollPane_1.setViewportView(textAreaInputText);
		textAreaInputText.setBackground(Color.WHITE);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 385, 360, 49);
		panel.add(scrollPane_2);

		textAreaPlainText = new JTextArea();
		scrollPane_2.setViewportView(textAreaPlainText);
		textAreaPlainText.setBackground(Color.WHITE);
		textAreaPlainText.setEditable(false);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 26, 360, 84);
		panel.add(panel_1);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Choose your Encryption and Decryption",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setLayout(null);

		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * COMBOBOX SELECT
		 * 
		 * 
		 */
		comboBoxLength = new JComboBox();
		comboBoxLength.setBackground(Color.WHITE);
		comboBoxLength.setBounds(190, 30, 160, 35);
		panel_1.add(comboBoxLength);

		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(Color.WHITE);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (comboBox.getSelectedItem().equals("AES")) {
					comboBoxLength.removeAllItems();
					comboBoxLength.addItem("128");
					comboBoxLength.addItem("192");
					comboBoxLength.addItem("256");

				}
				if (comboBox.getSelectedItem().equals("DES")) {
					comboBoxLength.removeAllItems();
					comboBoxLength.addItem("56");
				}

				if (comboBox.getSelectedItem().equals("RC2")) {
					comboBoxLength.removeAllItems();
					comboBoxLength.addItem("64");
					comboBoxLength.addItem("128");
					comboBoxLength.addItem("256");
					comboBoxLength.addItem("512");
					comboBoxLength.addItem("1024");
				}

				if (comboBox.getSelectedItem().equals("ARCFOUR")) {
					comboBoxLength.removeAllItems();
					comboBoxLength.addItem("40");
					comboBoxLength.addItem("128");
					comboBoxLength.addItem("256");
					comboBoxLength.addItem("512");
					comboBoxLength.addItem("1024");
				}

				if (comboBox.getSelectedItem().equals("AES&RSA")) {
					comboBoxLength.removeAllItems();
					comboBoxLength.addItem("1024");
					comboBoxLength.addItem("2048");
				}

				if (comboBox.getSelectedItem().equals("AFFINE")) {
					comboBoxLength.removeAllItems();
				}

				if (comboBox.getSelectedItem().equals("Moving")) {
					comboBoxLength.removeAllItems();
				}

				if (comboBox.getSelectedItem().equals("Replace")) {
					comboBoxLength.removeAllItems();
				}

			}
		});
		comboBox.setBounds(10, 30, 160, 35);
		comboBox.addItem("AES"); // 128, 192, 256
		comboBox.addItem("DES"); // 56
		comboBox.addItem("AES&RSA"); // RSA - 1024, 2048
		comboBox.addItem("ARCFOUR"); // 40, 128, 256, 512, 1024
		comboBox.addItem("RC2"); // 40 - 1024
		comboBox.addItem("AFFINE");
		comboBox.addItem("Moving");
		comboBox.addItem("Replace");
		panel_1.add(comboBox);
		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * END COMBOBOX SELECT
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * BUTTON TEXT
		 * 
		 * 
		 */
		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * BUTTON FILE
		 * 
		 * 
		 */
		btnOkText = new JButton("OK");
		btnOkText.setBackground(Color.WHITE);
		btnOkText.setBounds(207, 455, 150, 42);
		panel.add(btnOkText);

		lbThongBao = new JLabel("Hãy chọn kiểu mã hoá bên trên");
		lbThongBao.setBounds(0, 141, 380, 22);
		panel.add(lbThongBao);
		lbThongBao.setForeground(Color.RED);
		lbThongBao.setHorizontalAlignment(SwingConstants.CENTER);
		lbThongBao.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnOkText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// AES
				if (comboBox.getSelectedItem().equals("AES")) {
					int size = Integer.parseInt(comboBoxLength.getSelectedItem().toString());
					lbThongBao.setText(
							"Bạn đã chọn mã hoá " + comboBox.getSelectedItem() + " - Key size: " + size + " bits");
					AES aes = new AES();
					SecretKey secretKey = null;
					try {
						secretKey = aes.createKey(size);
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String inputText = new String(textAreaInputText.getText());
					byte[] cipherBytes = null;
					String cipherBytesText = null;
					try {
						cipherBytes = aes.encrypt(inputText, secretKey);
					} catch (InvalidKeyException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchPaddingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalBlockSizeException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadPaddingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					cipherBytesText = new String(cipherBytes);
					textAreaCipherText.setText(cipherBytesText);
					String plainText = null;
					try {
						plainText = aes.decript(cipherBytes, secretKey);
					} catch (InvalidKeyException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchPaddingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalBlockSizeException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadPaddingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					textAreaPlainText.setText(plainText);
				}

				// DES
				if (comboBox.getSelectedItem().equals("DES")) {
					int size = Integer.parseInt(comboBoxLength.getSelectedItem().toString());
					lbThongBao.setText(
							"Bạn đã chọn mã hoá " + comboBox.getSelectedItem() + " - Key size: " + size + " bits");
					DES des = new DES();
					SecretKey secretKey = null;
					try {
						secretKey = des.createKey(size);
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String inputText = new String(textAreaInputText.getText());
					byte[] cipherBytes = null;
					String cipherBytesText = null;
					try {
						cipherBytes = des.encrypt(inputText, secretKey);
					} catch (InvalidKeyException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchPaddingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalBlockSizeException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadPaddingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					cipherBytesText = new String(cipherBytes);
					textAreaCipherText.setText(cipherBytesText);
					String plainText = null;
					try {
						plainText = des.decript(cipherBytes, secretKey);
					} catch (InvalidKeyException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchPaddingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalBlockSizeException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadPaddingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					textAreaPlainText.setText(plainText);
				}

				// RC2
				if (comboBox.getSelectedItem().equals("RC2")) {
					int size = Integer.parseInt(comboBoxLength.getSelectedItem().toString());
					lbThongBao.setText(
							"Bạn đã chọn mã hoá " + comboBox.getSelectedItem() + " - Key size: " + size + " bits");
					RC2 rc2 = new RC2();
					String inputText = new String(textAreaInputText.getText());
					SecretKey secretKey = null;
					try {
						secretKey = rc2.createKey(size);
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					byte[] cipherBytes = null;
					try {
						cipherBytes = rc2.encrypt(inputText, secretKey);
					} catch (InvalidKeyException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchPaddingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalBlockSizeException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadPaddingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					textAreaCipherText.setText(new String(cipherBytes));
					String plainText = null;
					try {
						plainText = rc2.decript(cipherBytes, secretKey);
					} catch (InvalidKeyException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchPaddingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalBlockSizeException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadPaddingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					textAreaPlainText.setText(plainText);

				}

				// AES&RSA
				if (comboBox.getSelectedItem().equals("AES&RSA")) {
					int size = Integer.parseInt(comboBoxLength.getSelectedItem().toString());
					lbThongBao.setText(
							"Bạn đã chọn mã hoá " + comboBox.getSelectedItem() + " - Key size: " + size + " bits");
					String inputText = new String(textAreaInputText.getText());
					AESAndRSA rsa = new AESAndRSA();
					try {
						rsa.generateKey(size);
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					byte[] cipherText = null;
					try {
						cipherText = rsa.encryptText(inputText);
					} catch (InvalidKeyException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchPaddingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalBlockSizeException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadPaddingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					textAreaCipherText.setText(new String(cipherText));
					String plainText = null;
					try {
						plainText = rsa.decryptText(cipherText);
					} catch (InvalidKeyException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchPaddingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalBlockSizeException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadPaddingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					textAreaPlainText.setText(plainText);

				}

				// ARCFOUR
				if (comboBox.getSelectedItem().equals("ARCFOUR")) {
					int size = Integer.parseInt(comboBoxLength.getSelectedItem().toString());
					lbThongBao.setText(
							"Bạn đã chọn mã hoá " + comboBox.getSelectedItem() + " - Key size: " + size + " bits");
					ARCFOUR arcfour = new ARCFOUR();
					String inputText = new String(textAreaInputText.getText());
					SecretKey secretKey = null;
					try {
						secretKey = arcfour.createKey(size);
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					byte[] cipherBytes = null;
					try {
						cipherBytes = arcfour.encrypt(inputText, secretKey);
					} catch (InvalidKeyException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchPaddingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalBlockSizeException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadPaddingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					textAreaCipherText.setText(new String(cipherBytes));
					String plainText = null;
					try {
						plainText = arcfour.decript(cipherBytes, secretKey);
					} catch (InvalidKeyException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchPaddingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalBlockSizeException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadPaddingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					textAreaPlainText.setText(plainText);

				}

				// AFFINE
				if (comboBox.getSelectedItem().equals("AFFINE")) {
					lbThongBao.setText("Bạn đã chọn mã hoá Affine");
					String inputText = new String(textAreaInputText.getText());
					Affine affine = new Affine();
					String cipherText = affine.encrypt(inputText);
					textAreaCipherText.setText(cipherText);
					String plainText = affine.decrypt(cipherText);
					textAreaPlainText.setText(plainText);
				}

				// MOVING
				if (comboBox.getSelectedItem().equals("Moving")) {
					lbThongBao.setText("Bạn đã chọn mã hoá Moving");
					String inputText = new String(textAreaInputText.getText());
					DichChuyen dichChuyen = new DichChuyen();
//					int key = dichChuyen.giaiKhoa(9);
					int key = 9;
					String cipherText = dichChuyen.dichChuyen(inputText, key);
					textAreaCipherText.setText(cipherText);
					String plainText = dichChuyen.giaiMaDichChuyen(cipherText, key);
					textAreaPlainText.setText(plainText);
				}

				// REPLACE
				if (comboBox.getSelectedItem().equals("Replace")) {
					lbThongBao.setText("Bạn đã chọn mã hoá Replace");
					String inputText = new String(textAreaInputText.getText());
					ThayThe thayThe = new ThayThe();
					String cipherText = thayThe.maHoa(inputText);
					textAreaCipherText.setText(cipherText);
					String plainText = thayThe.giaiMa(cipherText);
					textAreaPlainText.setText(plainText);
				}
			}
		});

		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * WORKING WITH FILE
		 */

		panel_2 = new JPanel();
		panel_1.add(comboBoxLength);

		fileChooser = new JFileChooser();
		fileChooser2 = new JFileChooser();
		fileChooser3 = new JFileChooser();
		fileChooser4 = new JFileChooser();
		panel_2.setBackground(Color.WHITE);
		panel_2.setForeground(Color.BLACK);
		panel_2.setBorder(new TitledBorder(null, "FILE", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(400, 24, 754, 312);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		panel_2.add(fileChooser);
		panel_2.add(fileChooser2);
		/*
		 * 
		 * 
		 * Button Open Folder
		 */

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(new TitledBorder(null, "Choose your Encryption and Decryption", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_4.setBounds(10, 26, 360, 81);
		panel_2.add(panel_4);
		panel_4.setLayout(null);

		comboBoxLength2 = new JComboBox();
		comboBoxLength2.setBounds(190, 29, 160, 35);
		panel_4.add(comboBoxLength2);
		comboBoxLength2.setBackground(Color.WHITE);
		comboBox2 = new JComboBox();
		comboBox2.setBounds(10, 29, 160, 35);
		panel_4.add(comboBox2);
		comboBox2.setBackground(Color.WHITE);
		btnPrivateKey = new JButton("Choose file");
		btnPrivateKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				fileChooser5.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
				int option = fileChooser5.showOpenDialog(new Frame());
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser5.getSelectedFile();
					filePath = createPath(file.getPath());
					textFieldPublicKey.setText(file.getPath());
				}

			}
		});
		btnPrivateKey.setBackground(Color.WHITE);
		textFieldPublicKey = new JTextField();
		lbPrivateKey = new JLabel("Choose Public Key file\r\n");

		comboBox2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (comboBox2.getSelectedItem().equals("AES&RSA")) {
					btnPrivateKey.setVisible(true);
					textFieldPublicKey.setVisible(true);
					lbPrivateKey.setVisible(true);
				} else {
					btnPrivateKey.setVisible(false);
					textFieldPublicKey.setVisible(false);
					lbPrivateKey.setVisible(false);
				}
				if (comboBox2.getSelectedItem().equals("AES")) {
					comboBoxLength2.removeAllItems();
					comboBoxLength2.addItem("128");
					comboBoxLength2.addItem("192");
					comboBoxLength2.addItem("256");

				}
				if (comboBox2.getSelectedItem().equals("DES")) {
					comboBoxLength2.removeAllItems();
					comboBoxLength2.addItem("56");
				}

				if (comboBox2.getSelectedItem().equals("RC2")) {
					comboBoxLength2.removeAllItems();
					comboBoxLength2.addItem("64");
					comboBoxLength2.addItem("128");
					comboBoxLength2.addItem("256");
					comboBoxLength2.addItem("512");
					comboBoxLength2.addItem("1024");
				}

				if (comboBox2.getSelectedItem().equals("ARCFOUR")) {
					comboBoxLength2.removeAllItems();
					comboBoxLength2.addItem("40");
					comboBoxLength2.addItem("128");
					comboBoxLength2.addItem("256");
					comboBoxLength2.addItem("512");
					comboBoxLength2.addItem("1024");
				}

				if (comboBox2.getSelectedItem().equals("AES&RSA")) {
					comboBoxLength2.removeAllItems();
					comboBoxLength2.addItem("1024");
					comboBoxLength2.addItem("2048");
				}

//				if (comboBox.getSelectedItem().equals("AFFINE")) {
//					comboBoxLength2.removeAllItems();
//				}
//
//				if (comboBox.getSelectedItem().equals("Moving")) {
//					comboBoxLength2.removeAllItems();
//				}
//
//				if (comboBox.getSelectedItem().equals("Replace")) {
//					comboBoxLength2.removeAllItems();
//				}

			}
		});

		comboBox2.addItem("AES"); // 128, 192, 256
		comboBox2.addItem("DES"); // 56
		comboBox2.addItem("AES&RSA"); // RSA - 1024, 2048
		comboBox2.addItem("ARCFOUR"); // 40, 128, 256, 512, 1024
		comboBox2.addItem("RC2");

		lbThongBao2 = new JLabel("Hãy chọn kiểu mã hoá bên trên");
		lbThongBao2.setHorizontalAlignment(SwingConstants.CENTER);
		lbThongBao2.setForeground(Color.RED);
		lbThongBao2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbThongBao2.setBounds(0, 107, 370, 23);
		panel_2.add(lbThongBao2);

		panel_5.setBorder(new TitledBorder(null, "Encrypt file", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(10, 131, 360, 173);
		panel_2.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Choose your .txt file to Encrypt");
		lblNewLabel_3.setBounds(10, 21, 252, 14);
		panel_5.add(lblNewLabel_3);

		textFieldFilePath = new JTextField();
		textFieldFilePath.setBounds(20, 46, 199, 23);
		panel_5.add(textFieldFilePath);
		textFieldFilePath.setColumns(10);

		JButton btnSelectFile = new JButton("Choose file");
		btnSelectFile.setBounds(229, 45, 121, 23);
		panel_5.add(btnSelectFile);
		btnSelectFile.setBackground(Color.WHITE);

		JLabel lblNewLabel_4 = new JLabel("Choose your Folder to save Key file(s) and Encrypt file");
		lblNewLabel_4.setBounds(10, 77, 326, 14);
		panel_5.add(lblNewLabel_4);

		textFieldFolderPath = new JTextField();
		textFieldFolderPath.setBounds(20, 102, 199, 22);
		panel_5.add(textFieldFolderPath);
		textFieldFolderPath.setColumns(10);
		JButton btnOpenFolderFileEncrypt = new JButton("Open folder");
		btnOpenFolderFileEncrypt.setBounds(229, 101, 121, 23);
		panel_5.add(btnOpenFolderFileEncrypt);
		btnOpenFolderFileEncrypt.setBackground(Color.WHITE);
		btnOkEncryptFile = new JButton("OK");
		btnOkEncryptFile.setBounds(115, 132, 130, 30);
		panel_5.add(btnOkEncryptFile);
		btnOkEncryptFile.setBackground(Color.WHITE);
		btnOkEncryptFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbSuccess.setText("Thực hiện mã hoá thành công!");
				// AES
				if (comboBox2.getSelectedItem().equals("AES")) {
					int size = Integer.parseInt(comboBoxLength2.getSelectedItem().toString());
					lbThongBao2.setText(
							"Bạn đã chọn mã hoá " + comboBox2.getSelectedItem() + " - Key size: " + size + " bits");
					String folderPath = createPath(textFieldFolderPath.getText());
					String filePath = createPath(textFieldFilePath.getText());
					AESwithFile aesWithFile = new AESwithFile();
					try {
						aesWithFile.encrypt(filePath, folderPath, size);
					} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
							| IllegalBlockSizeException | BadPaddingException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				// DES
				if (comboBox2.getSelectedItem().equals("DES")) {
					int size = Integer.parseInt(comboBoxLength2.getSelectedItem().toString());
					lbThongBao2.setText(
							"Bạn đã chọn mã hoá " + comboBox2.getSelectedItem() + " - Key size: " + size + " bits");
					String folderPath = createPath(textFieldFolderPath.getText());
					String filePath = createPath(textFieldFilePath.getText());
					DESWithFile desWithFile = new DESWithFile();
					try {
						desWithFile.encrypt(filePath, folderPath, size);
					} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
							| IllegalBlockSizeException | BadPaddingException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				// RC2
				if (comboBox2.getSelectedItem().equals("RC2")) {
					int size = Integer.parseInt(comboBoxLength2.getSelectedItem().toString());
					lbThongBao2.setText(
							"Bạn đã chọn mã hoá " + comboBox2.getSelectedItem() + " - Key size: " + size + " bits");
					/*
					 * 
					 */
					String folderPath = createPath(textFieldFolderPath.getText());
					String filePath = createPath(textFieldFilePath.getText());
					RC2WithFile rc2WithFile = new RC2WithFile();
					try {
						rc2WithFile.encrypt(filePath, folderPath, size);
					} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
							| IllegalBlockSizeException | BadPaddingException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					/*
					 * 
					 */
				}

				// ARCFOUR
				if (comboBox2.getSelectedItem().equals("ARCFOUR")) {
					int size = Integer.parseInt(comboBoxLength2.getSelectedItem().toString());
					lbThongBao2.setText(
							"Bạn đã chọn mã hoá " + comboBox2.getSelectedItem() + " - Key size: " + size + " bits");
					String folderPath = createPath(textFieldFolderPath.getText());
					String filePath = createPath(textFieldFilePath.getText());
					ARCFOURWithFile arcfourWithFile = new ARCFOURWithFile();
					try {
						arcfourWithFile.encrypt(filePath, folderPath, size);
					} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
							| IllegalBlockSizeException | BadPaddingException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				// AES&RSA
				if (comboBox2.getSelectedItem().equals("AES&RSA")) {
					int size = Integer.parseInt(comboBoxLength2.getSelectedItem().toString());
					lbThongBao2.setText(
							"Bạn đã chọn mã hoá " + comboBox2.getSelectedItem() + " - Key size: " + size + " bits");
					String folderPath = createPath(textFieldFolderPath.getText());
					String filePath = createPath(textFieldFilePath.getText());
					AESAndRSAWithFile aesAndRSAWithFile = new AESAndRSAWithFile();
					try {
						aesAndRSAWithFile.encrypt(filePath, folderPath, size);
					} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
							| IllegalBlockSizeException | BadPaddingException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		panel_6.setBackground(Color.WHITE);
		panel_6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Decrypt file",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_6.setBounds(380, 26, 360, 278);
		panel_2.add(panel_6);
		panel_6.setLayout(null);

		textFieldSecretKeyPath = new JTextField();
		textFieldSecretKeyPath.setBounds(21, 40, 199, 23);
		panel_6.add(textFieldSecretKeyPath);
		textFieldSecretKeyPath.setColumns(10);

		textFieldEncryptPath = new JTextField();
		textFieldEncryptPath.setColumns(10);
		textFieldEncryptPath.setBounds(21, 95, 199, 22);
		panel_6.add(textFieldEncryptPath);

		JLabel lblNewLabel_5 = new JLabel("Choose your Secret Key ");
		lblNewLabel_5.setBounds(10, 23, 228, 14);
		panel_6.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Choose your Encrypt file");
		lblNewLabel_6.setBounds(10, 80, 228, 14);
		panel_6.add(lblNewLabel_6);

		btnSelectFileSecretKey = new JButton("Choose file");
		btnSelectFileSecretKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser3.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
				int option = fileChooser3.showOpenDialog(new Frame());
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser3.getSelectedFile();
					filePath = createPath(file.getPath());
					textFieldSecretKeyPath.setText(file.getPath());
				}

			}
		});
		btnSelectFileSecretKey.setBackground(Color.WHITE);
		btnSelectFileSecretKey.setBounds(230, 39, 120, 23);
		panel_6.add(btnSelectFileSecretKey);

		btnSelectFileEncrypt = new JButton("Choose file");
		btnSelectFileEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser4.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
				int option = fileChooser4.showOpenDialog(new Frame());
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser4.getSelectedFile();
					filePath = createPath(file.getPath());
					textFieldEncryptPath.setText(file.getPath());
				}

			}
		});
		btnSelectFileEncrypt.setBackground(Color.WHITE);
		btnSelectFileEncrypt.setBounds(230, 94, 120, 23);
		panel_6.add(btnSelectFileEncrypt);

		JButton btnOkDecryptFile = new JButton("OK");
		btnOkDecryptFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lbSuccess.setText("Thực hiện giải mã thành công!");
				// AES
				if (comboBox2.getSelectedItem().equals("AES")) {
					int size = Integer.parseInt(comboBoxLength2.getSelectedItem().toString());
					lbThongBao2.setText("");
					String keyPath = createPath(textFieldSecretKeyPath.getText());
					String filePath = createPath(textFieldEncryptPath.getText());
					String folderPath = getFolderPathOfFile(filePath);
					// Folder here
					AESwithFile AESWithFile = new AESwithFile();
					try {
						AESWithFile.decrypt(keyPath, filePath, folderPath);
						System.out.println(folderPath);
					} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
							| IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				// DES
				if (comboBox2.getSelectedItem().equals("DES")) {
					int size = Integer.parseInt(comboBoxLength2.getSelectedItem().toString());
					lbThongBao2.setText("");
					String keyPath = createPath(textFieldSecretKeyPath.getText());
					String filePath = createPath(textFieldEncryptPath.getText());
					String folderPath = getFolderPathOfFile(filePath);
					// Folder here
					DESWithFile DESWithFile = new DESWithFile();
					try {
						DESWithFile.decrypt(keyPath, filePath, folderPath);
						System.out.println(folderPath);
					} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
							| IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				// RC2
				if (comboBox2.getSelectedItem().equals("RC2")) {
					int size = Integer.parseInt(comboBoxLength2.getSelectedItem().toString());
					lbThongBao2.setText("");
					String keyPath = createPath(textFieldSecretKeyPath.getText());
					String filePath = createPath(textFieldEncryptPath.getText());
					String folderPath = getFolderPathOfFile(filePath);
					// Folder here
					RC2WithFile rc2WithFile = new RC2WithFile();
					try {
						rc2WithFile.decrypt(keyPath, filePath, folderPath);
						System.out.println(folderPath);
					} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
							| IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				// ARCFOUR
				if (comboBox2.getSelectedItem().equals("ARCFOUR")) {
					int size = Integer.parseInt(comboBoxLength2.getSelectedItem().toString());
					lbThongBao2.setText("");
					String keyPath = createPath(textFieldSecretKeyPath.getText());
					String filePath = createPath(textFieldEncryptPath.getText());
					String folderPath = getFolderPathOfFile(filePath);
					// Folder here
					ARCFOURWithFile ARCFOURWithFile = new ARCFOURWithFile();
					try {
						ARCFOURWithFile.decrypt(keyPath, filePath, folderPath);
						System.out.println(folderPath);
					} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
							| IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				// AES&RSA
				if (comboBox2.getSelectedItem().equals("AES&RSA")) {
					int size = Integer.parseInt(comboBoxLength2.getSelectedItem().toString());
					lbThongBao2.setText("");
					String secretKeyPath = createPath(textFieldSecretKeyPath.getText());
					String filePath = createPath(textFieldEncryptPath.getText());
					String folderPath = getFolderPathOfFile(filePath);
					String publicKeyPath = createPath(textFieldPublicKey.getText());
					AESAndRSAWithFile aesAndRSAWithFile = new AESAndRSAWithFile();
					try {
						aesAndRSAWithFile.decrypt(filePath, folderPath, secretKeyPath, publicKeyPath);
					} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
							| IllegalBlockSizeException | BadPaddingException | InvalidKeySpecException
							| IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnOkDecryptFile.setBackground(Color.WHITE);
		btnOkDecryptFile.setBounds(127, 237, 130, 30);
		panel_6.add(btnOkDecryptFile);

		lbSuccess = new JLabel("");
		lbSuccess.setBounds(43, 198, 285, 28);
		panel_6.add(lbSuccess);
		lbSuccess.setHorizontalAlignment(SwingConstants.CENTER);
		lbSuccess.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbSuccess.setForeground(Color.GREEN);

		textFieldPublicKey.setBounds(21, 154, 199, 23);
		panel_6.add(textFieldPublicKey);
		textFieldPublicKey.setColumns(10);

		btnPrivateKey.setBounds(230, 153, 120, 23);
		panel_6.add(btnPrivateKey);

		lbPrivateKey.setBounds(10, 139, 304, 14);
		panel_6.add(lbPrivateKey);
		btnOpenFolderFileEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int option = fileChooser.showOpenDialog(new Frame());
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					folderPath = createPath(file.getPath());
					textFieldFolderPath.setText(file.getPath());
				}
			}
		});
		btnSelectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser2.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
				int option = fileChooser2.showOpenDialog(new Frame());
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser2.getSelectedFile();
					filePath = createPath(file.getPath());
					textFieldFilePath.setText(file.getPath());
				}
			}
		});
		/*
		 * 
		 * End button open folder
		 * 
		 */

		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * Button select file
		 */
		/*
		 * End button select file
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "SIGNATURE", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(400, 362, 754, 188);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		panel_7.setBorder(new TitledBorder(null, "Create signature on file", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.setBounds(10, 21, 278, 156);
		panel_3.add(panel_7);
		panel_7.setLayout(null);
		
		textFieldSign = new JTextField();
		textFieldSign.setBounds(10, 50, 258, 20);
		panel_7.add(textFieldSign);
		textFieldSign.setColumns(10);
		
		JLabel lbSignResult = new JLabel("Choose file to be signed");
		lbSignResult.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbSignResult.setForeground(Color.RED);
		lbSignResult.setHorizontalAlignment(SwingConstants.CENTER);
		lbSignResult.setBounds(10, 19, 258, 20);
		panel_7.add(lbSignResult);
		
		JButton btnSign = new JButton("SIGN");
		btnSign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbSignResult.setText("Tạo chữ kí thành công!");
				String filePath = createPath(textFieldSign.getText());
				String folderPath = getFolderPathOfFile(filePath);
				SignatureFile signatureFile = new SignatureFile();
				signatureFile.createFileSigned(filePath, folderPath);
			}
		});
		btnSign.setBackground(Color.WHITE);
		btnSign.setBounds(53, 122, 174, 23);
		panel_7.add(btnSign);
		
		JButton btnChooseFileSign = new JButton("Choose file");
		btnChooseFileSign.setBackground(Color.WHITE);
		btnChooseFileSign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser6.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
				int option = fileChooser6.showOpenDialog(new Frame());
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser6.getSelectedFile();
					textFieldSign.setText(file.getPath());
				}
				
			}
		});
		btnChooseFileSign.setBounds(85, 75, 111, 23);
		panel_7.add(btnChooseFileSign);
		
		panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(null, "Verify file signed", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBackground(Color.WHITE);
		panel_8.setBounds(298, 21, 446, 156);
		panel_3.add(panel_8);
		panel_8.setLayout(null);
		
		textFieldOriginalFile = new JTextField();
		textFieldOriginalFile.setBounds(10, 38, 218, 23);
		panel_8.add(textFieldOriginalFile);
		textFieldOriginalFile.setColumns(10);
		
		textFieldSignedFile = new JTextField();
		textFieldSignedFile.setBounds(10, 79, 218, 23);
		panel_8.add(textFieldSignedFile);
		textFieldSignedFile.setColumns(10);
		
		textFieldPublicKeyVerify = new JTextField();
		textFieldPublicKeyVerify.setBounds(10, 125, 218, 22);
		panel_8.add(textFieldPublicKeyVerify);
		textFieldPublicKeyVerify.setColumns(10);
		
		btnOriginalFilePath = new JButton("Choose file");
		btnOriginalFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser7.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
				int option = fileChooser7.showOpenDialog(new Frame());
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser7.getSelectedFile();
					textFieldOriginalFile.setText(file.getPath());
				}
			}
		});
		btnOriginalFilePath.setBackground(Color.WHITE);
		btnOriginalFilePath.setBounds(228, 38, 99, 23);
		panel_8.add(btnOriginalFilePath);
		
		btnSignedFilePath = new JButton("Choose file");
		btnSignedFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser8.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
				int option = fileChooser8.showOpenDialog(new Frame());
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser8.getSelectedFile();
					textFieldSignedFile.setText(file.getPath());
				}
			}
		});
		btnSignedFilePath.setBackground(Color.WHITE);
		btnSignedFilePath.setBounds(228, 78, 99, 23);
		panel_8.add(btnSignedFilePath);
		
		btnPublicKeyVerifyFilePath = new JButton("Choose file");
		btnPublicKeyVerifyFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser9.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
				int option = fileChooser9.showOpenDialog(new Frame());
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser9.getSelectedFile();
					textFieldPublicKeyVerify.setText(file.getPath());
				}
			}
		});
		btnPublicKeyVerifyFilePath.setBackground(Color.WHITE);
		btnPublicKeyVerifyFilePath.setBounds(228, 124, 99, 23);
		panel_8.add(btnPublicKeyVerifyFilePath);
		
		btnVerify = new JButton("VERIFY");
		btnVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filePath = createPath(textFieldOriginalFile.getText());
				String signatureFilePath = createPath(textFieldSignedFile.getText());
				String publicKeyFilePath = createPath(textFieldPublicKeyVerify.getText());
				SignatureFile signatureFile = new SignatureFile();
				boolean result = signatureFile.verifySignature(filePath, signatureFilePath, publicKeyFilePath);
				if (result) {
					lbVerifyResult.setText("TRUE");
				} else {
					lbVerifyResult.setText("FALSE");
				}
			}
		});
		btnVerify.setBackground(Color.WHITE);
		btnVerify.setBounds(337, 79, 99, 68);
		panel_8.add(btnVerify);
		
		lbVerifyResult = new JLabel("");
		lbVerifyResult.setForeground(Color.RED);
		lbVerifyResult.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbVerifyResult.setHorizontalAlignment(SwingConstants.CENTER);
		lbVerifyResult.setBounds(337, 23, 99, 38);
		panel_8.add(lbVerifyResult);
		
		lblNewLabel_7 = new JLabel("Choose your Original file");
		lblNewLabel_7.setBounds(10, 23, 218, 14);
		panel_8.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("Choose your signed file");
		lblNewLabel_8.setBounds(10, 64, 218, 14);
		panel_8.add(lblNewLabel_8);
		
		lblNewLabel_9 = new JLabel("Choose your Public Key file");
		lblNewLabel_9.setBounds(10, 106, 218, 14);
		panel_8.add(lblNewLabel_9);
	}

	public String createPath(String str) {
		StringBuffer buffer = new StringBuffer(str);
		int i = 0;
		outer: for (; i < str.length(); i++) {
			if (String.valueOf(buffer.charAt(i)).equals("\\")) {
				buffer.insert(i, '\\');
				i++;
				continue outer;
			}
		}
		return buffer.toString();
	}

	public String getFolderPathOfFile(String str) {
		StringBuffer buffer = new StringBuffer(str);
		int i = str.length() - 1;
		int check = 0;
		for (; i > 0; i--) {
			if (String.valueOf(buffer.charAt(i)).equals("\\")) {
				check = i;
				break;
			}
		}
		return buffer.substring(0, (check - 1));
	}
}
