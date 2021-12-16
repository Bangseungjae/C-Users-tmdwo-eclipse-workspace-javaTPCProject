package javaTPCProject;

import org.json.JSONArray;
import org.json.JSONObject;

public class project01_B {

	public static void main(String[] args) {
		//JSON-Java(org.json)
		JSONArray students = new JSONArray();
		JSONObject student = new JSONObject();
		student.put("name", "È«±æµ¿");
		student.put("phone", "010-1111-1111");
		student.put("address", "¼­¿ï");
		System.out.println(student); // {"address":"¼­¿ï","phone":"010-1111-1111","name":"È«±æµ¿"}
		students.put(student);


		student = new JSONObject();
		student.put("name", "³ª±æµ¿");
		student.put("phone", "010-2222-2222");
		student.put("address", "±¤ÁÖ");
		students.put(student);
		System.out.println(students); // [{"address":"¼­¿ï","phone":"010-1111-1111","name":"È«±æµ¿"},{"address":"±¤ÁÖ","phone":"010-2222-2222","name":"³ª±æµ¿"}]
		
		JSONObject object = new JSONObject();
		
		object.put("students", students);
		System.out.println(object.toString(2)); // {"students":[{"address":"¼­¿ï","phone":"010-1111-1111","name":"È«±æµ¿"},{"address":"±¤ÁÖ","phone":"010-2222-2222","name":"³ª±æµ¿"}]}
	}

}
