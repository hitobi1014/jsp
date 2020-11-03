package kr.or.ddit.config.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.or.ddit.board.repository.BoardRepository;
import kr.or.ddit.board.repository.BoardRepositoryI;
import kr.or.ddit.board.service.BoardService;

@Configuration
public class JavaSpringConfig {

	//boardRepository, boardService
	//메소드이름 => 스프링 빈 이름
	
	//xml : <bean id="boardRepository" (메소드이름) class="BoardRepository" />
	@Bean
	public BoardRepositoryI boardRepository() {
//		 BoardRepositoryI boardRepository = new BoardRepository();
//		 return boardRepository;
		return new BoardRepository();
	}
	
	//xml : <bean id="boardService" (메소드이름) class="BoardService"/>
	@Bean
	public BoardService boardService() {
		BoardService boardService = new BoardService();
		boardService.setBoardRepository(boardRepository()); //스프링컨테이너가 읽어 new 연산자 한번만 실행함
		
		//아래와 같이 직접 new 연산자를 통해 생성한 객체는 스프링 빈이 아니다
		//@Bean 어노테이션이 붙은 메소드를 호출해야 스프링 컨테이너에서 관리되는 스프링빈을 얻을 수 있다
		//boardService.setBoardRepository(new BoardRepository());
		return boardService;
	}
}
