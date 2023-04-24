package com.api.eccom.model;

import lombok.Data;

import java.util.List;

@Data
public class InvoiceList {
    public List<Invoice_Details> invoiceDetailList;

    public Client client;
}
