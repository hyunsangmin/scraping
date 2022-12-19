package com.msp.hyun.Controller;

import java.util.ArrayList;
import java.util.Map;

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
	
	@GetMapping("home")
	public ModelAndView getHome() {
		mav = new ModelAndView();
		mav.setViewName("home");
		return mav;
	}
	
	@GetMapping("list")
	public ArrayList<Map<String,String>> getList() {
		//mav = new ModelAndView();
		//mav.setViewName("home");
		//return mav;
		return ss.getStockPriceList();
	}
	
	@GetMapping("login")
	public ModelAndView loginPage() {
		mav = new ModelAndView();
		mav.setViewName("home");
		return mav;
	}
}
