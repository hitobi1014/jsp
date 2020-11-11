package kr.or.ddit.config.ioc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// xml 대신 java에서 어노테이션을 이용하여 bean을 등록하는 방법
@Configuration
@ComponentScan(basePackages = {"kr.or.ddit.board"}) 
public class JavaSpringScanConfig {

}
