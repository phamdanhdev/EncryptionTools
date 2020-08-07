package phamdanh.swing.AESAndRSA;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESAndRSA {
	
	private KeyPair keyPair;
	private PublicKey publicKey;
	private PrivateKey privateKey;
	private byte[] secretKeyEncrypted;


	private Cipher cipherText;
	
	public void generateKey(int size) throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(size); // 2048
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		this.keyPair = keyPair;
		generatePublicKey();
		generatePrivateKey();
	}
	
	private void generatePublicKey() {
		PublicKey key = keyPair.getPublic();
		this.publicKey = key;
	}
	
	private void generatePrivateKey() {
		PrivateKey key = keyPair.getPrivate();
		this.privateKey = key;
	}
	
	public byte[] encryptText(String text) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		//Tao key AES
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		SecretKey secretKey = keyGenerator.generateKey();
		//Ma hoa text bang key AES
		this.cipherText = Cipher.getInstance("AES");
		this.cipherText.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] textEncrypted = this.cipherText.doFinal(text.getBytes());
		//Ma hoa key AES bang RSA Public Key
//		generatePublicKey();
//		generatePrivateKey();
		Cipher cipherKey1 = Cipher.getInstance("RSA");
//		cipherKey1.init(Cipher.ENCRYPT_MODE, publicKey);
		cipherKey1.init(Cipher.ENCRYPT_MODE, privateKey);
		this.secretKeyEncrypted = cipherKey1.doFinal(secretKey.getEncoded());
		return textEncrypted;
	}
	
	public String decryptText(byte[] text) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		// Giai ma key AES bang RSA Private Key
//		generatePrivateKey();
//		generatePublicKey();
		Cipher cipherKey2 = Cipher.getInstance("RSA");
//		cipherKey2.init(Cipher.PRIVATE_KEY, this.privateKey);
		cipherKey2.init(Cipher.DECRYPT_MODE, this.publicKey);
		byte[] secretKeyDecrypted = cipherKey2.doFinal(secretKeyEncrypted);
		//Chuyen byte[] secretKeyDecrypted ve lai SecretKey
		SecretKey secretKey = new SecretKeySpec(secretKeyDecrypted, 0, secretKeyDecrypted.length, "AES");
		this.cipherText.init(Cipher.DECRYPT_MODE, secretKey);
		//Giai ma Text
		byte[] textDecrypted = this.cipherText.doFinal(text);
		return new String(textDecrypted, "UTF-8");
		
//		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//		cipher.init(Cipher.DECRYPT_MODE, privateKey);
//		byte[] textDecrypted = new byte[3072];
//		textDecrypted = cipher.doFinal(text.getBytes("UTF-8"));
//		return new String(textDecrypted, "UTF-8");
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		String text = new String("Xin chào các bạn.@@!");
		AESAndRSA rsa = new AESAndRSA();
		System.out.println("========== RSA ==========");
		System.out.println("PLAIN TEXT: " + text);
		rsa.generateKey(2048);
		byte[] cipherText = rsa.encryptText(text);
		System.out.println("CIPHER TEXT: " + new String(cipherText));
		String plainText = rsa.decryptText(cipherText);
		System.out.println("PLAIN TEXT: " + plainText);
	}

}
