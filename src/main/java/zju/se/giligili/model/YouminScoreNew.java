package zju.se.giligili.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
public class YouminScoreNew {
    private static final long serialVersionUID = 1L;
    @Id
    String _id;
    String name;
    String year;
    String link;
    String img;
    Number userScore;
    List<Object>  score;
    public YouminScoreNew (String _id, String name, String year, String link,
                           String img, Number userScore, List<Object> score){
        this._id = _id;
        this.name = name;
        this.year = year;
        this.link = link;
        this.img = img;
        this.userScore = userScore;
        this.score = score;
    }
}