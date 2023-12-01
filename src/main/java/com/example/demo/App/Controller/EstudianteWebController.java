package com.example.demo.App.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.App.Entity.Estudiantes;
import com.example.demo.App.Exception.NotFoundException;
import com.example.demo.App.Repository.EstudianteRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "estudiante")
public class EstudianteWebController {
	@Autowired
    private EstudianteRepository estudianteRepository;


	  @GetMapping("")
	    public String EstudianteListTemplate(Model model) {
	        model.addAttribute("estudiante", estudianteRepository.findAll());
	        return "Coordinador_list";
	    }

	    @GetMapping("/new")
	    public String EstudianteNewTemplate(Model model) {
	        model.addAttribute("estudiante", new Estudiantes());
	        return "form-edit";
	    }

	    @GetMapping("/edit/{id}")
	    public String EstudianteEditTemplate(@PathVariable("id") String id, Model model) {
	        model.addAttribute("estudiante",estudianteRepository.findById(id).orElseThrow(() -> new NotFoundException("Estudiante no encontrado")));
	        return "asociaciones-edit";
	    }

	    @PostMapping("/save")
	    public String EstudianteSaveProcess(@ModelAttribute("estudiante") Estudiantes estudiante, Model model) {
	    	if (estudiante.getId().isEmpty()) {
	    		estudiante.setId(null);
	        }
	    	estudianteRepository.save(estudiante);
	        return "redirect:/coordinador";
	    }
	    
	    @PostMapping("/save/edit")
	    public String EstudianteSaveEditProcess(@ModelAttribute("estudiante") Estudiantes estudiante, Model model) {
	    	
	    	if (estudiante.getId().isEmpty()) {
	    		estudiante.setId(null);
	        }
	    	estudianteRepository.save(estudiante);
	        return "redirect:/asociaciones";
	    }


		@GetMapping(value = "/veree/{estid}")
		public String vere(@PathVariable(value = "estid") Long estid, Map<String, Object> model, RedirectAttributes flash) {
		    Estudiantes estudiante = estudianteRepository.findByestid(estid);
		    
		    if (estudiante == null) {
		        return "redirect:/listar-est";
		    }
		    
		    
		   
		    
		    Long puntajeCiencias = estudiante.getPuntaje_ciencias();
		    Long puntajeIngles = estudiante.getPuntaje_ingles();
		    Long puntajeDiseno = estudiante.getPuntaje_dis();
		    Long puntajeCompetencias = estudiante.getPuntaje_competencias();
		    Long puntajeComunicacion = estudiante.getPuntaje_comunicacion();
		    Long puntajeLectura = estudiante.getPuntaje_lectura();
		    Long puntajeProyectos = estudiante.getPuntaje_proyectos();
		    Long puntajeRaz = estudiante.getPuntaje_razonamiento();
		    Long niveingles = estudiante.getNivel_ingles();
		    
		    String levelingles = nivelIngles(niveingles);
		    String nivelCiencias = asignarNivel(puntajeCiencias);
		    String nivelIngles = asignarNivel(puntajeIngles);
		    String nivelDiseno = asignarNivel(puntajeDiseno);
		    String nivelCompetencias = asignarNivel(puntajeCompetencias);
		    String nivelComunicacion = asignarNivel(puntajeComunicacion);
		    String nivelLectura = asignarNivel(puntajeLectura);
		    String nivelProyectos = asignarNivel(puntajeProyectos);
		    String nivelRaz = asignarNivel(puntajeRaz);
		    
		    
		    Long promedio = calcularPromedio(
				    puntajeCiencias, puntajeIngles, puntajeDiseno,
				    puntajeCompetencias, puntajeComunicacion, puntajeLectura
				);
		    
		    Long ppromedio = promedio;
		    String nivelPromedio = asignarNivel(promedio);
		    
		    model.put("promedioPuntajes", ppromedio);
		    model.put("nivelPromedio", nivelPromedio);
		    model.put("estudiante", estudiante);
		    model.put("nivelCiencias", nivelCiencias);
		    model.put("nivelIngles", nivelIngles);
		    model.put("nivelDiseno", nivelDiseno);
		    model.put("nivelCompetencias", nivelCompetencias);
		    model.put("nivelComunicacion", nivelComunicacion );
		    model.put("nivelLectura", nivelLectura);
		    model.put("nivelProyectos", nivelProyectos);
		    model.put("nivelRaz", nivelRaz);
		    model.put("nivelingles", levelingles);
		    
		    model.put("titulo", "Informe estudiantes: " + estudiante.getNombre());

		    return "verest";
		}

		
		
		private String asignarNivel(Long puntaje) {
			 if (puntaje == null) {
			        return "Nivel no definido";
			    }

			    puntaje = puntaje == null ? 1L : puntaje;
		    if (puntaje > 1 && puntaje <= 120) {
		        return "Nivel 1";
		    } else if (puntaje > 120 && puntaje <=150) {
		        return "Nivel 2";
		    }else if(puntaje >150){
		    	return "Nivel 3";
		    } else {
		        return "Nivel no definido";
		    }
		}
		
		
		private String nivelIngles(Long level) {
			 if (level == null) {
			        return "Nivel no definido";
			    }

			    level = level == null ? 1L : level;
		    if (level == 1) {
		        return "Nivel A - Bajo";
		    } else if (level == 2 ) {
		        return "Nivel B - Medio";
		    }else if(level == 3){
		    	return "Nivel C - Alto";
		    } else {
		        return "Nivel no definido";
		    }
		}
		
		

		public Long calcularPromedio(Long... puntajes) {
		    if (puntajes.length == 0) {
		        return null;
		    }
		    
		    long suma = 0;
		    int cantidadPuntajes = 0;
		    for (Long puntaje : puntajes) {
		        if (puntaje != null) {
		            suma += puntaje;
		            cantidadPuntajes++;
		        }
		    }
		    
		    if (cantidadPuntajes == 0) {
		        return null;
		    }

		    return suma / cantidadPuntajes;
		}

		
		
		 
		@GetMapping("/login")
	    public String loginForm() {
	        return "login-Est";}

		@PostMapping("/login")
		public String loginSubmit(@RequestParam Long estid, @RequestParam String password, Model model, HttpSession session) {
		    Estudiantes estudiante = estudianteRepository.findByestid(estid);

		    if (estudiante != null && estudiante.getPassword().equals(password)) {
		        session.setAttribute("estudiante", estudiante);
		        return "redirect:/estudiante/veree/"+estid;
		    } else {
		        model.addAttribute("error", "Credenciales inválidas!");
		        return "login-Est";
		    }
		}



	

	    
	    
	}