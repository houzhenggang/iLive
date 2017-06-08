package izuanqian;

import com.google.gson.Gson;
import izuanqian.amap.GaoDeDiTuClient;
import izuanqian.user.dbo.DboGaoDeDiTuPoi;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by sanlion on 2017/5/27.
 */
@Slf4j
@Service
public class GaoDeDiTuRepository {

    @Autowired private GaoDeDiTuClient gaoDeDiTuClient;
    @Autowired
    @Qualifier("poiRedisTemplate")
    private StringRedisTemplate template;

    public List<DboGaoDeDiTuPoi> search(String city, double lng, double lat, String keyword) {
        String geoObj = new StringBuffer(String.valueOf(lng))
                .append("|").append(String.valueOf(lat))
                .append("|").append(String.valueOf(lng))
                .append("|").append(String.valueOf(lat)).toString();
        GaoDeDiTuClient.Vo vo = gaoDeDiTuClient.search(city, geoObj, keyword);
        if (Objects.isNull(vo)
                || Objects.isNull(vo.getData())
                || Objects.isNull(vo.getData().getPoi_list())) {
            return Collections.emptyList();
        }
        List<DboGaoDeDiTuPoi> pois = vo.getData().getPoi_list().stream()
                .map(poi -> new DboGaoDeDiTuPoi(poi))
                .collect(Collectors.toList());
        if (!Objects.isNull(pois)) {
            log.error("{}", pois);
            cache(pois);
        }
        return pois;
    }

    @Async
    void cache(List<DboGaoDeDiTuPoi> pois) {
        HashOperations<String, Object, Object> option = template.opsForHash();
        pois.stream().forEach(poi -> {
                    Map map = new Gson().fromJson(new Gson().toJson(poi), Map.class);
                    option.putAll(
                            "poi:untreated:gaodeditu:" + poi.getId(), map);
                }
        );
    }


}
