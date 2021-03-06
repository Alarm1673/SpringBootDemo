package com.zzl;

import com.zzl.bean.BlogProperties;
import com.zzl.bean.User;
import com.zzl.controller.HelloController;
import com.zzl.controller.UserController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * 单元测试的目的:
 * 简单的来说就是在我们增加或者修改一些代码以后对所有逻辑的一个检测,
 * 尤其是在我们后期修改后(不论是增加新功能,还是修复bug),都可以做到重新测试的工作。
 * 以减少我们在发布的时候出现更过甚至是之前已解决的问题再次重现。
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MockServletContext.class)
@ContextConfiguration(classes={BlogProperties.class})
public class DemoApplicationTests {

	private static final Log log = LogFactory.getLog(DemoApplicationTests.class);

	private MockMvc mvc;

	@Autowired
	private BlogProperties blogProperties;

	//在每个测试方法执行前,setUp()都会执行一遍,初始化mvc对象
	@Before
	public void setUp() throws Exception {
//		mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
		mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
	}

	@Test
	public void getHello() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Hello World")));
	}

	/**
	 * 读取properties文件
	 * @throws Exception
     */
	@Test
	public void getBlog()throws Exception{
		Assert.assertEquals(blogProperties.getName(), "zzl");
		Assert.assertEquals(blogProperties.getTitle(), "Spring Boot");

		log.info("随机数测试输出：");
		log.info("随机字符串 : " + blogProperties.getValue());
		log.info("随机int : " + blogProperties.getNumber());
		log.info("随机long : " + blogProperties.getBignumber());
		log.info("随机10以下 : " + blogProperties.getTest1());
		log.info("随机10-20 : " + blogProperties.getTest2());

	}

	@Test
	public void getUser()throws Exception{
		RequestBuilder requestBuilder = null;

		/**
		 * test get request, return []
		 * attention get() import static MockMvcRequestBuilders.*
		 * Explain:
		 * get(url): 创建一个get请求,
		 * perform(): 执行一个RequestBuilder请求,会自动执行SpringMvc的流程并映射到相应的控制器执行处理。
		 * get(): 声明发送一个get请求的方法。根据uri模板和uri变量值得到一个GET请求方式,另外还提供了其他的请求方法:比如 post, put, delete等
		 * param(): 添加request的参数
		 * andExpect(): 添加ResultMatcher验证规则,验证控制器执行完成后结果是否正确(对返回的数据进行的判断)。
		 * andDo(): 添加ResultHandler结果处理器,比如调试时打印结果到控制台(对返回的数据进行的判断)。
		 * andReturn(): 最后返回相应的MvcResult;然后进行自定义验证,进行下一步的异步处理(对返回的数据进行的判断)。
		 *
		 */
		requestBuilder = get("/users/");
		String strGet = mvc.perform(requestBuilder)
				.andExpect(status().isOk()) //返回的状态码是200
				.andExpect(content().string(equalTo("[]")))  //判断返回内容是否相等
				.andDo(print())  //打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();//将返回的数据转换成字符串
		System.out.println("strGet :" + strGet);

		requestBuilder = post("/users/add")
				.param("id","1")
				.param("name","zzl")
				.param("age","24");
		String strPost = mvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("success")))
				.andDo(print())
				.andReturn().getResponse().getContentAsString();
		System.out.println("strPost:" + strPost);


		requestBuilder = get("/users/");
		String strGet2 = mvc.perform(requestBuilder)
				.andExpect(status().isOk()) //返回的状态码是200
				.andDo(print())  //打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();//将返回的数据转换成字符串
		System.out.println("strGet2 :" + strGet2);

		requestBuilder = put("/users/update");
		// 4、put修改id为1的user
		requestBuilder = put("/users/1")
				.param("name", "测试")
				.param("age", "25");
		String strPut = mvc.perform(requestBuilder)
				.andExpect(content().string(equalTo("success")))
				.andReturn().getResponse().getContentAsString();
		System.out.println("strPut :" + strPut);

		requestBuilder = delete("/users/1");
		String strDel = mvc.perform(requestBuilder)
				.andExpect(content().string(equalTo("success")))
				.andReturn().getResponse().getContentAsString();
		System.out.println("strDel :" + strDel);

		requestBuilder = get("/users/");
		String strGet3 = mvc.perform(requestBuilder)
				.andExpect(status().isOk()) //返回的状态码是200
				.andDo(print())  //打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString();//将返回的数据转换成字符串
		System.out.println("strGet3 :" + strGet3);


	}



//	@Test
//	public void contextLoads() {
//	}

}
