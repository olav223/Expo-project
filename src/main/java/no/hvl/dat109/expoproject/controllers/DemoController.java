package no.hvl.dat109.expoproject.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api")
public class DemoController {

    @GetMapping("/demo")
    public List<String> demo(){
        List<String> list = new ArrayList<>(Arrays.asList("Hello", "World", "from", "Spring!"));

        return list;
    }
}
