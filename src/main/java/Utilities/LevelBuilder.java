package Utilities;

import Enemies.Goomba;
import Enemies.Piranha;
import Enemies.Troopa;
import Visuals.Cloud;
import Visuals.Fence;
import Visuals.FinishLine;
import Visuals.Grass;
import com.company.ConfigWindow;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

import static Enemies.Goomba.GoombaList;
import static Enemies.Piranha.PiranhaList;
import static Enemies.Troopa.TroopaList;
import static Visuals.Cloud.CloudList;
import static Visuals.Fence.FenceList;
import static Visuals.FinishLine.FinishLineList;
import static Visuals.Grass.GrassList;

public class LevelBuilder {
    public static int startingX;
    public static int startingY;

    public static int[][] getLevelData() {
        int[][] lvl = new int[15][ConfigWindow.tileWidth];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < ConfigWindow.tileWidth; j++) {
                lvl[i][j] = 90;
            }
        }
        return lvl;
    }


    public static int[][] getLevelDataFromFile(String filename) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File("worlds/" + filename + ".json"));

            if (rootNode.has("map")) {
                JsonNode mapNode = rootNode.get("map");
                int rowCount = mapNode.size();
                int[][] lvl = new int[rowCount][];
                for (int i = 0; i < rowCount; i++) {
                    JsonNode rowNode = mapNode.get(i);
                    int colCount = rowNode.size();
                    lvl[i] = new int[colCount];
                    for (int j = 0; j < colCount; j++) {
                        lvl[i][j] = rowNode.get(j).asInt();
                    }
                }
                getStartingCoordinates(rootNode);
                getFinishLine(rootNode);
                getVisuals(rootNode);
                //getWarps(rootNode);
                //getCoins(rootNode);
                getEnemy(rootNode);


                return lvl;
            } else {
                System.out.println("There's no file with that name!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new int[0][0];
    }

    private static void getStartingCoordinates(JsonNode rootNode) {
        JsonNode playerStartNode = rootNode.path("playerStart");
        startingX = playerStartNode.get(0).get(0).asInt();
        startingY = playerStartNode.get(0).get(1).asInt();
    }

    private static void getFinishLine(JsonNode rootNode) {
        JsonNode finishLineNode = rootNode.path("finishLine");
        if (!finishLineNode.isMissingNode() && !finishLineNode.isNull()) {
            FinishLineList.add(new FinishLine(finishLineNode.get(0).get(0).asInt(), finishLineNode.get(0).get(1).asInt()));
        }
    }

    private static void getEnemy(JsonNode rootNode) {
        JsonNode goombaNode = rootNode.path("mobs").path("goomba");
        for (JsonNode mob : goombaNode) {
            int x = mob.get(0).asInt();
            int y = mob.get(1).asInt();
            GoombaList.add(new Goomba(x, y));
        }

        JsonNode troopaNode = rootNode.path("mobs").path("troopa");
        for (JsonNode mob : troopaNode) {
            int x = mob.get(0).asInt();
            int y = mob.get(1).asInt();
            TroopaList.add(new Troopa(x, y));
        }

        JsonNode piranhaNode = rootNode.path("mobs").path("piranha");
        for (JsonNode mob : piranhaNode) {
            int x = mob.get(0).asInt();
            int y = mob.get(1).asInt();
            PiranhaList.add(new Piranha(x, y));
        }
    }

    private static void getVisuals(JsonNode rootNode) {
        JsonNode grassNode = rootNode.path("visuals").path("grass");
        for (JsonNode grass : grassNode) {
            int x = grass.get(0).asInt();
            int y = grass.get(1).asInt();
            GrassList.add(new Grass(x, y));
        }

        JsonNode cloudsNode = rootNode.path("visuals").path("clouds");
        for (JsonNode clouds : cloudsNode) {
            int x = clouds.get(0).asInt();
            int y = clouds.get(1).asInt();
            CloudList.add(new Cloud(x, y));
        }

        JsonNode fenceNode = rootNode.path("visuals").path("fence");
        for (JsonNode fence : fenceNode) {
            int x = fence.get(0).asInt();
            int y = fence.get(1).asInt();
            FenceList.add(new Fence(x, y));
        }
    }

//    private static void getWarps(JsonNode rootNode) {
//        JsonNode warpNode = rootNode.path("warps");
//        for (JsonNode warp : warpNode) {
//            int entranceX = warp.get(0).asInt();
//            int entranceY = warp.get(1).asInt();
//            int exitX = warp.get(2).asInt();
//            int exitY = warp.get(3).asInt();
//            WarpPipesMap.put(new WarpPipe(entranceX, entranceY, WARPPIPE), new WarpPipe(exitX, exitY, WARPPIPE));
//        }
//
//        JsonNode worldNode = rootNode.path("worldPipes");
//        int i = 0;
//        for (JsonNode warp : worldNode) {
//            int x = warp.get(0).asInt();
//            int y = warp.get(1).asInt();
//            if (GameStates.gameStates == GameStates.LOBBY) {
//                WorldWarpPipesMap.put(new WarpPipe(x, y, WARPPIPE), lobbyWorldValues.get(i));
//                i++;
//            }
//            else {
//                String world = warp.get(2).asText();
//                WorldWarpPipesMap.put(new WarpPipe(x, y, WARPPIPE), world);
//            }
//        }
//    }
//
//    private static void getCoins(JsonNode rootNode) {
//        JsonNode coinsNode = rootNode.path("coins");
//        for (JsonNode coin : coinsNode) {
//            int x = coin.get(0).asInt();
//            int y = coin.get(1).asInt();
//            CoinList.add(new Coin(x, y, COIN));
//        }
//    }
}
