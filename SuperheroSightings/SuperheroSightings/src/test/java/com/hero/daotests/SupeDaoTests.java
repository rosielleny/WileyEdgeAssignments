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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SupeDaoTests {
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");

    @Autowired
    SupeDao supeDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    SightingDao sightingDao;

    public SupeDaoTests() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        List<Supe> supes = supeDao.getAllSupes();
        for (Supe supe : supes) {
            supeDao.deleteSupe(supe.getSupeId());
        }

        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting sighting : sightings) {
            sightingDao.deleteSighting(sighting.getSightingId());
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
    public void testAddAndGetSupe() {
        Supe supe = new Supe();
        supe.setSupeId(999);
        supe.setSupeName("Test Name");
        supe.setSupeDescription("Test Description");
        supe.setSupePower("Test Superpower");
        supe = supeDao.createSupe(supe);

        Supe fromDao = supeDao.getSupeById(supe.getSupeId());

        assertEquals(supe, fromDao);
    }

    @Test
    public void testGetAllSupes() {
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


        List<Supe> supes = supeDao.getAllSupes();

        assertEquals(2, supes.size());
        assertTrue(supes.contains(supe));
        assertTrue(supes.contains(supe2));
    }

    @Test
    public void testUpdateSupe() {
        Supe supe = new Supe();
        supe.setSupeId(999);
        supe.setSupeName("Test Name");
        supe.setSupeDescription("Test Description");
        supe.setSupePower("Test Superpower");
        supe = supeDao.createSupe(supe);

        Supe fromDao = supeDao.getSupeById(supe.getSupeId());
        assertEquals(supe, fromDao);

        supe.setSupeName("New Test Name");
        supeDao.updateSupe(supe);

        assertNotEquals(supe, fromDao);

        fromDao = supeDao.getSupeById(supe.getSupeId());

        assertEquals(supe, fromDao);
    }

    @Test
    public void testDeleteSupeById() {
        Supe supe = new Supe();
        supe.setSupeId(999);
        supe.setSupeName("Test Name");
        supe.setSupeDescription("Test Description");
        supe.setSupePower("Test Superpower");
        supe = supeDao.createSupe(supe);

        Supe fromDao = supeDao.getSupeById(supe.getSupeId());
        assertEquals(supe, fromDao);

        supeDao.deleteSupe(supe.getSupeId());

        fromDao = supeDao.getSupeById(supe.getSupeId());
        assertNull(fromDao);
    }

    @Test
    public void testGetSupesByLocation() throws ParseException {
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
        sighting2.setLocationId(location.getLocationId());
        sighting2 = sightingDao.createSighting(sighting2);

        List<String> supesAtLocation = supeDao.getSuperheroesAtLocation(location.getLocationName());

        assertEquals(2, supesAtLocation.size());
        assertTrue(supesAtLocation.contains(supe.getSupeName()));
        assertTrue(supesAtLocation.contains(supe2.getSupeName()));
    }
}
