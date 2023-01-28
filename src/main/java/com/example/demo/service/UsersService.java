package com.example.demo.service;

import com.example.demo.halper.ConnectionSql;
import com.example.demo.vo.UsersEntityUpdateVO;
import com.example.demo.vo.UsersEntityVO;

import java.sql.*;
import java.util.Objects;

public class UsersService {

    public UsersService() {
    }


    public String createUser(UsersEntityVO usersEntityVO) {
        Connection conn;
        CallableStatement cStmt;
        String result;
        try {
             conn = ConnectionSql.getConntion();
            cStmt = conn.prepareCall("{?=call Adduser(?, ?, ?, ?,?)}");
            cStmt.registerOutParameter(1, java.sql.Types.INTEGER);
            cStmt.setString(2, usersEntityVO.getUserName());
            cStmt.setString(3, usersEntityVO.getEmail());
            cStmt.setString(4, usersEntityVO.getCountryCode());
            cStmt.setString(5, usersEntityVO.getPassword());
            cStmt.registerOutParameter(6, Types.VARCHAR);
            cStmt.execute();
            result = cStmt.getString(6);
            cStmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            result = e.toString();
        }
        if(!Objects.equals(result, "")){
            return result;
        }
        return "success ";
    }

    public String editUser(UsersEntityUpdateVO usersEntityUpdateVO) {
        LoginService loginService = new LoginService();
        String result ;

        try {
            int messege = loginService.loginTry(usersEntityUpdateVO.getTryLogin());
        } catch (Exception e) {
            result =e.toString();
            return result;
        }


        Connection conn;

        try {
           conn = ConnectionSql.getConntion();
            CallableStatement cStmt = conn.prepareCall("{?=call EditUser(?, ?,?)}");
            cStmt.registerOutParameter(1, java.sql.Types.INTEGER);
            cStmt.setString(2, usersEntityUpdateVO.getTryLogin().getUserName());
            cStmt.setString(3, usersEntityUpdateVO.getUserName());
            cStmt.registerOutParameter(4, Types.VARCHAR);
            cStmt.execute();
            result = cStmt.getString(4);
            cStmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            result=e.toString();
            return result;
        }
        if(result != null){
            return result;
        }
        return "successes";
    }

    public String editPassword(UsersEntityUpdateVO usersEntityUpdateVO) {
        LoginService loginService = new LoginService();
        String result ;
        try {
            int messege = loginService.loginTry(usersEntityUpdateVO.getTryLogin());
        } catch (Exception e) {
            result=e.toString();
            return result;
        }


        Connection conn;

        try {
           conn = ConnectionSql.getConntion();
            CallableStatement cStmt = conn.prepareCall("{?=call AddPassword(?, ?,?)}");
            cStmt.registerOutParameter(1, java.sql.Types.INTEGER);
            cStmt.setString(2, usersEntityUpdateVO.getTryLogin().getUserName());
            cStmt.setString(3, usersEntityUpdateVO.getPassword());
            cStmt.registerOutParameter(4, Types.VARCHAR);
            cStmt.execute();
            result = cStmt.getString(4);
            cStmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            result =e.toString();
            return result;
        }
        if(result != null){
            return result;
        }

        return "successes";
    }
}
