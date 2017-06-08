package izuanqian;

import com.google.common.base.Strings;

public class Mobiles {

    public static String asterisk(String mobile) {

        if (Strings.isNullOrEmpty(mobile) || mobile.length() != 11) {
            return mobile;
        }
        return new StringBuilder(mobile.substring(0, 3)).append("****").append(mobile.substring(7)).toString();
    }
}
