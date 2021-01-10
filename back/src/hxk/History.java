package hxk;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;

@WebServlet("/history")
public class History extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/x-www-form-urlencoded;charset=UTF-8");
        String[] ids = getCookie(req, "history-id").split("-");
        if (ids.length != 0) {
            String sql = Dao.createArrayQuery("select id, name3 from jing_ling where id in (", ids);
            String json = Dao.executeSQL(sql, ids);
            resp.getWriter().write(json);
        } else {
            resp.getWriter().write("{}");
        }
    }

    public String getCookie(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName()))
                    return cookie.getValue();
            }
        }
        return "";
    }
}