package se.regent.java.lib.listToFlat;


import com.sun.deploy.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectListToFlat {


    private String[] header;
    private char delimiter = ',';
    private static ObjectListToFlat objectListToFlat;


    private ObjectListToFlat(){
    }

    public static ObjectListToFlat getObject(){
        if(objectListToFlat == null){
            objectListToFlat = new ObjectListToFlat();
        }
        return objectListToFlat;
    }

    public ObjectListToFlat delimiter(char delimiter){
        this.delimiter = delimiter;
        return getObject();
    }

    public ObjectListToFlat customHeader(String[] header){
        this.header = header;
        return getObject();
    }


    public <E> String objectListToFlat(List list, Class input) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        E[] indices = (E[]) list.toArray();

        final StringBuffer sb = new StringBuffer();
        Method[] methods = input.getMethods();
        Method[] getMethods = Arrays.stream(methods).filter(method -> (method.getName().toLowerCase().startsWith("get") || method.getName().toLowerCase().startsWith("is") )&& !method.getName().toLowerCase().equals("getclass")).toArray(Method[]::new);
        if(header == null || header.length == 0 ) {
            generateHeader(sb, getMethods);
        }else{
            Iterator iterator = Arrays.stream(header).iterator();
            sb.append(StringUtils.join(Arrays.stream(header).collect(Collectors.toList()), Character.toString(delimiter) ) + "\n");
        }
        generateCSV(indices, sb, getMethods);

        return sb.toString();
    }

    private <E> void generateCSV(E[] indices, StringBuffer sb, Method[] getMethods) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        for (int j = 0; j < indices.length; j++) {
            for (int i = 0; i < getMethods.length; i++) {
                String localMethod = getStripUnwantedCharactersFromMethodName(getMethods[i]);
                Method m = indices[j].getClass().getMethod(localMethod);
                Object invoked = m.invoke(indices[j]);

                if(invoked instanceof String) {
                    String text = (String) invoked;
                    invoked = text;
                }

                sb.append(invoked);
                sb.append(i == getMethods.length - 1 ? "\n" : delimiter);
            }
        }
    }

    private void generateHeader(StringBuffer sb, Method[] getMethods) {
        for (int i = 0; i < getMethods.length; i++) {
            sb.append(getMethods[i].getName().replace("get", ""));
            sb.append(i == getMethods.length - 1 ? "\n" : delimiter);
        }
    }

    private String getStripUnwantedCharactersFromMethodName(Method getMethod) {
        String localMethod = getMethod.toString();
        localMethod = localMethod.substring(localMethod.lastIndexOf(".") + 1);
        localMethod = localMethod.replace("(", "").replace(")", "");
        return localMethod;
    }

}
