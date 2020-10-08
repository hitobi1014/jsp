package kr.or.ddit.cookie;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class CookieSplitTest {

	@Test
	public void getCookieValueSuccessTest() {
		/***Given***/
		CookieSplit cookieSplit = new CookieSplit();

		/***When***/
		String cookieValue = cookieSplit.getCookieValue("USERNM");
		/***Then***/
		assertEquals("brown", cookieValue);
	}
	
	@Test
	public void getCookieValueFailTest() {
		/***Given***/
		CookieSplit cookieSplit = new CookieSplit();
		
		/***When***/
		String cookieValue = cookieSplit.getCookieValue("PASSWORD"); // 해당하는 쿠키정보가 없으므로 공백으로 출력
		/***Then***/
		assertEquals("", cookieValue);
	}

	
}
