package com.chenliang.dao.user;

import com.chenliang.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDao {

    User getLoginUser(Connection connection,String userCode,String userPassword) throws SQLException;
}
