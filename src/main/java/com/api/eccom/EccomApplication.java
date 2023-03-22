package com.api.eccom;

import com.api.eccom.repository.ClientRepository;
import com.api.eccom.repository.InvoiceRepository;
import com.api.eccom.repository.Invoice_DetailsRepository;
import com.api.eccom.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EccomApplication implements CommandLineRunner {
	@Autowired
	ClientRepository clientRepository;

	@Autowired
	InvoiceRepository invoiceRepository;

	@Autowired
	Invoice_DetailsRepository invoice_DetailsRepository;

	@Autowired
	ProductsRepository productsRepository;

	public static void main(String[] args) {
		SpringApplication.run(EccomApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("LISTADO DE CLIENTES: "+ clientRepository.findAll());
		System.out.println("LISTADO DE FACTUAS: "+ invoiceRepository.findAll());
		System.out.println("LISTADO DE DETALLES: "+ invoice_DetailsRepository.findAll());
		System.out.println("LISTADO DE PRODUCTOS: "+ productsRepository.findAll());
	}
}
