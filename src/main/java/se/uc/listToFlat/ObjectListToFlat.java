package se.uc.listToFlat;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class ObjectListToFlat<E> {


    public String objectListToFlat(List list, Class input) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        E[] indices = (E[]) list.toArray();

        final StringBuffer sb = new StringBuffer();
        Method[] methods = input.getMethods();
        Method[] getMethods = Arrays.stream(methods).filter(method -> method.getName().toLowerCase().startsWith("get") && !method.getName().toLowerCase().equals("getclass")).toArray(Method[]::new);

        for (int i = 0; i < getMethods.length; i++) {
            sb.append(getMethods[i].getName().replace("get", ""));
            sb.append(i == getMethods.length - 1 ? "\n" : ",");
        }

        for (int j = 0; j < indices.length; j++) {
            for (int i = 0; i < getMethods.length; i++) {
                String localMethod = getStripUnwantedCharactersFromMethodName(getMethods[i]);
                Method m = indices[j].getClass().getMethod(localMethod);
                sb.append(m.invoke(indices[j]));
                sb.append(i == getMethods.length - 1 ? "\n" : ",");
            }
        }
        return sb.toString();
    }

    private String getStripUnwantedCharactersFromMethodName(Method getMethod) {
        String localMethod = getMethod.toString();
        localMethod = localMethod.substring(localMethod.lastIndexOf(".") + 1);
        localMethod = localMethod.replace("(", "").replace(")", "");
        return localMethod;
    }

}
