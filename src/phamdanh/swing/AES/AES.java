package phamdanh.swing.AES;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class AES {

	private SecretKey key;

	public SecretKey createKey(int size) throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(size);
		key = keyGenerator.generateKey();
		return key;
	}

	public byte[] encrypt(String text, SecretKey secretKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		if (key == null) {
			return new byte[] {};
		}

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] plainText = text.getBytes("UTF-8");
		byte[] cipherText = cipher.doFinal(plainText);
		return cipherText;
	}

	public String decript(byte[] text, SecretKey secretKey) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException {
		if (key == null) {
			return null;
		}
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] plainText = cipher.doFinal(text);
		String output = new String(plainText, "UTF-8");
		return output;

	}

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		AES aes = new AES();
		SecretKey key = aes.createKey(128);
		byte[] out = aes.encrypt("ABC", key);
		System.out.println(new String(out));
		System.out.println(aes.decript(out, key));
		
	}
	
}
