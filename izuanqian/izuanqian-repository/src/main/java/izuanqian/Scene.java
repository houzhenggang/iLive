package izuanqian;

import lombok.Data;

import java.util.List;

/**
 * Created by PC on 2017/4/5.
 */
@Data
public abstract class Scene<Context, Option> {

    Context context;
    List<Option> options;
}
