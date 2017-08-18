package com.javalec.discount;

import java.io.File;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

public class JBarcode {

	public void getBarcode(String barcode_num){
		try {
			Barcode barcode = BarcodeFactory.createCode128B(barcode_num);

			File file = new File("../ICT_NailRo_Project/src/main/webapp/resources/barcode/"+barcode_num+".png");

			BarcodeImageHandler.savePNG(barcode, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
