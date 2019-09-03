package evsgenerator;

public class ElectricVehicle {

    public enum Model {
        leaf, zoe, i3, egolf, models
    }

    private int numberNodes;
    private Model model;

    private int initialEnergy;
    private int maximumBatteryCapacity;
    private int vehicleMass;
    private double frontSurfaceArea;
    private double airDragCoefficient;
    private double internalMomentOfInertia;
    private double radialDragCoefficient;
    private double rollDragCoefficient;
    private int constantPowerIntake;
    private double propulsionEfficiency;
    private double recuperationEfficiency;

    public ElectricVehicle(Model model, int numberNodes) {
        this.model = model;
        this.numberNodes = numberNodes;
        switch (model) {
            case leaf:
                init(40000, 40000, 1557, 2.3, 0.28, 0, 0, 0, 0, 0.9, 0.4);
                break;
            case zoe:
                init(41000, 41000, 1468, 0.75, 0.29, 0, 0, 0, 0, 0.9, 0.4);
                break;
            case i3:
                init(33000, 33000, 1195, 2.38, 0.29, 0, 0, 0, 0, 0.9, 0.4);
                break;
            case egolf:
                init(35800, 35800, 1540, 1.97, 0.31, 0, 0, 0, 0, 0.9, 0.4);
                break;
            case models:
                init(75000, 75000, 2250, 2.34, 0.24, 0, 0, 0, 0, 0.9, 0.4);
                break;
        }
    }

    private void init(int initialEnergy, int maximumBatteryCapacity, int vehicleMass, double frontSurfaceArea,
                           double airDragCoefficient, double internalMomentOfInertia, double radialDragCoefficient, double rollDragCoefficient,
                           int constantPowerIntake, double propulsionEfficiency, double recuperationEfficiency) {
        this.initialEnergy = initialEnergy;
        this.maximumBatteryCapacity = maximumBatteryCapacity;
        this.vehicleMass = vehicleMass;
        this.frontSurfaceArea = frontSurfaceArea;
        this.airDragCoefficient = airDragCoefficient;
        this.internalMomentOfInertia = internalMomentOfInertia;
        this.radialDragCoefficient = radialDragCoefficient;
        this.rollDragCoefficient = rollDragCoefficient;
        this.constantPowerIntake = constantPowerIntake;
        this.propulsionEfficiency = propulsionEfficiency;
        this.recuperationEfficiency = recuperationEfficiency;
    }

    @Override
    public String toString() {
        return "        <param key=\"initialEnergy\" value=\""+initialEnergy+"\"/>\n" +
                "        <param key=\"maximumBatteryCapacity\" value=\""+maximumBatteryCapacity+"\"/>\n" +
                "        <param key=\"vehicleMass\" value=\""+vehicleMass+"\"/>\n" +
                "        <param key=\"frontSurfaceArea\" value=\""+frontSurfaceArea+"\"/>\n" +
                "        <param key=\"airDragCoefficient\" value=\""+airDragCoefficient+"\"/>\n" +
                "        <param key=\"internalMomentOfInertia\" value=\""+internalMomentOfInertia+"\"/>\n" +
                "        <param key=\"radialDragCoefficient\" value=\""+radialDragCoefficient+"\"/>\n" +
                "        <param key=\"rollDragCoefficient\" value=\""+rollDragCoefficient+"\"/>\n" +
                "        <param key=\"constantPowerIntake\" value=\""+constantPowerIntake+"\"/>\n" +
                "        <param key=\"propulsionEfficiency\" value=\""+propulsionEfficiency+"\"/>\n" +
                "        <param key=\"recuperationEfficiency\" value=\""+recuperationEfficiency+"\"/>";
    }

    public int getNumberNodes() {
        return numberNodes;
    }

    public Model getModel() {
        return model;
    }
}
