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
public class Soldier extends Actor {
    
    private static int idSoldier = 0;
    private int id;
    private int soldierRound;
    private final int DAMAGE = 1;
    
    public Soldier(int idRoom, int stamina)
    {
        super(idRoom,stamina);
        id = idSoldier++;
    }
    
    public int getSoldierId()
    {
        return this.id;
    }
    
    private void attack(Robot rb)
    {
        rb.reduceStamina(DAMAGE);
        
    }
    
    private void moveTo(Colony c)
    {   
        Soldier moveSoldier = c.subSoldier(super.getRoom(),this.id);        // o stratioths pou feugei apo to dwmatio gia na bei sto epomenw mesw ths subsoldier 
        Room presentRoom = c.getRoom(super.getRoom());
        Room nextRoom = presentRoom.getExit();
        
        nextRoom.addSoldier(moveSoldier);
        super.setRoom(nextRoom.getId());
    }
    
    
    public int getRound()
    {
        return this.soldierRound;
    }
    
    public void setSoldierRound(int currentRound)
    {
        this.soldierRound = currentRound;
    }
    
    
    
    @Override
    public  void act(Colony c)
    {
        
        Room presentRoom = c.getRoom(getRoom());
         if(presentRoom.hasRobot())
           attack(presentRoom.getRobot());
        else
             moveTo(c);
                
        soldierRound++;     //ENHMERWNEI TON GURO TOU SOLDEIRER
        
    }
    
}
