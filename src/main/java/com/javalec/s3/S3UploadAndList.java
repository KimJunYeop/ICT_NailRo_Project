package com.javalec.s3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class S3UploadAndList {
	private static final String BUCKET_NAME = "ictnailro";
	private static final String ACCESS_KEY = "AKIAJP2KS52PLCHWQKQA";
	private static final String SECRET_KEY = "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";
	private AmazonS3 amazonS3;

	public S3UploadAndList() {
		AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
		amazonS3 = new AmazonS3Client(awsCredentials);
	}

	public void uploadFile(File file) {
		if (amazonS3 != null) {
			try {
				PutObjectRequest putObjectRequest = new PutObjectRequest(
						BUCKET_NAME + "/s3"/* sub directory */, file.getName(), file);
				putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead); // file
																					// permission
				amazonS3.putObject(putObjectRequest); // upload file

			} catch (AmazonServiceException ase) {
				ase.printStackTrace();
			} finally {
				amazonS3 = null;
			}
		}
	}

	public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
		File convFile = new File(multipart.getOriginalFilename());
		multipart.transferTo(convFile);
		return convFile;
	}

	public ArrayList<ArrayList<String>> getS3File() {
		String link = "https://s3.ap-northeast-2.amazonaws.com/ictnailro/";
		Bucket bucket_nailro = null;
		ArrayList<ArrayList<String>> real = new ArrayList<ArrayList<String>>();
		
		List<Bucket> buckets = amazonS3.listBuckets();

		// Bucket Name 설정.
		for (Bucket bucket : buckets) {
			bucket_nailro = bucket;
		}

		// Object 가져오기.
		ObjectListing objects = amazonS3.listObjects(bucket_nailro.getName());
		do {
			for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) {
				ArrayList<String> list_element = new ArrayList<String>();
				list_element.add(objectSummary.getKey());
				list_element.add((link + objectSummary.getKey()));
				real.add(list_element);
				
			}
			objects = amazonS3.listNextBatchOfObjects(objects);
		} while (objects.isTruncated());

		return real;
	}

}
