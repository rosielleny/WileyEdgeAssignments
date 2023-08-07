package com.hero.daotests;

import com.hero.dto.entity.Location;
import com.hero.dto.entity.Sighting;
import com.hero.dto.entity.Supe;
import com.hero.model.dao.LocationDao;
import com.hero.model.dao.SightingDao;
import com.hero.model.dao.SupeDao;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SightingDaoTests {
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");

    @Autowired
    SightingDao sightingDao;

    @Autowired
    SupeDao supeDao;

    @Autowired
    LocationDao locationDao;

    public SightingDaoTests() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting sighting : sightings) {
            sightingDao.deleteSighting(sighting.getSightingId());
        }

        List<Supe> supes = supeDao.getAllSupes();
        for (Supe supe : supes) {
            supeDao.deleteSupe(supe.getSupeId());
        }

        List<Location> locations = locationDao.getAllLocations();
        for (Location location : locations) {
            locationDao.deleteLocation(location.getLocationId());
        }
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddAndGetSighting() throws ParseException {
        Supe supe = new Supe();
        supe.setSupeId(999);
        supe.setSupeName("Test Name");
        supe.setSupeDescription("Test Description");
        supe.setSupePower("Test Superpower");
        supe = supeDao.createSupe(supe);

        Location location = new Location();
        location.setLocationId(999);
        location.setLocationName("Test Name");
        location.setLocationAddress("Test Address");
        location.setLocationCoordinates("Test Coordinates");
        location.setLocationDescription("Test Description");
        location = locationDao.createLocation(location);


        Sighting sighting = new Sighting();
        sighting.setSightingId(999);
        sighting.setDateSeen(formatter.parse("01-08-2023"));
        sighting.setSupeId(supe.getSupeId());
        sighting.setLocationId(location.getLocationId());
        sighting = sightingDao.createSighting(sighting);

        Sighting fromDao = sightingDao.getSightingById(sighting.getSightingId());

        assertEquals(sighting, fromDao);
    }

    @Test
    public void testGetAllSightings() throws ParseException {
        Supe supe = new Supe();
        supe.setSupeId(999);
        supe.setSupeName("Test Name");
        supe.setSupeDescription("Test Description");
        supe.setSupePower("Test Superpower");
        supe = supeDao.createSupe(supe);

        Supe supe2 = new Supe();
        supe2.setSupeId(888);
        supe2.setSupeName("Test Name 2");
        supe2.setSupeDescription("Test Description 2");
        supe2.setSupePower("Test Superpower 2");
        supe2 = supeDao.createSupe(supe2);

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

        Sighting sighting = new Sighting();
        sighting.setSightingId(999);
        sighting.setDateSeen(formatter.parse("01-08-2023"));
        sighting.setSupeId(supe.getSupeId());
        sighting.setLocationId(location.getLocationId());
        sighting = sightingDao.createSighting(sighting);


        Sighting sighting2 = new Sighting();
        sighting2.setSightingId(888);
        sighting2.setDateSeen(formatter.parse("02-08-2023"));
        sighting2.setSupeId(supe2.getSupeId());
        sighting2.setLocationId(location2.getLocationId());
        sighting2 = sightingDao.createSighting(sighting2);

        List<Sighting> sightings = sightingDao.getAllSightings();

        assertEquals(2, sightings.size());
        assertTrue(sightings.contains(sighting));
        assertTrue(sightings.contains(sighting2));
    }

    @Test
    public void testUpdateSighting() throws ParseException {
        Supe supe = new Supe();
        supe.setSupeId(999);
        supe.setSupeName("Test Name");
        supe.setSupeDescription("Test Description");
        supe.setSupePower("Test Superpower");
        supe = supeDao.createSupe(supe);

        Location location = new Location();
        location.setLocationId(999);
        location.setLocationName("Test Name");
        location.setLocationAddress("Test Address");
        location.setLocationCoordinates("Test Coordinates");
        location.setLocationDescription("Test Description");
        location = locationDao.createLocation(location);

        Sighting sighting = new Sighting();
        sighting.setSightingId(999);
        sighting.setDateSeen(formatter.parse("01-08-2023"));
        sighting.setSupeId(supe.getSupeId());
        sighting.setLocationId(location.getLocationId());
        sighting = sightingDao.createSighting(sighting);

        Sighting fromDao = sightingDao.getSightingById(sighting.getSightingId());
        assertEquals(sighting, fromDao);

        //sighting.setDateSeen(formatter.parse("02-08-2023"));
        sightingDao.updateSighting(sighting.getSightingId(), "dateSeen", "02-08-2023");

        assertNotEquals(sighting, fromDao);

        fromDao = sightingDao.getSightingById(sighting.getSightingId());

        assertEquals(sighting, fromDao);
    }

    @Test
    public void testDeleteSightingById() throws ParseException {
        Supe supe = new Supe();
        supe.setSupeId(999);
        supe.setSupeName("Test Name");
        supe.setSupeDescription("Test Description");
        supe.setSupePower("Test Superpower");
        supe = supeDao.createSupe(supe);

        Location location = new Location();
        location.setLocationId(999);
        location.setLocationName("Test Name");
        location.setLocationAddress("Test Address");
        location.setLocationCoordinates("Test Coordinates");
        location.setLocationDescription("Test Description");
        location = locationDao.createLocation(location);

        Sighting sighting = new Sighting();
        sighting.setSightingId(999);
        sighting.setDateSeen(formatter.parse("01-08-2023"));
        sighting.setSupeId(supe.getSupeId());
        sighting.setLocationId(location.getLocationId());
        sighting = sightingDao.createSighting(sighting);

        Sighting fromDao = sightingDao.getSightingById(sighting.getSightingId());
        assertEquals(sighting, fromDao);

        sightingDao.deleteSighting(sighting.getSightingId());

        fromDao = sightingDao.getSightingById(sighting.getSightingId());
        assertNull(fromDao);
    }

    @Test
    public void testGetSightingsByLocation() throws ParseException {
        Supe supe = new Supe();
        supe.setSupeId(999);
        supe.setSupeName("Test Name");
        supe.setSupeDescription("Test Description");
        supe.setSupePower("Test Superpower");
        supe = supeDao.createSupe(supe);

        Location location = new Location();
        location.setLocationId(999);
        location.setLocationName("Test Name");
        location.setLocationAddress("Test Address");
        location.setLocationCoordinates("Test Coordinates");
        location.setLocationDescription("Test Description");
        location = locationDao.createLocation(location);

        Sighting sighting = new Sighting();
        sighting.setSightingId(999);
        sighting.setDateSeen(formatter.parse("01-08-2023"));
        sighting.setSupeId(supe.getSupeId());
        sighting.setLocationId(location.getLocationId());
        sighting = sightingDao.createSighting(sighting);

        Sighting sighting2 = new Sighting();
        sighting2.setSightingId(888);
        sighting2.setDateSeen(formatter.parse("02-08-2023"));
        sighting2.setSupeId(supe.getSupeId());
        sighting2.setLocationId(location.getLocationId());
        sighting2 = sightingDao.createSighting(sighting2);

        List<Sighting> sightings = sightingDao.getSightingsByLocation(location.getLocationId());

        assertEquals(2, sightings.size());
        assertTrue(sightings.contains(sighting));
        assertTrue(sightings.contains(sighting2));
    }

    @Test
    public void testGetSightingsByHero() throws ParseException {
        Supe supe = new Supe();
        supe.setSupeId(999);
        supe.setSupeName("Test Name");
        supe.setSupeDescription("Test Description");
        supe.setSupePower("Test Superpower");
        supe = supeDao.createSupe(supe);

        Location location = new Location();
        location.setLocationId(999);
        location.setLocationName("Test Name");
        location.setLocationAddress("Test Address");
        location.setLocationCoordinates("Test Coordinates");
        location.setLocationDescription("Test Description");
        location = locationDao.createLocation(location);

        Sighting sighting = new Sighting();
        sighting.setSightingId(999);
        sighting.setDateSeen(formatter.parse("01-08-2023"));
        sighting.setSupeId(supe.getSupeId());
        sighting.setLocationId(location.getLocationId());
        sighting = sightingDao.createSighting(sighting);

        Sighting sighting2 = new Sighting();
        sighting2.setSightingId(888);
        sighting2.setDateSeen(formatter.parse("02-08-2023"));
        sighting2.setSupeId(supe.getSupeId());
        sighting2.setLocationId(location.getLocationId());
        sighting2 = sightingDao.createSighting(sighting2);

        List<Sighting> sightings = sightingDao.getSightingsByHero(supe.getSupeId());

        assertEquals(2, sightings.size());
        assertTrue(sightings.contains(sighting));
        assertTrue(sightings.contains(sighting2));
    }

    @Test
    public void testGetSightingsByDate() throws ParseException {
        Supe supe = new Supe();
        supe.setSupeId(999);
        supe.setSupeName("Test Name");
        supe.setSupeDescription("Test Description");
        supe.setSupePower("Test Superpower");
        supe = supeDao.createSupe(supe);

        Supe supe2 = new Supe();
        supe2.setSupeId(888);
        supe2.setSupeName("Test Name 2");
        supe2.setSupeDescription("Test Description 2");
        supe2.setSupePower("Test Superpower 2");
        supe2 = supeDao.createSupe(supe2);

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

        Sighting sighting = new Sighting();
        sighting.setSightingId(999);
        sighting.setDateSeen(formatter.parse("01-08-2023"));
        sighting.setSupeId(supe.getSupeId());
        sighting.setLocationId(location.getLocationId());
        sighting = sightingDao.createSighting(sighting);

        Sighting sighting2 = new Sighting();
        sighting2.setSightingId(888);
        sighting2.setDateSeen(formatter.parse("01-08-2023"));
        sighting2.setSupeId(supe2.getSupeId());
        sighting2.setLocationId(location2.getLocationId());
        sighting2 = sightingDao.createSighting(sighting2);

        List<Sighting> sightings = sightingDao.getSightingsByDate("01-08-2023");

        assertEquals(2, sightings.size());
        assertTrue(sightings.contains(sighting));
        assertTrue(sightings.contains(sighting2));
    }
}
