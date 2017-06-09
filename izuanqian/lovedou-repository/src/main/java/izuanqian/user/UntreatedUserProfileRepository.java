package izuanqian.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UntreatedUserProfileRepository {

    @Autowired private UntreatedProfileMapper untreatedProfileMapper;



}