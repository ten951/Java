package com.wyt.learn.restsample.resources;

import com.wyt.learn.restsample.model.Calculation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Darcy
 *         Created by Darcy on 2017/7/17.
 */
@RestController
@RequestMapping("/calculation")
public class CalculationController {
    private static final String PATTERN = "^-?+\\d+\\.?+\\d*$";

    @RequestMapping("/power")
    public Calculation pow(@RequestParam(value = "base") String b, @RequestParam(value = "exponent") String e) {
        List<String> input = new ArrayList<>();
        input.add(b);
        input.add(e);
        List<String> output = new ArrayList<>();
        String powValue;
        if (b != null && e != null && b.matches(PATTERN) && e.matches(PATTERN)) {
            powValue = String.valueOf(Math.pow(Double.valueOf(b), Double.valueOf(e)));
        } else {
            powValue = "Base or/and Exponent is/are not set to numeric value.";
        }
        output.add(powValue);
        return new Calculation(input, output, "power");
    }

    @RequestMapping(value = "/sqrt/{value:.+}", method = RequestMethod.GET)
    public Calculation sqrt(@PathVariable(value = "value") String aValue) {
        List<String> input = new ArrayList<>();
        input.add(aValue);
        List<String> output = new ArrayList<>();
        String sqrtValue = "";
        if (aValue != null && aValue.matches(PATTERN)) {
            sqrtValue = String.valueOf(Math.sqrt(Double.valueOf(aValue)));
        } else {
            sqrtValue = "Input value is not set to numeric value.";
        }
        output.add(sqrtValue);
        return new Calculation(input, output, "sqrt");
    }
}
