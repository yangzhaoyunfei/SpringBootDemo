package com.dudu.service;

import com.dudu.domain.LearnResource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;

/**
 * Description: Service层单元测试,比较简单,使用上面两个注解就好了
 *
 * @author selfimpr  tangzw@zjbdos.com
 * @date 2018/05/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LearnServiceTest {

    /**
     * 注入要测试的对象
     */
    @Autowired
    private LearnService service;

    @Test
    public void queryLearnResouceList() {
    }

    @Test
    public void deleteBatch() {
    }

    @Test
    public void addBatch() {
    }

    @Test
    public void getLearn() {
        LearnResource learnResource = service.selectByKey(1001L);
        Assert.assertThat(learnResource.getAuthor(), is("嘟嘟MD独立博客"));
    }

    /**
     * 事务管理,测试回滚
     * 单元个测试的时候如果不想造成垃圾数据，可以开启事物功能，记在方法或者类头部添加@Transactional注解即可
     * 测试完数据就会回滚了，不会造成垃圾数据。如果你想关闭回滚，只要加上@Rollback(false)注解
     * 有时候会发现加了注解@Transactional 也不会回滚，那么你就要查看一下你的默认引擎是不是InnoDB，如果不是就要改成InnoDB
     */
    @Test
    @Transactional//默认测试完会回滚,如果要关闭,配合注解@Rollback(false)一起使用
    public void add() {
        LearnResource bean = new LearnResource();
        bean.setTitle("回滚用例");
        bean.setUrl("http://yangzhaoyunfei.com");
        bean.setAuthor("测试回滚");
        service.save(bean);
//        bean.setAuthor("测试回滚xxxxxxxxxxxxxxxxxxxxx");
//        service.save(bean);

    }
}