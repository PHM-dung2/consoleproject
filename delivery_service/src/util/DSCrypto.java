package util;

import java.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DSCrypto {
	// id, password 조합하여 보여져도 상관없는 안전한 패스워드 문자열을 리턴하는 메소드이다.
	public static String makeSafePassword(String id, String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			// id + password 를 조합하여 bytes 배열로 넣어준후, sha256 해시 byte 배열을 받는다.
			byte[] hashBytes = digest.digest(String.format("%s%s", id, password).getBytes());

			// 바이트 배열을 Base64 인코딩한 문자열로 리턴한다.
			return Base64.getEncoder().encodeToString(hashBytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace(); 
			return null;
		}
	}
}
