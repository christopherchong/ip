public class Parser {
    public static String[] parse(String input) {
        String command;
        String arguments = "";

        if (input.contains(" ")) {
            String[] inputArray = input.split(" ", 2);
            command = inputArray[0];
            arguments = inputArray[1];
        } else {
            command = input;
        }

        return new String[]{command, arguments};
    }
}
