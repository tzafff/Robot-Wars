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
public class ArmoredRobot extends Robot {
    
    private int idRoom;
    
    public ArmoredRobot(int idRoom, int stamina)
    {
       
        super(idRoom,4);
        super.setEnergyNeeded(3);
        super.setType("ArmoredRobot");
    }
    
    
   public void act(Colony c)
   {
       
   }
    
    
}
