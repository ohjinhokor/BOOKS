package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;


/**
 *  1. 파라미터 전송 기능
 *  http://localhost:8080/request-param?username=hello&age=20
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {


    //hello-form.html로부터의 요청을 통해 알 수 있듯이 request는 get뿐만 아니라, post로 넘어오는 값도 받을 수 있다.
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {

        System.out.println("전체 파라미터 조회 시작");

//        request.getParameterNames().asIterator()
//                .forEachRemaining(paramName-> System.out.println(paramName + "=" + request.getParameter(paramName)));
        Enumeration<String> paramEnumeration = request.getParameterNames();
        while (paramEnumeration.hasMoreElements()) {
            String name = paramEnumeration.nextElement();
            String parameter = request.getParameter(name);
            System.out.println("name = " + name+ " parameter = " + parameter);
        }
        System.out.println("전체 파라미터 조회 끝 \n");

        System.out.println("단일 파라미터 조회");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println();

        System.out.println("이름이 같은 복수 파라미터");
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("username = " + name);
        }

        response.getWriter().write("ok");
    }
}
