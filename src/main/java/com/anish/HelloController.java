package com.anish;


import com.anish.basics.Main;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HelloController {


    // GET /hello?name=test
    // {
    //    "name": "test",
    //    "currentTime": "<current time>"
    //}
    @GetMapping("/hello")
    public TESTModel showHello(@RequestParam String name){
        TESTModel model  =new TESTModel();
        model.setName(name);
        model.setLocalDateTime(LocalDateTime.now());
        return model;
    }

    //code,desc
    //00,desc 00
    //01,desc 01
    //02,desc 02
    //03,
    //04,desc 04
    // check the missing desc and if true then provide some message like desc is missing else provide ok.


    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Main.class.getClassLoader().getResourceAsStream("test.csv"), StandardCharsets.UTF_8))) {
            List<List<String>> records = reader.lines()
                    .map(line -> Arrays.asList(line.split(",")))
                    .filter(record -> record.size() > 1 && (record.get(1) == null || record.get(1).trim().isEmpty())) // Checks if desc is missing
                    .collect(Collectors.toList());

            // Print filtered records (for demonstration)
            records.forEach(record -> System.out.println(records));
            // Do something with the records

            records.forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
