public class Map {
    void mapData(){
        int[][] stage1 = {{4,4,4,4,4},{4,1,2,3,4},{4,4,4,4,4}};
        int[][] stage2 = {{0,0,4,4,4,4,4,4,4,0,0},
                            {4,4,4,0,0,1,0,0,4,4,4},
                            {4,0,0,0,0,2,0,0,0,0,4},
                            {4,0,1,2,0,3,0,2,1,0,4},
                            {4,4,4,0,0,2,0,0,4,4,4},
                            {0,4,0,0,0,1,0,0,0,4,0},
                            {0,4,4,4,4,4,4,4,4,0,0}};
        int stage = 1;
        System.out.printf("Stage%d\n\n",stage);
        stage++;
        printMap(stage1);
        mapInformation(stage1);
        System.out.printf("Stage%d\n\n",stage);
        printMap(stage2);
        mapInformation(stage2);
    }
    void printMap(int stage[][]){
        int rowLength = stage.length;
        int culumnLength = stage[0].length;

        for (int i = 0; i < rowLength; i++){
            for (int j = 0; j < culumnLength; j++){
                if (stage[i][j] == 0)
                    System.out.print(" ");
                if (stage[i][j] == 1)
                    System.out.print("O");
                if (stage[i][j] == 2)
                    System.out.print("o");
                if (stage[i][j] == 3)
                    System.out.print("P");
                if (stage[i][j] == 4)
                    System.out.print("#");
            }
            System.out.println();
        }
        System.out.println();
    }
    void mapInformation(int[][] stage){
        int width = 0;
        width = stage[0].length;
        int height = 0;
        height = stage.length;

        int hole = 0;
        int ball = 0;
        int[] playerPosition = new int[2];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (stage[i][j] == 1)
                    hole++;
                if (stage[i][j] == 2)
                    ball++;
                if (stage[i][j] == 3) {
                    playerPosition[0] = i+1;
                    playerPosition[1] = j+1;
                }
            }
        }
        System.out.printf("가로크기 : %d\n",width);
        System.out.printf("세로크기 : %d\n",height);
        System.out.printf("구멍의 수 : %d\n",hole);
        System.out.printf("공의 수 : %d\n",ball);
        System.out.printf("플레이어 위치 : %d행 %d열\n\n",playerPosition[0],playerPosition[1]);
    }
}