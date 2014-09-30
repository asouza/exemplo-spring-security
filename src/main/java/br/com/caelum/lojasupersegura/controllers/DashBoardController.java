package br.com.caelum.lojasupersegura.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dash")
public class DashBoardController {
	
	@RequestMapping("/index")
	public String index(){
		return "dash/index";
	}

	@RequestMapping("/ultimas-compras")
	public String ultimasCompras(){
		return "dash/ultimas-compras";
	}
	
	@RequestMapping("/mais-vendidos")
	public String maisVendidos(int ano){
		return "dash/mais-vendidos";
	}
	
	
	@RequestMapping("/menos-vendidos")
	public String menosVendidos(int ano){
		return  "dash/menos-vendidos";
	}
}
