package ru.stqa.addressbook;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import model.GroupData;
import ru.stqa.addressbook.common.CommonFunctions;

import java.util.ArrayList;


public class Generator {

    @Parameter(names = {"--type", "-t"})
    String type;

    @Parameter(names = {"--output", "-o"})
    String output;

    @Parameter(names = {"--format", "-f"})
    String format;

    @Parameter(names = {"--count", "-c"})
    int count;

    public static void main(String[] args) {
        var generator = new Generator();
        JCommander jCommander = new JCommander(generator);
        jCommander.parse(args);
        generator.run();
    }

    private void run() {
        var data = generate();
        save(data);
    }

    private Object generate() {
        if ("groups".equals(type)) {
            return generateGroups();
        } else if ("contacts".equals(type)) {
            return generateContacts();
        } else {
            throw new IllegalArgumentException("Неизвестный тип данных " + type);
        }
    }

    private Object generateGroups() {
        var result = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            result.add(new GroupData()
                    .withName(CommonFunctions.randomString(i * 10))
                    .withHeader(CommonFunctions.randomString(i * 10))
                    .withFooter(CommonFunctions.randomString(i * 10)));
        }
        return result;
    }

        private Object generateContacts() {
            return null;
        }

        private Object save(Object data) {
            ObjectMapper mapper = new ObjectMapper(); // create once, reuse
            return null;
        }
    }