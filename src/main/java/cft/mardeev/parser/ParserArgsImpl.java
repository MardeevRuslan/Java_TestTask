package cft.mardeev.parser;

import cft.mardeev.domain.Arguments;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ParserArgsImpl implements ParserArgs {

    private Arguments arguments;

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
                    System.out.println("Нет опции " + arg);
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
            case "-s":
            case "-f":
            case "-a":
                this.arguments.getOption().put(option, "Yes");
                return 0;
            case "-o":
            case "-p":
                arguments.getOption().put(option, value);
                return 1;
            default:
                System.out.println("Нет опции " + option);
                return 0;

        }
    }


}
