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
	
	// ���� �̹��� ���� �޼���
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
				System.out.println("�����");
				InputStream is = con.getInputStream();
				int read = 0;
				byte[] bytes = new byte[1024];
				//������ �̸����� ���� ����
				String tempname = Long.valueOf(new java.util.Date().getTime()).toString();
				File f= new File(tempname + ".jpg");
				f.createNewFile();//���� �ð����� ���� ����
				OutputStream outputStream = new FileOutputStream(f);// ���Ͽ� ��Ʈ�� ����
				while((read = is.read(bytes))!= -1) {//����� URL�� ����Ʈ ���� �޾ƿ�
					outputStream.write(bytes, 0, read);//���Ͽ� ����Ʈ �ڵ� ���پ� ����
				}
				is.close();
			}else {// ���� �߻�
				System.out.println("����ȵ�");
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
		
		// ���̹� Ŭ���� �÷������� ���� apiURL������ 
		String apiURL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=";
		//api����� �� ���� ���̵�� secret
		String client_id = "zz9m9oxdw9";
		String client_secret ="ip8nViYlUObAGXDqfJFXZgKogtMSKSXyRRVp1L2T";
		//InputStreamReader�� ����Ʈ ���� �ޱ� ������ ���ڿ��� �޴� BufferedReader�� ����
		BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
		
		
		try {
			System.out.print("�ּҸ� �Է��ϼ���.");
			String address = io.readLine();
			String addr = URLEncoder.encode(address, "UTF-8");
			//���� ���� apiURL�� ������ ���� �ּҰ�
			String reqURL = apiURL+addr;
			
			URL url = new URL(reqURL);
			//URL����
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", client_id);
			con.setRequestProperty("X-NCP-APIGW-API-KEY", client_secret);
			BufferedReader br;
			
			// URL������ ����� �Ǹ� 200�� �����Ѵ�.
			int responsCode = con.getResponseCode();
			if(responsCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			}else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));				
			}
			String line;
			StringBuffer response = new StringBuffer(); // JSON
			
			// �߰��� �κ�
			String x = ""; String y=""; String z="";//�浵, ����, �ּ�
			while((line = br.readLine())!= null) {//�� �پ� �Ҿ��
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
				System.out.println("�浵: "+ temp.get("x"));
				System.out.println("����: "+ temp.get("y"));
				
				//�߰��� �κ�
				x=(String)temp.get("x");
				y=(String)temp.get("y");
				z=(String)temp.get("roadAddress");				
			}
			
			// �߰��� �κ�
			map_services(x,y,z);
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
