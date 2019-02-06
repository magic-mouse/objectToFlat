package se.regent.java.lib.listToFlat;


import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ObjectListToFlatTest {

    @Test
    public void objectListToFlatTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //given
        char delimiter = ';';

        List<TestObjects> testObjectsList = new ArrayList<>();
        testObjectsList.add(new TestObjects("hello","world"));
        testObjectsList.add(new TestObjects("foo","bar"));
        testObjectsList.add(new TestObjects("foo,jpp","bar"));
        testObjectsList.add(new TestObjects("nom\"nom","mat"));
        testObjectsList.add(new TestObjects("foo;jpp","bar"));

        //when
        ObjectListToFlat objectListToFlat = new ObjectListToFlat();
        String flatFileList = objectListToFlat.objectListToFlat(testObjectsList, TestObjects.class);

        // 7 = 5 rows, 1 header, 1 in "foo;jpp" TODO: find a way to verify this with program.
        //then
        assertEquals(StringUtils.countMatches(flatFileList, delimiter),7);
    }

    @Test
    public void objectListToFlatTest2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //given
        String[] header = {"1", "2", "3"};
        char delimiter = ';';

        List<TestObjects2> testObjectsList2 = new ArrayList<>();
        testObjectsList2.add(new TestObjects2("hello","world","tom"));
        testObjectsList2.add(new TestObjects2("foo","bar", "tomtom"));

        //when
        ObjectListToFlat objectListToFlat = new ObjectListToFlat().customHeader(header);
        String flatFileList2 = objectListToFlat.objectListToFlat(testObjectsList2, TestObjects2.class);

        //then
        assertEquals(StringUtils.countMatches(flatFileList2, delimiter), 6);
    }

    @Test
    public void objectListToFlatTestLongInt() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //given
        char delimiter = ';';

        List<TestObjectsIntegersAndLong> testObjectsListIntLong = new ArrayList<>();
        testObjectsListIntLong.add(new TestObjectsIntegersAndLong("hello",10,15L));
        testObjectsListIntLong.add(new TestObjectsIntegersAndLong("foo",22, 30L));

        //when
        ObjectListToFlat objectListToFlat = new ObjectListToFlat().delimiter(delimiter);
        String flatFileListIntLong = objectListToFlat.objectListToFlat(testObjectsListIntLong, TestObjectsIntegersAndLong.class);

        //then
        assertEquals(StringUtils.countMatches(flatFileListIntLong, delimiter), 6);
    }

    @Test
    public void objectListToFlatTestLongInt2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //given
        char delimiter = '/';

        List<TestObjectsIntegersAndLong> testObjectsListIntLong = new ArrayList<>();
        testObjectsListIntLong.add(new TestObjectsIntegersAndLong("hello",10,15L));
        testObjectsListIntLong.add(new TestObjectsIntegersAndLong("foo",22, 30L));

        //when
        String flatFileListIntLong = new ObjectListToFlat().delimiter('/').customHeader(new String[]{"head","toe","tiefighter"}).objectListToFlat(testObjectsListIntLong, TestObjectsIntegersAndLong.class);

        //then
        assertEquals(StringUtils.countMatches(flatFileListIntLong, delimiter), 6);
    }




}