package pl.sda.sda_biuro_podrozy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.sda_biuro_podrozy.entities.HotelEntity;
import pl.sda.sda_biuro_podrozy.repository.HotelRepository;


import java.util.ArrayList;
import java.util.List;
@Service
public class HotelService {
    @Autowired//  wstrzykniecie obiektu do referencji
    HotelRepository hotelRepository;

    public List<HotelEntity> getListOfHotels(){
    List<HotelEntity> hotels = hotelRepository.findAll();

    return hotels;
    }
}
