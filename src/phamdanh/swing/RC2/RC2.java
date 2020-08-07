package phamdanh.swing.RC2;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class RC2 {
	
	private SecretKey key;

	public SecretKey createKey(int size) throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("RC2");
		keyGenerator.init(size);
		key = keyGenerator.generateKey();
		return key;
	}

	public byte[] encrypt(String text, SecretKey secretKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		if (secretKey == null) {
			return new byte[] {};
		}

		Cipher cipher = Cipher.getInstance("RC2");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] plainText = text.getBytes("UTF-8");
		byte[] cipherText = cipher.doFinal(plainText);
		return cipherText;
	}

	public String decript(byte[] text, SecretKey secretKey) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException {
		if (key == null) {
			return null;
		}
		Cipher cipher = Cipher.getInstance("RC2");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] plainText = cipher.doFinal(text);
		String output = new String(plainText, "UTF-8");
		return output;

	}

//	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
//		RC2 rc2 = new RC2();
//		SecretKey key = rc2.createKey();
//		byte[] out = rc2.encrypt("ABC");
//		System.out.println(new String(out));
//		System.out.println(rc2.decript(out));
//		
//	}

}
