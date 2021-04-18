package com.controledegastos.controler;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controledegastos.model.Usuario;
import com.controledegastos.repository.UsuarioRepository;

@Controller
@RequestMapping("/*")
public class GastosController {
	
	@Autowired
	UsuarioRepository repository;
	
	@PostMapping("**/salvarUsuario")
	public ModelAndView enviar( Usuario usuario) {
		ModelAndView andView = new ModelAndView("index");
		Usuario usuarioSalvar = repository.save(usuario);
		andView.addObject("usuarios", usuarioSalvar);
		andView.addObject("usuarioobj", usuarioSalvar );
		return andView;
	}
	
	@GetMapping("/loginUsuario")
	public ModelAndView logar() {
		ModelAndView andView = new ModelAndView("/loginUsuario");
		
		return andView;
	}
	
	@PostMapping("/usuarioLogado")
	public ModelAndView logado(Usuario usuario,@RequestParam ("email") String email,@RequestParam("senha") String senha) {
		ModelAndView andView = new ModelAndView("/usuarioLogado");
		
		Optional<Usuario> usuarioLogado = repository.findById(usuario.getId());
	
		if(usuarioLogado != null) {
		 usuarioLogado = Optional.of((Usuario) repository.findByLogin(email, senha));
			return andView;
		}
		else {
			System.out.println("ERROUR");
		}
		return andView;
	}
}
