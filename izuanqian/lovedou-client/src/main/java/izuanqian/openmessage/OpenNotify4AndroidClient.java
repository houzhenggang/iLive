package izuanqian.openmessage;

import com.taobao.api.ApiException;
import com.taobao.api.request.CloudpushNoticeAndroidRequest;
import com.taobao.api.request.CloudpushNoticeIosRequest;
import com.taobao.api.response.CloudpushNoticeAndroidResponse;
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
public class OpenNotify4AndroidClient {

    @Autowired private OpenApi openApi;

    /**
     * 发送推送通知
     *
     * @param title
     * @param summary
     * @param devices
     * @throws ApiException
     */
    public void notice(String title, String summary, List<String> devices) throws ApiException {
        CloudpushNoticeAndroidRequest request = new CloudpushNoticeAndroidRequest();
        request.setTitle(title);
        request.setSummary(summary);
        request.setTarget("all");
        request.setTargetValue(
                devices.stream().reduce((s, s2) -> new StringBuilder(s).append(",").append(s2).toString()).get()
        );
        CloudpushNoticeAndroidResponse execute = openApi.client().execute(request);
        log.info(execute.getMsg());
    }
}
