package proxet.tournament.generator.dto;

public class Player {

    private final String nickname;
    private final int waitingTime;
    private final int vehicleType;

    public Player(String nickname, int waitingTime, int vehicleType) {
        this.nickname = nickname;
        this.waitingTime = waitingTime;
        this.vehicleType = vehicleType;
    }

    public String getNickname() {
        return nickname;
    }

    public int getVehicleType() {
        return vehicleType;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    @Override
    public String toString() {
        return "Player{" + "nickname='" + nickname + '\''
                + ", waitingTime=" + waitingTime
                + ", vehicleType=" + vehicleType
                + '}';
    }
}
