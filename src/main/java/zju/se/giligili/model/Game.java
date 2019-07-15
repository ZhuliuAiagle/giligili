package zju.se.giligili.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;

import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(indexName = "games", type = "games")
public class Game{
    private static final long serialVersionUID = -5440668610178634891L;
    @Id
    private String _id;
    private String name;
    private String subname;
    private String introduction;
    private String coverUrl;
    private String startDate;
    private String issuer;
    private String engine;
    private Number avgScore;
    private List<String> type;
    private List<String> theme;
    private List<String> mode;
    private List<String> view;
    private String description;
    private List<String> imgUrl;
    private Map youminData;

}

