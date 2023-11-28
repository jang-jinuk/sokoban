import java.util.Scanner;

public class Command {
    int[] indicateMove(int[] currentLocation) {
        System.out.print("SOKOBAN> ");
        Scanner sc = new Scanner(System.in);
        String commandInput = sc.nextLine();
        char [] indicateMove = new char[commandInput.length()];

        for (int i = 0; i < commandInput.length(); i++){
                char rudder = commandInput.charAt(i);
            indicateMove[i] = rudder;
        }

        int [] changeLocation = movePlayer(indicateMove,currentLocation);

        return changeLocation;
    }
    int [] movePlayer (char [] indicateMove,int[] currentLocation) {
        int row = currentLocation[0];
        int culumn = currentLocation[1];

        for (int i = 0; i < indicateMove.length; i++) {
            switch (indicateMove[i]) {
                case 'w':
                    row--;
                    break;
                case 'a':
                    culumn--;
                    break;
                case 's':
                    row++;
                    break;
                case 'd':
                    culumn++;
                    break;
                case 'q':
                    System.out.println("Bye~");
                    break;
            }
        }
        currentLocation[0] = row;
        currentLocation[1] = culumn;
        return currentLocation;
    }
}
