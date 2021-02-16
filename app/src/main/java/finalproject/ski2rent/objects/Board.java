package finalproject.ski2rent.objects;

public abstract class Board {

    public enum eLevel {Bronze, Silver, Gold};
    public enum eCamberProfile {Camber, HybridCamber, Flat, Rocker, HybridRocker};
    public enum eType {Snowboard, Ski};

    protected String key = "";
    protected String name = "";
    protected String brand = "";
    protected String imagePath = "";
    protected double price = 0.0;
    protected eCamberProfile camberProfile;
    protected eLevel level;
    protected eType type;


    public Board() {}

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public eCamberProfile getCamberProfile() {
        return camberProfile;
    }

    public void setCamberProfile(eCamberProfile camberProfile) {
        this.camberProfile = camberProfile;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public eLevel getLevel() {
        return level;
    }

    public void setLevel(eLevel level) {
        this.level = level;
    }

    public eType getType() {
        return type;
    }

    public void setType(eType type) {
        this.type = type;
    }

    public boolean isSameKey(String otherKey) {
        return this.getKey().equals(otherKey);
    }

    public String description() {
        return getName() + " " + getType().name() + " " + getLevel().name();
    }
}
