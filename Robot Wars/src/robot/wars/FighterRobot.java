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
public class FighterRobot extends Robot{
    
    private final int DMG = 1;
    public FighterRobot(int idRoom, int stamina)
    {
        super(idRoom,1);
        super.setEnergyNeeded(4);
        super.setType("FighterRobot");

    }
    
    
    
    
    @Override
   public void act(Colony c)
   {
       
       
       int id = super.getRoom();        //GIA TO DWMATIO POU VALAME TO ROBOT
       Room presentRoom;                    
       int soldiersIntheRoom; 
       int nextRoomID = id;             //GIA TA DWMATIA POU PSAXNEI 
       do{
            presentRoom = c.getRoom(nextRoomID);    // EXEI FTASEI MASTER ROOM 
            if(presentRoom.getId() == -100)
            {
                break;
            }
            soldiersIntheRoom = presentRoom.numSoldiers(); 
            if(soldiersIntheRoom > 0 )
            {
                Soldier SoldiertoBattle;
                SoldiertoBattle = presentRoom.getSoldier(0);
                SoldiertoBattle.reduceStamina(DMG);
                break;
            }
            nextRoomID += c.numTunnels();   //PAEI STO EPOMENO DWMATIO 
            
       }while(true);
      
      
   }
    
}
