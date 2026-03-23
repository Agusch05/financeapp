package com.agustin.financeapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.agustin.financeapp.model.Debt;
import com.agustin.financeapp.service.DebtService;

@RestController
@RequestMapping("/debts")
public class DebtController {
	private final DebtService debtService;
	
	public DebtController(DebtService debtservice) {
		this.debtService = debtservice;
	}
	
	@GetMapping
	public List<Debt> getAllDebts(){
		return this.debtService.getAllDebts();
	}
	@PostMapping
	public Debt createDebt(@RequestBody Debt debt) {
		return this.debtService.addDebt(debt);
	}
}
