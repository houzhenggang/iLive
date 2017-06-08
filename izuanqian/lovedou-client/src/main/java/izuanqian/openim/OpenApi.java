/*
    © sanlion.do 
 */
package izuanqian.openim;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import org.springframework.stereotype.Service;

/**
 *
 * @author sanlion
 */
@Service
public class OpenApi {

    public static final String serverUrl = "http://gw.api.taobao.com/router/rest";
    public static final String appKey = "23428067";
    public static final String appSecret = "e956b1d1be32a439251eff72bff45a94";
    private TaobaoClient client = new DefaultTaobaoClient(serverUrl, appKey, appSecret);

    public TaobaoClient client() {
        return client;
    }
}
