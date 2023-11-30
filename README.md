# 1단계: 맵 정보를 객체로 저장 및 출력하기
<details>
<summary>문제 설명 및 요구사항</summary>

## 문제 설명
아래 내용을 문자열로 넘겨서 처리하는 함수를 작성한다.
```
Stage 1
#####
#OoP#
#####
=====
Stage 2
  #######
###  O  ###
#    o    #
# Oo P oO #
###  o  ###
 #   O  # 
 ########
```
위 값을 읽고 저장할 수 있는 적당한 객체 (혹은 클래스)를 생성하고 문자열로부터 읽은 값을 변환해서 저장한다.
### 저장 기준
|기호|의미|저장값|
|---|---|---|
|없음|빈 공간|0|
|O|구멍(Hall)|1|
|o|공(Ball)|2|
|P|플레이어(Player)|3|
|#|벽(Wall)|4|
|=|스테이지 구분자|값 없음|

객체에는 스테이지 정보 및 원래 지도 정보가 들어있어야 한다.

다음과 같은 형태로 각 스테이지 정보를 출력한다.
- 플레이어 위치는 배열 [0][0]을 기준으로 행과 열을 출력한다.
- 스테이지 구분값은 출력하지 않는다.
- 
```
Stage 1

#####
#OoP#
#####

가로크기: 5
세로크기: 3
구멍의 수: 1
공의 수: 1
플레이어 위치: 2행 4열

Stage 2

  #######
###  O  ###
#    o    #
# Oo P oO #
###  o  ###
 #   O  # 
 ########

가로크기: 11
세로크기: 7
구멍의 수: 4
공의 수: 4
플레이어 위치 4행 6열
```
## 1단계 코딩 요구사항
- 컴파일 또는 실행이 가능해야 한다.
- 자기만의 기준으로 최대한 간결하게 코드를 작성한다.
</details>

# 구조
Map class를 실행하는 Main class와 stage 맵 정보를 저장하고 출력하는 Map class를 구현했다.

### 목차
[1. Main class](#Main-class)   
[2. Map class](#Map-class)

# Main class
Map객체와 mapData 메서드를 활용하여 Main 클래스는 단순하게 구현하였다.

```java
  Map view = new Map();
  view.mapData();
```

# Map class
|메서드|기능|
|---|---|
|mapData|스테이지 맵 정보를 저장소 및 프로그램 통제 역할 수행|
|printMap(stage)|맵 정보를 받아 맵을 출력|
|mapInformation(stage)|맵 정보를 받아 정보를 관리하고 출력|

### mapData 메서드
- 스테이지 맵 정보를 저장소 및 프로그램 통제 역할 수행

1. 저장된 맵 정보를 저정한다.
2. 다른 메서드에 맵 정보를 전달한다.

```java
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
```
### printMap 메서드
- 맵 정보를 받아 맵을 출력

1. mapData 메서드로부터 맵 정보를 담고 있는 배열을 전달 받고 맵을 출력한다.
2. 맵 정보가 저장하고 있는 값을 [기준](###저장-기준)에 따라 변환하여 출력한다.

```java
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
```

### mapInformation 메서드
- 맵 정보를 받아 정보를 관리하고 출력

1. mapData 메서드로부터 맵 정보를 받아 가로크기, 세로크기, 구멍의 수, 공의 수, 플레이어의 위치를 파악한다.
2. 파악한 정보를 메세지와 함께 출력한다.
```java
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
```




