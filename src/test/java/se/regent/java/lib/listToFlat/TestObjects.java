package se.regent.java.lib.listToFlat;

class TestObjects {

    private String name;
    private String type;


    public TestObjects() {
    }


    TestObjects(String name, String type) {
        this.name = name;
        this.type = type;
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

    @Override
    public String toString() {
        return "TestObjects{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}