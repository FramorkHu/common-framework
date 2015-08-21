package org.commonframwork.web.servlet;

import org.commonframwork.core.Constant;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huyan on 2015/8/21.
 */
@WebServlet("/*")
public class DispatcherServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{


        req.setCharacterEncoding(Constant.UTF_8);
        String method = req.getMethod();
        String url = req.getPathInfo();



    }

}
