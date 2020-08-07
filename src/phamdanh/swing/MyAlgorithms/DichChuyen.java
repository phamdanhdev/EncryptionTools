package phamdanh.swing.MyAlgorithms;

public class DichChuyen {

	// Khong dung
//	public static char[] bangChuCai = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
//			'Q', 'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'W', 'Z' };

	public static String bangChuCaiStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static String bangChuCaiLowStr = "abcdefghijklmnopqrstuvwxyz";

	public int giaiKhoa(int key) {
		int giaiKhoa = 0;
		giaiKhoa = 100 % key;

		return giaiKhoa;

	}

	public String dichChuyen(String in, int key) {
		String maHoa = "";

		int keyNum = giaiKhoa(key);

		for (int i = 0; i < in.length(); i++) {
			Character x = in.charAt(i);
			if (x.equals(' ')) {
				maHoa += " ";
			}
			if (x.equals('.')) {
				maHoa += ".";
			}

			for (int j = 0; j < bangChuCaiStr.length(); j++) {

				Character y = bangChuCaiStr.charAt(j);
				Character z = bangChuCaiLowStr.charAt(j);

				if (x.equals(y)) {
//					maHoa += String.valueOf(bangChuCaiStr.charAt(j + keyNum));
					if (j + keyNum > 25) {
						maHoa += String.valueOf(bangChuCaiStr.charAt((j + keyNum) - 26));
					} else {
						maHoa += String.valueOf(bangChuCaiStr.charAt(j + keyNum));
					}
				} else {
					if (x.equals(z)) {
						if (j + keyNum > 25) {
							maHoa += String.valueOf(bangChuCaiLowStr.charAt((j + keyNum) - 26));
						} else {
							maHoa += String.valueOf(bangChuCaiLowStr.charAt(j + keyNum));
						}

					}
				}

			}

		}

		return maHoa;

	}

	public String giaiMaDichChuyen(String in, int key) {

		String giaiMa = "";

		int keyNum = giaiKhoa(key);

		for (int i = 0; i < in.length(); i++) {
			Character x = in.charAt(i);
			if (x.equals(' ')) {
				giaiMa += " ";
			}
			if (x.equals('.')) {
				giaiMa += ".";
			}

			for (int j = 0; j < bangChuCaiStr.length(); j++) {

				Character y = bangChuCaiStr.charAt(j);
				Character z = bangChuCaiLowStr.charAt(j);

				if (x.equals(y)) {
//					giaiMa += String.valueOf(bangChuCaiStr.charAt(j - keyNum));

					if (j - keyNum < 0) {
						giaiMa += String.valueOf(bangChuCaiStr.charAt(j - keyNum + 26));
					} else {
						giaiMa += String.valueOf(bangChuCaiStr.charAt(j - keyNum));
					}
				} else {
					if (x.equals(z)) {
						if (j - keyNum < 0) {
							giaiMa += String.valueOf(bangChuCaiLowStr.charAt((j - keyNum) + 26));
						} else {
							giaiMa += String.valueOf(bangChuCaiLowStr.charAt(j - keyNum));
						}

					}
				}
			}

		}

		return giaiMa;
	}
//
//	public static void main(String[] args) {
//		String text = "ABC DAF";
//		int key = 9;
//
//		System.out.println("KEY: " + key);
//
//		System.out.println("---------------------------");
//
//		System.out.println("CHUỖI CẦN MÃ HO�?: " + text);
//
//		System.out.println("---------------------------");
//
//		DichChuyen dichChuyen = new DichChuyen();
//
//		String chuoiMaHoa = dichChuyen.dichChuyen(text, key);
//
//		System.out.println("CHUỖI �?Ã MÃ HO�?: " + chuoiMaHoa);
//
//		System.out.println("---------------------------");
//
//		String chuoiGiaiMa = dichChuyen.giaiMaDichChuyen(chuoiMaHoa, key);
//
//		System.out.println("CHUỖI GIẢI MÃ: " + chuoiGiaiMa);
//	}

}
