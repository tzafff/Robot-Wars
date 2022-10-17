/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot.wars;

/**
 *
 * @author chris
 */
public abstract  class Robot extends Actor {
    
    private int energyNeeded;
    private String typeOfRobot;
    
    public Robot(int r, int stamina)
    {
        super(r,stamina);
    }
    
    public void setEnergyNeeded(int energy)
    {
        this.energyNeeded = energy;
    }
    
    public void setType(String type)
    {
        typeOfRobot = type;
    }
    
    public String getType()
    {
        return typeOfRobot;
    }
    
    public int getEnergyNeeded()
    {
        return this.energyNeeded;
    }
    
    @Override
    public  abstract void act(Colony c);
   
    
}
