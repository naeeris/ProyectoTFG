package com.example.proyectointermodular.model;

import jakarta.persistence.*;

import java.io.File;
import java.time.LocalDate;

//Anotaciones importantes
@Entity
@Table(name = "invoices")
public class Invoice {

    //Se crean los atributos de la entidad Invoice
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //La anotacion @Column no es necesaria pero así indicamos el nombre de la columna
    @Column(name = "invoice_reg_date", nullable = false)
    private LocalDate reg_date = LocalDate.now();

    @Column(name = "invoice_date", nullable = false)
    private LocalDate date;

    @Column(name = "invoice_supplier", nullable = false)
    private String supplier;

    @Column(name = "invoice_number", nullable = false)
    private String number;

    @Column(name = "invoice_amount", nullable = false)
    private Double amount;

    //Generamos el constructor vacio
    public Invoice() {
    }

    //Generamos el constructor con todos los atributos
    public Invoice(Long id, LocalDate reg_date, LocalDate date, String supplier, String number, Double amount) {
        this.id = id;
        this.reg_date = reg_date;
        this.date = date;
        this.supplier = supplier;
        this.number = number;
        this.amount = amount;
    }

    //Generamos todos los Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getReg_date() {
        return reg_date;
    }

    public void setReg_date(LocalDate reg_date) {
        this.reg_date = reg_date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    //Generamos el toString de la clase
    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", reg_date=" + reg_date +
                ", date=" + date +
                ", supplier='" + supplier + '\'' +
                ", number='" + number + '\'' +
                ", amount=" + amount +
                '}';
    }
}
