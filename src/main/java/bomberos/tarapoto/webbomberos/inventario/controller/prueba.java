package bomberos.tarapoto.webbomberos.inventario.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import bomberos.tarapoto.webbomberos.utils.resolvePath;

@Controller
@RequestMapping("/intranet/inventario")
public class prueba {
    
    @GetMapping("/prueba")
    public String prueba(Model model) {
        

        // AGREGAR ATRIBUTOS A LA VISTA APARTE DE LOS QUE SE ENVIAN EN EL MODELO
        
        // DEFINE LA  RUTA DE NAVEGACION EN LA VISTA
        List<Map<String, String>> ruta = resolvePath.getPath(new ArrayList<>(List.of("Inventario", "Prueba", "Nuevo")));
        
        // TITULO DE LA VISTA
        model.addAttribute("titulo", "SGACB - Gestión de inventario");
        
        // CONTROLA EL MENU ACTIVO
        model.addAttribute("moduloActivo", "inventario");
        
        // DEFINE LOS ENLACES PARA LAS RUTAS
        model.addAttribute("ruta", ruta);
        return "intranet/Inventario/prueba";
    }
}
