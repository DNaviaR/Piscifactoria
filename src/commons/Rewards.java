package commons;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import piscifactoria.Piscifactoria;
import piscifactoria.PiscifactoriaMar;
import piscifactoria.PiscifactoriaRio;
import piscifactoria.TanqueRio;

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
                guardarDocumentoXML(document, REWARDS_DIRECTORY + nombreArchivo + ".xml");
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
            for (Element elemento : raiz.elements()) {
                if ("name".equals(elemento.getName())) {
                    elemento.setText("Comida general " + nombre);
                } else if ("desc".equals(elemento.getName())) {
                    elemento.setText(descripcion);
                } else if ("rarity".equals(elemento.getName())) {
                    elemento.setText(Integer.toString(rarity));
                } else if ("give".equals(elemento.getName())) {
                    Element give = elemento;
                    give.addElement("food").addAttribute("type", "general").addText(Integer.toString(foodType));
                }
            }
            guardarDocumentoXML(document, REWARDS_DIRECTORY + "comida_" + numero + ".xml");
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
            for (Element elemento : raiz.elements()) {
                if ("name".equals(elemento.getName())) {
                    elemento.setText("Monedas " + nombre);
                } else if ("desc".equals(elemento.getName())) {
                    elemento.setText(descripcion);
                } else if ("rarity".equals(elemento.getName())) {
                    elemento.setText(Integer.toString(rarity));
                } else if ("give".equals(elemento.getName())) {
                    Element give = elemento;
                    give.addElement("coins").addText(Integer.toString(coins));
                }
            }
            guardarDocumentoXML(document, REWARDS_DIRECTORY + "monedas_" + numero + ".xml");
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
            for (Element elemento : raiz.elements()) {
                if ("name".equals(elemento.getName())) {
                    elemento.setText("Almacén central [" + String.valueOf(Character.toUpperCase(letra)) + "]");
                } else if ("desc".equals(elemento.getName())) {
                    elemento.setText(descripcion);
                } else if ("rarity".equals(elemento.getName())) {
                    elemento.setText(Integer.toString(rarity));
                } else if ("give".equals(elemento.getName())) {
                    Element give = elemento;
                    give.addElement("building").addAttribute("code", Integer.toString(codigo))
                            .addText("Almacén central");
                    give.addElement("part").addText(String.valueOf(Character.toUpperCase(letra)));
                    give.addElement("total").addText("ABCD");
                }
            }
            guardarDocumentoXML(document, REWARDS_DIRECTORY + "almacen_" + Character.toLowerCase(letra) + ".xml");
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
            for (Element elemento : raiz.elements()) {
                if ("name".equals(elemento.getName())) {
                    elemento.setText("Piscifactoría de mar [" + String.valueOf(Character.toUpperCase(letra)) + "]");
                } else if ("desc".equals(elemento.getName())) {
                    elemento.setText(descripcion);
                } else if ("rarity".equals(elemento.getName())) {
                    elemento.setText(Integer.toString(rarity));
                } else if ("give".equals(elemento.getName())) {
                    Element give = elemento;
                    give.addElement("building").addAttribute("code", Integer.toString(codigo))
                            .addText("Piscifactoría de mar");
                    give.addElement("part").addText(String.valueOf(Character.toUpperCase(letra)));
                    give.addElement("total").addText("AB");
                }
            }
            guardarDocumentoXML(document, REWARDS_DIRECTORY + "pisci_m_" + Character.toLowerCase(letra) + ".xml");
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
            for (Element elemento : raiz.elements()) {
                if ("name".equals(elemento.getName())) {
                    elemento.setText("Piscifactoría de río [" + String.valueOf(Character.toUpperCase(letra)) + "]");
                } else if ("desc".equals(elemento.getName())) {
                    elemento.setText(descripcion);
                } else if ("rarity".equals(elemento.getName())) {
                    elemento.setText(Integer.toString(rarity));
                } else if ("give".equals(elemento.getName())) {
                    Element give = elemento;
                    give.addElement("building").addAttribute("code", Integer.toString(codigo))
                            .addText("Piscifactoría de río");
                    give.addElement("part").addText(String.valueOf(Character.toUpperCase(letra)));
                    give.addElement("total").addText("AB");
                }
            }
            guardarDocumentoXML(document, REWARDS_DIRECTORY + "pisci_r_" + Character.toLowerCase(letra) + ".xml");
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
            for (Element elemento : raiz.elements()) {
                if ("name".equals(elemento.getName())) {
                    elemento.setText("Tanque de mar");
                } else if ("desc".equals(elemento.getName())) {
                    elemento.setText(descripcion);
                } else if ("rarity".equals(elemento.getName())) {
                    elemento.setText(Integer.toString(rarity));
                } else if ("give".equals(elemento.getName())) {
                    Element give = elemento;
                    give.addElement("building").addAttribute("code", Integer.toString(codigo))
                            .addText("Tanque de mar");
                    give.addElement("part").addText("A");
                    give.addElement("total").addText("A");
                }
            }
            guardarDocumentoXML(document, REWARDS_DIRECTORY + "tanque_m.xml");
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
            for (Element elemento : raiz.elements()) {
                if ("name".equals(elemento.getName())) {
                    elemento.setText("Tanque de río");
                } else if ("desc".equals(elemento.getName())) {
                    elemento.setText(descripcion);
                } else if ("rarity".equals(elemento.getName())) {
                    elemento.setText(Integer.toString(rarity));
                } else if ("give".equals(elemento.getName())) {
                    Element give = elemento;
                    give.addElement("building").addAttribute("code", Integer.toString(codigo))
                            .addText("Tanque de río");
                    give.addElement("part").addText("A");
                    give.addElement("total").addText("A");
                }
            }
            guardarDocumentoXML(document, REWARDS_DIRECTORY + "tanque_r.xml");
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
                    new OutputStreamWriter(new FileOutputStream(nombreArchivo), "UTF-8"),
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

    /**
     * Busca el contenido de la etiqueta "name"
     * 
     * @param archivo El XML en el que buscar
     * @return El contenido de la etiqueta "name"
     */
    String buscarNombre(File archivo) {
        SAXReader reader = null;
        String nombre = "";
        try {
            reader = new SAXReader();
            Document document = reader.read(archivo);
            Element raiz = document.getRootElement();
            for (Element elemento : raiz.elements()) {
                if ("name".equals(elemento.getName())) {
                    nombre = elemento.getText();
                }
            }
        } catch (Exception e) {
            Simulador.escribirError("Error al buscar el nombre del archivo XML");
        }
        return nombre;
    }

    /**
     * Busca el contenido de la etiqueta "quantity"
     * 
     * @param archivo El XML en el que buscar
     */
    void reducirQuantity(File archivo) {
        SAXReader reader = null;
        int quantity = 0;
        try {
            reader = new SAXReader();
            Document document = reader.read(archivo);
            Element raiz = document.getRootElement();
            for (Element elemento : raiz.elements()) {
                if ("quantity".equals(elemento.getName())) {
                    quantity = Integer.parseInt(elemento.getText()) - 1;
                    if (quantity == 0) {
                        archivo.delete();
                    } else {
                        elemento.setText(Integer.toString(quantity));
                        guardarDocumentoXML(document, archivo.getPath());
                    }
                }
            }
        } catch (Exception e) {
            Simulador.escribirError("Error al buscar el nombre del archivo XML");
        }
    }

    /**
     * Canjea una recompensa de comida
     * 
     * @param archivo archivo a canjear
     */
    void canjearComida(File archivo) {
        SAXReader reader = null;
        try {
            reader = new SAXReader();
            Document document = reader.read(archivo);
            Element raiz = document.getRootElement();
            Element giveElement = raiz.element("give");
            if (giveElement != null) {
                Element food = giveElement.element("food");
                if (food != null) {
                    int cantidad = Integer.parseInt(food.getText());
                    if (Simulador.almacenCentral.isActivo()) {
                        Simulador.almacenCentral.masComida(cantidad);
                    } else {
                        ApoyoMenu.repartirEquitativamente(Simulador.piscifactorias);
                    }
                    reducirQuantity(archivo);
                    System.out.println("Comida canjeada. Añadiendo "+cantidad+" de comida");
                    Simulador.registros.escribirTranscripcion("Recompensa " + archivo.getName() + " usada");
                    Simulador.registros.escribirLog("Recompensa " + archivo.getName() + " usada");
                }
            }
        } catch (Exception e) {
            Simulador.escribirError("Error al canjear comida");
        }
    }

    /**
     * Canjea una recompensa de monedas
     * 
     * @param archivo archivo a canjear
     */
    void canjearMonedas(File archivo) {
        SAXReader reader = null;
        try {
            reader = new SAXReader();
            Document document = reader.read(archivo);
            Element raiz = document.getRootElement();
            Element giveElement = raiz.element("give");
            if (giveElement != null) {
                Element coins = giveElement.element("coins");
                if (coins != null) {
                    int cantidad = Integer.parseInt(coins.getText());
                    Simulador.monedas.ingresar(cantidad);
                    System.out.println("Monedas canjeadas. Añadiendo "+cantidad+" monedas");
                    Simulador.registros.escribirTranscripcion("Recompensa " + archivo.getName() + " usada");
                    Simulador.registros.escribirLog("Recompensa " + archivo.getName() + " usada");
                }
            }
        } catch (Exception e) {
            Simulador.escribirError("Error al canjear monedas");
        }
    }

    /**
     * Canjea una recompensa de Almacen central
     */
    private void canjearAlmacen() {
        Set<Character> partesTotales = new TreeSet<>(Arrays.asList('A', 'B', 'C', 'D'));
        Set<Character> partesDiponibles = new TreeSet<>();
        ArrayList<String> archivosAlmacen = new ArrayList<>();
        File rewards = new File("rewards");
        if (rewards.exists() && rewards.isDirectory()) {
            File[] archivos = rewards.listFiles();
            if (archivos != null && archivos.length > 0) {
                for (File archivo : archivos) {
                    if (archivo.getName().startsWith("almacen_")) {
                        String cadena = buscarNombre(archivo);
                        archivosAlmacen.add(archivo.getName());
                        char caracter = cadena.charAt(cadena.indexOf("[") + 1);
                        partesDiponibles.add(caracter);
                    }
                }
            }
        }
        if (partesDiponibles.equals(partesTotales)) {
            Simulador.almacenCentral.setActivo(true);
            System.out.println("Almacen central canjeado");
            Simulador.registros.escribirLog("Recompensa Almacen central usada");
            Simulador.registros.escribirTranscripcion("Recompensa Almacen central usada");
            for (String string : archivosAlmacen) {
                reducirQuantity(new File(string));
            }
        } else {
            System.out.println("No tienes todas las partes para canjear el almacen");
            System.out.println("Partes disponibles: " + partesDiponibles);
            System.out.println("Partes necesarias: " + partesTotales);
        }
    }

    /**
     * Canjea una recompensa de Piscifactoria mar
     */
    private void canjearPisciM() {
        Scanner sc=new Scanner(System.in);
        Set<Character> partesTotales = new TreeSet<>(Arrays.asList('A', 'B'));
        Set<Character> partesDiponibles = new TreeSet<>();
        ArrayList<String> archivosPiscifactoriaM = new ArrayList<>();
        File rewards = new File("rewards");
        if (rewards.exists() && rewards.isDirectory()) {
            File[] archivos = rewards.listFiles();
            if (archivos != null && archivos.length > 0) {
                for (File archivo : archivos) {
                    if (archivo.getName().startsWith("pisci_m_")) {
                        String cadena = buscarNombre(archivo);
                        archivosPiscifactoriaM.add(REWARDS_DIRECTORY+archivo.getName());
                        char caracter = cadena.charAt(cadena.indexOf("[") + 1);
                        partesDiponibles.add(caracter);
                    }
                }
            }
        }
        if (partesDiponibles.equals(partesTotales)) {
            System.out.println("Indique el nombre de la nueva piscifactoria: ");
            String nombreNuevaPiscifactoria = sc.nextLine();
            Simulador.piscifactorias.add(new PiscifactoriaMar(nombreNuevaPiscifactoria));
            System.out.println("Piscifactoria de mar canjeada");
            System.out.println("Añadida nueva piscifactoria de mar " + nombreNuevaPiscifactoria);
            Simulador.registros.escribirLog("Recompensa Piscifactoria de mar usada");
            Simulador.registros.escribirTranscripcion("Recompensa Piscifactoria de mar usada");
            for (String string : archivosPiscifactoriaM) {
                reducirQuantity(new File(string));
            }
        } else {
            System.out.println("No tienes todas las partes para canjear la piscifactoria de mar");
            System.out.println("Partes disponibles: " + partesDiponibles);
            System.out.println("Partes necesarias: " + partesTotales);
        }
    }

    /**
     * Canjea una recompensa de Piscifactoria río
     */
    private void canjearPisciR() {
        Scanner sc=new Scanner(System.in);
        Set<Character> partesTotales = new TreeSet<>(Arrays.asList('A', 'B'));
        Set<Character> partesDiponibles = new TreeSet<>();
        ArrayList<String> archivosPiscifactoriaM = new ArrayList<>();
        File rewards = new File("rewards");
        if (rewards.exists() && rewards.isDirectory()) {
            File[] archivos = rewards.listFiles();
            if (archivos != null && archivos.length > 0) {
                for (File archivo : archivos) {
                    if (archivo.getName().startsWith("pisci_r_")) {
                        String cadena = buscarNombre(archivo);
                        archivosPiscifactoriaM.add(REWARDS_DIRECTORY+archivo.getName());
                        char caracter = cadena.charAt(cadena.indexOf("[") + 1);
                        partesDiponibles.add(caracter);
                    }
                }
            }
        }
        if (partesDiponibles.equals(partesTotales)) {
            System.out.println("Indique el nombre de la nueva piscifactoria: ");
            String nombreNuevaPiscifactoria = sc.nextLine();
            Simulador.piscifactorias.add(new PiscifactoriaRio(nombreNuevaPiscifactoria));
            System.out.println("Piscifactoria de rio canjeada");
            System.out.println("Añadida nueva piscifactoria de rio " + nombreNuevaPiscifactoria);
            Simulador.registros.escribirLog("Recompensa Piscifactoria de rio usada");
            Simulador.registros.escribirTranscripcion("Recompensa Piscifactoria de rio usada");
            for (String string : archivosPiscifactoriaM) {
                reducirQuantity(new File(string));
            }
        } else {
            System.out.println("No tienes todas las partes para canjear la piscifactoria de rio");
            System.out.println("Partes disponibles: " + partesDiponibles);
            System.out.println("Partes necesarias: " + partesTotales);
        }
    }

    /**
     * Canjea una recompensa de Tanque mar
     */
    private void canjearTanqueM(String archivo) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Piscifactoria> piscis_m = new ArrayList<>();
        for (Piscifactoria piscifactoria : Simulador.piscifactorias) {
            if (piscifactoria instanceof PiscifactoriaMar) {
                piscis_m.add(piscifactoria);
            }
        }
        int i = 1;
        System.out.println("0: Cancelar");
        for (Piscifactoria piscifactoria : piscis_m) {
            System.out.println(i + ": " + piscifactoria.getNombrePiscifactoria());
            i++;
        }
        String snum1;
        do {
            System.out.println("Seleccione una opcion");
            snum1 = sc.nextLine();
        } while (!ApoyoMenu.IsInteger(snum1));
        i = Integer.parseInt(snum1);
        if (i == 0) {
            System.out.println("Accion cancelada");
        } else {
            piscis_m.get(i - 1).getTanques().add(new TanqueRio<>(piscis_m.get(i - 1).getTanques().size() + 1));
            reducirQuantity(new File(archivo));
            System.out.println(
                    "Tanque mar canjeado y añadido a piscifactoria " + piscis_m.get(i - 1).getNombrePiscifactoria());
            Simulador.registros.escribirLog("Recompensa Tanque mar usada");
            Simulador.registros.escribirTranscripcion("Recompensa Tanque mar usada");
        }
    }

    /**
     * Canjea una recompensa de Tanque río
     */
    private void canjearTanqueR(String archivo) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Piscifactoria> piscis_r = new ArrayList<>();
        for (Piscifactoria piscifactoria : Simulador.piscifactorias) {
            if (piscifactoria instanceof PiscifactoriaRio) {
                piscis_r.add(piscifactoria);
            }
        }
        int i = 1;
        System.out.println("0: Cancelar");
        for (Piscifactoria piscifactoria : piscis_r) {
            System.out.println(i + ": " + piscifactoria.getNombrePiscifactoria());
            i++;
        }
        String snum1;
        do {
            System.out.println("Seleccione una opcion");
            snum1 = sc.nextLine();
        } while (!ApoyoMenu.IsInteger(snum1));
        i = Integer.parseInt(snum1);
        if (i == 0) {
            System.out.println("Accion cancelada");
        } else {
            piscis_r.get(i - 1).getTanques().add(new TanqueRio<>(piscis_r.get(i - 1).getTanques().size() + 1));
            reducirQuantity(new File(archivo));
            System.out.println(
                    "Tanque río canjeado y añadido a piscifactoria " + piscis_r.get(i - 1).getNombrePiscifactoria());
            Simulador.registros.escribirLog("Recompensa Tanque rio usada");
            Simulador.registros.escribirTranscripcion("Recompensa Tanque rio usada");
        }
    }

    /**
     * Obtiene el tipo de recompensa que se quiere canjear
     * y se llama a su método correspondiente
     * 
     * @param file La recompensa a canjear
     */
    void canjearRecompensa(File file) {
        String nombre = file.getName();
        if (nombre.startsWith("almacen_")) {
            if (Simulador.almacenCentral.isActivo()) {
                System.out.println("El almacen central ya esta activo");
            } else {
                canjearAlmacen();
            }
        } else if (nombre.startsWith("pisci_m_")) {
            canjearPisciM();
        } else if (nombre.startsWith("pisci_r_")) {
            canjearPisciR();
        } else if (nombre.startsWith("tanque_m")) {
            canjearTanqueM(REWARDS_DIRECTORY + "tanque_m.xml");
        } else if (nombre.startsWith("tanque_r")) {
            canjearTanqueR(REWARDS_DIRECTORY + "tanque_r.xml");
        } else {
            System.out.println("Ese archivo no puede ser canjeado");
        }
    }
}
