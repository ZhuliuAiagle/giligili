package zju.se.giligili.dao;

import zju.se.giligili.model.BaiduIndex;

public interface BaiduIndexDao {
    BaiduIndex getIndex(String name);
    BaiduIndex findIndexById(String id);
}
