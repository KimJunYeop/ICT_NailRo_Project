package com.javalec.object;

import java.util.ArrayList;

public class JTourCourse {
	String title;
	String image;
	int contenttypeid;
	int contentid;
	ArrayList<JTourCourseContent> course;

	public ArrayList<JTourCourseContent> getCourse() {
		return course;
	}

	public void setCourse(ArrayList<JTourCourseContent> course) {
		this.course = course;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getContenttypeid() {
		return contenttypeid;
	}

	public void setContenttypeid(int contenttypeid) {
		this.contenttypeid = contenttypeid;
	}

	public int getContentid() {
		return contentid;
	}

	public void setContentid(int contentid) {
		this.contentid = contentid;
	}

}
