package com.amstech.invoice.service.request.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class PaymentRequest {
	private Integer invoiceId;
    private BigDecimal amountPaid;
    private String paymentDate;  
    private String notes;
    private String paymentMethod;
    private String paymentStatus;
   }
//
//import { Component, OnInit } from '@angular/core';
//import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
//import { HttpClient } from '@angular/common/http';
//import { ClientService } from '../client.service';
//import { CompanyService } from '../company.service';
//
//@Component({
//  selector: 'app-create-invoice',
//  templateUrl: './create-invoice.component.html',
//  styleUrls: ['./create-invoice.component.scss'],
//  standalone:false
//})
//export class CreateInvoiceComponent implements OnInit {
//  invoiceForm: FormGroup;
//  paymentForm: FormGroup;
//
//  clients: any[] = [];
//  companies: any[] = [];
//
//  // Summary values
//  subTotal: number = 0;
//  taxAmount: number = 0;
//  grandTotal: number = 0;
//  balance: number = 0;
//
//  payments: any[] = [];
//
//  constructor(
//    private fb: FormBuilder,
//    private clientService: ClientService,
//    private companyService: CompanyService,
//    private http: HttpClient
//  ) {
//    this.invoiceForm = this.fb.group({
//      clientId: ['', Validators.required],
//      companyId: ['', Validators.required],
//      items: this.fb.array([])
//    });
//
//    this.paymentForm = this.fb.group({
//      paymentName: ['', Validators.required],
//      paymentDetails: [''],
//      paymentAmount: [0, Validators.required],
//      paymentDate: ['', Validators.required]
//    });
//  }
//
//  ngOnInit(): void {
//    this.getClients();
//    this.getCompanies();
//    this.addItem(); // Start with one item row
//  }
//
//  get items(): FormArray {
//    return this.invoiceForm.get('items') as FormArray;
//  }
//
//  getClients(): void {
//    this.clientService.getAllClients().subscribe(res => {
//      this.clients = res.content || res;
//    });
//  }
//
//  getCompanies(): void {
//    this.companyService.getAllCompanies(0, 10).subscribe(res => {
//      this.companies = res.content || res;
//    });
//  }
//
//  addItem(): void {
//    const item = this.fb.group({
//      category: [''],
//      quantity: [0],
//      price: [0],
//      subtotal: [0],
//      discount: [0],
//      tax: [0],
//      grandTotal: [0],
//      total: [0], //  Yeh line zaroori hai
//    });
//    this.items.push(item);
//  }
//  
//  
//  removeItem(index: number): void {
//    this.items.removeAt(index);
//    this.calculateAll();
//  }
//
//  updatePrice(index: number): void {
//    const item = this.items.at(index);
//    const category = item.get('category')?.value;
//
//    let price = 0;
//    if (category === 'Services') price = 500;
//    else if (category === 'Product') price = 1000;
//    else if (category === 'Audio') price = 750;
//    else price = 300;
//
//    item.patchValue({ price });
//    this.calculateSubtotal(index);
//  }
//
//  calculateSubtotal(index: number): void {
//    const item = this.items.at(index);
//    const qty = item.get('quantity')?.value || 0;
//    const price = item.get('price')?.value || 0;
//    const subtotal = qty * price;
//    item.patchValue({ subtotal });
//    this.calculateTotal(index);
//  }
//
//  calculateTotal(index: number): void {
//    const item = this.items.at(index);
//    const subtotal = item.get('subtotal')?.value || 0;
//    const discount = item.get('discount')?.value || 0;
//    const tax = item.get('tax')?.value || 0;
//
//    const discountAmount = (subtotal * discount) / 100;
//    const taxableAmount = subtotal - discountAmount;
//    const taxAmount = (taxableAmount * tax) / 100;
//    const total = taxableAmount + taxAmount;
//
//    item.patchValue({ grandTotal: taxableAmount, total });
//    this.calculateAll();
//  }
//
//  calculateAll(): void {
//    this.subTotal = 0;
//    this.taxAmount = 0;
//    this.grandTotal = 0;
//
//    this.items.controls.forEach(item => {
//      const subtotal = item.get('subtotal')?.value || 0;
//      const discount = item.get('discount')?.value || 0;
//      const tax = item.get('tax')?.value || 0;
//      const discountAmount = (subtotal * discount) / 100;
//      const taxable = subtotal - discountAmount;
//      const taxAmount = (taxable * tax) / 100;
//
//      this.subTotal += subtotal;
//      this.taxAmount += taxAmount;
//      this.grandTotal += item.get('total')?.value || 0;
//    });
//
//    const totalPaid = this.payments.reduce((acc, p) => acc + (Number(p.paymentAmount) || 0), 0);
//    this.balance = this.grandTotal - totalPaid;
//  }
//
//  addPayment(): void {
//    if (this.paymentForm.valid) {
//      this.payments.push(this.paymentForm.value);
//      this.paymentForm.reset();
//      this.calculateAll();
//    }
//  }
//
//  removePayment(index: number): void {
//    this.payments.splice(index, 1);
//    this.calculateAll();
//  }
//
//  saveInvoice(): void {
//    const payload = {
//      clientId: this.invoiceForm.value.clientId,
//      companyId: this.invoiceForm.value.companyId,
//      items: this.invoiceForm.value.items,
//      subTotal: this.subTotal,
//      taxAmount: this.taxAmount,
//      grandTotal: this.grandTotal,
//      balance: this.balance,
//      payments: this.payments
//    };
//
//    this.http.post('/api/invoices', payload).subscribe({
//      next: (res: any) => {
//        alert('Invoice Saved Successfully ✅');
//        if (res?.invoiceId) this.downloadPDF(res.invoiceId);
//      },
//      error: () => alert('Error Saving Invoice ❌')
//    });
//  }
//
//  downloadPDF(invoiceId: string): void {
//    this.http.get(`/api/invoices/${invoiceId}/pdf`, { responseType: 'blob' }).subscribe(blob => {
//      const fileURL = window.URL.createObjectURL(blob);
//      const link = document.createElement('a');
//      link.href = fileURL;
//      link.download = `invoice-${invoiceId}.pdf`;
//      link.click();
//    });
//  }
//}
