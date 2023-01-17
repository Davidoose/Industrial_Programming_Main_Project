package org.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.example.expProcessing.ProccesExpByLib.solvingExpressionByLib;

@RestController
public class WebController {
    @RequestMapping("/SolveExpression")
    public String SolvingExpby(@RequestParam(value = "Exp") String exp) {
        return solvingExpressionByLib(exp);
    }

    @RequestMapping("/SolveExpressionByLib")
    public String SolvingExpByLib(@RequestParam(value = "Exp") String exp) {
        return solvingExpressionByLib(exp);
    }
}