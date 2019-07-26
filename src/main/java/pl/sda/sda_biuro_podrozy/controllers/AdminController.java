package pl.sda.sda_biuro_podrozy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.sda_biuro_podrozy.dto.CityDto;
import pl.sda.sda_biuro_podrozy.dto.CountryDto;
import pl.sda.sda_biuro_podrozy.dto.HotelDto;
import pl.sda.sda_biuro_podrozy.dto.PostDto;
import pl.sda.sda_biuro_podrozy.entities.*;
import pl.sda.sda_biuro_podrozy.repository.PostRepository;
import pl.sda.sda_biuro_podrozy.service.*;


import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController {

    CountryService countryService;
    ContinentService continentService;
    CityService cityService;
    HotelService hotelService;
    PostService postService;
    PostRepository postRepository;

    @Autowired
    public AdminController(HotelService hotelService, CityService cityService, CountryService countryService, ContinentService continentService, PostService postService, PostRepository postRepository) {
        this.hotelService = hotelService;
        this.cityService = cityService;
        this.countryService = countryService;
        this.continentService = continentService;
        this.postService = postService;
        this.postRepository = postRepository;
    }

    @GetMapping("/post/admin/adminpanel")
    public String showAdminPanel(Model model) {
        List<PostEntity> posts = postService.getPublishedPosts();
        model.addAttribute("allPosts", posts);
        return "posts/admin/adminPanel";
    }

    @GetMapping("/post/admin/addcountry")
    public String showAddCountryForm(Model model) {
        List<ContinentEntity> continentsList = continentService.getListOfContinents();
        model.addAttribute("continentsList", continentsList);
        model.addAttribute("addCountry", new CountryEntity());
        return "posts/admin/addCountry";
    }

    @PostMapping("/post/admin/addcountry")
    public String addCountry(@ModelAttribute @Valid CountryDto countryDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "posts/admin/addCountry";
        }
        model.addAttribute("addCountry", countryDto);
        countryService.addCountry(countryDto);
        return "redirect:/post/admin/adminpanel";
    }

    @GetMapping("/post/admin/addcity")
    public String showAddCityForm(Model model) {
        List<CountryEntity> countriesList = countryService.getListOfCountries();
        model.addAttribute("countriesList", countriesList);
        model.addAttribute("addCity", new CityEntity());
        return "posts/admin/addCity";
    }

    @PostMapping("/post/admin/addcity")
    public String addCity(@ModelAttribute @Valid CityDto cityDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "posts/admin/addCity";
        }
        model.addAttribute("addCity", cityDto);
        cityService.addCity(cityDto);
        return "redirect:/post/admin/adminpanel";
    }

    @GetMapping("/post/admin/addhotel")
    public String showAddHotelForm(Model model) {
        List<CityEntity> citiesList = cityService.getListOfCities();
        model.addAttribute("citiesList", citiesList);
        model.addAttribute("addHotel", new HotelEntity());
        return "posts/admin/addHotel";
    }

    @PostMapping("/post/admin/addhotel")
    public String addHotel(@ModelAttribute @Valid HotelDto hotelDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "posts/admin/addHotel";
        }
        model.addAttribute("addHotel", hotelDto);
        hotelService.addHotel(hotelDto);
        return "redirect:/post/admin/adminpanel";
    }

    @GetMapping("/post/admin/delete/{postId}")
    public String deletePost(@PathVariable("postId") String postId, Model model) {
        PostEntity postEntity = postRepository.findById(Integer.valueOf(postId))
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + postId));
        postRepository.delete(postEntity);
        // model.addAttribute("postToDelete", userRepository.findAll());
        return "redirect:/post/admin/adminpanel";
    }

    //TODO: update -> dropdownlist and date do not work
    @GetMapping("/post/admin/edit/{postId}")
    public String editPost(@PathVariable("postId") String postId, Model model) {
        PostEntity postEntity = postRepository.findById(Integer.valueOf(postId))
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + postId));
        model.addAttribute("editPost", postEntity);
        return "posts/admin/editPost";
    }

}
