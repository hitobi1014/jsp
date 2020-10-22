package kr.or.ddit.fileupload;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUploadUtilTest {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtilTest.class);
	@Test
	public void getFileNameTest() {
		/***Given***/
		String contentDisPosition = "form-data; name=\"img\"; filename=\"\"";

		/***When***/
		String fileName=FileUploadUtil.getFileName(contentDisPosition);
		
		/***Then***/
		assertEquals("",fileName);
	}

	@Test
	public void UUIDTest() {
		/***Given***/
		
		/***When***/
		String uuid = UUID.randomUUID().toString();
		logger.debug("uuid : {}",uuid);
		/***Then***/
	}
	
	@Test
	public void getExtenstionTest() {
		/***Given***/
		String filename = "sally.png";
		/***When***/
		String extension = FileUploadUtil.getExtension(filename);
		/***Then***/
		assertEquals("png", extension);
	}
}
