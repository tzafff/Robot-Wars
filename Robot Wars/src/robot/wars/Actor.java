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
public abstract class Actor {
    
    private int room;
    private int stamina;
    
    public Actor(int r, int st)
    {
        this.room = r;
        this.stamina = st;
    }
    
    
    public int getRoom()
    {
        return room;
    }
    
    public void setRoom(int idRoom)
    {
        this.room = idRoom;             //GIA NA KSEREI O STRATIOTHS SE PIO DWMATIO EINAI 
    }
    
    public void leaveRoom()
    {
        
    }
    
    public int getStamina()
    {
        return this.stamina;
    }
    
    public void reduceStamina(int amount)
    {
        this.stamina -=amount;
        if(this.stamina <= 0)
        {
            leaveRoom();
        }
    }
    
    public abstract void act(Colony c);
   
    
}
