package br.com.sicpa.cap.converter;

import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.sicpa.cap.model.Gestor;

@FacesConverter(forClass=Gestor.class, value="gestorConverter")
public class GestorConverter implements Converter {  
	  
    private static Map<Long, Gestor> cache = new HashMap<Long, Gestor>();  
  
   
	public Object getAsObject(FacesContext context, UIComponent component, String value) {  
        if (value == null || value.equals("")) {  
            return null;  
        }  
        try {  
            return cache.get(Long.parseLong(value));  
        } catch (NumberFormatException e) {  
            throw new ConverterException("Invalid value: " + value, e);  
        }  
    }  
  
    public String getAsString(FacesContext context, UIComponent component, Object object) {  
    	
    	
    	
    	if(object instanceof Gestor) {
    		Gestor gestor = (Gestor) object;  
            Long id = (long) gestor.getIdGestor();  
            cache.put(id, gestor);  
            return String.valueOf(id.longValue());  
        
		}else{
			return null;
		}
		
    		
    	}
        
}

	
 
  
