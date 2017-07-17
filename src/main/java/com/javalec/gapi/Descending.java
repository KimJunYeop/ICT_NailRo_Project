package com.javalec.gapi;

import java.util.Comparator;

public class Descending  implements Comparator<JPlace> {
	@Override
	public int compare(JPlace o2, JPlace o1) {
		// TODO Auto-generated method stub
		return Double.compare(o1.getPlace_rating(),o2.getPlace_rating());
	}

}
