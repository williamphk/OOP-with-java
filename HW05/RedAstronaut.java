import java.util.Arrays;

public class RedAstronaut extends Player implements Impostor {
    private String skill;

    public static final int SUSLEVEL_DEFAULT = 15;
    public static final String SKILL_DEFAULT = "experienced";

    public RedAstronaut(String name, int susLevel, String skill) {
        super(name, susLevel);
        this.skill = skill;
    }

    public RedAstronaut(String name) {
        this(name, SUSLEVEL_DEFAULT, SKILL_DEFAULT);
    }

    public void emergencyMeeting() {
        if (this.isFrozen())
            return;
        Player[] playerList = Player.getPlayers();
        Arrays.sort(playerList);

        for (int i = playerList.length - 1; i >= 0; i--) {
            if (playerList[i] instanceof RedAstronaut || playerList[i].isFrozen())
                continue;
            if (playerList[i - 1] instanceof RedAstronaut || playerList[i - 1].isFrozen())
                continue;
            if (playerList[i].getSusLevel() == playerList[i - 1].getSusLevel()) {
                break;
            } else {
                playerList[i].setFrozen(true);
                break;
            }
        }
        this.gameOver();
    }

    public void freeze(Player p) {
        if (p.isFrozen() || p instanceof Impostor)
            return;
        if (this.getSusLevel() < p.getSusLevel()) {
            p.setFrozen(true);
        } else {
            this.setSusLevel(this.getSusLevel() * 2);
        }
        this.gameOver();
    }

    public void sabotage(Player p) {
        if (p.isFrozen() || p instanceof Impostor)
            return;
        if (this.getSusLevel() < 20) {
            p.setSusLevel((int) (p.getSusLevel() * 1.5));
        } else {
            p.setSusLevel((int) (p.getSusLevel() * 1.25));

        }
    }

    public boolean equals(Object o) {
        if (o instanceof RedAstronaut) {
            RedAstronaut player = (RedAstronaut) o;
            return this.getName().equals(player.getName()) && this.isFrozen() == player.isFrozen()
                    && this.getSusLevel() == player.getSusLevel() && this.skill == player.skill;
        }
        return false;
    }

    public String toString() {
        String frozenString = this.isFrozen() ? "frozen" : "not frozen";
        String message = "My name is " + this.getName() + ", and I have a susLevel of "
                + this.getSusLevel() + ". I am currently " + frozenString + ". I am an " + this.skill + " player!";
        if (this.getSusLevel() > 15) {
            return message.toUpperCase();
        } else
            return message;
    }

    public String getSkill() {
        return this.skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
