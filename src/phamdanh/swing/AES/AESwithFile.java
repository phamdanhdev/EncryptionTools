package phamdanh.swing.AES;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESwithFile {


	private SecretKey secretKey = null;


	public void createKey(int size, String folderPath) throws NoSuchAlgorithmException, IOException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(size);
		SecretKey key = keyGenerator.generateKey();
		this.secretKey = key;
		/*
		 * 
		 * Key to file
		 * 
		 */
		File file = new File(folderPath + "\\SecretKeyAES-" + size + "bits.txt");
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (Exception e) {
			System.out.println("KHONG THE TAO DUOC FILE");
			e.printStackTrace();
		}
		byte[] byteKey = key.getEncoded();
		FileOutputStream outputStream = new FileOutputStream(file);
		outputStream.write(byteKey);
		/*
		 * 
		 * 
		 * 
		 */

	}

	public void encrypt(String filePath, String folderPath, int keySize) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
		createKey(keySize, folderPath);
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			// Write file here
			File file = new File(folderPath + "\\EncryptAES.txt");
			try {
				if (!file.exists()) {
					file.createNewFile();
				}
			} catch (Exception e) {
				System.out.println("KHONG THE TAO DUOC FILE");
				e.printStackTrace();
			}
//			FileWriter fileWriter = new FileWriter(file, false);
//			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			// Read file tu filePath
			FileInputStream fileInputStream = new FileInputStream(filePath);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
			byte[] buffer = new byte[1024];
			int len;
			while ((len = bufferedInputStream.read(buffer)) != -1) {
				byte[] cipherText = cipher.doFinal(buffer, 0, len);
				// Ham ghi de file o day
				try {
					fileOutputStream.write(cipherText);
				} catch (Exception e) {
					System.out.println("LOI TRONG QUA TRINH GHI FILE");
					e.printStackTrace();
				}
			}
			// Dong I/O
			fileInputStream.close();
			bufferedInputStream.close();
			fileOutputStream.close();

		} catch (Exception e) {
			System.out.println("ENCRYPT FILE BI LOI");
			e.printStackTrace();
		}
	}

	public void decrypt(String keyPath, String filePath, String folderPath)
			throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException,
			InvalidKeyException, IOException {
		byte[] byteKey = Files.readAllBytes(Paths.get(keyPath));
		SecretKey key = new SecretKeySpec(byteKey, 0, byteKey.length, "AES");
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);

			// Write file here
			File file = new File(folderPath + "\\DecryptAES.txt");

			try {
				if (!file.exists()) {
					file.createNewFile();
				}
			} catch (Exception e) {
				System.out.println("KHONG THE TAO DUOC FILE");
				e.printStackTrace();
			}

			FileOutputStream fileOutputStream = new FileOutputStream(file);
			FileInputStream fileInputStream = new FileInputStream(filePath);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
			byte[] buffer = new byte[1024];
			int len;
			System.out.println("CHAY VAO DAY");
			while ((len = bufferedInputStream.read(buffer)) != -1) {
				byte[] cipherText = cipher.doFinal(buffer, 0, len);
				// Ham ghi de file o day
				try {
					fileOutputStream.write(cipherText);
				} catch (Exception e) {
					System.out.println("LOI TRONG QUA TRINH GHI FILE");
					e.printStackTrace();
				}
			}
			// Dong I/O
			fileInputStream.close();
			bufferedInputStream.close();
			fileOutputStream.close();

		} catch (Exception e) {
			System.out.println("LOI TRONG QUA TRINH DECRYPT");
			e.printStackTrace();
		}

	}


}
