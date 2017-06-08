package izuanqian;

import lombok.Data;

import java.util.List;

/**
 * @author sanlion do
 */
@Data
public class PoiArrayVo {

    private int status;
    private String message;
    private int count;
    private List<PoiVo> data;

    @Data
    public static class PoiVo{

        private String id;
        private String title;
        private String address;
        private String tel;
        private Location location;
    }

    @Data
    public static class Location{

        private double lat;
        private double lng;
    }

}
