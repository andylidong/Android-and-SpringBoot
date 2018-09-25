package com.andy.heyi;

import com.andy.heyi.model.customer.Customer;
import com.andy.heyi.service.customer.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * @ClassName com.andy.heyi.HeyiApplocationTest
 * @Description: 基于SpringBoot平台整合Junit分别完成客户端、服务端的单元测试
 * @Author: lidong
 * @CreateDate: 2018/8/16$ 9:22 AM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/8/16$ 9:22 AM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/


@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.andy.heyi.HeyiApplication.class)
public class HeyiApplocationTest {


    /**
     * 模拟mvc测试对象
     */
    private MockMvc mockMvc;
    /**
     * web项目上下文
     */
    @Autowired
    private WebApplicationContext webApplicationContext;


    /**
     * 所有测试方法执行之前执行该方法
     */
    @Before
    public void before() {
        // 获取mockmvc对象实例
        mockMvc = MockMvcBuilders.webAppContextSetup( webApplicationContext ).build();
    }

    /**
     * 测试访问/login
     * 1 perform方法其实只是为了构建一个请求，并且返回ResultActions实例，该实例则是可以获取到请求的返回内容。
     * 2 MockMvcRequestBuilders该抽象类则是可以构建多种请求方式，如：Post、Get、Put、Delete等常用的请求方式，其中参数则是我们需要请求的本项目的相对路径，/则是项目请求的根路径。
     * 3 param方法用于在发送请求时携带参数，当然除了该方法还有很多其他的方法，大家可以根据实际请求情况选择调用。
     * 4 andReturn方法则是在发送请求后需要获取放回时调用，该方法返回MvcResult对象，该对象可以获取到返回的视图名称、返回的Response状态、获取拦截请求的拦截器集合等。
     * 5 我们在这里就是使用到了第4步内的MvcResult对象实例获取的MockHttpServletResponse对象从而才得到的Status状态码。
     * 6 同样也是使用MvcResult实例获取的MockHttpServletResponse对象从而得到的请求返回的字符串内容。【可以查看rest返回的json数据】
     * 7 使用Junit内部验证类Assert判断返回的状态码是否正常为200
     * 8 判断返回的字符串是否与我们预计的一样。
     *
     * @throws Exception
     */
    @Test
    public void testAndy() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(// 1
                        MockMvcRequestBuilders.get( "/api/v1/andy" ) // 2
                        // .param( "name", "admin" ) // 3
                )
                .andReturn();// 4
        int status = mvcResult.getResponse().getStatus(); // 5
        String responseString = mvcResult.getResponse().getContentAsString(); // 6
        Assert.assertEquals( "请求错误", 200, status ); // 7
        Assert.assertEquals( "返回结果不一致", "{\"code\":200,\"data\":\"这是: test , 口头禅: This is a demo for test\",\"msg\":\"success\"}", responseString ); // 8
    }


    @Autowired
    private CustomerService customerService;


    @Test
    public void testCustomer() {
        // 添加一条数据
//        customerService.save( new Customer( "andy", "Lee" ) );
        // 查询全部
        List<Customer> data = customerService.findAll();
        data.stream().forEach( System.out::println );
    }
}
