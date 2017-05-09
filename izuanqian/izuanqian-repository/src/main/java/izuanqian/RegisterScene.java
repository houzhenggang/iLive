package izuanqian;

import lombok.Data;

/**
 * Created by PC on 2017/4/5.
 */
public class RegisterScene extends Scene<RegisterScene.Context, RegisterScene.Option>{

    @Data
    public static class Context{

        private String token;
        private String deviceType;
        private String deviceCode;
    }

    @Data
    public static class Option {

        @Step(descriptions = {"请输入手机号", "老板说，填手机号可以用于找回密码等"})
        private String mobile;

    }
}
