package test;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Entity  implements C,D {

    private String key;

    private String type;

    private String data;

}
