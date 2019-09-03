package evsgenerator;

import java.io.*;
import java.util.*;

/**
 * Generate a XML file with the properties for the
 * simulation of electric vehicles consumption on ns3 module.
 *
 * With this layout:
 *
    <ElectricVehicles>
        <ElectricVehicle node="0">
            <param key="initialEnergy" value="15000"/>
            <param key="maximumBatteryCapacity" value="324000"/>
            <param key="vehicleMass" value="19000"/>
            <param key="frontSurfaceArea" value="8.25"/>
            <param key="airDragCoefficient" value="0.6"/>
            <param key="internalMomentOfInertia" value="0.01"/>
            <param key="radialDragCoefficient" value="0.5"/>
            <param key="rollDragCoefficient" value="0.01"/>
            <param key="constantPowerIntake" value="100"/>
            <param key="propulsionEfficiency" value="0.9"/>
            <param key="recuperationEfficiency" value="0.9"/>
        </ElectricVehicle>
    </ElectricVehicles>
*/

public class EVsGenerator {

    private static int totalNodes;
    private static List<ElectricVehicle> models;

    private static Properties readProperties() {
        try (InputStream input = new FileInputStream("electricVehicles.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            return prop;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static void readModels(Properties prop) {
        models = new ArrayList<>();
        for (ElectricVehicle.Model model : ElectricVehicle.Model.values()) {
            int numberNodes = Integer.parseInt(prop.getProperty("nodes."+ model.name(), "0"));
            totalNodes += numberNodes;
            ElectricVehicle electricVehicle = new ElectricVehicle(model, numberNodes);
            models.add(electricVehicle);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Properties props = readProperties();
        if (props == null) {
            System.err.println("Fichero de propiedades 'electricVehicles.properties' no encontrado.");
            return;
        }

        readModels(props);
        String fileOutput = props.getProperty("output", "EVnodes.xml");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileOutput, "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
            System.err.println("Error creando fichero de salida " + fileOutput + ".");
            return;
        }

        System.out.println("Starting generating XML file -> " + fileOutput);
        writer.println("<!-- Electric Vehicles file for ns-3 simulations -->");
        writer.println("<!-- TOTAL NODES: " + totalNodes + "-->");
        writer.println("<ElectricVehicles>");
        int node = 0;
        for (ElectricVehicle model : models) {
            System.out.println("Electric Vehicle model -> " + model.getModel());
            writer.println("<!-- Starting model: " + model.getModel() + " with " + model.getNumberNodes() + " nodes. -->");
            node = imprimirVehiculosModelo(writer, model, node);
            writer.println("<!-- END model: " + model.getModel() + " with " + model.getNumberNodes() + " nodes. -->");
        }

        writer.println("</ElectricVehicles>");
        writer.close();
        System.out.println("Finished.");
    }

    private static int imprimirVehiculosModelo(PrintWriter writer, ElectricVehicle v, int node) {
        for (int i=0; i < v.getNumberNodes(); i++){
            writer.println("  <ElectricVehicle node=\""+node+"\">");
            writer.println(v);
            writer.println("  </ElectricVehicle>");
            node++;
        }
        return node;
    }

}
