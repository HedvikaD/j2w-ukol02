package cz.czechitas.java2webapps.ukol2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.Random;

@Controller
public class MainColtroller {

    private final Random random = new Random();

    private static List<String> readAllLines(String resource)throws IOException{
        ClassLoader classLoader=Thread.currentThread().getContextClassLoader();
        try(InputStream inputStream=classLoader.getResourceAsStream(resource);
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream,StandardCharsets.UTF_8))){
            return reader
                    .lines()
                    .collect(Collectors.toList());
        }
    }

    @GetMapping("/")
    public ModelAndView nahodnyCitatObrazek() throws IOException {

        ModelAndView result = new ModelAndView("zmenaStranky");

        int nahodnyIndexCitatu = random.nextInt(readAllLines("citaty.txt").size());
        String nahodnyCitat = readAllLines("citaty.txt").get(nahodnyIndexCitatu);
        result.addObject("text", nahodnyCitat);

        List<String> seznamObrazku = readAllLines("obrazky.txt");
        int nahodnyIndexObrazku = random.nextInt(seznamObrazku.size());
        String nahodnyObrazek = seznamObrazku.get(nahodnyIndexObrazku);
        result.addObject("obrazek", nahodnyObrazek);

        return result;
    }
}

