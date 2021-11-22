package proxet.tournament.generator;

import proxet.tournament.generator.dto.Player;
import proxet.tournament.generator.dto.TeamGeneratorResult;

import java.io.*;
import java.util.*;

public class TeamGenerator {

    public TeamGeneratorResult generateTeams(String filePath) {
        List<Player> firstTeam = new ArrayList<>();
        List<Player> secondTeam = new ArrayList<>();

        List<Player> playersFromFile = getPlayersFromFile(filePath);
        playersFromFile.sort(Comparator.comparingInt(Player::getWaitingTime).reversed());

        for (Player player : playersFromFile) {
            long firstTeamCount = firstTeam.stream().filter(member -> player.getVehicleType() == member.getVehicleType()).count();
            long secondTeamCount = secondTeam.stream().filter(member -> player.getVehicleType() == member.getVehicleType()).count();
            if (firstTeamCount < 3) {
                firstTeam.add(player);
            } else if (secondTeamCount < 3){
                secondTeam.add(player);
            }
            if (secondTeam.size() == 9) break;
        }
        return new TeamGeneratorResult(firstTeam, secondTeam);
    }

    public List<Player> getPlayersFromFile(String filePath) {
        List<Player> players = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            String playerInfoLine;
            while ((playerInfoLine = fileReader.readLine()) != null) {
                String[] playerInfo = playerInfoLine.split("\t");
                players.add(new Player(playerInfo[0],
                        Integer.parseInt(playerInfo[1]),
                        Integer.parseInt(playerInfo[2])));
            }
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read player info from file " + filePath);
        }
        return players;
    }
}
