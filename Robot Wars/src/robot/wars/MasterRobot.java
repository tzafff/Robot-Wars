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
public class MasterRobot extends Robot {
    
    public MasterRobot(int idRoom, int stamina)
    {
        super(idRoom,1);
        super.setEnergyNeeded(0);
        super.setType("MasterRobot");
    }
    
    public void act(Colony c)
    {
       
    }
    
}
