package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the saving database table.
 * 
 */
@Entity
@NamedQuery(name="Saving.findAll", query="SELECT s FROM Saving s")
public class Saving implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	private int id;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="current_amount")
	private BigDecimal currentAmount;

	@Column(name="goal_name")
	private String goalName;

	@Column(name="plan_name")
	private String planName;

	@Column(name="saved_amount")
	private BigDecimal savedAmount;

	@Column(name="target_amount")
	private BigDecimal targetAmount;

	@Column(name="user_id")
	private int userId;

	public Saving() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public BigDecimal getCurrentAmount() {
		return this.currentAmount;
	}

	public void setCurrentAmount(BigDecimal currentAmount) {
		this.currentAmount = currentAmount;
	}

	public String getGoalName() {
		return this.goalName;
	}

	public void setGoalName(String goalName) {
		this.goalName = goalName;
	}

	public String getPlanName() {
		return this.planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public BigDecimal getSavedAmount() {
		return this.savedAmount;
	}

	public void setSavedAmount(BigDecimal savedAmount) {
		this.savedAmount = savedAmount;
	}

	public BigDecimal getTargetAmount() {
		return this.targetAmount;
	}

	public void setTargetAmount(BigDecimal targetAmount) {
		this.targetAmount = targetAmount;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}