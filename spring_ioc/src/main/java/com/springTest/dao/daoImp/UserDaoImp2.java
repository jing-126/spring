package com.springTest.dao.daoImp;

import com.springTest.dao.UserDao;
import com.springTest.domain.User;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class UserDaoImp2 implements UserDao {
//    集合数据类型注入
    private List<String> list;
    private Map<String, User> map;
    private Properties prop;

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setMap(Map<String, User> map) {
        this.map = map;
    }

    public void setProp(Properties prop) {
        this.prop = prop;
    }

    @Override
    public void save() {
        System.out.println(list);
        System.out.println(map);
        System.out.println(prop);
        System.out.println("userDaoImp2....");
    }
}
