package com.landlord.domain.utilities;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.landlord.domain.property.PropertyId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UtilityEntryTest {

    private UtilityEntry testObject;

    @Mock
    private Multimap<UtilityType, MeterValue> map;

    @Before
    public void init() {
        testObject = new UtilityEntry(mock(UtilityEntryId.class), mock(PropertyId.class));
        testObject.setMap(map);
    }

    @Test
    public void testAddMeterValue() {
        //arrange
        MeterValue meterValue = new MeterValue(new Date(), 30D);

        //act
        testObject.addMeterValue(UtilityType.ELECTRICITY, meterValue);

        //assert
        verify(map).put(UtilityType.ELECTRICITY, meterValue);
    }

    @Test
    public void testMeterValuesByType() {
        //arrange
        MeterValue meterValueMock = mock(MeterValue.class);
        when(map.get(UtilityType.GAS)).thenReturn(Lists.newArrayList(meterValueMock));

        //act
        Collection<MeterValue> result = testObject.meterValuesByType(UtilityType.GAS);

        //assert
        assertEquals(meterValueMock, result.iterator().next());
    }
}