package se.uc.listToFlat;

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

        List<TestObjects2> testObjectsList2 = new ArrayList();
        testObjectsList2.add(new TestObjects2("hello","world","tom"));
        testObjectsList2.add(new TestObjects2("foo","bar", "tomtom"));

        List<TestObjectsIntegersAndLong> testObjectsListIntLong = new ArrayList();
        testObjectsListIntLong.add(new TestObjectsIntegersAndLong("hello",10,15L));
        testObjectsListIntLong.add(new TestObjectsIntegersAndLong("foo",22, 30L));



        ObjectListToFlat<TestObjects> testObjectsObjectListToFlat = new ObjectListToFlat<>();
        String flatFileList = testObjectsObjectListToFlat.objectListToFlat(testObjectsList, TestObjects.class);
        System.out.println("=== Test Result ===");
        System.out.println(flatFileList);

        ObjectListToFlat<TestObjects2> testObjectsObjectListToFlat2 = new ObjectListToFlat<>();
        String flatFileList2 = testObjectsObjectListToFlat2.objectListToFlat(testObjectsList2, TestObjects2.class);
        System.out.println("=== Test Result ===");
        System.out.println(flatFileList2);

        ObjectListToFlat<TestObjectsIntegersAndLong> testObjectsObjectListToFlatIntLong = new ObjectListToFlat<>();
        String flatFileListIntLong = testObjectsObjectListToFlatIntLong.objectListToFlat(testObjectsListIntLong, TestObjectsIntegersAndLong.class);
        System.out.println("=== Test Result ===");
        System.out.println(flatFileListIntLong);



    }

}