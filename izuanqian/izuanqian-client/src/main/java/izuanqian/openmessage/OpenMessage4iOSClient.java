package izuanqian.openmessage;

import com.google.gson.Gson;
import com.taobao.api.ApiException;
import com.taobao.api.request.CloudpushMessageAndroidRequest;
import com.taobao.api.response.CloudpushMessageAndroidResponse;
import izuanqian.openim.OpenApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sanlion on 2017/5/17.
 */
@Slf4j
@Service
public class OpenMessage4iOSClient {

    @Autowired private OpenApi openApi;

    /**
     * 发送消息
     *
     * @param body
     * @param devices
     * @throws ApiException
     */
    public void notice(Object body, List<String> devices) throws ApiException {
        CloudpushMessageAndroidRequest request = new CloudpushMessageAndroidRequest();
        request.setBody(new Gson().toJson(body));
        request.setTarget("all");
        request.setTargetValue(
                devices.stream().reduce((s, s2) -> new StringBuilder(s).append(",").append(s2).toString()).get()
        );
        CloudpushMessageAndroidResponse execute = openApi.client().execute(request);
        log.info(execute.getMsg());
    }
}
