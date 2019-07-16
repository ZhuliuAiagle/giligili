package zju.se.giligili.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zju.se.giligili.dao.ProcessDao;
import zju.se.giligili.model.Process;
import zju.se.giligili.service.ProcessService;

@Service("processService")
public class ProcessServiceImpl implements ProcessService {
    @Autowired
    ProcessDao processDao;
    public Process getByName(String name){
        return processDao.getByName(name);
    }
}
