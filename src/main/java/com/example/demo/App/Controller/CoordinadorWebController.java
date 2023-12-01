package com.example.demo.App.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.App.Entity.Coordinador;
import com.example.demo.App.Entity.Estudiantes;
import com.example.demo.App.Exception.NotFoundException;
import com.example.demo.App.Repository.CoordinadorRepository;
import com.example.demo.App.Repository.EstudianteRepository;

import jakarta.servlet.http.HttpSession;





@Controller
@RequestMapping(value = "coordinador")
public class CoordinadorWebController {
	@Autowired
    private CoordinadorRepository coordinadorRepository;
	@Autowired
    private EstudianteRepository estudianteRepository;
	@Autowired
	private MongoTemplate mongotemplate;

	  @GetMapping("")
	    public String CoordinadoresListTemplate(Model model) {
	        model.addAttribute("coordinador", coordinadorRepository.findAll());
	        return "Coordinador_list";
	    }


		@GetMapping("/listar-estudiantes")
		public String listarEstudiantes(Model model) {
		    List<Estudiantes> estudiantes = estudianteRepository.findAll();
		    model.addAttribute("estudiante", estudiantes);	  
		    Map<String, Long> notasPromedioMap = new HashMap<>();
		    Map<String, String> nivelp = new HashMap<>();
		    for (Estudiantes estudiante : estudiantes) {
		        Long notaPromedio = calcularPromedio(
		            estudiante.getPuntaje_ciencias(),
		            estudiante.getPuntaje_ingles(),
		            estudiante.getPuntaje_dis(),
		            estudiante.getPuntaje_competencias(),
		            estudiante.getPuntaje_comunicacion(),
		            estudiante.getPuntaje_lectura(),
		            estudiante.getPuntaje_proyectos(),
		            estudiante.getPuntaje_razonamiento()	            
		           	
		        );
		        Long notapfinal = notaPromedio;
		        String nivelPromedio = asignarNivel(notapfinal);
		        notasPromedioMap.put(estudiante.getId(), notapfinal);
		        nivelp.put(estudiante.getId(), nivelPromedio);
		    }

		    model.addAttribute("notasPromedioMap", notasPromedioMap);
		    model.addAttribute("nivelp", nivelp);

		    return "listar-coor";
		}
		
		
		
		
		

		@GetMapping(value = "/verc/{id}")
		public String ver(@PathVariable(value = "id") String id, Map<String, Object> model, RedirectAttributes flash) {
			Query query = new Query(Criteria.where("id").is(id));
		    Estudiantes estudiante = mongotemplate.findOne(query, Estudiantes.class);
		    
		    if (estudiante == null) {
		        return "redirect:/listar-estudiantes";
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

		    String nivelCiencias = asignarNivel(puntajeCiencias);
		    String nivelIngles = asignarNivel(puntajeIngles);
		    String nivelDiseno = asignarNivel(puntajeDiseno);
		    String nivelCompetencias = asignarNivel(puntajeCompetencias);
		    String nivelComunicacion = asignarNivel(puntajeComunicacion);
		    String nivelLectura = asignarNivel(puntajeLectura);
		    String nivelProyectos = asignarNivel(puntajeProyectos);
		    String nivelRaz = asignarNivel(puntajeRaz);
		    String levelingles = nivelIngles(niveingles);
		    
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

		    return "verestc";
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

		
		
		
		
		
	  
	    @GetMapping("/new")
	    public String CoordinadorNewTemplate(Model model) {
	        model.addAttribute("coordinador", new Coordinador());
	        return "form-edit";
	    }
	    
	    
	    @GetMapping("/newe")
	    public String EstudianteNewTemplate(Model model) {
	        model.addAttribute("estudiante", new Estudiantes());
	        return "form-addest";
	    }

	    @GetMapping("/edit/{id}")
	    public String EstudianteEditTemplate(@PathVariable("id") String id, Model model) {
	        model.addAttribute("estudiante",estudianteRepository.findById(id).orElseThrow(() -> new NotFoundException("Estudiante no encontrado")));
	        return "edit-est";
	    }

	    @PostMapping("/save")
	    public String CoordinadorSaveProcess(@ModelAttribute("coordinador") Coordinador coordinador, Model model) {
	    	if (coordinador.getId().isEmpty()) {
	    		coordinador.setId(null);
	        }
	    	coordinadorRepository.save(coordinador);
	        return "redirect:/coordinador";
	    }
	    
	    @PostMapping("/formc")
	    public String EstudianteSaveProcess(@ModelAttribute("estudiante") Estudiantes estudiantes, Model model) {
	    	if (estudiantes.getId().isEmpty()) {
	    		estudiantes.setId(null);
	        
	    	estudianteRepository.save(estudiantes);
	        return "redirect:/coordinador/listar-estudiantes";
	    	} 
	        else {
	        	return "form-addest";
	        }
	    }
	    
	    
	    
	    @PostMapping("/save/edit")
	    public String estudianteSaveEditProcess(@ModelAttribute("estudiante") Estudiantes estudiante, Model model) {
	    	
	    	if (estudiante.getId().isEmpty()) {
	    		estudiante.setId(null);
	        }
	    	estudianteRepository.save(estudiante);
	        return "redirect:/coordinador/listar-estudiantes";
	    }


	    @GetMapping("/delete/{id}")
	    public String estudianteDeleteProcess(@PathVariable("id") String id) {
	    	estudianteRepository.deleteById(id);
	        return "redirect:/coordinador/listar-estudiantes";
	    }
	    


		
		
		 
		@GetMapping("/login")
	    public String loginForm() {
	        return "login-coor";}

	    @PostMapping("/login")
	    public String loginSubmit(@RequestParam Long Coorid, @RequestParam String password, Model model, HttpSession session) {
	        Coordinador coordinador = coordinadorRepository.findBycoorid(Coorid);

	        if (coordinador != null && coordinador.getPassword().equals(password)) {
	            session.setAttribute("coordinador", coordinador);        	
	            return "redirect:/coordinador/listar-estudiantes";
	        } else {
	            model.addAttribute("error", "Credenciales inválidas");
	            return "login-coor";
	        }
	    }
	    
	    
	}

