package phucnph22239.poly.lovely_hotel.DTO;

public class KhachHang {
    private int id ;
    private String name ;
    private int phone ;
    private String birthday ;

    public KhachHang() {
    }

    public KhachHang(int id, String name, int phone, String birthday) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
