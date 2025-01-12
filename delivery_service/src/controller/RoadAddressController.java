package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import model.dto.RoadAddressDto;

public class RoadAddressController {
	private RoadAddressController() {
	}

	private static RoadAddressController roadAddressController = new RoadAddressController();

	public static RoadAddressController getInstance() {
		return roadAddressController;
	}

	// 검색할 주소 키워드를 입력받습니다. 키워드로 검색한 도로명 주소 목록을 리턴합니다.
	public ArrayList<RoadAddressDto> getRoadAddress(String keyword) {
		ArrayList<RoadAddressDto> roadAddressList = new ArrayList<>();
		
		try {
			// 도로명주소 API 키 (개인신청해서 받은 개발용 API 키)
			String apiKey = "devU01TX0FVVEgyMDI1MDEwMzIwMTczMzExNTM4MTk=";

			// API URL 생성
			String apiURL = "https://www.juso.go.kr/addrlink/addrLinkApi.do";
			StringBuilder urlBuilder = new StringBuilder(apiURL);
			urlBuilder.append("?confmKey=").append(apiKey);
			urlBuilder.append("&currentPage=1"); // 현재 페이지
			urlBuilder.append("&countPerPage=10"); // 한 페이지에 출력할 데이터 수
			urlBuilder.append("&keyword=").append(URLEncoder.encode(keyword, "UTF-8"));
			urlBuilder.append("&resultType=json"); // 결과 형식 (JSON)

			// URL 연결
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			// 응답 코드 확인
			int responseCode = conn.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
				StringBuilder response = new StringBuilder();
				String inputLine;

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// JSON 파싱
				roadAddressList = parseJsonResponse(response.toString());
			} else {
				System.err.printf("도로명 주소 HTTP 요청 실패: (%d:%s)\n", responseCode, conn.getResponseMessage());
			}

			conn.disconnect();			
		} catch (org.json.simple.parser.ParseException e) {
			System.err.println("도로명 주소 JSON 파싱 실패 : " + e.getMessage());
		} catch (IOException e) {
			System.err.println("도로명 주소 JSON 파싱 실패(IOException): " + e.getMessage());
		} catch (Exception e) {
			System.err.println("도로명 주소 JSON 파싱 실패(Exception): " + e.getMessage());
		}		

		return roadAddressList;
	}

	// 도로명 주소 목록을 리스트에 담아서 리턴한다.
	private ArrayList<RoadAddressDto> parseJsonResponse(String jsonResponse) throws org.json.simple.parser.ParseException {
		ArrayList<RoadAddressDto> roadAddressList = new ArrayList<>();
		
		// Simple JSON 파서
		JSONParser parser = new JSONParser();
		JSONObject rootObject = (JSONObject) parser.parse(jsonResponse);

		// results 객체 가져오기
		JSONObject results = (JSONObject) rootObject.get("results");

		// common 객체에서 총 검색 결과 수 확인
		JSONObject common = (JSONObject) results.get("common");
		String totalCount = (String) common.get("totalCount");	
		
		// juso 배열 가져오기
		JSONArray jusoArray = (JSONArray) results.get("juso");
		for (Object obj : jusoArray) {
			JSONObject address = (JSONObject) obj;			
			RoadAddressDto roadAddressDto = new RoadAddressDto();
			roadAddressDto.setRoadAddress( (String)address.get("roadAddr") );
			roadAddressDto.setJibunAddress( (String)address.get("jibunAddr") );
			roadAddressDto.setZipCode( (String)address.get("zipNo") );
			roadAddressDto.setSi((String)address.get("siNm") );
			roadAddressDto.setSgg((String)address.get("sggNm") );
			roadAddressList.add(roadAddressDto);
		}
		
		return roadAddressList;
	}
}
