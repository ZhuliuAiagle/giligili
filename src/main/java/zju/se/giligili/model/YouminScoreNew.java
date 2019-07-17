package zju.se.giligili.model;


import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class YouminScoreNew {
    @Id
    String _id;
    String name;
    List<Map> score;
}
