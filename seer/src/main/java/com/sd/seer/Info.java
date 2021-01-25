package com.sd.seer;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/info")
public class Info extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/x-www-form-urlencoded;charset=UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        int type = Integer.parseInt(req.getParameter("type"));
        String sql = "select name? as name,n_type,sex,race,info? as info,ti_li,gong_ji,fang_yu,te_gong,te_fang,su_du,sum from jing_ling where id=?;";
        Object[] params = {type, type, id};
        resp.getWriter().write(Dao.executeSQL(sql, params));
    }
}
