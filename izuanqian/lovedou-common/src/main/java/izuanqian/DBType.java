package izuanqian;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author sanlion do
 */
@Slf4j
public class DBType {

    private static final String ENUM_TYPE_CODE = "code";

    /**
     * get enum type by db countyCode
     *
     * @param enumTypeClazz
     * @param code
     * @param <T>
     * @return
     */
    public static <T extends Enum> T type(Class<T> enumTypeClazz, int code) {
        if (!enumTypeClazz.isEnum()) {
            return null;
        }
        try {
            return Arrays.stream(enumTypeClazz.getEnumConstants())
                    .filter(t -> code == getTypeValue(t)).findAny().get();
        } catch (Exception e) {
            log.error("db state error, {}, {}", enumTypeClazz, code);
            return Arrays.stream(enumTypeClazz.getEnumConstants()).findFirst().get();
        }
    }

    private static int getTypeValue(Enum enumObject) {
        Class clazz = enumObject.getClass();
        try {
            Field dbIndex = clazz.getDeclaredField(ENUM_TYPE_CODE);
            dbIndex.setAccessible(true);
            return (int) dbIndex.get(Enum.valueOf(clazz, enumObject.name()));
        } catch (IllegalAccessException | NoSuchFieldException e) {
            log.error(e.getMessage());
            return 0;
        }
    }
}
