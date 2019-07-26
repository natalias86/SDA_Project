package pl.sda.sda_biuro_podrozy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.sda.sda_biuro_podrozy.dto.CityDto;
import pl.sda.sda_biuro_podrozy.dto.HotelDto;
import pl.sda.sda_biuro_podrozy.entities.CityEntity;
import pl.sda.sda_biuro_podrozy.entities.CountryEntity;
import pl.sda.sda_biuro_podrozy.entities.HotelEntity;
import pl.sda.sda_biuro_podrozy.repository.HotelRepository;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HotelService {
    @Autowired
    HotelRepository hotelRepository;

    public List<HotelEntity> getListOfHotels() {
        List<HotelEntity> hotels = hotelRepository.findAll();
        return hotels;
    }

    public void addHotel(HotelDto hotelDto) {
        HotelEntity hotelEntity = new HotelEntity();
        hotelEntity.setName(hotelDto.getName());
        hotelEntity.setStandard(Integer.valueOf(hotelDto.getStandard()));
        hotelEntity.setDescription(hotelDto.getDescription());
        hotelEntity.setCityEntity(hotelDto.getCityEntity());
        hotelRepository.save(hotelEntity);

    }
//TODO: distinct by standard to return HotelEntity

/*     public List<Integer> getListOfHotelsByStandard(){
         List<HotelEntity> hotels = hotelRepository.findAll();

         List<Integer> hotelsByStandard =  hotels.stream().map(p->p.getStandard()).distinct().collect(Collectors.toList());
                 //filter(distinctByKey(HotelEntity::getStandard)).collect(Collectors.toList());
         return hotelsByStandard;
     }*/
public List<HotelEntity> getListOfHotelsByCity(CityEntity cityEntity) {

    List<HotelEntity> hotelsByCity = hotelRepository.findByCityEntity(cityEntity);
    return hotelsByCity;
}

}