package kr.or.ddit.board.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.repository.BoardRepositoryI;

// <bean id="boardService" /> 동일
@Service("boardService")
public class BoardService implements BoardServiceI{
	
	@Resource(name="boardRepository")
	private BoardRepositoryI boardRepository;

	public BoardService() {
		
	}
	
	public BoardService(BoardRepositoryI boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	public BoardRepositoryI getBoardRepository() {
		return boardRepository;
	}

	public void setBoardRepository(BoardRepositoryI boardRepository) {
		this.boardRepository = boardRepository;
	}

	@Override
	public BoardVo getBoard(int boardNo) {
		return boardRepository.getBoard(boardNo);
	}
	
}
