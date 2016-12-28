package pay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParameterUtils {
    public static List<Parameter> list(Parameter... parameters) {
        List<Parameter> parameterList = new ArrayList<>();
        for (int i = 0; i < parameters.length; i++) {
            parameterList.add(parameters[i]);
        }
        return parameterList;
    }

    public static void sort(List<Parameter> parameters) {
        Collections.sort(parameters);
    }

    public static String concat(List<Parameter> parameters) {
        StringBuffer sb = new StringBuffer();
        boolean first = true;
        for (Parameter parameter : parameters) {
            if (first) {
                first = false;
            } else {
                sb.append("&");
            }
            sb.append(parameter.getName() + "=" + parameter.getValue());
        }
        return sb.toString();
    }
}
