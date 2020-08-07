package phamdanh.swing.atbm;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

public class SignatureFile {

	public void createFileSigned(String filePath, String folderPath) {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA", "SUN");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			keyPairGenerator.initialize(1024, random);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			PrivateKey privateKey = keyPair.getPrivate();
			PublicKey publicKey = keyPair.getPublic();

			// Tao Signature => File + Sign(PrivateKey)
			Signature signature = Signature.getInstance("SHA1withDSA", "SUN");
			signature.initSign(privateKey); // Tao chu ki voi PrivateKey
			FileInputStream fileInputStream = new FileInputStream(filePath);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
			byte[] buffer = new byte[1024];
			int len;
			while ((len = bufferedInputStream.read(buffer)) != -1) {
				signature.update(buffer, 0, len); // Them du lieu vao cho signature
			}
			bufferedInputStream.close();
			fileInputStream.close();
			byte[] realSignature = signature.sign();

			// Save signature to file
			File file = new File(folderPath + "\\SignedFile.txt");
			try {
				if (!file.exists()) {
					file.createNewFile();
				}
			} catch (Exception e) {
				System.out.println("KHONG THE TAO DUOC FILE");
				e.printStackTrace();
			}
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(realSignature);
			fileOutputStream.close();

			// Save public key
			File file2 = new File(folderPath + "\\VerifyPublicKey.txt");
			try {
				if (!file.exists()) {
					file.createNewFile();
				}
			} catch (Exception e) {
				System.out.println("KHONG THE TAO DUOC FILE");
				e.printStackTrace();
			}
			FileOutputStream fileOutputStream1 = new FileOutputStream(file2);
			fileOutputStream1.write(publicKey.getEncoded());
			fileOutputStream1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean verifySignature(String filePath, String signatureFilePath, String publicKeyFilePath) {
		boolean result = false;
		try {

			// Lay Public Key vao Byte
			FileInputStream fileInputStream = new FileInputStream(publicKeyFilePath);
			byte[] publickeyByte = new byte[fileInputStream.available()];
			fileInputStream.read(publickeyByte);
			fileInputStream.close();

			// Chuyen Public Key Byte ve PublicKey
			X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(publickeyByte);
			KeyFactory factory = KeyFactory.getInstance("DSA", "SUN");
			PublicKey publicKey = factory.generatePublic(encodedKeySpec);

			// Lay Signature tu file.txt
			FileInputStream fileInputStream2 = new FileInputStream(signatureFilePath);
			byte[] realSignature = new byte[fileInputStream2.available()];
			fileInputStream2.read(realSignature);
			fileInputStream2.close();

			// Tao Signature = File + Sign(PublicKey)
			Signature signature = Signature.getInstance("SHA1withDSA", "SUN");
			signature.initVerify(publicKey); // Xac minh chu ki voi PublicKey
			FileInputStream fileInputStream3 = new FileInputStream(filePath);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream3);
			int len;
			byte[] buffer = new byte[1024];
			while ((len = bufferedInputStream.read(buffer)) != -1) {
				signature.update(buffer, 0, len);

			}
			bufferedInputStream.close();
			fileInputStream3.close();
			result = signature.verify(realSignature);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
