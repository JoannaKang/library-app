package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultipyRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// RestController: API의 진입 지점을 만들어준다
@RestController
public class CalculatorController {
    // HTTP GET 메서드를 만들어준다
    @GetMapping("/add")
    public int addTwoNumbers(CalculatorAddRequest request) {
        return request.getNumber1() + request.getNumber2();
    }

    @PostMapping("/multiply")
    public int multipyTwoNumbers(@RequestBody CalculatorMultipyRequest request) {
        return request.getNumber1() * request.getNumber2();
    }
}
