package com.wyt.learn.restsample.resources;

import com.wyt.learn.restsample.model.Calculation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Darcy
 *         Created by Darcy on 2017/7/17.
 */
@RestController //是@Controller和@ResponseBody的组和,因此返回一个域对象.而不是试图
@RequestMapping("/calculation")
public class CalculationController {
    //为使用现成的基本检查,我们使用了正则表达式 来只允许有效的数字做为参数
    private static final String PATTERN = "^-?+\\d+\\.?+\\d*$";

    /**
     * (@RequestParam) 负责将查询参数绑定到控制器方法的参数.value是参数名称.defaultValue 默认值.
     *
     * @param b 底数
     * @param e 指数
     * @return Json
     */
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

    /**
     * 求平方根 (@PathVariable 允许您将Java参数映射到一个路径参数上,在URI中创建占位符)
     *
     * @param aValue
     * @return
     */
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
