package com.chenliang.service.user;

import com.chenliang.dao.BaseDao;
import com.chenliang.dao.user.UserDao;
import com.chenliang.dao.user.UserDaoImp;
import com.chenliang.pojo.User;
import com.mysql.cj.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImp implements UserService{

    // 服务层调用Dao层的方法
    // 所以可以new一个Dao的对象来方便后面的Service方法调用Dao内的方法
    UserDao userDao;
    public UserServiceImp() {
        userDao = new UserDaoImp();
    }

    @Override
    public User Login(String userCode, String password) {
        Connection connection = null;
        User user = null;

        connection = BaseDao.getConnection();
        try {
            user = userDao.getLoginUser(connection, userCode, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //在Service层关闭数据库连接，其他资源在Dao层创建，所以在Dao层关闭
            BaseDao.closeResources(connection,null,null);
        }
        return user;
    }

    @Test
    public void Test(){
        UserServiceImp userServiceImp = new UserServiceImp();
        User admin = userServiceImp.Login("1", "123456");

        System.out.println(admin.getUserPassword());
    }
}

