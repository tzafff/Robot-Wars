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
public class FireRobot extends Robot{
    
    private final int DMG = 3;
    public FireRobot(int idRoom, int stamina)
    {
        super(idRoom,1);
        super.setEnergyNeeded(4);
        super.setType("FireRobot");

    }
    
    
    @Override
   public void act(Colony c)
   {
       if(super.getStamina() <= 0)
       {
            int id = super.getRoom();
            Room presentRoom = c.getRoom(id);       //DWMATIO POU EINAI TO ROBOT
            int soldiersIntheRoom = presentRoom.numSoldiers(); 
            
            if( soldiersIntheRoom == 0 )            //RWTAME AN EINAI STRATIOTES MESA STO DWMATIO 
            {
                return ;
            }
            else                                            //vrhke stratiwth 
            {
                for(int i = 0; i < soldiersIntheRoom; i++)              //NA SKOTWSEI OLOUS TOU SOLDIERS ME FOR 
                {
                    Soldier SoldiertoBattle;
                    SoldiertoBattle = presentRoom.getSoldier(i);
                    SoldiertoBattle.reduceStamina(DMG);
                }
            }
            
       }
       
   }
}
