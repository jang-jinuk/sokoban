public class Sokoban {
    void goSokoban() {
        int[][] stage2 = new int[][]{{0, 0, 4, 4, 4, 4, 4, 4, 4, 0, 0},
                {4, 4, 4, 0, 0, 1, 0, 0, 4, 4, 4},
                {4, 0, 0, 0, 0, 2, 0, 0, 0, 0, 4},
                {4, 0, 1, 2, 0, 3, 0, 2, 1, 0, 4},
                {4, 4, 4, 0, 0, 2, 0, 0, 4, 4, 4},
                {0, 4, 0, 0, 0, 1, 0, 0, 0, 4, 0},
                {0, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0}};

        while (true) {
            printMap(stage2);

            int[] currentLocation = playerLocation(stage2);
            int currentRow = currentLocation[0];
            int currentColumn = currentLocation[1];

            Command act = new Command();
            char[] userCommand = act.inputCommand();

            for (int i = 0; i < userCommand.length; i++) {

                int [] coordinate = act.commandCenter(userCommand[i], currentRow, currentColumn);

                int changeRow = coordinate[0];
                int changeColumn = coordinate[1];

                if (stage2[changeRow][changeColumn] == 1 || stage2[changeRow][changeColumn] == 2
                        || stage2[changeRow][changeColumn] == 4) {
                    printMap(stage2);
                    System.out.printf("%c : (경고!) 해당 명령을 수행할 수 없습니다!\n\n", userCommand[i]);
                }
                else if (stage2[changeRow][changeColumn] == 3) {
                    printMap(stage2);
                    act.commandNotice(userCommand[i]);
                } else {
                        stage2[changeRow][changeColumn] = 3;
                        stage2[currentRow][currentColumn] = 0;
                        printMap(stage2);
                        act.commandNotice(userCommand[i]);
                }
                currentLocation = playerLocation(stage2);
                currentRow = currentLocation[0];
                currentColumn = currentLocation[1];
            }
        }
    }
    void printMap(int[][] stage) {
        int rowLength = stage.length;
        int culumnLength = stage[0].length;

        for (int i = 0; i < rowLength; ++i) {
            for (int j = 0; j < culumnLength; ++j) {
                if (stage[i][j] == 0) {
                    System.out.print(" ");
                }
                if (stage[i][j] == 1) {
                    System.out.print("O");
                }
                if (stage[i][j] == 2) {
                    System.out.print("o");
                }
                if (stage[i][j] == 3) {
                    System.out.print("P");
                }
                if (stage[i][j] == 4) {
                    System.out.print("#");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    int[] playerLocation(int[][] stage) {

        int rowLength = stage.length;
        int culumnLength = stage[0].length;
        int[] currentLocation = new int[2];

        for (int i = 0; i < rowLength; ++i) {
            for (int j = 0; j < culumnLength; ++j) {
                if (stage[i][j] == 3) {
                    currentLocation[0] = i;
                    currentLocation[1] = j;
                }
            }
        }
        return currentLocation;
    }
}