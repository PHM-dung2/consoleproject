package view;

public class a {
	
	private static int getKorCnt(String kor) {
	    int cnt = 0;
	    for (int i = 0 ; i < kor.length() ; i++) {
	        if (kor.charAt(i) >= '가' && kor.charAt(i) <= '힣') {
	            cnt++;
	        }
	    } return cnt;
	}
	//- 전각문자의 개수만큼 문자열을 조정해주는 메서드
	public static String convert(String word, int size) {
	    String formatter = String.format("%%-%ds", size -getKorCnt(word));
	    return String.format(formatter, word);
	}
	
}


