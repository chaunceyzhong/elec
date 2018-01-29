package cn.itcast.elec.util;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chauncey 2018/1/28.
 */
public class SystemFilter implements Filter{

    /**
     * 存放系统没有session,但是需要访问的url,这些url运行放行.
     */
    private List<String> list = new ArrayList<>();

    @Override public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**每次访问URL连接之前，都要访问的方法*/
    @Override public void doFilter(ServletRequest req, ServletResponse rep,
            FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) rep;
        String path = request.getServletPath();
        this.forwardIndexPage(request, path);
        filterChain.doFilter(request, response);
    }

    @Override public void destroy() {

    }

    /**
     * 跳转到index.jsp页面,执行记住我的代码逻辑.
     * @param request
     * @param path
     */
    private void forwardIndexPage(HttpServletRequest request,String path){
        if("/index.jsp".equals(path)){
            String name = "";
            String password = "";
            String checked = "";
            Cookie[] cookies = request.getCookies();
            if(cookies!=null && cookies.length>0){
                for(Cookie cookie : cookies){
                   if("name".equals(cookie.getValue())){
                       name = cookie.getValue();
                       try {
                           name = URLDecoder.decode(name,"UTF-8");
                       } catch (UnsupportedEncodingException e) {
                           e.printStackTrace();
                       }
                       checked = "checked";
                   }
                   if("password".equals(cookie.getValue())){
                       password = cookie.getValue();
                   }
                }
            }
            request.setAttribute("name", name);
            request.setAttribute("password", password);
            request.setAttribute("checked", checked);
        }
    }
}
