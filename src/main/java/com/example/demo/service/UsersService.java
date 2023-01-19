package com.example.demo.service;

import com.example.demo.vo.UsersEntityUpdateVO;
import com.example.demo.vo.UsersEntityVO;

import java.sql.*;

public class UsersService {

    public UsersService() {
    }

    public String createUser(UsersEntityVO usersEntityVO) {
        Connection conn = null;
        CallableStatement cStmt = null;
        String result;
        String error = "";
        try {
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Login", "sa", "1234");
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
        } catch (SQLException e) {
            result = e.toString();
        }
        if(result != ""){
            return result;
        }
        return "success ";
    }

    public String editUser(UsersEntityUpdateVO usersEntityUpdateVO) {
        LoginService loginService = new LoginService();

        String messege = loginService.loginTry(usersEntityUpdateVO.getTryLogin());
        if (messege != "successes") {
            return usersEntityUpdateVO.getUserName();
        }

        Connection conn = null;
        String result ;
        String error = "";
        try {
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Login", "sa", "1234");
            CallableStatement cStmt = conn.prepareCall("{?=call EditUser(?, ?,?)}");
            cStmt.registerOutParameter(1, java.sql.Types.INTEGER);
            cStmt.setString(2, usersEntityUpdateVO.getTryLogin().getUserName());
            cStmt.setString(3, usersEntityUpdateVO.getUserName());
            cStmt.registerOutParameter(4, Types.VARCHAR);
            cStmt.execute();
            result = cStmt.getString(4);
            cStmt.close();
            conn.close();
        } catch (SQLException e) {
            result=e.toString();
        }
        if(result != null){
            return result;
        }
        return "successes";
    }

    public String editPassword(UsersEntityUpdateVO usersEntityUpdateVO) {
        LoginService loginService = new LoginService();

        String messege = loginService.loginTry(usersEntityUpdateVO.getTryLogin());
        if (messege != "successes") {
            return messege;
        }

        Connection conn = null;
        String result ;
        try {
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Login", "sa", "1234");
            CallableStatement cStmt = conn.prepareCall("{?=call AddPassword(?, ?,?)}");
            cStmt.registerOutParameter(1, java.sql.Types.INTEGER);
            cStmt.setString(2, usersEntityUpdateVO.getTryLogin().getUserName());
            cStmt.setString(3, usersEntityUpdateVO.getPassword());
            cStmt.registerOutParameter(4, Types.VARCHAR);
            cStmt.execute();
            result = cStmt.getString(4);
            cStmt.close();
            conn.close();
        } catch (SQLException e) {
            result =e.toString();
        }
        if(result != null){
            return result;
        }

        return "successes";
    }
}
