package Utilities;

import Enemies.Goomba;
import Enemies.Piranha;
import Enemies.Troopa;
import Scenes.Playing;
import Visuals.Cloud;
import Visuals.Fence;
import Visuals.FinishLine;
import Visuals.Grass;
import com.company.ConfigWindow;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import static Enemies.Goomba.GoombaList;
import static Enemies.Piranha.PiranhaList;
import static Enemies.Troopa.TroopaList;
import static Visuals.Cloud.CloudList;
import static Visuals.Fence.FenceList;
import static Visuals.FinishLine.FinishLineList;
import static Visuals.Grass.GrassList;

public class SaveLevel {
    public static void saveJson() {
        int[][] lvl = Playing.lvl;

        JSONObject jsonObject = new JSONObject(); // Main object

        JSONArray mapArray = new JSONArray();
        JSONArray playerCoords = new JSONArray();
        JSONArray playerStart = new JSONArray(); // Array in array... that way it works
        JSONObject mobsObject = new JSONObject();
        JSONObject visualsObject = new JSONObject();

        // Map load
        for (int i = 0; i < lvl.length; i++) {
            JSONArray rowArray = new JSONArray();
            for (int j = 0; j < lvl[i].length; j++) {
                int cell = lvl[i][j];
                if (cell == 201) {
                    cell = 90;
                    playerCoords.add(j * 48);
                    playerCoords.add(i * 48);
                }
                rowArray.add(cell);
            }
            mapArray.add(rowArray);
        }
        if (playerCoords.isEmpty()) {
            playerCoords.add(LevelBuilder.startingX);
            playerCoords.add(LevelBuilder.startingY);
        }
        playerStart.add(playerCoords);

        // Mobs load
        // Goomba
        JSONArray goombaArray = new JSONArray();
        for (Goomba go : GoombaList) {
            JSONArray coordinates = new JSONArray();
            coordinates.add(go.getX());
            coordinates.add(go.getY());
            goombaArray.add(coordinates);
        }

        // Troopa
        JSONArray troopaArray = new JSONArray();
        for (Troopa tr : TroopaList) {
            JSONArray coordinates = new JSONArray();
            coordinates.add(tr.getX());
            coordinates.add(tr.getY());
            troopaArray.add(coordinates);
        }

        // Piranha
        JSONArray piranhaArray = new JSONArray();
        for (Piranha pi : PiranhaList) {
            JSONArray coordinates = new JSONArray();
            coordinates.add(pi.getX());
            coordinates.add(pi.getY());
            piranhaArray.add(coordinates);
        }

        // Adding to mobsObject
        mobsObject.put("troopa", troopaArray);
        mobsObject.put("goomba", goombaArray);
        mobsObject.put("piranha", piranhaArray);


        // Visuals load
        // FinishLine
        JSONArray finishLineCoords = new JSONArray();
        for (FinishLine fl : FinishLineList) {
            JSONArray coordinates = new JSONArray();
            coordinates.add(fl.getX());
            coordinates.add(fl.getY());
            finishLineCoords.add(coordinates);
        }

        // Grass
        JSONArray grassArray = new JSONArray();
        for (Grass gr : GrassList) {
            JSONArray coordinates = new JSONArray();
            coordinates.add(gr.getX());
            coordinates.add(gr.getY());
            grassArray.add(coordinates);
        }

        // Cloud
        JSONArray cloudArray = new JSONArray();
        for (Cloud c : CloudList) {
            JSONArray coordinates = new JSONArray();
            coordinates.add(c.getX());
            coordinates.add(c.getY());
            cloudArray.add(coordinates);
        }

        // Fence
        JSONArray fenceArray = new JSONArray();
        for (Fence f : FenceList) {
            JSONArray coordinates = new JSONArray();
            coordinates.add(f.getX());
            coordinates.add(f.getY());
            fenceArray.add(coordinates);
        }

        visualsObject.put("grass", grassArray);
        visualsObject.put("clouds", cloudArray);
        visualsObject.put("fence", fenceArray);


        // Setting everything inside main object
        jsonObject.put("visuals", visualsObject);
        jsonObject.put("mobs", mobsObject);
        if (FinishLineList.size() != 0) {
            jsonObject.put("finishLine", finishLineCoords);
        }
        jsonObject.put("playerStart", playerStart);
        jsonObject.put("map", mapArray);

        try {
            FileWriter file = new FileWriter("Worlds/" + ConfigWindow.worldName + ".json");
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
