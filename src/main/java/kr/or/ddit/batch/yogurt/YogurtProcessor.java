package kr.or.ddit.batch.yogurt;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import kr.or.ddit.batch.yogurt.model.CycleVo;
import kr.or.ddit.batch.yogurt.model.DailyVo;

public class YogurtProcessor implements ItemProcessor<CycleVo, List<DailyVo>>{
	private static final Logger logger = LoggerFactory.getLogger(YogurtProcessor.class);
	//jobLauncher를 실행하면서 두 번째 인자로 넣어준 jobParameter 값을 SPEL을 통해 주입
	//단 jobParameter에 접근하기 위해서는 해당 스프링 빈의 scope를 step으로 지정해야한다
	@Value("#{jobParameters[ym]}")
	private String ym;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	@Override
	public List<DailyVo> process(CycleVo item) throws Exception {
		// 생성 월이 2020년 11월
		// ex) cid=1, pid=100, day=2 ,cnt=3 ...
		// ==> cid=1, pid=100, dt=20201102 ,cnt=3 ...
		// ==> cid=1, pid=100, dt=20201109 ,cnt=3 ...
		// ==> cid=1, pid=100, dt=20201116 ,cnt=3 ...
		// ==> cid=1, pid=100, dt=20201123 ,cnt=3 ...
		// ==> cid=1, pid=100, dt=20201130 ,cnt=3 ...
		
		logger.debug("ym : {}, item : {}",ym,item);
		List<DailyVo> dailyVoList = new ArrayList<DailyVo>();
		
		//Calendar = Calendar.MONTH => 0부터 1월이 시작 , 원하는달 쓰려면 값에서 -1 해줌
		Calendar calendar = Calendar.getInstance(); // 생성자가 없음
		calendar.set(Calendar.YEAR, Integer.parseInt(ym.substring(0, 4))); 	// 연도 설정 
		calendar.set(Calendar.MONTH, Integer.parseInt(ym.substring(4))-1); 	// 월 설정 
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));	// 해당 월에 설정된 마지막날짜를 구할 수 있음
		
		// calendar 2020 11 30
		Date endTime = calendar.getTime();
		String endTimeStr =sdf.format(endTime);
		int endTimeInt = Integer.parseInt(endTimeStr);
		
		// 해당월의 1일로 설정
		calendar.set(Calendar.DAY_OF_MONTH, 1); // 2020 11 01
		Date dt = calendar.getTime();
		String dtStr = sdf.format(dt);
		int dtInt = Integer.parseInt(dtStr);
		
		while(endTimeInt >= dtInt) {
			//calendar 날짜가 item의 애음요일과 같을때만 dailyVo를 생성
			if(item.getDay() == calendar.get(Calendar.DAY_OF_WEEK)) {
				DailyVo dailyVo = new DailyVo();
				dailyVo.setCid(item.getCid());
				dailyVo.setCnt(item.getCnt());
				dailyVo.setPid(item.getPid());
				dailyVo.setDt(sdf.format(calendar.getTime()));
				dailyVoList.add(dailyVo);
			}
			// 다음 날짜로 설정
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1);
			dt = calendar.getTime();
			dtStr = sdf.format(dt);
			dtInt = Integer.parseInt(dtStr);
		}
		return dailyVoList;
	}

}
