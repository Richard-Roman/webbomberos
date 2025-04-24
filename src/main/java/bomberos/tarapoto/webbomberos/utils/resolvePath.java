package bomberos.tarapoto.webbomberos.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class resolvePath {
    public static List<Map<String, String>> getPath(List<String> pathList) {
    List<Map<String, String>> rutas = new ArrayList<>();
    String acumulado = "";
    for (int i = 0; i < pathList.size(); i++) {
        acumulado += "/" + pathList.get(i).toLowerCase();
        Map<String, String> map = new HashMap<>();
        map.put("nombre", pathList.get(i));
        map.put("url", acumulado);
        rutas.add(map);
    }
    System.out.println("Rutas generadas: " + rutas.toString());
        return rutas;
    }
}
