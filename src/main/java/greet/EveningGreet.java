package greet;

import net.Greatable;

public class EveningGreet extends Greatable {

    @Override
    public String buildResponse(String userName) {
        return "Good Evening, " + userName;
    }
}
