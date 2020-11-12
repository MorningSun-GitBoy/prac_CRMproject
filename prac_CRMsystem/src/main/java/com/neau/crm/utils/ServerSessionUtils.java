package com.neau.crm.utils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class ServerSessionUtils {
    private ServerSessionUtils(){}
    private static SqlSessionFactory factory;
    private static ThreadLocal<SqlSession> t = new ThreadLocal<SqlSession>();
    static {
        String resource = "mybatis-config.xml";
        InputStream is = null;
        try{
            is = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        factory = new SqlSessionFactoryBuilder().build(is);
    }
    public static SqlSession getSqlSession(){
        SqlSession session = t.get();
        if(session==null){
            session = factory.openSession(true);
            t.set(session);
        }
        return session;
    }
    public static void toClose(SqlSession session){
        if(session!=null){
            session.close();
            t.remove();
        }
    }
}
