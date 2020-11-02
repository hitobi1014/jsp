package kr.or.ddit.config.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.repository.BoardRepositoryI;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:kr/or/ddit/config/spring/ioc/scope.xml"})
public class IocScopeTest {
	
	private static final Logger logger = LoggerFactory.getLogger(IocScopeTest.class);
	@Autowired
	ApplicationContext context;

	@Resource(name="boardRepository")
	private BoardRepositoryI boardRepository;
	
	@Resource(name="boardRepository")
	private BoardRepositoryI boardRepository2;
	
	@Resource(name="boardRepository_a")
	private BoardRepositoryI boardRepository_a;
	
	@Resource(name="boardRepository_p")
	private BoardRepositoryI boardRepository_p;
	
	@Resource(name="boardRepository_p")
	private BoardRepositoryI boardRepository_p2;
	
	@Test
	public void prototypeTest() {
		/***Given***/
		/***When***/
		for(int i=0; i<10; i++) {
			BoardRepositoryI boardRepository =  context.getBean("boardRepository",BoardRepositoryI.class); // 싱글톤으로 설정
			
			BoardRepositoryI boardRepositoryp =  context.getBean("boardRepository_p",BoardRepositoryI.class); // 프로토타입으로 설정
			logger.debug("singleton-boardRepository : {}, prototype-boardRepository : {}",boardRepository , boardRepositoryp );
		}

		/***Then***/
		assertNotEquals(boardRepository_p, boardRepository_p2);
	}
	
	@Test
	public void singletonTest() {
		/***Given***/
		

		/***When***/

		/***Then***/
		assertEquals(boardRepository, boardRepository2);
		assertNotEquals(boardRepository, boardRepository_a);
		assertNotEquals(boardRepository_a, boardRepository2);
	}

}
