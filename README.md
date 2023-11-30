# 2단계: 플레이어 이동 구현하기   
<details>
<summary>문제 설명 및 요구사항</summary>

## 문제 설명
스테이지 2 지도가 아래처럼 있다.

```
Stage 2

  #######
###  O  ###
#    o    #
# Oo P oO #
###  o  ###
 #   O  # 
 ########
```
사용자 입력을 받아서 아래의 동작에 따라 플레이어가 이동하는 프로그램을 구현하시오
```
w: 위쪽
a: 왼쪽
s: 아래쪽
d: 오른쪽
q: 프로그램 종료
```
## 요구사항
- 처음 시작하면 스테이지 2의 지도를 출력한다.
- 간단한 프롬프트 (예: SOKOBAN> )를 표시해 준다.
- 하나 이상의 문자를 입력받은 경우 순서대로 처리해서 단계별 상태를 출력한다.
- 이동 중 벽이나 공등 다른 물체에 부딪히면 (경고!) 해당 명령을 수행할 수 없습니다! 라는 메시지를 출력하고 플레이어를 움직이지 않는다.
- 지원하지 않는 명령을 입력했을 경우 (경고) 지원하지 않는 명령입니다! 라는 메시지를 출력하고, 다시 사용자의 입력을 받는다.

## 동작 예시
```
Stage 2

  #######
###  O  ###
#    o    #
# Oo P oO #
###  o  ###
 #   O  # 
 ########

SOKOBAN> ddzw (엔터)

  #######
###  O  ###
#    o    #
# Oo  PoO #
###  o  ###
 #   O  # 
 ########
 
 D: 오른쪽으로 이동합니다.
 
  #######
###  O  ###
#    o    #
# Oo  PoO #
###  o  ###
 #   O  # 
 ########
 
 D: (경고!) 해당 명령을 수행할 수 없습니다!
 
  #######
###  O  ###
#    o    #
# Oo  PoO #
###  o  ###
 #   O  # 
 ########
 
 Z: (경고!) 해당 명령을 수행할 수 없습니다!
 
  #######
###  O  ###
#    o    #
# Oo  PoO #
###  o  ###
 #   O  # 
 ########
 
 W: 위로 이동합니다.
 
   #######
###  O  ###
#    oP   #
# Oo   oO #
###  o  ###
 #   O  # 
 ########
 
SOKOBAN> q
Bye~
```
## 2단계 코딩 요구사항
- 너무 크지 않은 함수 단위로 구현하고 중복된 코드를 줄이도록 노력한다.
- 전역변수의 사용을 자제한다.
- 객체, 리스트, 배열 등을 적절히 활용한다.
- 1단계와 같이 README.md 파일을 작성한다.
- 구현 완료 커밋에 v2 태그를 생성하고 GitHub에 push 한다.
</details>

# 구조
Sokoban 클래스를 실행해주는 Main class와 스테이지 2 맵에 대한 정보를 저장하고 관리하는 Sokoban class, 프로폼트를 실행하여 사용자의 입력을 받고 관리하는 Command class를 구현했다.

### 목차
[1. Main class](#Main-class)   
[2. Sokoban class](#Sokoban-class)   
[3. Command class](#Command-class)

# Main class
Sokoban 객체와 goSokoban()메서드를 활용하여 Main클래스는 단순하게 구현하였다.
```java
Sokoban Lets = new Sokoban();
Lets.goSokoban();
```
# Sokoban class
|메서드|기능|
|---|---|
|goSokoban()|맵 정보를 저장하고 소코반게임의 시작,진행,종료에 대한 전반적인 역할 수행|
|printMap(stage)|맵 정보를 받아 출력|
|playLocation(stage)|맵 정보를 받아 플레이어의 현재 위치를 파악|

### goSokoban 메서드
- 맵 정보를 저장하고 소코반게임의 시작,진행,종료에 대한 전반적인 역할 수행

1. 맵 정보를 저장하고 저장한 맵 정보를 각 메서드에 전달한다.
2. 사용자의 입력에 대한 명령이 유효한지 판단하고 플레이어의 위치를 바꿔주거나 경고 메세지를 출력한다.
```java
    void goSokoban() {
        int[][] stage2 = new int[][]{{0, 0, 4, 4, 4, 4, 4, 4, 4, 0, 0},
                {4, 4, 4, 0, 0, 1, 0, 0, 4, 4, 4},
                {4, 0, 0, 0, 0, 2, 0, 0, 0, 0, 4},
                {4, 0, 1, 2, 0, 3, 0, 2, 1, 0, 4},
                {4, 4, 4, 0, 0, 2, 0, 0, 4, 4, 4},
                {0, 4, 0, 0, 0, 1, 0, 0, 0, 4, 0},
                {0, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0}};
        int[] currentLocation = playerLocation(stage2);
        while (true) {
            printMap(stage2);

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
```
### printMap 메서드
- 맵 정보를 받아 출력

1. goSokoban 메서드로부터 맵 정보를 담고 있는 배열을 전달 받고 맵을 출력한다.
2. 맵 정보가 저장하고 있는 값을 아래 기준에 따라 변환하여 출력한다.

|기호|저장 값|
|:---:|:---:|
|공백|0|
|O|1|
|o|2|
|P|3|
|#|4|

```java
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
```

### playLocation 매서드
- 맵 정보를 받아 플레이어의 현재 위치를 파악
  
1. goSokoban 메서드에서 맵 정보를 받아 현재위치를 배열의 인덱스 값으로 파악한다.
2. 파악한 배열의 인덱스 값을 currentLocation 배열에 담아 반환한다.

  ```java
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
  ```
# Command class
|메서드|기능|
|---|---|
|inputCommand()|프롬포트를 실행하여 사용자의 입력을 받음|
|commandCenter(userCommand, currentRow, currentColumn)|사용자 입력에 따른 플레이어의 위치 조정|
|commandNotice(userCommand)|사용자 입력에 따라 플레이어 움직에 대한 메세지를 출력|

### intputCommmand 메서드
- 프롬포트를 실행하여 사용자의 입력을 받음

1. 사용자 입력을 받는 프롬포트를 실행하여 입력을 받고 배열에 저장하여 반환한다.
2. 입력 받은 값을 대,소문자 모두 받을 수 있도록 입력 받은 값은 모두 대문자로 변경해준다.
3. 입력 받은 중 q가 있다면 "Bye~~"라는 메세지를 출력하고 프로그램을 종료한다.

```java
char[] inputCommand() {

        System.out.print("SOKOBAN> ");
        Scanner sc = new Scanner(System.in);
        String commandInput = sc.nextLine();
        System.out.println();
        commandInput = commandInput.toUpperCase();
        char [] userCommand = new char[commandInput.length()];

        for (int i = 0; i < commandInput.length(); i++) {
            char commandBox = commandInput.charAt(i);
            userCommand[i] = commandBox;

            if (userCommand[i] == 'Q') {
                System.out.println("Bye~~");
                System.exit(0);
            }
        }
        return userCommand;
    }
```

### commandCenter
- 사용자 입력에 따른 플레이어의 위치 조정

1. 사용자에게 받은 입력 값과 플레어의 현재위치를 받아 플레어의 위치를 조정하고 변경된 위치를 반환한다.
2. 입력 받은 값을 해당 되는 움직에 따라 위치를 조정한다.

```java
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
```
### commandNotice
- 사용자 입력에 따라 플레이어 움직에 대한 메세지를 출력

1. 사용자 입력을 받아와 해당 되는 움직임의 메세지를 출력한다.
2. 지원하지 않는 입력 시 경고 메세지를 출력한다.

```java
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
```
