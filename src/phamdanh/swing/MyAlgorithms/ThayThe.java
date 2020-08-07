package phamdanh.swing.MyAlgorithms;

import java.util.Random;

public class ThayThe {

	public char[] bangChuCai = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'W', 'Z' };
	public char[] bangChuCaiLow = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
			'r', 's', 't', 'u', 'v', 'x', 'y', 'w', 'z' };

	public static String createKeyUpper() {
		String bangChuCaiTaoKey = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		StringBuilder sb = new StringBuilder(bangChuCaiTaoKey);

		String result = "";

		Random rd = new Random();
		while (sb.length() > 0) {

			int indexRD = rd.nextInt(sb.length());
			result += sb.charAt(indexRD);
			sb.deleteCharAt(indexRD);

		}

		return result.toString();
	}

	public static String createKeyLower() {
		String bangChuCaiTaoKey = "abcdefghijklmnopqrstuvwxyz";

		StringBuilder sb = new StringBuilder(bangChuCaiTaoKey);

		String result = "";

		Random rd = new Random();
		while (sb.length() > 0) {

			int indexRD = rd.nextInt(sb.length());
			result += sb.charAt(indexRD);
			sb.deleteCharAt(indexRD);

		}

		return result.toString();
	}

	static String keyUpper = createKeyUpper();
	static String keyLower = createKeyLower();

	public String maHoa(String plainText) {
		String result = "";
		outer: for (int i = 0; i < plainText.length(); i++) {
			for (int j = 0; j < bangChuCai.length; j++) {
				Character c = plainText.charAt(i);
				if (c.equals(' ') || c.equals('.')) {
					result += c;
					continue outer;
				}
				if (c.equals(bangChuCai[j])) {
					result += keyUpper.charAt(j);

				} else if (c.equals(bangChuCaiLow[j])) {
					result += keyLower.charAt(j);
				}
			}
		}
		return result.toString();
	}

	public String giaiMa(String cipherText) {
		String result = "";
		outer: for (int i = 0; i < cipherText.length(); i++) {
			Character c = cipherText.charAt(i);
			for (int j = 0; j < keyUpper.length(); j++) {
				if (c.equals(' ') || c.equals('.')) {
					result += c;
					continue outer;
				}
				if (c.equals(keyUpper.charAt(j))) {
					result += bangChuCai[j];
				} else if (c.equals(keyLower.charAt(j))) {
					result += bangChuCaiLow[j];
				}
			}

		}
		return result.toString();

	}

//	public static void main(String[] args) {
//
//		ThayThe thayThe = new ThayThe();
//
//		char[] key = thayThe.taoKey();
//
//		String plainText = "HELLOWORLD";
//
//		System.out.println("KEY: " + String.copyValueOf(key));
//
//		System.out.println("==========================");
//
//		System.out.println("PLAINTEXT: " + plainText);
//
//		String cipherText = thayThe.maHoa(plainText, key);
//
//		System.out.println("==========================");
//
//		System.out.println("CIPHERTEXT: " + cipherText);
//
//		String plainTextBack = thayThe.giaiMa(cipherText, key);
//
//		System.out.println("==========================");
//
//		System.out.println("PLAINTEXT: " + plainTextBack);
//
//	}

}
