package zju.se.giligili.model;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "news", type = "news")
public class News {
    String _id;

    String name;

    Map news;
}
