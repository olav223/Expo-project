package no.hvl.dat109.expoproject.api;
import no.hvl.dat109.expoproject.interfaces.api.IQrGenerator;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.net.MalformedURLException;
import java.net.URL;

public class QrGenerator implements IQrGenerator {
    private final String url =  "https://api.qrserver.com/v1/create-qr-code/?data=";
    URL next;
    public QrGenerator(String path) throws MalformedURLException {
        next = new URL(url + path);
    }
    @Override
    public String generateQrCode() {

        return next.toString();
    }
}
