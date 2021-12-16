package javaTPCProject;

import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Project01_C {

	public static void main(String[] args) {
		String src = "info.json";
		//IO->Stream(스트림)
		InputStream is = javaTPCProject.Project01_C.class.getResourceAsStream(src); // 파일을 받아옴
		if(is==null) {	
			throw new NullPointerException("Can not find resource file"); // 예외처리
		}
		JSONTokener tokener = new JSONTokener(is);// 인풋스트림의 객체를 메모리로 올려주는게 JSONTokener
		JSONObject object = new JSONObject(tokener);
		JSONArray students = object.getJSONArray("students"); // JSONObject형태를 JSONArray로 변
		//students.forEach(System.out::println);
		for(int i=0; i<students.length();i++) {
			JSONObject student = (JSONObject)students.get(i);
			System.out.print(student.get("name")+"\t");
			System.out.print(student.get("address")+"\t");
			System.out.println(student.get("phone")+"\t");
		}

	}

}
