package javaTPCProject;

import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Project01_C {

	public static void main(String[] args) {
		String src = "info.json";
		//IO->Stream(��Ʈ��)
		InputStream is = javaTPCProject.Project01_C.class.getResourceAsStream(src); // ������ �޾ƿ�
		if(is==null) {	
			throw new NullPointerException("Can not find resource file"); // ����ó��
		}
		JSONTokener tokener = new JSONTokener(is);// ��ǲ��Ʈ���� ��ü�� �޸𸮷� �÷��ִ°� JSONTokener
		JSONObject object = new JSONObject(tokener);
		JSONArray students = object.getJSONArray("students"); // JSONObject���¸� JSONArray�� ��
		//students.forEach(System.out::println);
		for(int i=0; i<students.length();i++) {
			JSONObject student = (JSONObject)students.get(i);
			System.out.print(student.get("name")+"\t");
			System.out.print(student.get("address")+"\t");
			System.out.println(student.get("phone")+"\t");
		}

	}

}
