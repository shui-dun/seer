package com.sd.seer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ji-neng")
public class JiNeng extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/x-www-form-urlencoded;charset=UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        String sql = "select * from ji_neng where jing_ling_id=?;";
        Object[] params = {id};
        resp.getWriter().write(Dao.executeSQL(sql, params));
    }
}
