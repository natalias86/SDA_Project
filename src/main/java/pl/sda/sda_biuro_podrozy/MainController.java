package pl.sda.sda_biuro_podrozy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sda.sda_biuro_podrozy.entities.*;
import pl.sda.sda_biuro_podrozy.service.*;

import java.util.List;


@Controller
public class MainController {

    PostService postService;
    HotelService hotelService;
    ContinentService continentService;
    CountryService countryService;
    CityService cityService;

    @Autowired
    public MainController(PostService postService, HotelService hotelService, ContinentService continentService, CountryService countryService, CityService cityService) {
        this.postService = postService;
        this.hotelService = hotelService;
        this.continentService = continentService;
        this.countryService = countryService;
        this.cityService = cityService;
    }

    //TODO: filtering data from dropdown list
    @GetMapping("/homepage")
    public String showMainForm(Model model) {
        List<ContinentEntity> continentsList = continentService.getListOfContinents();
        List<CountryEntity> countriesList = countryService.getListOfCountries();
        List<CityEntity> citiesList = cityService.getListOfCities();
        List<HotelEntity> hotelsList = hotelService.getListOfHotels();
        List<PostEntity> posts = postService.getPublishedPosts();
        model.addAttribute("continentsList", continentsList);
           /*  model.addAttribute("countriesList", countriesList);
           model.addAttribute("citiesList", citiesList);
            model.addAttribute("hotelsList",hotelsList);*/
        model.addAttribute("allPosts", posts);

        return "homePage";
    }


}
