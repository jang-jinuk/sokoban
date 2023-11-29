import java.util.Scanner;

public class Command {
    char[] inputCommand() {
        System.out.print("SOKOBAN> ");
        Scanner sc = new Scanner(System.in);
        String commandInput = sc.nextLine();
        System.out.println();
        commandInput = commandInput.toUpperCase();
        char [] userCommand = new char[commandInput.length()];

        for (int i = 0; i < commandInput.length(); i++){
                char rudder = commandInput.charAt(i);
            userCommand[i] = rudder;
        }

        return userCommand;
    }
    int [] commandCenter(char userCommand, int currentRow, int currentCulumn) {
        int[] coordinate = new int[2];

            switch (userCommand) {
                case 'W':
                    coordinate[0] = --currentRow;
                    coordinate[1] = currentCulumn;
                    break;
                case 'A':
                    coordinate[0] = currentRow;
                    coordinate[1] = --currentCulumn;
                    break;
                case 'S':
                    coordinate[0] = ++currentRow;
                    coordinate[1] = currentCulumn;
                    break;
                case 'D':
                    coordinate[0] = currentRow;
                    coordinate[1] = ++currentCulumn;
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
        }
    }
}
