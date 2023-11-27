public class Map {
    void mapData() {
        int[][] stage2 = new int[][]{{0, 0, 4, 4, 4, 4, 4, 4, 4, 0, 0},
                {4, 4, 4, 0, 0, 1, 0, 0, 4, 4, 4},
                {4, 0, 0, 0, 0, 2, 0, 0, 0, 0, 4},
                {4, 0, 1, 2, 0, 3, 0, 2, 1, 0, 4},
                {4, 4, 4, 0, 0, 2, 0, 0, 4, 4, 4},
                {0, 4, 0, 0, 0, 1, 0, 0, 0, 4, 0},
                {0, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0}};
        int stage = 2;
        System.out.printf("Stage%d\n\n", stage);
        printMap(stage2);
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
}