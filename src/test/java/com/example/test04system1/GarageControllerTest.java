package com.example.test04system1;

import com.example.test04system1.domain.Car;
import com.example.test04system1.domain.Garage;
import com.example.test04system1.exception.ForbiddenTypeLPGException;
import com.example.test04system1.exception.NoCarInGarageException;
import com.example.test04system1.model.DriveType;
import com.example.test04system1.repository.CarRepository;
import com.example.test04system1.repository.GarageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class GarageControllerTest extends BaseIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private GarageRepository garageRepository;
    @Autowired
    private CarRepository carRepository;
    @Value("${garage.alerts.url}")
    private String urlAlertsByGarageId;

    private static final long GARAGE_ID = 1L;
    private static final long CAR_ONE_ID = 1L;
    private static final long CAR_TWO_ID = 2L;
    private static final long CAR_THREE_ID = 3L;

    @BeforeEach
    void setUp() {
        Garage garage = new Garage();
        garage.setId(GARAGE_ID);
        garage.setName("garage1");
        garage.setCapacity(5);
        garage.setAllowedLpg(false);
        garageRepository.save(garage);

        Car car1 = new Car(CAR_ONE_ID, "BMW", "X4", 2020, DriveType.PETROL, "1C4GJ25342B521742", null);
        Car car2 = new Car(CAR_TWO_ID, "Opel", "Astra", 2018, DriveType.LPG, "JH4KA4650JC000403", null);
        Car car3 = new Car(CAR_THREE_ID, "Seat", "Cordoba", 2017, DriveType.DIESEL, "1FVACXCSX7HY67259", garage);
        carRepository.saveAll(Arrays.asList(car1, car2, car3));
    }

    @Test
    public void shouldAddCarToGarage() throws Exception {
        this.mockMvc.perform(put("/garages/{id}/cars/{carId}/add", GARAGE_ID, CAR_ONE_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cars", hasSize(2)))
                .andExpect(jsonPath("$._links.alerts.href").value(urlAlertsByGarageId + GARAGE_ID));
    }

    @Test
    public void shouldThrowForbiddenTypeLPGException() throws Exception {
        this.mockMvc.perform(put("/garages/{id}/cars/{carId}/add", GARAGE_ID, CAR_TWO_ID))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ForbiddenTypeLPGException));
    }

    @Test
    public void shouldRemoveCarFromGarage() throws Exception {
        this.mockMvc.perform(put("/garages/{id}/cars/{carId}/remove", GARAGE_ID, CAR_THREE_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cars", hasSize(0)));
    }

    @Test
    public void shouldThrowExceptionWhenNoCarInGarage() throws Exception {
        this.mockMvc.perform(put("/garages/{id}/cars/{carId}/remove", GARAGE_ID, CAR_ONE_ID))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoCarInGarageException));

    }

}