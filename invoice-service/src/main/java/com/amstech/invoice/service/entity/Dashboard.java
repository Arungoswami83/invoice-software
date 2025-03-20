package com.amstech.invoice.service.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 * The persistent class for the dashboard database table.
 * 
 */
@Entity
@NamedQuery(name="Dashboard.findAll", query="SELECT d FROM Dashboard d")
public class Dashboard implements Serializable {
	private static final long serialVersionUID = 1L;

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    @Column(name = "total_income", nullable = false)
	    private BigDecimal totalIncome = BigDecimal.ZERO;

	    @Column(name = "total_expense", nullable = false)
	    private BigDecimal totalExpense = BigDecimal.ZERO;

	    @Column(name = "total_receivables", nullable = false)
	    private BigDecimal totalReceivables = BigDecimal.ZERO;

	    @Column(name = "total_payments_received", nullable = false)
	    private BigDecimal totalPaymentsReceived = BigDecimal.ZERO;

	    @ManyToOne
	    @JoinColumn(name = "client_id", nullable = false)
	    private Client client;  

	    @Column(name = "created_at", updatable = false)
	    private LocalDateTime createdAt = LocalDateTime.now();

	    @Column(name = "updated_at")
	    private LocalDateTime updatedAt= LocalDateTime.now();

	    public Dashboard() {
	    	
	    }

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public BigDecimal getTotalIncome() {
			return totalIncome;
		}

		public void setTotalIncome(BigDecimal totalIncome) {
			this.totalIncome = totalIncome;
		}

		public BigDecimal getTotalExpense() {
			return totalExpense;
		}

		public void setTotalExpense(BigDecimal totalExpense) {
			this.totalExpense = totalExpense;
		}

		public BigDecimal getTotalReceivables() {
			return totalReceivables;
		}

		public void setTotalReceivables(BigDecimal totalReceivables) {
			this.totalReceivables = totalReceivables;
		}

		public BigDecimal getTotalPaymentsReceived() {
			return totalPaymentsReceived;
		}

		public void setTotalPaymentsReceived(BigDecimal totalPaymentsReceived) {
			this.totalPaymentsReceived = totalPaymentsReceived;
		}

		public Client getClient() {
			return client;
		}

		public void setClient(Client client) {
			this.client = client;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		public LocalDateTime getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
		}
	    

}