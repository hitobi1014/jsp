package kr.or.ddit.fileupload.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.InputStream;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import kr.or.ddit.WebTestConfig;

public class FileUploadControllerTest extends WebTestConfig{
								
	@Test
	public void view() throws Exception {
		mockMvc.perform(get("/fileupload/view")).andExpect(status().isOk());
	}
	
	@Test
	public void uploadTest() throws Exception {
		
		InputStream is = getClass().getResourceAsStream("/kr/or/ddit/upload/apeach.png");
//		FileInputStream fis = new FileInputStream("D:\\A_TeachingMaterial\\6.JspSpring\\workspace\\spring\\src\\test\\resources\\kr\\or\\ddit\\upload\\apeach.png");
		MockMultipartFile file = new MockMultipartFile("file", "apeach.png", "image/png", is);
		mockMvc.perform(fileUpload("/fileupload/upload").file(file).param("userid", "브라운")).andExpect(status().isOk());
		
	}

}
