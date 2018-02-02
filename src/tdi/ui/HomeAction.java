package tdi.ui;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tdi.model.Provinsi;

@Controller
public class HomeAction {
           //pendekatan rest
    
	   @RequestMapping(path= "/greet/{name}",method=RequestMethod.GET)    
	    public String greet(@PathVariable String name, ModelMap model){
	        String greet =" Hello !!! " + name + ", For the King and Country";
	        model.addAttribute("greet", greet);
	        System.out.println(greet);
	        return "halo";
	    }
            @RequestMapping(path= "/berandadua",method=RequestMethod.GET)    
	    public String fungsiKedua(HttpServletRequest request){
	        String tankName = request.getParameter("tank");
                request.setAttribute("tankVar", tankName);
                return "beranda";
	    }
            @RequestMapping(path= "/kelurahan/{name}",method=RequestMethod.GET)    
	    public String getProvinsi(@PathVariable String name, ModelMap model){
	        Provinsi p = new Provinsi(3,63,name);
	        model.addAttribute("provinsi", p);
	        return "halo";
	    }
}

 
 