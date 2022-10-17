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
public class ShooterRobot extends Robot{
    private int rest = 0;
    private final int DMG = 3;
    public ShooterRobot(int idRoom, int stamina)
    {
       
        super(idRoom,1);
        super.setEnergyNeeded(4);
        super.setType("ShooterRobot");
    }
    
    @Override
   public void act(Colony c)
   {
      int id = super.getRoom();
      Room presentRoom = c.getRoom(id);
      
      
      if(rest >0)
      {    
          rest--;
      }
      else
      {
        if( presentRoom.numSoldiers() == 0 )            //RWTAME AN EINAI STRATIOTES MESA STO DWMATIO 
        {
            return ;
        }
        else                                            //vrhke stratiwth 
        {
            Soldier SoldiertoBattle;
            SoldiertoBattle = presentRoom.getSoldier(0);
            SoldiertoBattle.reduceStamina(DMG);
            rest = 3;
        }
      }   
      
   }
    
}
