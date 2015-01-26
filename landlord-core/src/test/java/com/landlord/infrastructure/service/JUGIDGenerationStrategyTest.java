package com.landlord.infrastructure.service;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class JUGIDGenerationStrategyTest {

    @Test
    public void testGenerate() {
        //arrange
        JUGIDGenerationStrategy generator = new JUGIDGenerationStrategy();

        //act
        String id = generator.generate();

        //assert
        assertNotNull(id);
    }

    @Test
    public void testGenerateGeneratesUniqueIds() {
        //arrange
        JUGIDGenerationStrategy generator1 = new JUGIDGenerationStrategy();
        JUGIDGenerationStrategy generator2 = new JUGIDGenerationStrategy();

        //act
        String id1 = generator1.generate();
        String id2 = generator2.generate();

        //assert
        assertNotEquals(id1, id2);
    }
}