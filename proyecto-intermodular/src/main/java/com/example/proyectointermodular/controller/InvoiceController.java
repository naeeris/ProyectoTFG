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
    private InvoiceRepository invoiceRepository;

    /*//Metodo para contar el total de facturas recibidas
    @GetMapping("/")
    public String countInvoices(Model model) {
        //Para contar el total de facturas
        long totalInvoices = invoiceRepository.count();

        //Agregar datos al modelo
        model.addAttribute("totalInvoices", totalInvoices);

        return "index";
    }*/

    //Metodo para llegar al index e incluir el total de facturas registradas en la BD
    @GetMapping("/")
    public String index(Model model){
        //Para contar el total de facturas
        long totalInvoices = invoiceRepository.count();

        //Calcular el sumatorio del total de facturas
        double totalAmount = invoiceRepository.sumTotalAmount();

        //Agregar datos al modelo
        model.addAttribute("totalInvoices", totalInvoices);
        model.addAttribute("totalAmount", totalAmount);

        return "index";
    }

    //Metodo para listar todas las facturas registradas
    @GetMapping("/invoices")
    public String findAll(Model model){
        model.addAttribute("invoices", invoiceRepository.findAll());
        return "invoice-list";

    }

    //Metodo para mostrar una factura teniendo en cuenta el ID
    @GetMapping("/invoices/view/{id}")
    public String findById(Model model, @PathVariable Long id){
        model.addAttribute("invoice", invoiceRepository.findById(id).get());
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
        invoiceRepository.save(invoice);
        return "redirect:/invoices";
    }

    //Metodo para editar una invoice a través de un formulario con informacion
    @GetMapping("/invoices/edit/{id}")
    public String editForm(Model model, @PathVariable Long id){
        //Verificamos que el ID existe en la BD
        if(invoiceRepository.existsById(id)){
            invoiceRepository.findById(id).ifPresent(invoice -> model.addAttribute("invoice", invoice));
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
        if(invoiceRepository.existsById(id)){
            invoiceRepository.findById(id).ifPresent(invoice -> {
                //Actualizamos los atributos modificables
                invoice.setDate(updatedInvoice.getDate());
                invoice.setSupplier(updatedInvoice.getSupplier());
                invoice.setNumber(updatedInvoice.getNumber());
                invoice.setAmount(updatedInvoice.getAmount());

                //Guardo la info actualizada en la BD
                invoiceRepository.save(invoice);
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
        invoiceRepository.findById(id).ifPresent(invoice -> {
            invoiceRepository.deleteById(invoice.getId());
        });

        return "redirect:/invoices";
    }



}
