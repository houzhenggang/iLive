package izuanqian;

import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 版本
 *
 * @author sanlion do
 */
public class Version {

    private String version;
    private List<Integer> points = new ArrayList<>();

    public Version(String version) {
        this.version = version;
        this.points = toPoints();
    }

    /**
     * 是否 >
     *
     * @param version
     * @return
     */
    public boolean gt(Version version) {
        return compare(this.points, version.points) > 0;
    }

    /**
     * 是否 =
     *
     * @param version
     * @return
     */
    public boolean eq(Version version) {
        return compare(this.points, version.points) == 0;
    }

    /**
     * 是否 >=
     *
     * @param version
     * @return
     */
    public boolean gte(Version version) {
        return compare(this.points, version.points) >= 0;
    }

    private static int compare(List<Integer> points1, List<Integer> points2) {
        if (points1.isEmpty()) {
            return -1;
        }
        if (points2.isEmpty()) {
            return 1;
        }
        for (int i = 0; i < Math.min(points1.size(), points2.size()); i++) {
            int compare = Integer.valueOf(points1.get(i)).compareTo(points2.get(i));
            if (compare != 0) {
                return compare;
            }
        }
        if (points1.size() == points2.size()) {
            return 0;
        }
        return Integer.valueOf(points1.size()).compareTo(points2.size());
    }

    private List<Integer> toPoints() {
        if (Strings.isNullOrEmpty(this.version)) {
            return Collections.emptyList();
        }
        return
                Arrays.stream(this.version.split("[.]"))
                        .map(
                                point ->
                                        Integer.parseInt(point))
                        .collect(Collectors.toList());
    }
}
