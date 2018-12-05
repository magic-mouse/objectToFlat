package se.uc.listToFlat;

public class TestObjectsIntegersAndLong {

    private String name;
    private int type;
    private Long hello;

    public TestObjectsIntegersAndLong() {
    }


    TestObjectsIntegersAndLong(String name, int type, Long hello) {
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getHello() {
        return hello;
    }

    public void setHello(Long hello) {
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