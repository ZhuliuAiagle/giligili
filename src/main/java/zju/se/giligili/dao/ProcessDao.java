package zju.se.giligili.dao;
import zju.se.giligili.model.Process;

public interface ProcessDao {
    Process getByName(String name);
}
