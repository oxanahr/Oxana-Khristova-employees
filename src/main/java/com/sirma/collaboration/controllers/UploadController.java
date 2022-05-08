package com.sirma.collaboration.controllers;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.sirma.collaboration.models.EmployeeDto;
import com.sirma.collaboration.models.ResultPairDto;
import com.sirma.collaboration.services.EmployeesService;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class UploadController {

    private final EmployeesService employeesService;

    @Autowired
    public UploadController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping("/")
    public String getEmployees() {
        return "employees";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("employees") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
        } else {
            try (Reader reader = new BufferedReader(new InputStreamReader(new BOMInputStream(file.getInputStream()), StandardCharsets.UTF_8))) {

                CsvToBean<EmployeeDto> csvToBean = new CsvToBeanBuilder(reader)
                        .withSeparator(',')
                        .withType(EmployeeDto.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                List<EmployeeDto> employeeDtoList = csvToBean.parse();
                List<ResultPairDto> pair = this.employeesService.findEmployeesPair(employeeDtoList);
                model.addAttribute("pairResult", pair);
            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
            }
        }

        return "employees";
    }
}

