package zju.se.giligili.service;

import zju.se.giligili.model.News;

import java.util.List;

public interface NewsService {
    List<News> findAllByName(String name);

}
