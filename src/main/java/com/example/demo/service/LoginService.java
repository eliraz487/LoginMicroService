package com.example.demo.service;

import com.example.demo.halper.ConnectionSql;
import com.example.demo.vo.TryLoginEntityVO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class LoginService {
    public LoginService() {
    }


    /**
     * execute stored procedures that check if login information is correct and return the user's id and throw exception if he faild
     * @param tryLoginEntityDTO object with the login informtion (ip,mac,header,username,password)
     * @return the user id
     * @throws Exception a error message
     */
    public int loginTry(TryLoginEntityVO tryLoginEntityDTO) throws SQLException, ClassNotFoundException, IllegalAccessException {
        String error = "";
        int id = 0;
        Connection conn = null;
        conn = ConnectionSql.getConntion();
        CallableStatement cStmt = conn.prepareCall("{?=call loginTry(?, ?, ?, ?, ?,?)}");
        cStmt.registerOutParameter(1, java.sql.Types.INTEGER);
        cStmt.setString(2, tryLoginEntityDTO.getIp());
        cStmt.setString(3, tryLoginEntityDTO.getMac());
        cStmt.setString(4, tryLoginEntityDTO.getHeaders());
        cStmt.setString(5, tryLoginEntityDTO.getUserName());
        cStmt.setString(6, tryLoginEntityDTO.getPassword());
        cStmt.registerOutParameter(7, Types.VARCHAR);
        cStmt.execute();
        id = cStmt.getInt(1);
        error = cStmt.getString(7);
        cStmt.close();
        conn.close();


        if (error != null) {
            throw new IllegalAccessException(error);
        }
        return id;
    }
}
