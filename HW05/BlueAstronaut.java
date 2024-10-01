import java.util.Arrays;

public class BlueAstronaut extends Player implements Crewmate {
    private int numTasks;
    private int taskSpeed;

    public static final int SUSLEVEL_DEFAULT = 15;
    public static final int NUMTASKS_DEFAULT = 6;
    public static final int TASKSPEED_DEFAULT = 10;

    public BlueAstronaut(String name, int susLevel, int numTasks, int taskSpeed) {
        super(name, susLevel);
        this.numTasks = numTasks;
        this.taskSpeed = taskSpeed;
    }

    public BlueAstronaut(String name) {
        this(name, SUSLEVEL_DEFAULT, NUMTASKS_DEFAULT, TASKSPEED_DEFAULT);
    }

    public void emergencyMeeting() {
        if (this.isFrozen())
            return;
        Player[] playerList = Player.getPlayers();
        Arrays.sort(playerList);
        System.out.println(playerList[playerList.length - 1].toString());
        for (int i = playerList.length - 1; i >= 0; i--) {
            if (playerList[i].isFrozen())
                continue;
            if (playerList[i - 1].isFrozen())
                continue;
            if (playerList[i].getSusLevel() == playerList[i - 1].getSusLevel()) {
                System.out.println("Same level");
            } else
                playerList[i].setFrozen(true);
            this.gameOver();
            break;
        }
    }

    int count = 0;

    public void completeTask() {
        if (this.isFrozen())
            return;
        if (this.taskSpeed > 20) {
            this.numTasks -= 2;
        } else {
            this.numTasks--;
        }
        if (this.numTasks < 0) {
            this.numTasks = 0;
            count++;
        }
        if (count == 1) {
            System.out.println("I have completed all my tasks");
            this.setSusLevel((int) (this.getSusLevel() * 0.5));
        }
    }

    public boolean equals(Object o) {
        if (o instanceof BlueAstronaut) {
            BlueAstronaut player = (BlueAstronaut) o;
            return this.getName().equals(player.getName()) && this.isFrozen() == player.isFrozen()
                    && this.getSusLevel() == player.getSusLevel() && this.numTasks == player.numTasks
                    && this.taskSpeed == player.taskSpeed;
        }
        return false;
    }

    public String toString() {
        String frozenString = this.isFrozen() ? "frozen" : "not frozen";
        String message = "My name is " + this.getName() + ", and I have a susLevel of "
                + this.getSusLevel() + ". I am currently " + frozenString + ". I have " + this.numTasks + " left over.";
        if (this.getSusLevel() > 15) {
            return message.toUpperCase();
        } else
            return message;
    }

    public int getNumTasks() {
        return this.numTasks;
    }

    public void setNumTasks(int numTasks) {
        this.numTasks = numTasks;
    }

    public int getTaskSpeed() {
        return this.taskSpeed;
    }

    public void setTaskSpeed(int taskSpeed) {
        this.taskSpeed = taskSpeed;
    }
}
