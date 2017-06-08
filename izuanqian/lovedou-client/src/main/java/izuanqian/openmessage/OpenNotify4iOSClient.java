package izuanqian.openmessage;

import com.taobao.api.ApiException;
import com.taobao.api.request.CloudpushNoticeIosRequest;
import com.taobao.api.response.CloudpushNoticeIosResponse;
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
public class OpenNotify4iOSClient {

    @Autowired private OpenApi openApi;

    /**
     * 发送推送通知
     *
     * @param summary
     * @param devices
     * @throws ApiException
     */
    public void notice(String summary, List<String> devices) throws ApiException {
        CloudpushNoticeIosRequest request = new CloudpushNoticeIosRequest();
        request.setSummary(summary);
        request.setEnv("PRODUCT");
        request.setTarget("all");
        request.setTargetValue(
                devices.stream().reduce((s, s2) -> new StringBuilder(s).append(",").append(s2).toString()).get()
        );
        CloudpushNoticeIosResponse execute = openApi.client().execute(request);
        log.info(execute.getMsg());
    }
}
