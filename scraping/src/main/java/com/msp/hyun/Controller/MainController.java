package com.msp.hyun.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.msp.hyun.Service.ScrapingService;


@RestController
@RequestMapping("/scraping")
public class MainController {
	
	private ModelAndView mav;
	
	@Autowired
	private ScrapingService ss;
	
	@GetMapping("list")
	public void getList() {
		//mav = new ModelAndView();
		//mav.setViewName("home");
		//return mav;
		ss.getStockPriceList();
	}
	
	@GetMapping("login")
	public ModelAndView loginPage() {
		mav = new ModelAndView();
		mav.setViewName("home");
		return mav;
	}
}
