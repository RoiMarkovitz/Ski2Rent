package finalproject.ski2rent.objects;

public class Customer {
    private String uid = "";
    private String name = "";
    private String phone = "";
 //   private String favoriteSushi = null;   // reference
 //   private Sushi favoriteSushi = null;   // copy

    public Customer() { }

    public String getUid() {
        return uid;
    }

    public Customer setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Customer setPhone(String phone) {
        this.phone = phone;
        return this;
    }

//    public String getFavoriteSushi() {
//        return favoriteSushi;
//    }
//
//    public User setFavoriteSushi(String favoriteSushi) {
//        this.favoriteSushi = favoriteSushi;
//        return this;
//    }

}
