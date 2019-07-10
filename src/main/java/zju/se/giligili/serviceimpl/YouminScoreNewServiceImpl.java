package zju.se.giligili.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zju.se.giligili.dao.YouminScoreNewDao;
import zju.se.giligili.model.YouminScoreNew;
import zju.se.giligili.service.YouminScoreNewService;

@Service("youminScoreNewService")
public class YouminScoreNewServiceImpl implements YouminScoreNewService {
    @Autowired
    private YouminScoreNewDao youminScoreNewDao;
    @Override
    public YouminScoreNew getScoreByName(String name){
        return youminScoreNewDao.getScoreByName(name);
    }
}
