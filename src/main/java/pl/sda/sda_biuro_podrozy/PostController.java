package pl.sda.sda_biuro_podrozy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.sda_biuro_podrozy.dto.PostDto;
import pl.sda.sda_biuro_podrozy.entities.ContinentEntity;
import pl.sda.sda_biuro_podrozy.entities.HotelEntity;
import pl.sda.sda_biuro_podrozy.entities.PostEntity;
import pl.sda.sda_biuro_podrozy.service.HotelService;
import pl.sda.sda_biuro_podrozy.service.PostService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    HotelService hotelService;

    @GetMapping("/post/admin/add")
    public String showPostForm(Model model) {
        List<HotelEntity> hotelsList = hotelService.getListOfHotels();
        model.addAttribute("hotelsList", hotelsList);
        model.addAttribute("addPost", new PostEntity());
        return "posts/admin/addPost";
    }

    //TODO: add post doesn't work
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
        return "redirect:/homepage";
    }

    @GetMapping("/post/{postId}")

    public String showSinglePost(@PathVariable String postId, Model model) {//Spring pozwala nam w mapowaniu wykorzystać tzw. path variables->skazujemy że w pewnym miejscu adresu URL będzie informacja którą chcemy uzyskać
        //  PostDto postDto = new PostDto(Long.valueOf(postId), "Tytul posta ktory bedzie wyswietlony", "tekst posta");
        PostDto postDto = postService.getSinglePost(Integer.valueOf(postId));
        model.addAttribute("post", postDto);  //Model przekazuje  do widoku nasz komunikat za pomoca klucza (nazwy atrybutu)
        return "posts/singlePost";
    }

    @GetMapping("/post/admin/edit")
    public String showEditPostForm(Model model) {

        return "posts/admin/editPost";
    }

    @GetMapping("/post/admin/remove")
    public String showRemovePostForm(Model model) {

        return "posts/admin/removePost";
    }
}
