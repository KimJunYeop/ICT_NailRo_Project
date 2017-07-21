package com.javalec.gapi;

import org.junit.Test;

public class JunitTest {
	
	
//	@Test
//	public void test() throws ApiException, InterruptedException, IOException{
//		GooglePlace test = new GooglePlace();
////		test.gTest();
//		ArrayList<JPlace> place = new ArrayList<JPlace>();
//		place = test.search("곡성");
//		System.out.println("------------------");
//		System.out.println(place);
//		Descending descending = new Descending();
//		Collections.sort(place,descending);
//		
//		System.out.println("what the...");
//		for(int i = 0; i < place.size(); i++){
//			System.out.println(" -----------------" + i + "------------------------");
//			JSONArray array = place.get(i).getReviews();
//			for(int j = 0; j < array.length(); j++){
//				JSONObject obj = (JSONObject) array.get(j);
//				
//				System.out.println("author name : " + obj.getString("author_name"));
//				System.out.println("rating  : " + obj.getDouble("rating"));
//				System.out.println("text  : " + obj.getString("text"));
//			}
//		}
//	}
	
	@Test
	public void chatTest(){
		String str = "코스부산";
		
		if(str.matches("코스.*.*")){
			System.out.println("success");
			System.out.println(str);
		}else{
			System.out.println("fail");
		}
		
	}
	
}


