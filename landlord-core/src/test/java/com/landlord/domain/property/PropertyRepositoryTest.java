package com.landlord.domain.property;

import com.landlord.config.Config;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class PropertyRepositoryTest {

    @Inject
    private PropertyRepository propertyRepository;

    @Inject
    private PropertyFactory propertyFactory;

    private Property property1;
    private Property property2;

    @Before
    public void init() {
        property1 = propertyFactory.createProperty("", SharingType.NON_SHARED, PropertyType.APARTMENT);
        property2 = propertyFactory.createProperty("", SharingType.SHARED, PropertyType.HOUSE);
    }

    @Test
    public void testFindProperty() throws Exception {
        //setup
        propertyRepository.clear();
        propertyRepository.create(property1);
        //act
        Property foundProperty = propertyRepository.find(property1.getID());
        //assert
        assertEquals(property1, foundProperty);
    }

    @Test
    public void testFindAll() throws Exception {
        //setup
        propertyRepository.clear();
        propertyRepository.create(property1);
        propertyRepository.create(property2);
        //act
        Set<Property> properties = propertyRepository.findAll();
        //assert
        assertThat(properties, Matchers.containsInAnyOrder(property1, property2));
    }

    @Test
    public void testCreate() throws Exception {
        //setup
        propertyRepository.clear();
        //act
        propertyRepository.create(property1);
        //assert
        assertEquals(1, propertyRepository.findAll().size());
    }

    @Test
    public void testUpdate() throws Exception {
        //setup
        propertyRepository.clear();
        propertyRepository.create(property1);
        property1.modifyPropertyType(PropertyType.FLAT);
        //act
        propertyRepository.update(property1);
        //assert
        Property modifiedProperty = propertyRepository.find(property1.getID());
        assertEquals(modifiedProperty.propertyType(), PropertyType.FLAT);
    }
}