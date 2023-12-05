package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        // hello.html로 데이터 전달
        // attributeName: key, attributeValue: value
        model.addAttribute("data", "hello!!");
        // templates 폴더에 있는 hello라는 model에 가서 렌더링
        return "hello";
    }
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }
    @GetMapping("hello-string")
    @ResponseBody   // http 프로토콜에 있는 바디 부분을 그대로 보여준다
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
        // return "<html> hello " + name + "</html>" 이랑 똑같다! 템플릿 엔진이 변환 해준 것
    }
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

}
