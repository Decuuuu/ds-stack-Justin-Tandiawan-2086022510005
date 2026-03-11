class Vehicle {
void move() {
System.out.println("Vehicle is moving");
}
}

class Car extends Vehicle {
@Override
void move() {
System.out.println("Car is moving");
}
}

public class APP2  {
public static void main(String[] args) {
Vehicle v1 = new Vehicle();
Vehicle v2 = new Car();

v1.move();
v2.move();
}
}