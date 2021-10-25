package com.sd.seer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getid")
public class GetId extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sql = "select id from jing_ling;";
        Object[] parms = {};
        String json = Dao.executeSQL(sql, parms);
        resp.getWriter().write(json);
    }
}
