package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView modelAndView = new ModelAndView("response/hello")
                .addObject("data","hello!");
        return modelAndView;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        // ModelAndView modelAndView = new ModelAndView("response/hello")
        //        .addObject("data","hello!");
        model.addAttribute("data","hello!");
        return "response/hello";
    }

    @RequestMapping("/response/hello") // 컨트롤러 매핑 == 뷰 논리 이름 -> 그대로 모델에만 data 추가해도 됨
    public void responseViewV3(Model model) {
        // ModelAndView modelAndView = new ModelAndView("response/hello")
        //        .addObject("data","hello!");
        model.addAttribute("data","hello!");
        //return "response/hello";
    }
}
