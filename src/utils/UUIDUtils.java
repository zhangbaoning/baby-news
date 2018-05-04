package utils;

import java.util.UUID;

/**
 * @Description
 * @Author zhangbaoning
 * @Date 2018/5/4
 */
public class UUIDUtils {
    /**
     * 获得主键ID
     *
     * @return
     */
    public static String getId() {
        return UUID.randomUUID().toString()
                .replace("-", "").substring(0, 16);
    }
}
