package zju.se.giligili.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zju.se.giligili.dao.BaiduIndexDao;
import zju.se.giligili.model.BaiduIndex;
import zju.se.giligili.service.BaiduIndexService;

@Service
public class BaiduIndexServiceImpl implements BaiduIndexService {
    @Autowired
    private BaiduIndexDao baiduIndexDao;
    @Override
    public BaiduIndex getIndex(String name) {
        return baiduIndexDao.getIndex(name);
    }

    @Override
    public BaiduIndex findIndexById(String key) {
        return baiduIndexDao.findIndexById(key);
    }
}
