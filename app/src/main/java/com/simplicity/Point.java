package com.simplicity;

import java.util.HashMap;

import org.json.simple.JSONObject;

public class Point {
    private int x = 0;
    private int y = 0;

    // a constructor!
    public Point(int a, int b) {
        x = a;
        y = b;
    }

    public Point(JSONObject jsonObject) {
        x = Integer.parseInt(jsonObject.get("x").toString());
        y = Integer.parseInt(jsonObject.get("y").toString());
    }

    public JSONObject toJson() {
        HashMap<String, Object> pointMap = new HashMap<String, Object>();
        pointMap.put("x", x);
        pointMap.put("y", y);

        JSONObject pointJSON = new JSONObject(pointMap);
        return pointJSON;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int ax) {
        x = ax;
    }

    public void setY(int ay) {
        y = ay;
    }

}
