package greet;

import net.Greatable;

public class MorningGreet extends Greatable {

    @Override
    public String buildResponse(String userName) {
        return "Good Morning, " + userName;
    }
}
