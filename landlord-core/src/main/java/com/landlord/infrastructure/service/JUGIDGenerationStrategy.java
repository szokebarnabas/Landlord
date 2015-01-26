package com.landlord.infrastructure.service;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import landlord.ddd.pattern.idgeneration.IDGenerator;

import javax.inject.Named;
import java.util.UUID;

@Named
public class JUGIDGenerationStrategy implements IDGenerator<String> {

    private UUID generateUUID() {
        EthernetAddress address = EthernetAddress.fromInterface();
        TimeBasedGenerator uuidGenerator = Generators.timeBasedGenerator(address);
        return uuidGenerator.generate();
    }

    @Override
    public String generate() {
        return generateUUID().toString();
    }
}
