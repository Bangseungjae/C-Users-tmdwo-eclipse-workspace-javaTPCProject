package javaTPCProject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.google.gson.stream.JsonToken;

public class Project01_D {

	public static void main(String[] args) {
		
		// 네이버 클라우드 플랫폼에서 얻은 apiURL연결할 
		String apiURL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=";
		//api등록할 떄 얻은 아이디와 secret
		String client_id = "zz9m9oxdw9";
		String client_secret ="ip8nViYlUObAGXDqfJFXZgKogtMSKSXyRRVp1L2T";
		//InputStreamReader는 바이트 값을 받기 떄문에 문자열을 받는 BufferedReader로 생성
		BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
		
		
		try {
			System.out.print("주소를 입력하세요.");
			String address = io.readLine();
			String addr = URLEncoder.encode(address, "UTF-8");
			//위에 적은 apiURL과 쿼리로 날릴 주소값
			String reqURL = apiURL+addr;
			
			URL url = new URL(reqURL);
			//URL연결
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", client_id);
			con.setRequestProperty("X-NCP-APIGW-API-KEY", client_secret);
			BufferedReader br;
			
			// URL연결이 제대로 되면 200을 리턴한다.
			int responsCode = con.getResponseCode();
			if(responsCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			}else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));				
			}
			String line;
			StringBuffer response = new StringBuffer(); // JSON
			while((line = br.readLine())!= null) {//한 줄씩 잃어옴
				response.append(line);
			}
			br.close();
			
			JSONTokener tokener = new JSONTokener(response.toString());
			JSONObject object = new JSONObject(tokener);
			System.out.println(object.toString(2));
			
			JSONArray arr = object.getJSONArray("addresses");
			for(int i=0; i<arr.length();i++) {
				JSONObject temp = (JSONObject) arr.get(i);
				System.out.println("address: "+ temp.get("roadAddress"));
				System.out.println("jibunAdress: "+ temp.get("jibunAddress"));
				System.out.println("경도: "+ temp.get("x"));
				System.out.println("위도: "+ temp.get("y"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
