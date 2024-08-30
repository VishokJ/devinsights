package com.vishok.devinsights.controller;

import com.vishok.devinsights.service.DataProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/process")
public class DataProcessingController {

    private final DataProcessingService dataProcessingService;

    @Autowired
    public DataProcessingController(DataProcessingService dataProcessingService) {
        this.dataProcessingService = dataProcessingService;
    }


//    @PostMapping("/processData")
//    public ResponseEntity<String> processData(@RequestBody DataType data) {
//        dataProcessingService.processAndStoreData(data);
//        return ResponseEntity.ok("Data processed successfully");
//    }
}

