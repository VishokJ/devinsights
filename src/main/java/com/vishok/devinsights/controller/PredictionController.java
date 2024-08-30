package com.vishok.devinsights.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/predict")
public class PredictionController {
    @PostMapping("/makePrediction")
    public ResponseEntity<String> makePrediction(@RequestBody String inputData) {
        String prediction = callPythonPredictionService(inputData);
        return ResponseEntity.ok(prediction);
    }

    private String callPythonPredictionService(String inputData) {
        // Logic to call Python prediction service and return prediction result
        return "{}"; // Placeholder
    }
}
