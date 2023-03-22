package no.hvl.dat109.expoproject.api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

class QrGeneratorTest {
    QrGenerator gen;
    @BeforeEach
    void setup() throws MalformedURLException {
        gen = new QrGenerator("http://ider-database.westeurope.cloudapp.azure.com:8080/Prosjekt-Expo-0.0.1-SNAPSHOT/stand?id=2");
    }

    @Test
    void generateQrCode() {
        assertEquals("https://api.qrserver.com/v1/create-qr-code/?data=http://ider-database.westeurope.cloudapp.azure.com:8080/Prosjekt-Expo-0.0.1-SNAPSHOT/stand?id=2",
                gen.generateQrCode());
    }
}