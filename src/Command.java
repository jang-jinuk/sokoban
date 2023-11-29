import java.util.Scanner;

public class Command {
    char[] inputCommand() {
        String commandInput;
        char[] userCommand;
        boolean offButton = true;

        System.out.print("SOKOBAN> ");
        Scanner sc = new Scanner(System.in);
        commandInput = sc.nextLine();
        System.out.println();
        commandInput = commandInput.toUpperCase();
        userCommand = new char[commandInput.length()];

        for (int i = 0; i < commandInput.length(); i++) {
            char rudder = commandInput.charAt(i);
            userCommand[i] = rudder;
            if (userCommand[i] == 'Q') {
                System.out.println("Bye~~");
                System.exit(0);
            }
        }
        return userCommand;
    }
    int [] commandCenter(char userCommand, int currentRow, int currentColumn) {
        int[] coordinate = new int[2];

            switch (userCommand) {
                case 'W':
                    coordinate[0] = --currentRow;
                    coordinate[1] = currentColumn;
                    break;
                case 'A':
                    coordinate[0] = currentRow;
                    coordinate[1] = --currentColumn;
                    break;
                case 'S':
                    coordinate[0] = ++currentRow;
                    coordinate[1] = currentColumn;
                    break;
                case 'D':
                    coordinate[0] = currentRow;
                    coordinate[1] = ++currentColumn;
                    break;
                default:
                    coordinate[0] = currentRow;
                    coordinate[1] = currentColumn;
                    break;
            }
        return coordinate;
    }
    void commandNotice(char userCommand){
        switch (userCommand) {
            case 'W':
                System.out.println("W : 위로 이동합니다.\n");
                break;
            case 'A':
                System.out.println("A : 왼쪽으로 이동합니다.\n");
                break;
            case 'S':
                System.out.println("S : 아래로 이동합니다.\n");
                break;
            case 'D':
                System.out.println("D : 오른쪽으로 이동합니다.\n");
                break;
            default:
                System.out.printf("%c : (경고) 지원하지 않는 명령입니다!\n\n",userCommand);
        }
    }
}
