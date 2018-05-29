##assertThat 基本语法
    assertThat( [value], [matcher statement] );
##Matcher 匹配符联合使用
####联合匹配符not和equalTo表示“不等于”
    assertThat( something, not( equalTo( "developer" ) ) ); 

####联合匹配符not和containsString表示“不包含子字符串”
    assertThat( something, not( containsString( "Works" ) ) ); 

####联合匹配符anyOf和containsString表示“包含任何一个子字符串”
    assertThat(something, anyOf(containsString("developer"), containsString("Works")));

##JUnit 4.4 默认提供一些可读的描述性错误信息
    String s = "hello world!"; 
    assertThat( s, anyOf( containsString("developer"), containsString("Works") ) ); 
    // 如果出错后，系统会自动抛出以下提示信息：
    java.lang.AssertionError: 
    Expected: (a string containing "developer" or a string containing "Works") 
    got: "hello world!"
    
#字符相关匹配符
    /**equalTo匹配符断言被测的testedValue等于expectedValue，
    * equalTo可以断言数值之间，字符串之间和对象之间是否相等，相当于Object的equals方法
    */
    assertThat(testedValue, equalTo(expectedValue));
    /**equalToIgnoringCase匹配符断言被测的字符串testedString
    *在忽略大小写的情况下等于expectedString
    */
    assertThat(testedString, equalToIgnoringCase(expectedString));
    /**equalToIgnoringWhiteSpace匹配符断言被测的字符串testedString
    *在忽略头尾的任意个空格的情况下等于expectedString，
    *注意：字符串中的空格不能被忽略
    */
    assertThat(testedString, equalToIgnoringWhiteSpace(expectedString);
    /**containsString匹配符断言被测的字符串testedString包含子字符串subString**/
    assertThat(testedString, containsString(subString) );
    /**endsWith匹配符断言被测的字符串testedString以子字符串suffix结尾*/
    assertThat(testedString, endsWith(suffix));
    /**startsWith匹配符断言被测的字符串testedString以子字符串prefix开始*/
    assertThat(testedString, startsWith(prefix));

    
#
    