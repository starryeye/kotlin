package sub13_nested_class.ex2;

public class JavaOneRoomHouse {

    /**
     * Java 에서 클래스 안에 클래스를 만들 때, 권장되지 않는 방식인..
     * inner class 예시
     */

    private final String address;
    private final Room room;

    public JavaOneRoomHouse(String address, double roomArea) {
        this.address = address;
        this.room = new Room(roomArea);
    }

    public String getAddress() {
        return address;
    }

    public Room getRoom() {
        return room;
    }

    private class Room {

        private final double area;

        public Room(double area) {
            this.area = area;
        }

        public double getArea() {
            return area;
        }

        public String getAddress() {
            return JavaOneRoomHouse.this.address; // inner class 에서는 외부 인스턴스에 접근이 가능하다.
        }
    }
}
