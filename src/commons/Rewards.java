package commons;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * Clase que gestiona las recompensas
 */
public class Rewards {
    /**
     * Ruta donde se almacenarán las recompensas
     */
    private static final String REWARDS_DIRECTORY = "rewards/";

    /**
     * Crea la plantilla inicial de XML de las recompensas
     * 
     * @return La plantilla XML
     */
    public Document generarXML() {
        Document document = DocumentHelper.createDocument();
        Element raiz = document.addElement("reward");
        raiz.addElement("name").addText("");
        raiz.addElement("origin").addText("Diego");
        raiz.addElement("desc").addText("");
        raiz.addElement("rarity").addText("");
        raiz.addElement("give");
        raiz.addElement("quantity").addText("1");
        return document;
    }

    /**
     * Actualiza el archivo existente aumentando en uno su cantidad
     * 
     * @param nombreArchivo El archivo a actualizar
     */
    private void actualizarArchivoXML(String nombreArchivo) {
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(REWARDS_DIRECTORY + nombreArchivo + ".xml");
            Element quantityElement = document.getRootElement().element("quantity");
            if (quantityElement != null) {
                int cantidadActual = Integer.parseInt(quantityElement.getTextTrim());
                quantityElement.setText(Integer.toString(cantidadActual + 1));
                guardarDocumentoXML(document, nombreArchivo);
            } else {
                Simulador.escribirError("Error al modificar la etiqueta quantity del archivo rewards");
            }
        } catch (Exception e) {
            Simulador.escribirError("Error al actualizar el archivo rewards");
        }
    }

    /**
     * Comprueba si el archivo ya existe
     * 
     * @param nombre Nombre de la recompensa
     * @return Si el archivo existe o no
     */
    private boolean existeArchivo(String nombre) {
        File carpeta = new File(REWARDS_DIRECTORY);
        if (carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    if (archivo.isFile() && archivo.getName().startsWith(nombre)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Crea una recompensa de comida
     * 
     * @param numero El nivel de recompensa
     */
    public void generarComida(int numero) {
        if (existeArchivo("comida_" + numero)) {
            actualizarArchivoXML("comida_" + numero);
        } else {
            Document document = generarXML();
            String nombre = "";
            String descripcion = " unidades de pienso multipropósito para todo tipo de peces.";
            int foodType = 0;
            int rarity = 0;
            Element raiz = document.getRootElement();
            switch (numero) {
                case 1:
                    nombre = "I";
                    foodType = 50;
                    descripcion = foodType + descripcion;
                    rarity = 0;
                    break;
                case 2:
                    nombre = "II";
                    foodType = 100;
                    descripcion = foodType + descripcion;
                    rarity = 1;
                    break;
                case 3:
                    nombre = "III";
                    foodType = 250;
                    descripcion = foodType + descripcion;
                    rarity = 2;
                    break;
                case 4:
                    nombre = "IV";
                    foodType = 500;
                    descripcion = foodType + descripcion;
                    rarity = 3;
                    break;
                case 5:
                    nombre = "V";
                    foodType = 1000;
                    descripcion = foodType + descripcion;
                    rarity = 4;
                    break;
            }
            for (Object obj : raiz.elements()) {
                if (obj instanceof Element) {
                    Element elemento = (Element) obj;
                    if ("name".equals(elemento.getName())) {
                        elemento.setText("Comida general " + nombre);
                    } else if ("desc".equals(elemento.getName())) {
                        elemento.setText(descripcion);
                    } else if ("rarity".equals(elemento.getName())) {
                        elemento.setText(Integer.toString(rarity));
                    } else if ("give".equals(elemento.getName())) {
                        Element give = (Element) obj;
                        give.addElement("food").addAttribute("type", "general").addText(Integer.toString(foodType));
                    }
                }
            }
            guardarDocumentoXML(document, "comida_" + numero);
            Simulador.registros.escribirTranscripcion("Recompensa comida_" + nombre + " creada");
        }
    }

    /**
     * Crea una recompensa de monedas
     * 
     * @param numero El nivel de recompensa
     */
    public void generarMonedas(int numero) {
        if (existeArchivo("monedas_" + numero)) {
            actualizarArchivoXML("monedas_" + numero);
        } else {
            Document document = generarXML();
            String nombre = "";
            String descripcion = " monedas";
            int coins = 0;
            int rarity = 0;
            Element raiz = document.getRootElement();
            switch (numero) {
                case 1:
                    nombre = "I";
                    coins = 100;
                    descripcion = coins + descripcion;
                    rarity = 0;
                    break;
                case 2:
                    nombre = "II";
                    coins = 300;
                    descripcion = coins + descripcion;
                    rarity = 1;
                    break;
                case 3:
                    nombre = "III";
                    coins = 500;
                    descripcion = coins + descripcion;
                    rarity = 2;
                    break;
                case 4:
                    nombre = "IV";
                    coins = 750;
                    descripcion = coins + descripcion;
                    rarity = 3;
                    break;
                case 5:
                    nombre = "V";
                    coins = 1000;
                    descripcion = coins + descripcion;
                    rarity = 4;
                    break;
            }
            for (Object obj : raiz.elements()) {
                if (obj instanceof Element) {
                    Element elemento = (Element) obj;
                    if ("name".equals(elemento.getName())) {
                        elemento.setText("Monedas " + nombre);
                    } else if ("desc".equals(elemento.getName())) {
                        elemento.setText(descripcion);
                    } else if ("rarity".equals(elemento.getName())) {
                        elemento.setText(Integer.toString(rarity));
                    } else if ("give".equals(elemento.getName())) {
                        Element give = (Element) obj;
                        give.addElement("coins").addText(Integer.toString(coins));
                    }
                }
            }
            guardarDocumentoXML(document, "monedas_" + numero);
            Simulador.registros.escribirTranscripcion("Recompensa monedas_" + nombre + " creada");
        }
    }

    /**
     * Crea una recompensa de almacen central
     * 
     * @param letra El nivel de recompensa
     */
    public void generarAlmacen(char letra) {
        if (existeArchivo("almacen_" + Character.toLowerCase(letra))) {
            actualizarArchivoXML("almacen_" + Character.toLowerCase(letra));
        } else {
            Document document = generarXML();
            String descripcion = "Materiales para la construcción de un almacén central. Con la parte A, B, C y D, puedes obtenerlo de forma gratuita.";
            int codigo = 4;
            int rarity = 3;
            Element raiz = document.getRootElement();
            for (Object obj : raiz.elements()) {
                if (obj instanceof Element) {
                    Element elemento = (Element) obj;
                    if ("name".equals(elemento.getName())) {
                        elemento.setText("Almacén central [" + String.valueOf(Character.toUpperCase(letra)) + "]");
                    } else if ("desc".equals(elemento.getName())) {
                        elemento.setText(descripcion);
                    } else if ("rarity".equals(elemento.getName())) {
                        elemento.setText(Integer.toString(rarity));
                    } else if ("give".equals(elemento.getName())) {
                        Element give = (Element) obj;
                        give.addElement("building").addAttribute("code", Integer.toString(codigo))
                                .addText("Almacén central");
                        give.addElement("part").addText(String.valueOf(Character.toUpperCase(letra)));
                        give.addElement("total").addText("ABCD");
                    }
                }
            }
            guardarDocumentoXML(document, "almacen_" + Character.toLowerCase(letra));
            Simulador.registros.escribirTranscripcion("Recompensa almacen_" + Character.toLowerCase(letra) + " creada");
        }
    }

    /**
     * Crea una recompensa de piscifactoria de mar
     * 
     * @param letra El nivel de recompensa
     */
    public void generarPiscifactoriaMar(char letra) {
        if (existeArchivo("pisci_m_" + Character.toLowerCase(letra))) {
            actualizarArchivoXML("pisci_m_" + Character.toLowerCase(letra));
        } else {
            Document document = generarXML();
            String descripcion = "Materiales para la construcción de una piscifactoría de mar. Con la parte A y B, puedes obtenerla de forma gratuita.";
            int codigo = 1;
            int rarity = 4;
            Element raiz = document.getRootElement();
            for (Object obj : raiz.elements()) {
                if (obj instanceof Element) {
                    Element elemento = (Element) obj;
                    if ("name".equals(elemento.getName())) {
                        elemento.setText("Almacén central [" + String.valueOf(Character.toUpperCase(letra)) + "]");
                    } else if ("desc".equals(elemento.getName())) {
                        elemento.setText(descripcion);
                    } else if ("rarity".equals(elemento.getName())) {
                        elemento.setText(Integer.toString(rarity));
                    } else if ("give".equals(elemento.getName())) {
                        Element give = (Element) obj;
                        give.addElement("building").addAttribute("code", Integer.toString(codigo))
                                .addText("Piscifactoría de mar");
                        give.addElement("part").addText(String.valueOf(Character.toUpperCase(letra)));
                        give.addElement("total").addText("AB");
                    }
                }
            }
            guardarDocumentoXML(document, "pisci_m_" + Character.toLowerCase(letra));
            Simulador.registros.escribirTranscripcion("Recompensa pisci_m_" + Character.toLowerCase(letra) + " creada");
        }
    }

    /**
     * Crea una recompensa de piscifcatoria de rio
     * 
     * @param letra El nivel de recompensa
     */
    public void generarPiscifactoriaRio(char letra) {
        if (existeArchivo("pisci_r_" + Character.toLowerCase(letra))) {
            actualizarArchivoXML("pisci_r_" + Character.toLowerCase(letra));
        } else {
            Document document = generarXML();
            String descripcion = "Materiales para la construcción de una piscifactoría de río. Con la parte A y B, puedes obtenerla de forma gratuita.";
            int codigo = 0;
            int rarity = 3;
            Element raiz = document.getRootElement();
            for (Object obj : raiz.elements()) {
                if (obj instanceof Element) {
                    Element elemento = (Element) obj;
                    if ("name".equals(elemento.getName())) {
                        elemento.setText("Almacén central [" + String.valueOf(Character.toUpperCase(letra)) + "]");
                    } else if ("desc".equals(elemento.getName())) {
                        elemento.setText(descripcion);
                    } else if ("rarity".equals(elemento.getName())) {
                        elemento.setText(Integer.toString(rarity));
                    } else if ("give".equals(elemento.getName())) {
                        Element give = (Element) obj;
                        give.addElement("building").addAttribute("code", Integer.toString(codigo))
                                .addText("Piscifactoría de río");
                        give.addElement("part").addText(String.valueOf(Character.toUpperCase(letra)));
                        give.addElement("total").addText("AB");
                    }
                }
            }
            guardarDocumentoXML(document, "pisci_r_" + Character.toLowerCase(letra));
            Simulador.registros.escribirTranscripcion("Recompensa pisci_r_" + Character.toLowerCase(letra) + " creada");
        }
    }

    /**
     * Crea una recompensa de tanque de mar
     * 
     */
    public void generarTanqueMar() {
        if (existeArchivo("tanque_m")) {
            actualizarArchivoXML("tanque_m");
        } else {
            Document document = generarXML();
            String descripcion = "Materiales para la construcción, de forma gratuita, de un tanque de una piscifactoría de mar.";
            int codigo = 3;
            int rarity = 3;
            Element raiz = document.getRootElement();
            for (Object obj : raiz.elements()) {
                if (obj instanceof Element) {
                    Element elemento = (Element) obj;
                    if ("name".equals(elemento.getName())) {
                        elemento.setText("Tanque de mar");
                    } else if ("desc".equals(elemento.getName())) {
                        elemento.setText(descripcion);
                    } else if ("rarity".equals(elemento.getName())) {
                        elemento.setText(Integer.toString(rarity));
                    } else if ("give".equals(elemento.getName())) {
                        Element give = (Element) obj;
                        give.addElement("building").addAttribute("code", Integer.toString(codigo))
                                .addText("Tanque de mar");
                        give.addElement("part").addText("A");
                        give.addElement("total").addText("A");
                    }
                }
            }
            guardarDocumentoXML(document, "tanque_m");
            Simulador.registros.escribirTranscripcion("Recompensa tanque_m creada");
        }
    }

    /**
     * Crea una recompensa de tanque de rio
     * 
     */
    public void generarTanqueRio() {
        if (existeArchivo("tanque_r")) {
            actualizarArchivoXML("tanque_r");
        } else {
            Document document = generarXML();
            String descripcion = "Materiales para la construcción, de forma gratuita, de un tanque de una piscifactoría de río.";
            int codigo = 2;
            int rarity = 2;
            Element raiz = document.getRootElement();
            for (Object obj : raiz.elements()) {
                if (obj instanceof Element) {
                    Element elemento = (Element) obj;
                    if ("name".equals(elemento.getName())) {
                        elemento.setText("Tanque de río");
                    } else if ("desc".equals(elemento.getName())) {
                        elemento.setText(descripcion);
                    } else if ("rarity".equals(elemento.getName())) {
                        elemento.setText(Integer.toString(rarity));
                    } else if ("give".equals(elemento.getName())) {
                        Element give = (Element) obj;
                        give.addElement("building").addAttribute("code", Integer.toString(codigo))
                                .addText("Tanque de río");
                        give.addElement("part").addText("A");
                        give.addElement("total").addText("A");
                    }
                }
            }
            guardarDocumentoXML(document, "tanque_r");
            Simulador.registros.escribirTranscripcion("Recompensa tanque_r creada");
        }
    }
    

    /**
     * Guarda el XML
     * 
     * @param document      El XML a guardar
     * @param nombreArchivo El nombre del archivo a guardar
     */
    private void guardarDocumentoXML(Document document, String nombreArchivo) {
        XMLWriter writer = null;
        try {
            writer = new XMLWriter(
                    new OutputStreamWriter(new FileOutputStream(REWARDS_DIRECTORY + nombreArchivo + ".xml"), "UTF-8"),
                    OutputFormat.createPrettyPrint());
            writer.write(document);
            writer.flush();
            Simulador.registros.escribirLog("Recompensa creada");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception e) {
                Simulador.escribirError("Error al cerrar el XMLWriter");
            }
        }
    }
}
