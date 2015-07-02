package mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//-->
@Controller
@RequestMapping("/")
public class MyController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        // Добавляем аттрибут ${message} для hello.jsp
        model.addAttribute("message", "Добро пожаловать на наш сайт!");
        model.addAttribute("a", 2);
        model.addAttribute("b", 12);
        return "hello";
    }

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public String add(ModelMap model, @RequestParam(value = "a", required = false) Integer a,
                      @RequestParam("b") int b) {
        if( a == null )
            a = 123;
        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("sum", a + b);
        return "hello2";
    }
}
//<--