package com.amstech.invoice.service.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentMethod {
    CASH, 
    CARD, 
    UPI, 
    BANK_TRANSFER,
    CHEQUE;
}

