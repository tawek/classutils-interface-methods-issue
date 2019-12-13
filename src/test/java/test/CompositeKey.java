package test;

import lombok.Builder;
import lombok.Value;
import org.springframework.context.annotation.Bean;

@Value
@Builder
public class CompositeKey implements A, B {

    String id;

    String type;

}
