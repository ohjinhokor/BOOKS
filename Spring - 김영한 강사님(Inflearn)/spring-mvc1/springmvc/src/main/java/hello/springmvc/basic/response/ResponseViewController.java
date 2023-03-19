package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseView1() {
        ModelAndView mav = new ModelAndView("hello")
            .addObject("data", "hello!");

        return mav;
    }

    // 반환형이 String인 경우 responseBody가 없으면 메서드가 반환하는 경로에 있는 뷰를 (뷰 리졸버가)찾아서 렌더링한다.
    // 만약 responseBody가 붙어있으면 문자열을 반환함.
    @RequestMapping("/response-view-v2")
    public String responseView2(Model model) {
        model.addAttribute("data", "hello!");
        return "hello";
    }

    // 추천하지는 않는 방법
    @RequestMapping("/hello")
    public void responseView3(Model model) {
        model.addAttribute("data", "hello!");
    }
}
