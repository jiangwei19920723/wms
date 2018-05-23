package cn.jcloud.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @author 蒋维
* @date 创建时间：2018年5月2日 下午5:11:58
* @version 1.0
*/
@Controller
public class IndexCotroller {
	@RequestMapping("/")
    public String index() {
        return "redirect:/page/login/login.html";
    }
}
