package com.code.financeapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.code.financeapp.model.Income;
import com.code.financeapp.service.IncomeService;

@RestController
@RequestMapping("/incomes")
public class IncomeController {
	
	private final IncomeService incomeService;
	
	public IncomeController(IncomeService incomeService) {
		this.incomeService = incomeService;
	}
	
	@GetMapping
	public List<Income> getAllIncomes(){
		return this.incomeService.getAllIncomes();
	}
	
	@PostMapping
	public Income createIncome(@RequestBody Income income) {
        return incomeService.addIncome(income);
    }
}
