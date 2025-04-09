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

		@Column(name = "total_revenue")
		private BigDecimal totalRevenue;

		@Column(name = "total_expenditure")
		private BigDecimal totalExpenditure;

		 @Column(name = "total_receivables")
		 private BigDecimal totalReceivables;

		 @Column(name = "total_payments_received")
		 private BigDecimal totalPaymentsReceived;

		 @Column(name = "total_sales")
		 private BigDecimal totalSales;

		 @Column(name = "profit")
		 private BigDecimal profit;

		 @ManyToOne
		 @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
		 private Client client; 

	    @Column(name = "created_at", updatable = false)
	    @Temporal(TemporalType.TIMESTAMP)
	    private LocalDateTime createdAt;
	    
	    @Column(name = "updated_at")
	    @Temporal(TemporalType.TIMESTAMP)
	    private LocalDateTime updatedAt;

	    public Dashboard() {
	    	
	    }

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}
		 @PrePersist
		    protected void onCreate() {
		        this.createdAt = LocalDateTime.now();
		        this.updatedAt = LocalDateTime.now();
		    }

		  @PreUpdate
		    protected void onUpdate() {
		        this.updatedAt = LocalDateTime.now();
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

		public BigDecimal getTotalRevenue() {
			return totalRevenue;
		}

		public void setTotalRevenue(BigDecimal totalRevenue) {
			this.totalRevenue = totalRevenue;
		}

		public BigDecimal getTotalExpenditure() {
			return totalExpenditure;
		}

		public void setTotalExpenditure(BigDecimal totalExpenditure) {
			this.totalExpenditure = totalExpenditure;
		}

		public BigDecimal getTotalSales() {
			return totalSales;
		}

		public void setTotalSales(BigDecimal totalSales) {
			this.totalSales = totalSales;
		}

		public BigDecimal getProfit() {
			return profit;
		}

		public void setProfit(BigDecimal profit) {
			this.profit = profit;
		}
		
}