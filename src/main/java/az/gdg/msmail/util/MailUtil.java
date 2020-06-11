package az.gdg.msmail.util;

import java.util.List;

public class MailUtil {

    private MailUtil() {
        throw new IllegalStateException("Utility Class");
    }

    public static String listToString(List<String> addresses) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        for (String email : addresses) {
            sb.append(email);
            i++;
            if (addresses.size() > i) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
