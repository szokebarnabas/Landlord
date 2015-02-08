package com.landlord.domain.utilities;

import com.google.common.collect.Multimap;
import com.landlord.domain.property.PropertyId;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UtilitiesTest {

    private Utilities testObj = new Utilities(mock(UtilityDetailsId.class));
    @Mock
    private PropertyId propertyId1;
    @Mock
    private UtilityEntry entry1;
    @Mock
    private UtilityEntry entry2;
    @Mock
    private Multimap<PropertyId, UtilityEntry> mapMock;

    @Test
    public void testAddNewUtilityEntry() throws Exception {
        // arrange
        testObj.setEntryMap(mapMock);
        // act
        testObj.addNewUtilityEntry(propertyId1, entry1);
        // assert
        verify(mapMock).put(propertyId1, entry1);
    }

    @Test
    public void testFindEntriesByPropertyId() throws Exception {
        // arrange
        testObj.addNewUtilityEntry(propertyId1, entry1);
        testObj.addNewUtilityEntry(propertyId1, entry2);
        // act
        Collection<UtilityEntry> result = testObj.findEntriesByPropertyId(propertyId1);
        // assert
        assertThat(result, Matchers.hasItems(entry1, entry2));
    }
}