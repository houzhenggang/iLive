package izuanqian;

import com.google.gson.Gson;
import izuanqian.baidu.BaiduClient;
import izuanqian.baidu.vo.ClientPoiArrayVo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BaiduPoiService {

    @Autowired private BaiduClient baiduClient;

    public List<Poi> search(double lng, double lat, String keyword) {
        String category = "filter:cater";
        String location = new StringBuilder(String.valueOf(lat)).append(",").append(String.valueOf(lng)).toString();
        String value = baiduClient.search(keyword, location, 10000, "z5js42W2np1ws91jEuLnuQyytgIBdyyT", category);
        ClientPoiArrayVo clientPoiArrayVo = new Gson().fromJson(value, ClientPoiArrayVo.class);
        if (0 != clientPoiArrayVo.getStatus()) {
            throw new BizException(17061201, clientPoiArrayVo.getMessage());
        }
        return clientPoiArrayVo.getResults().stream().map(poi -> new Poi(poi)).collect(Collectors.toList());
    }

    @Data
    public static class Poi {

        private String name;
        private double lng;
        private double lat;
        private String address;
        private String telephone;
        private String uid;
        private String type;

        public Poi(ClientPoiArrayVo.Poi poi) {

            this.name = poi.getName();
            this.lat = poi.getLocation().getLat();
            this.lng = poi.getLocation().getLng();
            this.address  = poi.getAddress();
            this.telephone = poi.getTelephone();
            this.uid = poi.getUid();
        }
    }
}
