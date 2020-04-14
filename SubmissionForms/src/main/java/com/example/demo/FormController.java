package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormController {
	
	@Autowired
	CustomersRepo repo;
	
	@RequestMapping("/")
	public String Edureka() {
		return "edureka";
	}
	
	@RequestMapping("/details")
	public String details(Customers customers) {
		repo.save(customers);
		return "edureka";
	}
	
	@RequestMapping("/getdetails")
	public String getdetails(Customers customers) {
		
		return "viewcustomer";
	}
	
	@PostMapping("/getdetails")
	public ModelAndView getdetails(@RequestParam int cid) {
		ModelAndView mv = new ModelAndView("retrieve");
		Customers customers = repo.findById(cid).orElse(null);
		mv.addObject(customers);
		
		
		return mv;
	}
	
	@RequestMapping("/customers")
	@ResponseBody
	public List<Customers> getcustomers() {
		
		return repo.findAll();
		
	}
	
	@RequestMapping("/customers/2")
	@ResponseBody
	public Optional<Customers> getcustomers2() {
		return repo.findById(102);
	}
	
	@RequestMapping("/customers/{cid}")
	@ResponseBody
	public Optional<Customers> getCustbyId(@PathVariable("cid") int cid) {
		return repo.findById(cid);
		
	}
	
	@PostMapping("/customers")
	public Customers postcustomers(@RequestBody Customers customers) {
		repo.save(customers);
		return customers; 
	}
	
	@DeleteMapping("/customers/{cid}")
	public Customers deletecustomers(@PathVariable("cid") int cid) {
		Customers cust = repo.getOne(cid);
		repo.delete(cust);
		return cust;
	}
	
	@PutMapping(path = "/customers" ,consumes = "Application/Json")
	public Customers updatecustomers(@RequestBody Customers customers) {
		repo.save(customers);
		return customers;
	}
	
	

//	@PostMapping("details")
//		public String viewdetails(@RequestParam("cid") String cid,@RequestParam("cname") String cname, 
//				@RequestParam("cemail") String cemail, ModelMap modelmap) {
//		
//		modelmap.put("cid",cid);
//		modelmap.put("cname", cname);
//		modelmap.put("cemail", cemail);
//			
//			
//			
//			
//			return "viewcustomer";
//		}
	}
	
	


