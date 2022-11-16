package phucnph22239.poly.lovely_hotel.DTO;

public class Phong {
    public int id;
    public int room_type_id;
    public String name;
    public int price;

    public Phong() {
    }

    public Phong(int id, int room_type_id, String name, int price) {
        this.id = id;
        this.room_type_id = room_type_id;
        this.name = name;
        this.price = price;
    }
}
