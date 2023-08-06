package com.birunthaban.service;

import com.birunthaban.model.Hotel;
import com.birunthaban.model.User;
import com.birunthaban.repository.HotelRepository;
import com.birunthaban.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HotelService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HotelRepository hotelRepository;
    public String registerHotel(Hotel hotel){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user=userRepository.findByEmail(username);
        if(user.isPresent()){
            var h= Hotel.builder().address(hotel.getAddress()).name(hotel.getName()).owner(user.get()).build();
            hotelRepository.save(h);
        }
        return "ok saved";
    }


}
