
package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the cash_flow database table.
 * 
 */
@Entity
@Table(name="cash_flow")
@NamedQuery(name="CashFlow.findAll", query="SELECT c FROM CashFlow c")
public class CashFlow implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String month;

	@Column(name="total_expense")
	private BigDecimal totalExpense;

	@Column(name="total_income")
	private BigDecimal totalIncome;

	@Column(name="user_id")
	private int userId;

	public CashFlow() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMonth() {
		return this.month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public BigDecimal getTotalExpense() {
		return this.totalExpense;
	}

	public void setTotalExpense(BigDecimal totalExpense) {
		this.totalExpense = totalExpense;
	}

	public BigDecimal getTotalIncome() {
		return this.totalIncome;
	}

	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
