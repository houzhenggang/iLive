package izuanqian;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * Created by PC on 2017/4/5.
 */
@Component
public class SceneCacher {

    private static final Map<String, Scene> scenes = Maps.newConcurrentMap();

    public void put(String token, Scene scene) {
        scenes.put(token, scene);
    }

    public Optional<Scene> get(String token) {
        return scenes.containsKey(token) ? Optional.of(scenes.get(token)) : Optional.empty();
    }

    public void clear(String token){
        scenes.remove(token);
    }
}
