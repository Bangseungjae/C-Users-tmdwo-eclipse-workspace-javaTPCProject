package javaTPCProject;


import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kr.inflearn.BookDTO;

public class Project01_A {

	public static void main(String[] args) {
		//Object(BookDTO) -> JSON(String)
		BookDTO dto = new BookDTO("�ڹ�", 21000, "������", 670);
		Gson g = new Gson();
		String json = g.toJson(dto);
		System.out.println(json); //{"title":"�ڹ�","price":21000,"company":"������","page":670}
		
		// JSON(String) -> Object(BookDTO)
		BookDTO dto1 = g.fromJson(json, BookDTO.class);
		System.out.println(dto1); //BookDTO [title=�ڹ�, price=21000, company=������, page=670]
		
		//Object(List<BookDTO>) ->JSON(String) : [{    ], {    }...]
		List<BookDTO> list = new ArrayList<BookDTO>();
		list.add(new BookDTO("�ڹ�", 21000, "������", 670));
		list.add(new BookDTO("�ڹ�1", 31000, "������", 270));
		list.add(new BookDTO("�ڹ�2", 10000, "������", 300));
		
		String listJson = g.toJson(list);
		System.out.println(listJson);
		
		//JSON(String) -> Object(List<BookDTO>J)
		List<BookDTO> list1 = g.fromJson(listJson, new TypeToken<List<BookDTO>>() {}.getType());
		list1.stream().forEach(System.out::println);
	}

}
