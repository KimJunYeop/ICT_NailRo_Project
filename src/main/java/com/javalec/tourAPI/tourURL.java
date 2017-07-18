package com.javalec.tourAPI;

public class tourURL {
	static private String url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/";
	static private String serviceKey = "9%2FgsanrvJGqg3eF9InVtlFrz2SOHea2S3MOgQE%2BwO0PPiHgPf2jIdgxy1vvpUhV%2BWlZ0httNIoeTJKmhZjgE7g%3D%3D";
	private String type;
	private String option;
	private String areaCode;
	private String keyword;
	private String contentID;
	
	public tourURL(String areaCode, String keyword){
		this.type = "searchKeyword";
		this.option = "&MobileOS=ETC&MobileApp=nailrochat&numOfRows=100";
		this.areaCode = areaCode;
		this.keyword = keyword;
	}
	
	public tourURL(String contentID){
		this.type = "detailCommon";
		this.option = "&MobileOS=ETC&MobileApp=nailrochat&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y";
		this.contentID = contentID;
	}
	
	public String getUrl() {
		if(this.type.equals("searchKeyword")){
			return this.url + this.type + "?ServiceKey=" + this.serviceKey + this.option +"&areaCode=" + this.areaCode + "&keyword=" + this.keyword;
		}
		else{
			return this.url + this.type + "?ServiceKey=" + this.serviceKey + this.option +"&contentId=" + this.contentID;
		}
	}
	
}
