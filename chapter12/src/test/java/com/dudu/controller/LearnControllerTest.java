package com.dudu.controller;

import com.dudu.domain.User;
import com.dudu.service.LearnService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Description: Controller 单元测试
 * <p>
 * mockMvc.perform 执行一个请求,返回 ResultActions 对象
 * <p>
 * MockMvcRequestBuilders.get(“/user/1”) 构造一个请求，Post请求就用.post方法
 * contentType(MediaType.APPLICATION_JSON_UTF8) 代表发送端发送的数据格式是application/json;charset=UTF-8
 * accept(MediaType.APPLICATION_JSON_UTF8) 代表客户端希望接受的数据类型为application/json;charset=UTF-8
 * session(session) 注入一个session，这样拦截器才可以通过
 * <p>
 * ResultActions.andExpect 添加执行完成后的断言
 * ResultActions.andExpect(MockMvcResultMatchers.status().isOk()) 检查请求的状态响应码是否为200如果不是则抛异常，测试不通过
 * andExpect(MockMvcResultMatchers.jsonPath(“$.author”).value(“嘟嘟MD独立博客”)) 这里jsonPath用来获取author字段比对是否为"嘟嘟MD独立博客",不是就测试不通过
 * ResultActions.andDo 添加一个结果处理器，表示要对结果做点什么事情，比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息
 *
 * @author yangzhaoyunfei yangzhaoyunfei@qq.com
 * @date 2018/05/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest

public class LearnControllerTest {
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private LearnService service;

    private MockMvc mvc;
    private MockHttpSession session;

    /**
     * 准备工作
     */
    @Before
    public void setupMockMvc() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //构建一个MockMvc对象
        session = new MockHttpSession();
        User user = new User("root", "12345678");
        session.setAttribute("user", user); //拦截器那边会判断用户是否登录，所以这里注入一个用户
    }

    /**
     * 新增测试用例
     *
     * @throws Exception
     */
    @Test
    public void add() throws Exception {
        String json = "{\"author\":\"yangzhaoyunfei\",\"title\":\"springdemo\",\"url\":\"http://yangzhaoyunfei.com/\"}";
        mvc.perform(MockMvcRequestBuilders.post("/learn/add")
                .accept(MediaType.APPLICATION_JSON_UTF8)//希望接受的媒体类型
                .content(json.getBytes()) //发送的内容,传json参数
                .session(session)//请求携带的session
        )
                .andExpect(MockMvcResultMatchers.status().isOk())//期望的结果
                .andDo(MockMvcResultHandlers.print());//打印响应
    }

    /**
     * 检索测试用例
     *
     * @throws Exception
     */
    @Test
    public void query() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/learn/resource/1001")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("嘟嘟MD独立博客"))//从结果中解析
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Spring Boot干货系列"))
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 修改测试用例
     *
     * @throws Exception
     */
    @Test
    public void update() throws Exception {
        String json = "{\"id\":1032,\"author\":\"测试修改\",\"title\":\"Spring Boot干货系列\",\"url\":\"http://yangzhaoyunfei.com\"}\n";
        mvc.perform(MockMvcRequestBuilders.post("/learn/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)//传json参数,Set the request body as a UTF-8 String.
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 删除教程测试用例
     *
     * @throws Exception
     */
    @Test
    public void deleteLearn() throws Exception {
        String json = "[1031]";//传入一个json格式的数组
        mvc.perform(MockMvcRequestBuilders.post("/learn/delete")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)//传json参数
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}