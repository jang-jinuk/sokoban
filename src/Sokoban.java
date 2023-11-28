public class Sokoban {
    void goSokoban() {
        int[][] stage2 = new int[][]{{0, 0, 4, 4, 4, 4, 4, 4, 4, 0, 0},
                {4, 4, 4, 0, 0, 1, 0, 0, 4, 4, 4},
                {4, 0, 0, 0, 0, 2, 0, 0, 0, 0, 4},
                {4, 0, 1, 2, 0, 3, 0, 2, 1, 0, 4},
                {4, 4, 4, 0, 0, 2, 0, 0, 4, 4, 4},
                {0, 4, 0, 0, 0, 1, 0, 0, 0, 4, 0},
                {0, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0}};

            printMap(stage2);

            int[] currentLocation = playerLocation(stage2);
            int currentRow = currentLocation[0];
            int currentCulumn = currentLocation[1];

            Command location = new Command();
            int[] changeLocation = location.indicateMove(currentLocation);
            int changeRow = changeLocation[0];
            int changeCulumn = changeLocation[1];

            if (stage2[changeRow][changeCulumn] == 1 || stage2[changeRow][changeCulumn] == 2 || stage2[changeRow][changeCulumn] == 4) {
                System.out.println("(경고!) 해당 명령을 수행할 수 없습니다!");
            } else {
                stage2[changeRow][changeCulumn] = 3;
                stage2[currentRow][currentCulumn] = 0;
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