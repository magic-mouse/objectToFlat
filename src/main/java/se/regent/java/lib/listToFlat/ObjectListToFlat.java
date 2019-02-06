package se.regent.java.lib.listToFlat;


import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectListToFlat {


    private String[] header;
    private String[] methods;
    private char delimiter = ';';


    public ObjectListToFlat(){

    }


    public ObjectListToFlat delimiter(char delimiter){
        this.delimiter = delimiter;
        return this;
    }

    public ObjectListToFlat customHeader(String[] header){
        this.header = header;
        return this;
    }

    public ObjectListToFlat customMethods(String[] methods) {
        this.methods = methods;
        return this;
    }

    public <E> String objectListToFlat(List list, Class input) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        E[] indices = (E[]) list.toArray();
        final StringBuffer sb = new StringBuffer();
        Method[] getMethods = extractMethodsFromClass(input);

        if (methods == null || methods.length <= 0) {
            methods = convertMethodsToStringArray(getMethods);
        }

        if (header == null || header.length == 0) {
            generateHeader(sb, getMethods);
        } else {
            sb.append(StringUtils.join(Arrays.stream(header).collect(Collectors.toList()), Character.toString(delimiter)) + "\n");
        }

        generateCSV(indices, sb, methods);

        return sb.toString();
    }

    private String[] convertMethodsToStringArray(Method[] getMethods) {
        List<String> customMethods = new ArrayList<>();
        for (int i = 0; i < getMethods.length; i++) {
            String localMethod = getStripUnwantedCharactersFromMethodName(getMethods[i]);
            customMethods.add(localMethod);
        }
        return customMethods.toArray(new String[customMethods.size()]);
    }

    private <E> void generateCSV(E[] indices, StringBuffer sb, String[] getMethods) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        for (int j = 0; j < indices.length; j++) {
            for (int i = 0; i < getMethods.length; i++) {

                Method m = indices[j].getClass().getMethod(getMethods[i]);
                Object invoked = m.invoke(indices[j]);

                if (invoked == null) {
                    invoked = "";
                }

                if (invoked instanceof String) {
                    String text = (String) invoked;
                    text = text.replaceAll("\"", "\"\"");
                    text = "\"" + text + "\"";
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

    private Method[] extractMethodsFromClass(Class input) {
        Method[] methods = input.getMethods();
        Method[] getMethods = Arrays.stream(methods).filter(method -> (method.getName().toLowerCase().startsWith("get") || method.getName().toLowerCase().startsWith("is")) && !method.getName().toLowerCase().equals("getclass")).toArray(Method[]::new);
        return getMethods;
    }

}
