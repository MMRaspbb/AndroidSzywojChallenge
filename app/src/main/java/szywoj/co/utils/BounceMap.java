package szywoj.co.utils;

import java.util.Random;

public class BounceMap {
    private int mapWidth;
    private int mapHight;
    private double objectWidth;
    private double objectHight;
    private double parameterA = 0.7;
    private double parameterB = 0;
    private double currentX = 100;
    private double XMultiplier = 1;
    public BounceMap(int mapHight, int mapWidth, double objectHight, double objectWidth){
        this.mapHight = mapHight;
        this.mapWidth = mapWidth;
        this.objectHight = objectHight;
        this.objectWidth = objectWidth;
    }
    private void setCurrentX(double x){
        currentX = x;
    }
    private void setParameterA(double a){
        parameterA = a;
    }
    private void setParameterB(double b){
        parameterB = b;
    }
    public double getCurrentX(){
        return currentX;
    }
    public double getCurrentY(){
        return parameterA*currentX + parameterB;
    }
    public void moveBy(double number){
        double xDistance = SquareFormula.positiveZeroPlace(1 + Math.pow(parameterA,2), 0, -1*Math.pow(number,2));
        double currentY = Math.floor(currentX * parameterA + parameterB);
        if(xDistance*XMultiplier + currentX + objectWidth/2 > mapWidth){
            double leftoverX = xDistance + currentX*XMultiplier + objectWidth/2 - mapWidth;
            XMultiplier *= -1;
            currentX = mapWidth - objectWidth/2;
            edgeBounce();
            moveBy(Math.sqrt(Math.pow(leftoverX,2) + Math.pow(leftoverX*parameterA, 2)));
        } else if(currentX + xDistance*XMultiplier - objectWidth/2 < 0){
            double leftoverX = -(currentX + xDistance*XMultiplier - objectWidth/2);
            XMultiplier*=-1;
            currentX = objectWidth/2;
            edgeBounce();
            moveBy(Math.sqrt(Math.pow(leftoverX,2) + Math.pow(leftoverX*parameterA, 2)));
        } else if (currentY + xDistance*parameterA*XMultiplier + objectHight/2 > mapHight) {
            double leftoverY = mapHight - currentY - xDistance*parameterA - objectHight/2;
            currentX = ((mapHight - objectHight/2) - parameterB)/parameterA;
            edgeBounce();
            moveBy(Math.sqrt(Math.pow(leftoverY,2) + Math.pow(leftoverY/parameterA, 2)));
        } else if (currentY + xDistance*parameterA*XMultiplier - objectHight/2 < 0) {
            double leftoverY = -(currentY + xDistance*parameterA - objectHight/2);
            currentX = (objectHight/2 - parameterB)/parameterA;
            edgeBounce();
            moveBy((Math.sqrt(Math.pow(leftoverY,2) + Math.pow(leftoverY/parameterA, 2))));
        } else {
            currentX += xDistance*XMultiplier;
        }
    }
    private void edgeBounce(){
        double currentY = parameterA * currentX + parameterB;
        double currentAngle = Math.atan(parameterA);
        parameterA = Math.tan(Math.PI - currentAngle);
        //parameterA = -1/parameterA;
        parameterB = currentY - parameterA*currentX;
    }

    public boolean hitObject(float x, float y) {
        double currentY = currentX*parameterA + parameterB;
        if(x > currentX - objectWidth/2 && x < currentX + objectWidth/2 && y > currentY - objectHight/2 && y < currentY + objectHight/2){
            return true;
        }
        return false;
    }

    public void randomizeAndCalculate() {
        double currentY = parameterA*currentX + parameterB;
        if(Math.random() > 0.5){
            parameterA = Math.random();
        } else{
            parameterA = Math.random() * 10;
        }
        if(Math.random() > 0.5){
            XMultiplier *= -1;
        }
        parameterB = currentY - currentX * parameterA;
    }
}

