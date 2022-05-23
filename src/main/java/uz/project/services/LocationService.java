package uz.project.services;

import org.springframework.stereotype.Service;
import uz.project.models.Location;
import uz.project.repositories.LocationRepository;

import java.util.List;

@Service
public class LocationService {

private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }


    public Location saveLocation(Location location){
        return locationRepository.save(location);
    }


    public Location getLocationWithId(Long id){
        return locationRepository.findLocationById(id);
    }


    public boolean deleteLocation(Location location){
        try {
           locationRepository.delete(location);
           return true;
       }catch (Exception e){
           return false;
       }
    }


    public List<Location> getAllLocations(){
        return locationRepository.findAll();
    }
}

