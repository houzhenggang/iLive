package izuanqian.baidu.vo;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ClientPoiArrayVo {

    private int status;
    private String message;
    private Result results;

    public static class Result extends ArrayList<Poi> {

    }

    @Data
    public static class Poi {

        private String name;
        private String address;
        private LocationVo.Location location;
        private String telephone;
        private String uid;
        private PoiDetail detail_info;
    }

    @Data
    public static class PoiDetail {
        private double distance;
        private String tag;
        private String type;
    }
}
