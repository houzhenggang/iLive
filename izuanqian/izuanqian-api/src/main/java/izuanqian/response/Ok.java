package izuanqian.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by root on 17-3-5.
 */
@Data
@NoArgsConstructor
public class Ok extends ApiResponse {

    public Ok(String message) {
        super();
        this.setMessage(message);
    }

    public Ok(String message, Object obj) {
        this(message);
        this.setObj(obj);
    }


}
