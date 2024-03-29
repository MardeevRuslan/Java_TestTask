package cft.mardeev.data;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Getter
public class Arguments {
    private final Map<String, String> option = new HashMap<>();
    private final List<String> files = new ArrayList<>();
}
