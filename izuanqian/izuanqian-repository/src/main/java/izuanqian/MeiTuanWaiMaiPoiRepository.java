package izuanqian;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * Created by sanlion on 2017/5/26.
 */
@Service
public class MeiTuanWaiMaiPoiRepository {

    @Autowired private StringRedisTemplate template;

    public List<DboMeiTuanPoi> query(double lng, double lat, String address) throws IOException {
        Document document
                = Jsoup.connect("http://waimai.meituan.com/geo/geohash")
                .data("lat", String.valueOf(lat))
                .data("lng", String.valueOf(lng))
                .data("addr", address)
                .userAgent("Mozilla")
                .get();
        Elements div = document.getElementsByAttribute("data-title");
        List<DboMeiTuanPoi> pois = new ArrayList<>();
        for (Element element : div) {
            String id = element.attr("data-poiid");
            String title = element.attr("data-title");
            String logo = element.getElementsByTag("img").attr("data-src");
            pois.add(new DboMeiTuanPoi(id, title, logo));
            template.opsForHash().putAll(
                    "poi:untreated:meituanwaimai:" + id,
                    new HashMap<String, Object>() {{
                        put("title", title);
                        put("logo", logo);
                    }});
        }

        return pois;
    }

    @Data
    @RequiredArgsConstructor
    public static class DboMeiTuanPoi {

        @NonNull private String id;
        @NonNull private String title;
        @NonNull private String logo;
    }
}
