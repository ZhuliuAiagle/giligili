package zju.se.giligili.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class BaiduIndex {
    @Id
    private String _id;

    private String name;

    private String all;

    private String wise;

    private String sex;

    public BaiduIndex(){

    }
}
