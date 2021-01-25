package com.sd.seer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet("/find")
public class Find extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/x-www-form-urlencoded;charset=UTF-8");
        String sql = "select distinct jing_ling_id as id, name3" +
                " from ji_neng" +
                "         join (select id, name3" +
                "               from jing_ling" +
                "               where (name1 like ? or name2 like ? or name3 like ?)" +
                "                 and (id >= ? and id <= ?)" +
                "                 and (sex like ?)" +
                "                 and (race like ?)" +
                "                 and (info1 like ? or info2 like ? or info3 like ?)" +
                "                 and (sum >= ? and sum <= ?)) as temp" +
                "              on ji_neng.jing_ling_id = temp.id" +
                " where name like ? ;";
        String name = "%" + req.getParameter("name") + "%";
        String sex = req.getParameter("sex");
        if (sex.equals("任意")) {
            sex = "%%";
        }
        String info = "%" + req.getParameter("info") + "%";
        Object[] parms = {name, name, name, parseInt(req.getParameter("idmin"), 1),
                parseInt(req.getParameter("idmax"), 865), sex,
                "%" + req.getParameter("race") + "%", info, info, info,
                parseInt(req.getParameter("sum-min"), 0),
                parseInt(req.getParameter("sum-max"), 1000),
                "%" + req.getParameter("ji-neng") + "%"};
        resp.getWriter().write(Dao.executeSQL(sql, parms));
    }

    public static int parseInt(String str, int backup) {
        Pattern pattern = Pattern.compile("^[-+]?[\\d]*$");
        if (pattern.matcher(str).matches() && !str.equals(""))
            return Integer.parseInt(str);
        else return backup;
    }
}
