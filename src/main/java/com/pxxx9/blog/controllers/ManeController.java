package com.pxxx9.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ManeController {

    @GetMapping("/") // при переходе на главн страницу будет вызываться функц (home)
    public String home( Model model) {  // в этом методе мы просто вызыв определн шаблон (хтмл страницу)
        model.addAttribute("title", "главная страница");
        return "home";
    }

    //здесь мы прописали обработку главной страницы. При переходе на
    // гл страницу у нас будет вызыв метод home.
    @GetMapping("/about") // при переходе на главн страницу будет вызываться функц (home)
    public String about( Model model) {  // в этом методе мы просто вызыв определн шаблон (хтмл страницу)
        model.addAttribute("title", "страница про нас");
        return "about";
    }

}
