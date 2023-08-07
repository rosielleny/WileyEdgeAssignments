package com.hero.daotests;

import com.hero.dto.entity.Organisation;
import com.hero.model.dao.OrganisationDao;
import com.hero.model.dao.SupeDao;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrganisationDaoTests {
    @Autowired
    OrganisationDao organisationDao;

    @Autowired
    SupeDao supeDao;

    public OrganisationDaoTests() {
    }

    @BeforeAll
    static void setUpClass() {
    }

    @AfterAll
    static void tearDownClass() {
    }

    @BeforeEach
    void setUp() {
        List<Organisation> organisations = organisationDao.getAllOrganisations();
        for (Organisation organisation : organisations) {
            organisationDao.deleteOrganisation(organisation.getOrganisationId());
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testAddAndGetOrganisation() {
        Organisation organisation = new Organisation();
        organisation.setOrganisationId(999);
        organisation.setOrganisationName("Test Name");
        organisation.setOrganisationAddress("Test Address");
        organisation.setOrganisationDescription("Test Description");
        organisation.setOrganisationContactInfo("Test Contact Info");
        organisation = organisationDao.addOrganisation(organisation);


        Organisation fromDao = organisationDao.getOrganisationById(organisation.getOrganisationId());
        assertEquals(organisation, fromDao);
    }

    @Test
    public void testGetAllOrganisations() {
        Organisation organisation = new Organisation();
        organisation.setOrganisationId(999);
        organisation.setOrganisationName("Test Name");
        organisation.setOrganisationAddress("Test Address");
        organisation.setOrganisationDescription("Test Description");
        organisation.setOrganisationContactInfo("Test Contact Info");
        organisation = organisationDao.addOrganisation(organisation);

        Organisation organisation2 = new Organisation();
        organisation2.setOrganisationId(888);
        organisation2.setOrganisationName("Test Name 2");
        organisation2.setOrganisationAddress("Test Address 2");
        organisation2.setOrganisationDescription("Test Description 2");
        organisation2.setOrganisationContactInfo("Test Contact Info 2");
        organisation2 = organisationDao.addOrganisation(organisation2);

        List<Organisation> organisations = organisationDao.getAllOrganisations();

        assertEquals(2, organisations.size());
        assertTrue(organisations.contains(organisation));
        assertTrue(organisations.contains(organisation2));
    }

    @Test
    public void testUpdateOrganisation() {
        Organisation organisation = new Organisation();
        organisation.setOrganisationId(999);
        organisation.setOrganisationName("Test Name");
        organisation.setOrganisationAddress("Test Address");
        organisation.setOrganisationDescription("Test Description");
        organisation.setOrganisationContactInfo("Test Contact Info");
        organisation = organisationDao.addOrganisation(organisation);

        Organisation fromDao = organisationDao.getOrganisationById(organisation.getOrganisationId());
        assertEquals(organisation, fromDao);

        organisation.setOrganisationName("New Test Organisation Name");
        organisationDao.updateOrganisation(organisation.getOrganisationId(), organisation.getOrganisationName(), organisation.getOrganisationDescription(), organisation.getOrganisationAddress(), organisation.getOrganisationContactInfo());

        assertNotEquals(organisation, fromDao);

        fromDao = organisationDao.getOrganisationById(organisation.getOrganisationId());

        assertEquals(organisation, fromDao);
    }

    @Test
    public void testDeleteOrganisationById() {
        Organisation organisation = new Organisation();
        organisation.setOrganisationId(999);
        organisation.setOrganisationName("Test Name");
        organisation.setOrganisationAddress("Test Address");
        organisation.setOrganisationDescription("Test Description");
        organisation.setOrganisationContactInfo("Test Contact Info");
        organisation = organisationDao.addOrganisation(organisation);

        Organisation fromDao = organisationDao.getOrganisationById(organisation.getOrganisationId());
        assertEquals(organisation, fromDao);

        organisationDao.deleteOrganisation(organisation.getOrganisationId());

        fromDao = organisationDao.getOrganisationById(organisation.getOrganisationId());
        assertNull(fromDao);
    }


}
