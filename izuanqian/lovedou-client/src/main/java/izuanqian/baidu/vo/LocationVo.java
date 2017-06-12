package izuanqian.baidu.vo;

import lombok.Data;

import java.util.List;

@Data
public class LocationVo {

    private int status;
    private Result result;

    @Data
    public static class Result{

        private Location location;
        private String formatted_address; // 结构化地址信息
        private String sematic_description; // 当前位置结合POI的语义化结果描述。
        private String business; // 商圈
        private List<Poi> pois;
    }

    @Data
    public static class Location {

        private double lat;
        private double lng;
    }

    @Data
    public static class Poi {

        private String addr;
        private String cp;
        private String direction;
        private String distance;
        private String name;
        private String poiType;
        private Location point;
        private String tel;
        private String uid;
    }
}
