package izuanqian.api.device.o;

import lombok.Getter;

/**
 * Created by sanlion on 2017/6/5.
 */
@Getter
public class AppBackStateUpdate {

    private AppBackState state;

    public static enum AppBackState {

        Background, Foreground
    }
}
