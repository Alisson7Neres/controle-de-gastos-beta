package com.controledegastos.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
	
	Usuario usuario;
	
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
	public String logado(Usuario usuario,@RequestParam ("email") String email,@RequestParam("senha") String senha) throws Exception {
		
		Usuario usuarioLogado = repository.findByLogin(usuario.getEmail(), usuario.getSenha());
		
		// Verifica o email e a senha, se for autenticado será redirecionado
		if(usuarioLogado != null) {
			return "usuarioLogado";
		}
		
		return "usuarioLogado";
	}
}
