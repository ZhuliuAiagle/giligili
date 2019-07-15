package zju.se.giligili.dao;

import zju.se.giligili.model.Wordcloud;

public interface WordcloudDao {
    Wordcloud getCloudByName(String name);
}
