package com.sparrow.orm;

import com.sparrow.constant.DATE_TIME;
import com.sparrow.container.Container;
import com.sparrow.core.spi.ApplicationContext;
import com.sparrow.enums.GENDER;
import com.sparrow.enums.STATUS_RECORD;
import com.sparrow.orm.dao.UserDAO;
import com.sparrow.orm.po.User;
import com.sparrow.utility.DateTimeUtility;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

/**
 * Created by harry on 2018/2/6.
 */
public class UserDaoTest {
    @Test
    public void userTest() {
        JDBCTemplateTest jdbcTemplateTest=new JDBCTemplateTest();
        jdbcTemplateTest.initStruction();
        Container container = ApplicationContext.getContainer();
        container.init("/dao.xml", null);
        UserDAO userDAO = container.getBean("userDao");

        User user = new User();
        user.setUserName("harry");
        user.setBirthday(new Date(DateTimeUtility.parse("2018-02-06", DATE_TIME.FORMAT_YYYY_MM_DD)));
        user.setAvatar("http://www.sparrowzoo.com/avator.jpg");
        user.setCent(10L);
        user.setPassword("123455");
        user.setNickName("nickName");
        user.setUpdateTime(System.currentTimeMillis());
        user.setCreateTime(System.currentTimeMillis());
        user.setSex((byte)GENDER.FEMALE.ordinal());
        user.setStatus((byte)STATUS_RECORD.ENABLE.ordinal());
        Long id = userDAO.insert(user);

        user = userDAO.getEntity(id);
        System.out.println(user.getAvatar());

        List<User> userList = userDAO.query("harry", "nickName", 1, 1);
        System.out.println(userList.size());
        while (true) {
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
            }
        }
    }
}
