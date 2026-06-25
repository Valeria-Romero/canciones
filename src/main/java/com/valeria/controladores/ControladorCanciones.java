package com.valeria.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.valeria.modelos.Cancion;
import com.valeria.servicios.ServicioCanciones;

import jakarta.validation.Valid;

@Controller
public class ControladorCanciones {

	@Autowired
	private ServicioCanciones servicio;
//	Mostrar canciones
	@GetMapping("/canciones")
	public String desplegarCanciones(Model model) {
		model.addAttribute("listaCanciones", servicio.obtenerTodasLasCanciones());
		return "canciones.jsp";
	}
	
	@GetMapping("/canciones/detalle/{idCancion}")
	public String desplegarDetalleCancion(@PathVariable("idCancion") Long idCancion, Model model) {
		Cancion cancion = servicio.obtenerCancionPorId(idCancion);
		model.addAttribute("cancion",cancion);
		return "detalleCancion.jsp";
	}
	
//	Parte agregar canciones
	@GetMapping("/canciones/formulario/agregar")
	public String formularioAgregarCancion(@ModelAttribute("cancion")Cancion cancion) {
		return "agregarCancion.jsp";
	}
	
	@PostMapping("/canciones/procesa/agregar")
	public String procesarAgregarCancion(@Valid @ModelAttribute("cancion") Cancion cancion, BindingResult result) {
//		Se revisa si hay errores
		if (result.hasErrors()) {
			return "agregarCancion.jsp";
		}
//		Se llama al servicio si no hay errores para guardar la cancion
		servicio.agregarCancion(cancion);
		return "redirect:/canciones";
	}
}
