package greet;

import net.Greatable;

public class DayGreet extends Greatable {

    @Override
    public String buildResponse(String userName) {
        return "Good day," + userName;
    }
}
