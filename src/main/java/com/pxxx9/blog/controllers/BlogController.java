package com.pxxx9.blog.controllers;

import com.pxxx9.blog.models.post;
import com.pxxx9.blog.repo.post_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;


@Controller
public class BlogController {

    //создаем переменную котор будет ссылаться на репозиторий(гдес содержаться методы CRUD для табл пост
    @Autowired
    private post_repository postRepository;



    @GetMapping ("/blog")
    public String blogmain(Model model){
        //здесь будут содержаться все значения, ролученные из таблицы в базе данных
        Iterable<post> posts = postRepository.findAll();// этот метод вытащит все записи из табл пост (нашли/вытащили)
        model.addAttribute("posts",posts );  // передали в модель

        return "blog-main";
    }

    @GetMapping ("/blog/add")
    public String blogadd(Model model){

        return "blog-add";
    }


    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title,
                              @RequestParam String anons,
                              @RequestParam String full_text,
                              Model model){
        post post = new post(title, anons, full_text);
        postRepository.save(post);

        return "redirect:/blog";
    }



    @GetMapping ("/blog/{id}")
    public String blogDetails(@PathVariable(value="id") long id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }

       Optional<post> postt= postRepository.findById(id);
        ArrayList<post> res=new ArrayList<>();
        postt.ifPresent(res::add);

        model.addAttribute("post", res );  // передали в модель

        return "blog-details";
    }


    @GetMapping ("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value="id") long id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }

        Optional<post> postt= postRepository.findById(id);
        ArrayList<post> res=new ArrayList<>();
        postt.ifPresent(res::add);

        model.addAttribute("post", res );  // передали в модель

        return "blog-edit";
    }


    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value="id") long id, @RequestParam String title,
                              @RequestParam String anons,
                              @RequestParam String full_text,
                              Model model) throws Exception {
        post post = postRepository.findById(id).orElseThrow(Exception::new);
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postRepository.save(post);

        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value="id") long id,
                                 Model model) throws Exception {
        post post = postRepository.findById(id).orElseThrow(Exception::new);
        postRepository.delete(post);
        return "redirect:/blog";
    }
    // ПРОВЕРОЧНОЕ ДОБАВЛЕНИЕ
}
