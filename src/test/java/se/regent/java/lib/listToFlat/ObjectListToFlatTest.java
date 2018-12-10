package se.regent.java.lib.listToFlat;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;



public class ObjectListToFlatTest {

    @Test
    public void objectListToFlatTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<TestObjects> testObjectsList = new ArrayList();
        testObjectsList.add(new TestObjects("hello","world"));
        testObjectsList.add(new TestObjects("foo","bar"));
        testObjectsList.add(new TestObjects("foo,jpp","bar"));
        testObjectsList.add(new TestObjects("nom\"nom","mat"));

        ObjectListToFlat objectListToFlat = ObjectListToFlat.getObject();
        objectListToFlat.delimiter(';');
        String flatFileList = objectListToFlat.objectListToFlat(testObjectsList, TestObjects.class);
        System.out.println("=== Test Result ===");
        System.out.println(flatFileList);
    }

    @Test
    public void objectListToFlatTest2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<TestObjects2> testObjectsList2 = new ArrayList();
        testObjectsList2.add(new TestObjects2("hello","world","tom"));
        testObjectsList2.add(new TestObjects2("foo","bar", "tomtom"));

        String[] header = {"1", "2", "3"};

        ObjectListToFlat objectListToFlat = ObjectListToFlat.getObject().delimiter(';').customHeader(header);
        String flatFileList2 = objectListToFlat.objectListToFlat(testObjectsList2, TestObjects2.class);
        System.out.println("=== Test Result ===");
        System.out.println(flatFileList2);
    }

    @Test
    public void objectListToFlatTestLongInt() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<TestObjectsIntegersAndLong> testObjectsListIntLong = new ArrayList();
        testObjectsListIntLong.add(new TestObjectsIntegersAndLong("hello",10,15L));
        testObjectsListIntLong.add(new TestObjectsIntegersAndLong("foo",22, 30L));

        ObjectListToFlat objectListToFlat = ObjectListToFlat.getObject();

        String flatFileListIntLong = objectListToFlat.objectListToFlat(testObjectsListIntLong, TestObjectsIntegersAndLong.class);
        System.out.println("=== Test Result ===");
        System.out.println(flatFileListIntLong);
    }


}