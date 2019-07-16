package zju.se.giligili.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Getter
@Setter
@Document(collection = "baiduIndex")
@NoArgsConstructor
@AllArgsConstructor
public class BaiduIndex {
    @Id
    private String _id;

    private String name;

    private Map all;

    private Map wise;

    private Map pc;

    private Map sex;

}
