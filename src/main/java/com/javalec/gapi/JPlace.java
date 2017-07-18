package com.javalec.gapi;

import org.json.JSONArray;

public class JPlace {
	String formatted_address;
	String name;
	String photo_reference;
	String place_id;
	JSONArray reviews;
	double review_rating;
	double place_rating;

	public double getReview_rating() {
		return review_rating;
	}

	public void setReview_rating(double review_rating) {
		this.review_rating = review_rating;
	}

	public double getPlace_rating() {
		return place_rating;
	}

	public void setPlace_rating(double place_rating) {
		this.place_rating = place_rating;
	}

	public JSONArray getReviews() {
		return reviews;
	}

	public void setReviews(JSONArray reviews) {
		this.reviews = reviews;
	}

	public String getPlace_id() {
		return place_id;
	}

	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto_reference() {
		return photo_reference;
	}

	public void setPhoto_reference(String photo_reference) {
		this.photo_reference = photo_reference;
	}

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}

}
