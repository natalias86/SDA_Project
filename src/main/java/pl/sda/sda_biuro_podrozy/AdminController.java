package pl.sda.sda_biuro_podrozy;

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
import pl.sda.sda_biuro_podrozy.entities.CityEntity;
import pl.sda.sda_biuro_podrozy.entities.ContinentEntity;
import pl.sda.sda_biuro_podrozy.entities.CountryEntity;
import pl.sda.sda_biuro_podrozy.service.CityService;
import pl.sda.sda_biuro_podrozy.service.ContinentService;
import pl.sda.sda_biuro_podrozy.service.CountryService;


import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController {

    CountryService countryService;
    ContinentService continentService;
    CityService cityService;

    @Autowired
    public AdminController(CityService cityService, CountryService countryService, ContinentService continentService) {
        this.cityService = cityService;
        this.countryService = countryService;
        this.continentService = continentService;
    }

    @GetMapping("/post/admin/adminpanel")
    public String showAdminPanel() {
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
        return "redirect:/homepage";
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
        return "redirect:/homepage";
    }

}
