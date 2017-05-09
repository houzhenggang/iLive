package izuanqian.amap;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author sanlion do
 */
@Data
public class SearchVo {

    private int status;
    private String info;
    private List<PoiVo> pois;

    @Data
    public static class PoiVo {
        private String id;
        private String name;
        private String type;
        private String address;
        private String location;
        private double distance;
        private String pname;
        private String cityname;
        private String adname;
        private List<PhotoVo> photos;
    }

    @Data
    public static class PhotoVo{

        private String url;
    }
}
