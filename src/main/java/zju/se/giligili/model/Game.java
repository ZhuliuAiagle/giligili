package zju.se.giligili.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@Data
@Document(indexName = "game")
public class Game {
    private static final long serialVersionUID = 1L;
    @Id
    private String _id;
    private String name;
    private String subname;
    private String introduction;
    private String coverUrl;
    private String startDate;
    private String issuer;
    private String engine;
    private List<String> type;
    private List<String> theme;
    private List<String> mode;
    private List<String> view;
    private String description;
    private List<String> imgUrl;
    private Map youminScoreNew;

    public Game(String _id, String name, String introduction, String coverUrl,
                String subname, String startDate,String issuer, String engine, List<String> type,
                List<String> theme, List<String> mode, List<String> view, String description,
                List<String> imgUrl, Map youminScoreNew){
        this._id  = _id;
        this.name = name;
        this.subname = subname;
        this.introduction = introduction;
        this.coverUrl = coverUrl;
        this.startDate = startDate;
        this.issuer = issuer;
        this.engine = engine;
        this.type = type;
        this.theme = theme;
        this.mode = mode;
        this.view = view;
        this.description = description;
        this.imgUrl = imgUrl;
        this.youminScoreNew = youminScoreNew;
    }
}
