package com.ogoodo.wx.api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

// 正常情况下我们写了5个测试类，我们需要一个一个执行。 
// 打包测试，就是新增一个类，然后将我们写好的其他测试类配置在一起，然后直接运行这个类就达到同时运行其他几个测试的目的。
@RunWith(Suite.class) 
@SuiteClasses({ShiroTest.class, JdbcTest.class}) // , BTest.class, CTest.class
public class ABCSuite {
    // 类中不需要编写代码
}
