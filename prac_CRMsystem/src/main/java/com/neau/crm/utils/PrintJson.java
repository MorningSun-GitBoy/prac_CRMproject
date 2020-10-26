package com.neau.crm.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PrintJson {
    public static void printJsonFlag(HttpServletResponse rep,boolean flag){
        Map<String,Boolean> map = new HashMap<String,Boolean>();
        map.put("success",flag);

        ObjectMapper om = new ObjectMapper();
        try{
            String json = om.writeValueAsString(map);
            rep.getWriter().print(json);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void printJsonObj(HttpServletResponse rep,Object obj){
        ObjectMapper om = new ObjectMapper();
        try{
            String json = om.writeValueAsString(obj);
            rep.getWriter().print(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
