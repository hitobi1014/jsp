package kr.or.ddit.fileupload;

import static org.junit.Assert.*;

import org.junit.Test;

public class FileUploadUtilTest {

	@Test
	public void getFileNameTest() {
		/***Given***/
		String contentDisPosition = "form-data; name=\"img\"; filename=\"contentType.jpg\"";

		/***When***/
		String fileName=FileUploadUtil.getFileName(contentDisPosition);
		
		/***Then***/
		assertEquals("contentType.jpg",fileName);
	}

}
