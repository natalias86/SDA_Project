package pl.sda.sda_biuro_podrozy.controllers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.sda_biuro_podrozy.entities.*;
import pl.sda.sda_biuro_podrozy.service.*;

import java.time.LocalDate;
import java.util.*;
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

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    static class SelectedCriteriaForm {
        private Integer continentId;
        private Integer countryId;
        private Integer cityId;

        public SelectedCriteriaForm(Integer continentId, Integer countryId, Integer cityId) {
            this.continentId = continentId;
            this.countryId = countryId;
            this.cityId = cityId;
        }
    }

    @RequestMapping(value = "/homepage", method = {RequestMethod.GET})
    public String showMainForm(@ModelAttribute("criteriaForm") SelectedCriteriaForm form, Model model) {

        List<PostEntity> allPosts = postService.getPublishedPosts();
        List<ContinentEntity> continentsList = continentService.getListOfContinents();
        List<PostEntity> postsLastMinute = new ArrayList<>();
        List<HotelEntity> hotelsList = new ArrayList<>();
        List<PostEntity> postsSelected = new ArrayList<>();
        List<HotelEntity> hotelsSelected = new ArrayList<>();
        List<CountryEntity> countriesSelected = new ArrayList<>();
        List<CityEntity> citiesSelected = new ArrayList<>();

        if (form.getContinentId() == null && form.getCountryId() == null && form.getCityId() == null) {
            continentsList = continentService.getListOfContinents();
            countriesSelected = countryService.getListOfCountries();
            citiesSelected = cityService.getListOfCities();
            hotelsSelected = hotelService.getListOfHotels();
        } else {
            if (form.getContinentId() != null) {
                // todo load country list by continent id
                //  countriesList = Arrays.asList(countryService.getListOfCountries().iterator().next());

                ContinentEntity continentEntity = continentService.getContinentById(form.getContinentId());
                countriesSelected = countryService.getListOfCountries().stream().filter(p -> p.getContinentEntity().equals(continentEntity)).collect(Collectors.toList());
                for (CountryEntity countryEntity : countriesSelected) {
                    citiesSelected.addAll(cityService.getListOfCitiesByCountry(countryEntity));

                }
                hotelsSelected.clear();
                for (CityEntity cityEntity : citiesSelected) {
                    hotelsSelected.addAll(hotelService.getListOfHotelsByCity(cityEntity));
                }
            }
            if (form.getCountryId() != null) {

                CountryEntity countryEntity = countryService.getCountryById(form.getCountryId());
                hotelsSelected.clear();
                citiesSelected = cityService.getListOfCities().stream().filter(p -> p.getCountryEntity().equals(countryEntity)).collect(Collectors.toList());
                for (CityEntity cityEntity : citiesSelected) {
                    hotelsSelected.addAll(hotelService.getListOfHotelsByCity(cityEntity));
                }
            }
            if (form.getCityId() != null) {
                hotelsSelected.clear();
                CityEntity cityEntity = cityService.getCityById(form.getCityId());
                hotelsSelected = hotelService.getListOfHotels().stream().filter(p -> p.getCityEntity().equals(cityEntity)).collect(Collectors.toList());

            }
        }

        //find posts by criteria selected:
        for (HotelEntity hotelEntity : hotelsSelected) {
            postsSelected.addAll(allPosts.stream().filter(p -> p.getHotelEntity().equals(hotelEntity)).collect(Collectors.toList()));
        }

        //Promoted:
        List<PostEntity> postsPromoted = postService.getPublishedPosts().stream().filter(p -> p.isPromoted() == true).collect(Collectors.toList());
        //LastMinute:
        LocalDate today = LocalDate.now();
        for (PostEntity post : allPosts) {
            if (post.getStartDate().isBefore(today.plusDays(7))) {
                postsLastMinute.add(post);
            }
        }

        model.addAttribute("continentsList", continentsList);
        model.addAttribute("countriesList", countriesSelected);
        model.addAttribute("citiesList", citiesSelected);
        model.addAttribute("posts", postsSelected);
        model.addAttribute("postsPromoted", postsPromoted);
        model.addAttribute("postsLastMinute", postsLastMinute);
        return "homePage";
    }

}