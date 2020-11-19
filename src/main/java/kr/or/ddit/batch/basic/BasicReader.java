package kr.or.ddit.batch.basic;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class BasicReader implements ItemReader<String>{
	private static final Logger logger = LoggerFactory.getLogger(BasicReader.class);
	private List<String> list;
	private int index;
	
	public BasicReader() {
		list = new ArrayList<String>();
		list.add("brown");
		list.add("sally");
		list.add("cony");
		list.add("ryan");
		list.add("moon");
	}
	//return값이 Processor에게 전달됨
	//더 이상 읽을 데이터가 없을 때 null 리턴 => spring batch 모듈에서 읽을 데이터가 없다고 인식
	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		logger.debug("=====read()=====");
		if(index <list.size()) {
			String item = list.get(index++);
			logger.debug("item : {}",item);
			return item;
		}else {
			index = 0;
			return null;
		}
	}

}
