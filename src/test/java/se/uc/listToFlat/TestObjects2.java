package se.uc.listToFlat;

public class TestObjects2 {

    private String name;
    private String type;
    private String hello;

    public TestObjects2() {
    }


    TestObjects2(String name, String type, String hello) {
        this.name = name;
        this.type = type;
        this.hello = hello;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    @Override
    public String toString() {
        return "TestObjects{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", hello='" + hello + '\'' +
                '}';
    }
}