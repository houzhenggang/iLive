package izuanqian;

import lombok.Data;

/**
 * @author sanlion do
 */
@Data
public class GeoVo extends QqLbsVo<GeoVo.Body> {

    @Data
    public static class Body {

        private Location location;
        private String address;
        private FormattedAddress formatted_addresses;
        private Component address_component;
        private AdInfo ad_info;
    }

    @Data
    public static class Location {

        private double lat;
        private double lng;
    }

    @Data
    public static class FormattedAddress {

        private String recommend;
        private String rough;
    }

    @Data
    public static class Component {

        private String nation;
        private String province;
        private String city;
        private String district;
        private String street;
        private String street_number;
    }

    @Data
    public static class AdInfo {

        private String adcode;
        private String name;
        private Location location;
        private String nation;
        private String province;
        private String city;
        private String district;
    }
}
