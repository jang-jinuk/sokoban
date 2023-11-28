// 맵의 정보를 통해 플레이어의 현재위치를 파악한다.
// 사용자가 입력한 커맨드에 따라 현재위치를 변경한다.
// 변경한 위치가 벽, 구멍, 공이 있다면 경고를 하고 전에 있던 위치로 돌아가고 다음 커맨드를 수행한다.

public class Main {
    public static void main(String[] args) {
        Sokoban Lets = new Sokoban();
        Lets.goSokoban();
    }
}
