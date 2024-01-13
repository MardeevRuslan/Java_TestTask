package cft.mardeev.parser;

import cft.mardeev.domain.Arguments;
import cft.mardeev.utils.Literals;
import cft.mardeev.utils.Option;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@AllArgsConstructor
public class ParserArgsImpl implements ParserArgs {

    private Arguments arguments;
    private final String MOCK_VALUE = "YES";

    private Logger logger;

    @Override
    public Arguments parse(String[] args) {
        parser(args);
        return this.arguments;
    }

    private void parser(String[] args) {
        int size = args.length;
        for (int i = 0; i < size; i++) {
            String arg = args[i];
            if (arg.charAt(0) == '-') {
                if (arg.length() > 2) {
                    logger.info(Literals.OPTION_NOT + arg);
                } else {
                    if (i + 1 >= size) {
                        i += addOption(arg, null);
                    } else {
                        i += addOption(arg, args[i + 1]);
                    }
                }
            } else {
                arguments.getFiles().add(arg);
            }
        }
    }

    private int addOption(String option, String value) {
        switch (option) {
            case Option.OPTION_S:
            case Option.OPTION_F:
            case Option.OPTION_A:
                this.arguments.getOption().put(option, MOCK_VALUE);
                return 0;
            case Option.OPTION_O:
            case Option.OPTION_P:
                value += "";
                arguments.getOption().put(option, value);
                return 1;
            default:
                logger.info(Literals.OPTION_NOT + option);
                return 0;
        }
    }


}
