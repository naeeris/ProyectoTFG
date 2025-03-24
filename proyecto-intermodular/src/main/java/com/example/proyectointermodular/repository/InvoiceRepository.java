package com.example.proyectointermodular.repository;

import com.example.proyectointermodular.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    //Metodo para poder darle un id y que te proporcione el id que estas buscando
    List<Invoice> findAllById(@Param("id") Long id);

    //Metodo para contar todas las facturas
    long count();

    //Metodo para sumar el total de los importes de la facturas registrados en la BD
    @Query("SELECT SUM(amount) FROM Invoice ")
    double sumTotalAmount();
}
