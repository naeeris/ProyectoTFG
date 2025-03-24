package com.example.proyectointermodular;

import com.example.proyectointermodular.model.Invoice;
import com.example.proyectointermodular.repository.InvoiceRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class ProyectoIntermodularApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(ProyectoIntermodularApplication.class, args);
        System.out.println("Aplicación Arrancada");

        var repository = context.getBean(InvoiceRepository.class);

        List<Invoice> invoices = List.of(
                new Invoice(null, LocalDate.now(), LocalDate.of(2025,03,15), "Merck Life", "20254879", 154.63),
                new Invoice(null, LocalDate.now(), LocalDate.of(2025,02,02), "Life Technologies", "RI_20257857", 1024.87),
                new Invoice(null, LocalDate.now(), LocalDate.of(2025,01,31), "Linde Gas", "FV-587489", 65.97),
                new Invoice(null, LocalDate.now(), LocalDate.of(2025, 02, 27), "Inditex", "25/41257", 350.00)
        );
        repository.saveAll(invoices);


    }

}
