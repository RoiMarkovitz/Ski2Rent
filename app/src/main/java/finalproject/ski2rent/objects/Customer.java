package finalproject.ski2rent.objects;

public class Customer {
    private String uid = "";
    private String phone = "";

    public Customer() { }

    public String getUid() {
        return uid;
    }

    public Customer setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Customer setPhone(String phone) {
        this.phone = phone;
        return this;
    }

}
