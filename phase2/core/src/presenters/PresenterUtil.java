package presenters;

import org.json.simple.*;
import java.util.List;
import org.json.simple.*;

public class PresenterUtil<T> {
    public JSONObject createJSON(String status, String result, String dtype, List<String> data) {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();

        json.put("status", status);
        json.put("result", result);

        item.put(dtype, data);

        array.add(item);

        json.put("data", array);

        return json;
    }

    public JSONObject createJSON(String status, String result) {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        json.put("status", status);
        json.put("result", result);

        json.put("data", array);

        return json;
    }

}
