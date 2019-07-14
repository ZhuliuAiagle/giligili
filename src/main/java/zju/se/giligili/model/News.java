package zju.se.giligili.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(indexName = "news", type = "news")
public class News implements Serializable {
    private static final long serialVersionUID = -6716042863801643868L;
    @Id
    String _id;

    String name;

    List<Map> news;
}
