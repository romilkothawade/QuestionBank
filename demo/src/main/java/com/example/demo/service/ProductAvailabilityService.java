package com.example.demo.service;

import com.example.demo.model.Availability;
import com.example.demo.model.Capacity;
import com.example.demo.model.StoreInput;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductAvailabilityService {

    private static List<Availability> availabilityList = new ArrayList<>();
    private static List<Capacity> capacityList = new ArrayList<>();

    public void setAvailability() {
        availabilityList.add(new Availability("Store001", "Prod1", LocalDate.of(2021, 02, 19), 1.0));
        availabilityList.add(new Availability("Store001", "Prod1", LocalDate.of(2021, 02, 20), 3.0));
        availabilityList.add(new Availability("Store001", "Prod1", LocalDate.of(2021, 02, 21), 0));

    }

    public void setCapacity() {
        capacityList.add(new Capacity("Store001", "Prod1", LocalDate.of(2021, 02, 19), 0));
        capacityList.add(new Capacity("Store001", "Prod1", LocalDate.of(2021, 02, 20), 2.0));
        capacityList.add(new Capacity("Store001", "Prod1", LocalDate.of(2021, 02, 21), 2.0));
    }

    @Async
    public CompletableFuture<Availability> getAvailability(String storeNo) {
        Optional<Availability> first = availabilityList.stream().filter(i -> i.getStoreNo().equals(storeNo)).findFirst();
        return CompletableFuture.completedFuture(first.get());
    }

    @Async
    public CompletableFuture<Capacity> getCapacity(String storeNo) {
        Optional<Capacity> first = capacityList.stream().filter(i -> i.getStoreNo().equals(storeNo)).findFirst();
        return CompletableFuture.completedFuture(first.get());
    }


    public String getStatus(StoreInput storeInput) {
        double availabilty = availabilityList.stream().filter(i -> i.getDate().equals(storeInput.getReqDate())).mapToDouble(i -> i.getAvailQty()).sum();
        double capacity = capacityList.stream().filter(i -> i.getDate().equals(storeInput.getReqDate())).mapToDouble(i -> i.getNoOfOrdersAccepted()).sum();
        String status = null;
        if (availabilty > 0) {
            if (capacity == 0)
                return "No Capacity";
            else
                return "Available";
        } else
            return status;
    }


}
