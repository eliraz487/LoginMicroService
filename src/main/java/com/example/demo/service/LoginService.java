package com.example.demo.service;

import com.example.demo.vo.TryLoginEntityVO;

import java.sql.*;

public class LoginService {
    public LoginService() {
    }

    public String loginTry(TryLoginEntityVO tryLoginEntityDTO) {
        String result = "";
        String error = "";
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Login", "sa", "1234");
            CallableStatement cStmt = conn.prepareCall("{?=call loginTry(?, ?, ?, ?, ?,?)}");
            cStmt.registerOutParameter(1, java.sql.Types.INTEGER);
            cStmt.setString(2, tryLoginEntityDTO.getIp());
            cStmt.setString(3, tryLoginEntityDTO.getMac());
            cStmt.setString(4, tryLoginEntityDTO.getHeaders());
            cStmt.setString(5, tryLoginEntityDTO.getUserName());
            cStmt.setString(6, tryLoginEntityDTO.getPassword());
            cStmt.registerOutParameter(7, Types.VARCHAR);
            cStmt.execute();
            result = cStmt.getString(7);
            cStmt.close();
            conn.close();

        } catch (SQLException | ClassNotFoundException e) {
            error = e.toString();
        }
        if(result != null){
            return result;
        }
        return "successes";
    }
}
