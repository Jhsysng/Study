package hello.thymeleafbasic.basic;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/basic")
public class BasicController {
    @GetMapping("text-basic")
    public String textBasic(Model model){
        model.addAttribute("data", "Hello Spring");
        return "basic/text-basic";
    }

    @GetMapping("text-unescaped")
    public String textUnescaped(Model model){
        model.addAttribute("data", "Heelo <b> Spring!</b>");
        return "basic/text-unescaped";
    }

    @GetMapping("/basic-objects")
    public String basicObjects(HttpSession httpSession) {
        httpSession.setAttribute("sessionData", "Hello Session");

        return "basic/basic-objects";
    }

    @GetMapping("/date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }

    @GetMapping("/literal")
    public String literal(Model model){
        model.addAttribute("data", "Spring!");
        return "basic/literal";
    }

    @GetMapping("/operation")
    public String operation(Model model) {
        model.addAttribute("nullData", null);
        model.addAttribute("data", "Spring!");

        return "basic/operation";
    }

    @GetMapping("/attribute")
    public String attribute() {
        return "basic/attribute";
    }

    @GetMapping("/each")
    public String each(Model model){
        addUser(model);

        return "basic/each";
    }

    @GetMapping("/condition")
    public String condtion(Model model){
        addUser(model);
        return "basic/condition";
    }

    @GetMapping("/comments")
    public String commets(Model model){
        return "basic/comments";
    }

    @GetMapping("/block")
    public String block(Model model){
        addUser(model);

        return "basic/block";
    }

    @GetMapping("/javascript")
    public String javascript(Model model){
        model.addAttribute("user", new User("UserA", 10));
        addUser(model);

        return "basic/javascript";
    }

    private void addUser(Model model){
        List<User> list = new ArrayList<>();
        list.add(new User("UserA", 10));
        list.add(new User("UserB", 20));
        list.add(new User("UserC", 30));

        model.addAttribute("users", list);
    }

    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "Hello " + data;
        }
    }

    @Data
    @AllArgsConstructor
    public class User {
        private String username;
        private int age;
    }
}
