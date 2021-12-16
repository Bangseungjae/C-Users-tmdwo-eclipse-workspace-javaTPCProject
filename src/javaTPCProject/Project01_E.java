package javaTPCProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Project01_E {
	
	// 지도 이미지 생성 메서드
	public static void map_services(String point_x, String point_y, String address) {
		String URL_STATICMAP = "https://naveropenapi.apigw.ntruss.com/map-static/v2/raster?";
		try {
			String pos = URLEncoder.encode(point_x + " "+ point_y, "UTF-8");
			String url = URL_STATICMAP;
			url += "center=" + point_x + "," + point_y;
			url += "&level=16&w=700&h=500";
			url += "&markers=type:t|size:mid|pos:"+pos+"|label:"+URLEncoder.encode(address,"UTF-8");
			URL u = new URL(url);
			HttpURLConnection con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "zz9m9oxdw9");
			con.setRequestProperty("X-NCP-APIGW-API-KEY", "ip8nViYlUObAGXDqfJFXZgKogtMSKSXyRRVp1L2T");
			int responseCode = con.getResponseCode();
			BufferedReader br;
			
			if(responseCode == 200) {
				System.out.println("연결됨");
				InputStream is = con.getInputStream();
				int read = 0;
				byte[] bytes = new byte[1024];
				//랜덤한 이름으로 파일 생성
				String tempname = Long.valueOf(new java.util.Date().getTime()).toString();
				File f= new File(tempname + ".jpg");
				f.createNewFile();//생성 시간으로 파일 생성
				OutputStream outputStream = new FileOutputStream(f);// 파일에 스트림 연결
				while((read = is.read(bytes))!= -1) {//연결된 URL로 바이트 값을 받아옴
					outputStream.write(bytes, 0, read);//파일에 파이트 코드 한줄씩 쓰기
				}
				is.close();
			}else {// 에러 발생
				System.out.println("연결안됨");
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while((inputLine = br.readLine())!=null) {
					response.append(inputLine);
				}
				br.close();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

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
			
			// 추가된 부분
			String x = ""; String y=""; String z="";//경도, 위도, 주소
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
				
				//추가된 부분
				x=(String)temp.get("x");
				y=(String)temp.get("y");
				z=(String)temp.get("roadAddress");				
			}
			
			// 추가된 부분
			map_services(x,y,z);
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
