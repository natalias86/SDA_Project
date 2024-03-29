package pl.sda.sda_biuro_podrozy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.sda_biuro_podrozy.dto.PostDto;
import pl.sda.sda_biuro_podrozy.entities.HotelEntity;
import pl.sda.sda_biuro_podrozy.entities.PostEntity;
import pl.sda.sda_biuro_podrozy.repository.PostRepository;
import pl.sda.sda_biuro_podrozy.service.HotelService;
import pl.sda.sda_biuro_podrozy.service.PostService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PostController {

   private PostService postService;
   private HotelService hotelService;
   private PostRepository postRepository;
    @Autowired
    public PostController(PostService postService, HotelService hotelService, PostRepository postRepository) {
        this.postService = postService;
        this.hotelService = hotelService;
        this.postRepository = postRepository;
    }

    @GetMapping("/post/admin/add")
    public String showPostForm(Model model) {
        List<HotelEntity> hotelsList = hotelService.getListOfHotels();
        model.addAttribute("hotelsList", hotelsList);
        model.addAttribute("addPost", new PostEntity());
        return "posts/admin/addPost";
    }

    @PostMapping("/post/admin/add")
    public String addPost(@ModelAttribute("addPost") @Valid PostDto postDto,
                          BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("has some errors in 'addPost'");
            return "/posts/admin/addPost";
        }
        model.addAttribute("addPost", postDto);
        postService.addPost(postDto);
        return "redirect:/post/admin/adminpanel";
    }

    @GetMapping("/post/{postId}")

    public String showSinglePost(@PathVariable String postId, Model model) {

        PostDto postDto = postService.getSinglePost(Integer.valueOf(postId));
        model.addAttribute("post", postDto);
  //      model.addAttribute("addItem", new ItemEntity());
        return "posts/singlePost";
    }

}
