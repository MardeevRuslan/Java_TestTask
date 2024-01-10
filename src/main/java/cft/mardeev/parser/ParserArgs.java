package cft.mardeev.parser;

import cft.mardeev.domain.Argument;


public class ParserArgs {

    private Argument argument;

    public ParserArgs(Argument argument) {
        this.argument = argument;
    }

    public void parser(String[] args) {
        int size = args.length;
        for (int i = 0; i < size; i++) {
            String arg = args[i];
            if (arg.charAt(0) == '-') {
                if (arg.length() > 2) {
                    System.out.println("Нет опции " + arg);
                } else {
                    i += addOption(arg.charAt(1), args[i + 1]);
                }
            } else {
                argument.getFiles().add(arg);
            }
        }
    }

    private int addOption(Character option, String value) {
        switch (option) {
            case 's':
            case 'f':
                argument.getOptions().put(option, null);
                return 0;
            case 'o':
            case 'p':
                argument.getOptions().put(option, value);
                return 1;
            default:
                System.out.println("Not option -" + option);
                return 0;

        }
    }
}
