package finalproject.ski2rent.objects;

public abstract class Board {

    public enum eLevel {Bronze, Silver, Gold};
    public enum eCamberProfile {Camber, HybridCamber, Flat, Rocker, HybridRocker};
    public enum eType {Snowboard, Ski};

    private String key = "";
    private String name = "";
    private String brand = "";
    private String imagePath = "";
    private eCamberProfile camberProfile;
    private eLevel level;
    private eType type;


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
