package kr.or.ddit.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import kr.or.ddit.mvc.view.ExcelDownloadView;
import kr.or.ddit.mvc.view.ProfileDownView;
import kr.or.ddit.mvc.view.ProfileImgView;

@Configuration
@ComponentScan(basePackages = {"kr.or.ddit"}, useDefaultFilters = false,
			includeFilters = {@Filter(type=FilterType.ANNOTATION, classes = {Controller.class, ControllerAdvice.class})})
@EnableWebMvc
public class ApplicationContext extends WebMvcConfigurerAdapter{
	
	//<mvc:default-servlet-handler/> => extends로 상속(WebMvcConfigurerAdapter)
	// 재정의
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	/*
	 <bean id="fileDown" class="kr.or.ddit.mvc.view.ProfileDownView"/>
	<bean id="profileImgView" class="kr.or.ddit.mvc.view.ProfileImgView"/>
	<bean id="excelView" class="kr.or.ddit.mvc.view.ExcelDownloadView"/>
	<bean id="jsonView" class="org.springframework.web.servlet.view.json.MappingJackson2JsonView"/>
	 */
	
	@Bean
	public ProfileDownView fileDown() {
		return new ProfileDownView();
	}
	
	@Bean
	public ProfileImgView profileImgView() {
		return new ProfileImgView();
	}
	
	@Bean
	public ExcelDownloadView excelView() {
		return new ExcelDownloadView();
	}
	
	@Bean
	public MappingJackson2JsonView jsonView() {
		return new MappingJackson2JsonView();
	}
	
	/*
	 <bean class="org.springframework.web.servlet.view.BeanNameViewResolver">
		<property name="order" value="1"></property>
	</bean>
	 */ 
	@Bean // viewResolver를 읽기위한 bean 등록
	public BeanNameViewResolver beanNameVierResolver() {
		BeanNameViewResolver beanNameViewResolver = new BeanNameViewResolver();
		beanNameViewResolver.setOrder(1);
		return beanNameViewResolver;
	}
	
	/*
	 <bean id="tilesConfigurer" class="org.springframework.web.servlet.view.tiles3.TilesConfigurer">
		<property name="definitions">
			<list>
				<value>classpath:kr/or/ddit/config/tiles/tiles-definition.xml</value>
			</list>
		</property>
	</bean>
	 */
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions("classpath:kr/or/ddit/config/tiles/tiles-definition.xml");
		return configurer;
	}
	
	/*
	 <bean class="org.springframework.web.servlet.view.tiles3.TilesViewResolver">
		<property name="order" value="0"></property>
		<property name="viewClass" value="org.springframework.web.servlet.view.tiles3.TilesView"/>
	</bean>
	 */
	@Bean
	public TilesViewResolver tilesViewResolver() {
		TilesViewResolver resolver = new TilesViewResolver();
		resolver.setOrder(0);
		resolver.setViewClass(TilesView.class);
		return resolver;
	}
	
	/*
	 <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="order" value="2"/>
		<property name="prefix" value="/WEB-INF/views/"/>
		<property name="suffix" value=".jsp"/>
	</bean>
	 */
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setOrder(2);
		internalResourceViewResolver.setPrefix("/WEB-INF/views/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
	}
	
	//<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver"/>
	
	@Bean
	public MultipartResolver multipartResolver() {
		MultipartResolver multipartResolver = new CommonsMultipartResolver(); 
		return multipartResolver;
	}
	
	//<bean id="localeResolver" class="org.springframework.web.servlet.i18n.SessionLocaleResolver"/>
	@Bean
	public SessionLocaleResolver localeResolver() {
		return new SessionLocaleResolver();
	}
	
	/*
	 <mvc:interceptors>
		<mvc:interceptor>
			<mvc:mapping path="/**"/>
			<bean class="org.springframework.web.servlet.i18n.LocaleChangeInterceptor">
				<property name="paramName" value="lang"/>
			</bean>
		</mvc:interceptor>
	</mvc:interceptors>
	 */
	@Override //인터셉터 등록
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		
		registry.addInterceptor(localeChangeInterceptor).addPathPatterns("/**");
	}
	
	//<mvc:resources location="/WEB-INF/views/error/" mapping="/resources/**"/>
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/views/error/");
	}
}
