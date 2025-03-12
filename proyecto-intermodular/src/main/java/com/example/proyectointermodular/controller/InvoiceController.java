package com.example.proyectointermodular.controller;

import com.example.proyectointermodular.model.Invoice;
import com.example.proyectointermodular.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InvoiceController {

    //Metodo que nos permite extraer los datos de la BD
    @Autowired
    private InvoiceRepository repository;

    //Metodo para redireccionar el directorio al listado de facturas
    @GetMapping("/")
    public String index(){
        return "redirect:/invoices";
    }

    //Metodo para listar todas las facturas registradas
    @GetMapping("/invoices")
    public String findAll(Model model){
        model.addAttribute("invoices", repository.findAll());
        return "invoice-list";

    }

    //Metodo para mostrar una factura teniendo en cuenta el ID
    @GetMapping("/invoices/{id}")
    public String findById(Model model, @PathVariable Long id){
        model.addAttribute("invoice", repository.findById(id));
        return "invoice-view";
    }

    //Metodo para obtener el formulario vacío
    @GetMapping("/invoices/form")
    public String getForm(Model model){
        model.addAttribute("invoice", new Invoice());
        return "invoice-form";

    }

    //Metodo para crear una invoice
    @PostMapping("/invoices")
    public String createInvoice(@ModelAttribute Invoice invoice){
        repository.save(invoice);
        return "redirect:/invoices";
    }

    //Metodo para editar una invoice a través de un formulario con informacion
    @GetMapping("/invoices/edit/{id}")
    public String editForm(Model model, @PathVariable Long id){
        //Verificamos que el ID existe en la BD
        if(repository.existsById(id)){
            repository.findById(id).ifPresent(invoice -> model.addAttribute("invoice", invoice));
            return "invoice-form";
        }else{
            //Si no encuentra el ID, redirige al formulario vacio para crear una nueva factura
            return "redirect:/invoices/form";
        }

    }

    //Metodo para actualizar la informacion una factura en BD
    @PostMapping("/invoices/update/{id}")
    public String updateInvoice(@ModelAttribute Invoice updatedInvoice, @PathVariable Long id){
        //Si el ID esta en BD -> actualiza la siguiente info
        if(repository.existsById(id)){
            repository.findById(id).ifPresent(invoice -> {
                //Actualizamos los atributos modificables
                invoice.setDate(updatedInvoice.getDate());
                invoice.setSupplier(updatedInvoice.getSupplier());
                invoice.setNumber(updatedInvoice.getNumber());
                invoice.setAmount(updatedInvoice.getAmount());

                //Guardo la info actualizada en la BD
                repository.save(invoice);
            });

            return "redirect:/invoices";

        }else{

            //Si no encuentra el ID, redirige al formulario vacio para crear una nueva factura
            return "redirect:/invoices/form";
        }
    }

    //Metodo para eliminar una invoice teneindo en cuenta el ID
    @GetMapping("/invoices/delete/{id}")
    public String deleteById(@PathVariable Long id){
        repository.findById(id).ifPresent(invoice -> {
            repository.deleteById(invoice.getId());
        });

        return "redirect:/invoices";
    }



}
