package com.dudu;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Description: assertThat 基本语法
 *
 * @author selfimpr  tangzw@zjbdos.com
 * @date 2018/05/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class assertThatDemo {
    private Object object=new Object();
    private Object testedValue=new Object();
    private Object expectedValue=new Object();


    /**
     * 数值相关匹配符
     */
    public void numerical() {
        Double testedDouble = 1.0;
        Float testedNumber = 1.0f;


        /**closeTo匹配符断言被测的浮点型数testedDouble在20.0¡À0.5范围之内*/
        assertThat(testedDouble, closeTo(20.0, 0.5));

        /**greaterThan匹配符断言被测的数值testedNumber大于16.0*/
        assertThat(testedNumber, greaterThan(16.0f));

        /** lessThan匹配符断言被测的数值testedNumber小于16.0*/
        assertThat(testedNumber, lessThan(16.0f));

        /** greaterThanOrEqualTo匹配符断言被测的数值testedNumber大于等于16.0*/
        assertThat(testedNumber, greaterThanOrEqualTo(16.0f));

        /** lessThanOrEqualTo匹配符断言被测的testedNumber小于等于16.0*/
        assertThat(testedNumber, lessThanOrEqualTo(16.0f));
    }
    /**
     *一般匹配符
     */
public void generic(){
    Integer testedNumber=3;
    String testedString = "yangzhaoyunfei";
    String expectedString = "yangzhaoyunfei";
    class Cheddar{
        private Cheddar() {
        }
    }
    Cheddar testedObject=new Cheddar();

    /**nullValue()匹配符断言被测object的值为null*/
    assertThat(object,nullValue());

    /**notNullValue()匹配符断言被测object的值不为null*/
    assertThat(object,notNullValue());

    /**is匹配符断言被测的object等于后面给出匹配表达式*/

    assertThat(testedString, is(equalTo(expectedValue)));

    /**is匹配符简写应用之一，is(equalTo(x))的简写，断言testedValue等于expectedValue*/
    assertThat(testedValue, is(expectedValue));

    /**is匹配符简写应用之二，is(instanceOf(SomeClass.class))的简写，断言testedObject为Cheddar的实例*/
    assertThat(testedObject, is(Cheddar.class));

    /**not匹配符和is匹配符正好相反，断言被测的object不等于后面给出的object*/

    assertThat(testedString, not(expectedString));

    /**allOf匹配符断言符合所有条件，相当于“与”（&&）*/
    assertThat(testedNumber, allOf( greaterThan(8), lessThan(16) ) );

    /**anyOf匹配符断言符合条件之一，相当于“或”（||）*/
    assertThat(testedNumber, anyOf( greaterThan(16), lessThan(8) ) );
}

    /**
     * 集合相关匹配符
     */
    public void collection(){
        class A implements Iterable{
            @Override
            public Iterator iterator() {
                return null;
            }
        }
    Map<String,String> mapObject=new HashMap<>();
    A iterableObject=new A();
    Object element=new A();
    Object value=new Object();

    /**hasEntry匹配符断言被测的Map对象mapObject含有一个键值为"key"对应元素值为"value"的Entry项*/
    assertThat(mapObject, hasEntry("key", "value" ) );
    /**hasItem匹配符表明被测的迭代对象iterableObject含有元素element项则测试通过*/
    assertThat(iterableObject, hasItem (element));
    /** hasKey匹配符断言被测的Map对象mapObject含有键值“key”*/
    assertThat(mapObject, hasKey ("key"));
    /** hasValue匹配符断言被测的Map对象mapObject含有元素值value*/
    assertThat(mapObject, hasValue(value));
}
}