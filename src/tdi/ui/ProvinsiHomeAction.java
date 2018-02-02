/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdi.ui;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tdi.repo.ProvinsiDAO;
import tdi.model.Provinsi;

/**
 *
 * @author St0rm
 */
@Controller
public class ProvinsiHomeAction {
    
    @Autowired       
    ProvinsiDAO provinsidao;
    
            @RequestMapping(path= "/provinsishow",method=RequestMethod.GET)    
	    public String showProvinsi(ModelMap model){
	        //Provinsi p = new Provinsi(3,63,name);
	        Provinsi p = provinsidao.getProvinsiByID(2);
                List<Provinsi> provList = provinsidao.getAllProvinsi();
                model.addAttribute("provinsi", p);
                model.addAttribute("listprov", provList);
	        return "provinsihal";
	    }
}
