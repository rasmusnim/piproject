package coolcats.se.homecontrol;

import java.util.List;

/**
 * Created by nim on 2017-03-26.
 */

public class SenderObject {
    private String action;
    private List<String> params;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }
}
