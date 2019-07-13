package zju.se.giligili.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("youminScoreNewService")
public class YouminScoreNewServiceImpl implements YouminScoreNewService {
    @Autowired
    private YouminScoreNewDao youminScoreNewDao;
    @Override
    public YouminScoreNew getScoreByName(String name){
        return youminScoreNewDao.getScoreByName(name);
    }
}
