package com.amstech.invoice.service.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name="invoice_history")
@NamedQuery(name="InvoiceHistory.findAll", query="SELECT b FROM InvoiceHistory b")
public class InvoiceHistory implements Serializable{
	private static final long serialVersionUID = 1L;
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int id;

	    @ManyToOne
	    @JoinColumn(name = "invoice_id", nullable = false)
	    private Invoice invoice;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "action", nullable = false)
	    private InvoiceAction action;

	    @Column(name = "changed_by", nullable = false, length = 100)
	    private String changedBy;

	    @Column(name = "changed_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	    private Timestamp changedAt;

	    @Column(name = "old_value", columnDefinition = "JSON")
	    private String oldValue;

	    @Column(name = "new_value", columnDefinition = "JSON")
	    private String newValue;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public Invoice getInvoice() {
			return invoice;
		}

		public void setInvoice(Invoice invoice) {
			this.invoice = invoice;
		}

		public InvoiceAction getAction() {
			return action;
		}

		public void setAction(InvoiceAction action) {
			this.action = action;
		}

		public String getChangedBy() {
			return changedBy;
		}

		public void setChangedBy(String changedBy) {
			this.changedBy = changedBy;
		}

		public Timestamp getChangedAt() {
			return changedAt;
		}

		public void setChangedAt(Timestamp changedAt) {
			this.changedAt = changedAt;
		}

		public String getOldValue() {
			return oldValue;
		}

		public void setOldValue(String oldValue) {
			this.oldValue = oldValue;
		}

		public String getNewValue() {
			return newValue;
		}

		public void setNewValue(String newValue) {
			this.newValue = newValue;
		}
	}


