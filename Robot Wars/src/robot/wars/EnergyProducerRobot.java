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
public class EnergyProducerRobot extends Robot {
    
    public EnergyProducerRobot(int idRoom, int stamina)
    {
       
        super(idRoom,1);
        super.setEnergyNeeded(3);
        super.setType("EnergyProducerRobot");
    }
    
    @Override
    public void act(Colony c)
    {
       c.increaseEnergy(1);
    }
    
    
}
