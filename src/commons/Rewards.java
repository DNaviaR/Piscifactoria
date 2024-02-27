package commons;
import java.io.File;
import java.io.IOException;

public class Rewards {

    private static final String REWARDS_DIRECTORY = "rewards/";

    public static void generarComida(String rewardName, int quantity) {
        generateReward("food", rewardName, quantity);
    }

    public static void generarMonedas(String rewardName, int quantity) {
        generateReward("coins", rewardName, quantity);
    }

    public static void generateBuildingMaterialReward(String rewardName, int quantity) {
        generateReward("building_materials", rewardName, quantity);
    }

    // Método privado para generar recompensas
    private static void generateReward(String type, String rewardName, int quantity) {
        String fileName = REWARDS_DIRECTORY + type + "_" + rewardName + ".xml";
        File rewardFile = new File(fileName);

        try {
            if (!rewardFile.exists()) {
                rewardFile.createNewFile();
            }

            // Incrementar la cantidad existente en el archivo
            int existingQuantity = XMLParser.getQuantityFromXML(rewardFile);
            int newQuantity = existingQuantity + quantity;

            // Actualizar la cantidad en el archivo XML
            XMLParser.updateQuantityInXML(rewardFile, newQuantity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para mostrar las recompensas disponibles en el menú
    public static void showAvailableRewards() {
        File rewardsDirectory = new File(REWARDS_DIRECTORY);
        File[] rewardFiles = rewardsDirectory.listFiles();

        if (rewardFiles != null) {
            for (File rewardFile : rewardFiles) {
                String rewardName = extractRewardName(rewardFile.getName());
                int quantity = XMLParser.getQuantityFromXML(rewardFile);

                System.out.println(rewardName + ": " + quantity + " unidades");
            }
        }
    }

    // Método privado para extraer el nombre de la recompensa del nombre del archivo
    private static String extractRewardName(String fileName) {
        String[] parts = fileName.split("_");
        return parts[1].split("\\.")[0]; // Eliminar la extensión .xml
    }

    // Método para canjear una recompensa
    public static void redeemReward(String rewardName) {
        File rewardFile = new File(REWARDS_DIRECTORY + rewardName + ".xml");

        if (rewardFile.exists()) {
            int quantity = XMLParser.getQuantityFromXML(rewardFile);

            if (quantity > 1) {
                // Reducir la cantidad si hay más de una unidad
                XMLParser.updateQuantityInXML(rewardFile, quantity - 1);
            } else {
                // Eliminar el archivo si solo queda una unidad
                rewardFile.delete();
            }

            // Aquí puedes implementar la lógica específica para cada tipo de recompensa
            // (almacén central, distribución entre piscifactorías, etc.)
            processReward(rewardName);
        } else {
            System.out.println("No tienes esa recompensa disponible.");
        }
    }

    // Método privado para procesar la recompensa (puedes personalizarlo según tus necesidades)
    private static void processReward(String rewardName) {
        System.out.println("Has canjeado la recompensa: " + rewardName);

        // Aquí puedes agregar lógica específica para cada tipo de recompensa
        // (almacén central, distribución entre piscifactorías, etc.)
    }
}
