package pl.sda.sda_biuro_podrozy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.sda_biuro_podrozy.dto.CityDto;
import pl.sda.sda_biuro_podrozy.dto.HotelDto;
import pl.sda.sda_biuro_podrozy.dto.PostDto;
import pl.sda.sda_biuro_podrozy.entities.*;
import pl.sda.sda_biuro_podrozy.service.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


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
  /*  @GetMapping("/homepage")
    public String showMainForm(Model model) {
        List<ContinentEntity> continentsList = continentService.getListOfContinents();
        List<CountryEntity> countriesList = countryService.getListOfCountries();
        List<CityEntity> citiesList = cityService.getListOfCities();
       //Set<Integer> hotelsListByStandard = hotelService.getListOfHotels().stream().map(p->p.getStandard()).distinct().collect(Collectors.toList());

        List<PostEntity> posts = postService.getPublishedPosts();
        model.addAttribute("continentsList", continentsList);
        model.addAttribute("countriesList", countriesList);
        model.addAttribute("citiesList", citiesList);
      //  model.addAttribute("hotelsListByStandard",hotelsListByStandard);
        model.addAttribute("allPosts", posts);

        return "homePage";
    }
*/
    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    static class SelectedCriteriasForm {
        private Integer continentId;
        private Integer countryId;
        private Integer cityId;

        public SelectedCriteriasForm(Integer continentId, Integer countryId, Integer cityId) {
            this.continentId = continentId;
            this.countryId = countryId;
            this.cityId = cityId;
        }
    }

    @RequestMapping(value = "/homepage", method = {RequestMethod.GET})
    public String showMainForm(@ModelAttribute("criteriaForm") SelectedCriteriasForm form, Model model) {

        List<ContinentEntity> continentsList = continentService.getListOfContinents();
        List<CountryEntity> countriesList;
        if (form.getContinentId() != null) {
           // todo load country list by continent id
            countriesList = Arrays.asList(countryService.getListOfCountries().iterator().next());
        } else {
             countriesList = countryService.getListOfCountries();
        }
        List<CityEntity> citiesList = cityService.getListOfCities();


        System.out.println(form);
        model.addAttribute("continentsList", continentsList);
        model.addAttribute("countriesList", countriesList);
        model.addAttribute("citiesList", citiesList);
//        model.addAttribute("posts")

        return "homePage";
    }

    @PostMapping("/homepage")
    public String selectPosts(SelectedCriteriasForm form, PostDto postDto, Model model) {


        return "posts/selectedPosts";
    }


}