package izuanqian.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloHtmlController {

    @RequestMapping("/index")
    public String index(ModelMap map){
        map.addAttribute("host", "http://www.github.com/sanlion");
        return "index";
    }
}
