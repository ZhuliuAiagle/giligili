package zju.se.giligili.model;


import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Process {
    private static final long serialVersionUID = -5440668610178634891L;
    @Id
    String _id;
    String name;
    Number avg;
    Number qoq;
    Number yoy;
    Number mark;
}
