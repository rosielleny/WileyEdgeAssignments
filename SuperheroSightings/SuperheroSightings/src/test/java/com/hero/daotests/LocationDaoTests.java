package com.hero.daotests;

import com.hero.dto.entity.Location;
import com.hero.model.dao.LocationDao;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LocationDaoTests {

    @Autowired
    LocationDao locationDao;

    public LocationDaoTests() {
    }

    @BeforeAll
    static void setUpClass() {
    }

    @AfterAll
    static void tearDownClass() {
    }

    @BeforeEach
    void setUp() {
        List<Location> locations = locationDao.getAllLocations();
        for (Location location : locations) {
            locationDao.deleteLocation(location.getLocationId());
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testAddAndGetLocation() {
        Location location = new Location();
        location.setLocationId(999);
        location.setLocationName("Test Name");
        location.setLocationAddress("Test Address");
        location.setLocationCoordinates("Test Coordinates");
        location.setLocationDescription("Test Description");
        location = locationDao.createLocation(location);

        Location fromDao = locationDao.getLocationById(location.getLocationId());
        assertEquals(location, fromDao);
    }

    @Test
    public void testGetAllLocations() {
        Location location = new Location();
        location.setLocationId(999);
        location.setLocationName("Test Name");
        location.setLocationAddress("Test Address");
        location.setLocationCoordinates("Test Coordinates");
        location.setLocationDescription("Test Description");
        location = locationDao.createLocation(location);

        Location location2 = new Location();
        location2.setLocationId(888);
        location2.setLocationName("Test Name 2");
        location2.setLocationAddress("Test Address 2");
        location2.setLocationCoordinates("Test Coordinates 2");
        location2.setLocationDescription("Test Description 2");
        location2 = locationDao.createLocation(location2);

        List<Location> locations = locationDao.getAllLocations();

        assertEquals(2, locations.size());
        assertTrue(locations.contains(location));
        assertTrue(locations.contains(location2));
    }

    @Test
    public void testUpdateLocation() {
        Location location = new Location();
        location.setLocationId(999);
        location.setLocationName("Test Name");
        location.setLocationAddress("Test Address");
        location.setLocationCoordinates("Test Coordinates");
        location.setLocationDescription("Test Description");
        location = locationDao.createLocation(location);

        Location fromDao = locationDao.getLocationById(location.getLocationId());
        assertEquals(location, fromDao);

        String newLocationName = "New Test Location Name";
        int updateResult = locationDao.updateLocation(location.getLocationId(), newLocationName);
        assertEquals(1, updateResult);

        fromDao = locationDao.getLocationById(location.getLocationId());

        assertEquals(newLocationName, fromDao.getLocationName());
    }
    
    @Test
    public void testDeleteLocationById() {
        Location location = new Location();
        location.setLocationId(999);
        location.setLocationName("Test Name");
        location.setLocationAddress("Test Address");
        location.setLocationCoordinates("Test Coordinates");
        location.setLocationDescription("Test Description");
        location = locationDao.createLocation(location);

        Location fromDao = locationDao.getLocationById(location.getLocationId());
        assertEquals(location, fromDao);

        locationDao.deleteLocation(location.getLocationId());

        fromDao = locationDao.getLocationById(location.getLocationId());
        assertNull(fromDao);
    }
}
