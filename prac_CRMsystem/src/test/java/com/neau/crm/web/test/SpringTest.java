package com.neau.crm.web.test;


import com.neau.crm.web.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        String[] names = context.getBeanDefinitionNames();
        for(String name : names){
            System.out.println(name);
        }
        UserDao userDao = context.getBean("userDao",UserDao.class);
        System.out.println(userDao.selectAllUser());
    }
}
