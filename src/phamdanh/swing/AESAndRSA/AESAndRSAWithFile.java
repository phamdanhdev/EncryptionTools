package phamdanh.swing.AESAndRSA;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESAndRSAWithFile {


	private PrivateKey privateKey;

	public void generateKey(int keySize, String folderPath)
			throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(keySize);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		try (FileOutputStream out = new FileOutputStream(folderPath + "\\PrivateKey-" + keySize + "bits.txt")) {
			PrivateKey key = keyPair.getPrivate();
			this.privateKey = key;
			out.write(key.getEncoded());
			out.close();
		}
		try (FileOutputStream out = new FileOutputStream(folderPath + "\\PublicKey-" + keySize + "bits.txt")) {
			PublicKey key = keyPair.getPublic();
			out.write(key.getEncoded());
			out.close();
		}
	}

	public void encrypt(String filePath, String folderPath, int keySize) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
		/*
		 * 
		 * Tao Key AES va ghi file
		 */
		generateKey(keySize, folderPath);
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		SecretKey secretKey = keyGenerator.generateKey();

		// Ma hoa text bang key AES
		Cipher cipherText = Cipher.getInstance("AES");
		try {
			// Khoi tao Cipher voi che do Encrypt
			cipherText.init(Cipher.ENCRYPT_MODE, secretKey);
			// Write file here
			File file = new File(folderPath + "\\EncryptAES&RSA.txt");
			try {
				if (!file.exists()) {
					file.createNewFile();
				}
			} catch (Exception e) {
				System.out.println("KHONG THE TAO DUOC FILE");
				e.printStackTrace();
			}

			FileOutputStream fileOutputStream = new FileOutputStream(file);
			// Read file tu filePath
			FileInputStream fileInputStream = new FileInputStream(filePath);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
			byte[] buffer = new byte[1024];
			int len;
			while ((len = bufferedInputStream.read(buffer)) != -1) {
				byte[] cipherFileText = cipherText.doFinal(buffer, 0, len);
				// Ham ghi de file o day
				try {
					fileOutputStream.write(cipherFileText);
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
		// Ma hoa key AES bang RSA Public Key
		Cipher cipherKey = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipherKey.init(Cipher.ENCRYPT_MODE, privateKey);
		byte[] secretKeyEncrypted = cipherKey.doFinal(secretKey.getEncoded());
		/*
		 * 
		 * 
		 */
		File file1 = new File(folderPath + "\\SecretKeyRSA&AES-" + keySize + "bits.txt");
		try {
			if (!file1.exists()) {
				file1.createNewFile();
			}
		} catch (Exception e) {
			System.out.println("KHONG THE TAO DUOC FILE");
			e.printStackTrace();
		}
		FileOutputStream outputStream = new FileOutputStream(file1);
		outputStream.write(secretKeyEncrypted);
		outputStream.close();
		/*
		 * 
		 * 
		 */

	}

	public void decrypt(String filePath, String folderPath, String secretKeyPath, String publicKeyPath)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, IOException, InvalidKeySpecException {
		/*
		 * 
		 * GET PUBLIC KEY
		 */
		byte[] bytes = Files.readAllBytes(Paths.get(publicKeyPath));
		X509EncodedKeySpec ks = new X509EncodedKeySpec(bytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PublicKey publicKey = kf.generatePublic(ks);
		/*
		 * 
		 * END GET PUBLIC KEY
		 */
		// Giai ma key AES bang RSA Private Key
		/*
		 * 
		 * 
		 */
		// Doc SecretKey tu file
		byte[] secretKeyEncrypted = Files.readAllBytes(Paths.get(secretKeyPath));
		Cipher cipherKey = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipherKey.init(Cipher.DECRYPT_MODE, publicKey);
		/*
		 * 
		 * 
		 */
		byte[] secretKeyDecrypted = cipherKey.doFinal(secretKeyEncrypted);
		// Chuyen byte[] secretKeyDecrypted ve lai SecretKey
		SecretKey secretKey = new SecretKeySpec(secretKeyDecrypted, 0, secretKeyDecrypted.length, "AES");
		Cipher cipherText = Cipher.getInstance("AES");
		cipherText.init(Cipher.DECRYPT_MODE, secretKey);
		// Giai ma File

		try {

			// Write file here
			File file = new File(folderPath + "\\DecryptAES&RSA.txt");

			try {
				if (!file.exists()) {
					file.createNewFile();
				}
			} catch (Exception e) {
				System.out.println("KHONG THE TAO DUOC FILE");
				e.printStackTrace();
			}


			FileOutputStream fileOutputStream = new FileOutputStream(file);
			// Read file tu filePath
			FileInputStream fileInputStream = new FileInputStream(filePath);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
			byte[] buffer = new byte[1024];
			int len;
			while ((len = bufferedInputStream.read(buffer)) != -1) {
				byte[] cipherTextOut = cipherText.doFinal(buffer, 0, len);
				// Ham ghi de file o day
				try {
					fileOutputStream.write(cipherTextOut);
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

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		String text = new String("Xin chào các bạn.@@!");
		AESAndRSA rsa = new AESAndRSA();
		System.out.println("========== RSA ==========");
		System.out.println("PLAIN TEXT: " + text);
		rsa.generateKey(2048);
		byte[] cipherText = rsa.encryptText(text);
		System.out.println("CIPHER TEXT: " + cipherText);
		String plainText = rsa.decryptText(cipherText);
		System.out.println("PLAIN TEXT: " + plainText);
	}

}
