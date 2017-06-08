package izuanqian;

import com.google.common.base.Strings;
import izuanqian.amap.SearchVo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author sanlion do
 */
@Data
public class PoiSearch {

    private String id;
    private String name;
    private String tag;
    private double lng;
    private double lat;
    private String address;
    private double distance;
    private List<String> imgUrls = new ArrayList<>();

    public PoiSearch(SearchVo.PoiVo poiVo) {
        this.id = poiVo.getId();
        this.name = poiVo.getName();
        this.tag = !Strings.isNullOrEmpty(poiVo.getType()) ? poiVo.getType().split(";")[2] : "";
        this.address = poiVo.getAddress();
        this.distance = poiVo.getDistance();
        if (!Strings.isNullOrEmpty(poiVo.getLocation())) {
            String[] location = poiVo.getLocation().split(",");
            this.lng = Double.parseDouble(location[0]);
            this.lat = Double.parseDouble(location[1]);
        }
        if (!Objects.isNull(poiVo.getPhotos())) {
            this.imgUrls = poiVo.getPhotos().stream().map(photoVo -> photoVo.getUrl()).collect(Collectors.toList());
        }
    }
}
