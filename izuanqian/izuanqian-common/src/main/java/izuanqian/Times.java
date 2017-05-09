package izuanqian;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * Created by PC on 2017/3/23.
 */
public class Times {
    public static final String __ = "M月dd日 HH:mm";
    public static final String yyyyMMddHHmm = "yyyy-MM-dd HH:mm";

    /**
     * 格式化 <- 默认值，空
     *
     * @param date    时间
     * @param pattern 模式
     * @return
     */
    public static String format(Date date, String pattern) {
        return format(date, pattern, "");
    }

    /**
     * 格式化
     *
     * @param date    时间
     * @param pattern 模式
     * @param ifNull  默认值
     * @return
     */
    public static String format(Date date, String pattern, String ifNull) {
        if (Objects.isNull(date)) {
            return ifNull;
        }
        try {
            return
                    LocalDateTime
                            .ofInstant(date.toInstant(), ZoneId.systemDefault())
                            .format(DateTimeFormatter.ofPattern(pattern));
        } catch (Exception e) {
            return ifNull;
        }
    }
}
