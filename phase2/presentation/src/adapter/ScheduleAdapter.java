package adapter;

import model.ScheduleEntry;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.DateTimeUtil;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

/**
 * Adapter class for mapping Event entities to ScheduleEntry models
 */
public class ScheduleAdapter {
    private static final ScheduleAdapter Instance = new ScheduleAdapter();

    /**
     * Gets current instance of a ScheduleAdapter
     * @return An instance of a ScheduleAdapter object
     */
    public static ScheduleAdapter getInstance() { return Instance; }

    /**
     * Empty ScheduleAdapter constructor
     */
    private ScheduleAdapter() {}

    /**
     * Adapts data into a list of schedule entries
     * @param data JSONArray of JSON formatted Event entities
     * @return List of ScheduleEntry models
     */
    public List<ScheduleEntry> adaptData(JSONArray data) {
        List<ScheduleEntry> schedule = new ArrayList<>();
        for (Object datum : data) {
            JSONObject jsonObject = (JSONObject) datum;
            schedule.add(mapScheduleEntry(jsonObject));
        }
        return schedule;
    }

    /**
     * Maps the jsonObject into a ScheduleEntry model
     * @param jsonObject JSONObject of JSON formatted Event entity
     * @return ScheduleEntry model with mapped attributes
     */
    private ScheduleEntry mapScheduleEntry(JSONObject jsonObject) {
        LocalDateTime start = DateTimeUtil.getInstance().parse(String.valueOf(jsonObject.get("start")));
        LocalDateTime end = DateTimeUtil.getInstance().parse(String.valueOf(jsonObject.get("end")));
        Duration duration = Duration.between(start, end);
        String eventId = String.valueOf(jsonObject.get("id"));
        String eventName = String.valueOf(jsonObject.get("eventName"));
        String roomName = String.valueOf(jsonObject.get("roomName"));
        String amenities = getAmenities(jsonObject);
        String attendees = convertListToString((JSONArray) jsonObject.get("users"));
        String speakers = convertListToString((JSONArray) jsonObject.get("speakers"));
        int remainingSpots = parseInt(String.valueOf(jsonObject.get("remainingSpots")));
        int capacity = parseInt(String.valueOf(jsonObject.get("capacity")));
        boolean vip = jsonObject.get("vip").equals(true);

        return new ScheduleEntry(start, end, eventId, eventName, roomName, amenities, attendees, speakers, duration,
                remainingSpots, capacity, vip);
    }

    /**
     * Converts jsonArray into an appropriate String representation
     * @param jsonArray JSONArray object representing a List of String objects
     * @return String representation of jsonArray
     */
    private String convertListToString(JSONArray jsonArray) {
        if (jsonArray.isEmpty()) return "None";
        StringBuilder sb = new StringBuilder();
        String prefix = "";
        for (Object object : jsonArray) {
            sb.append(prefix);
            sb.append(object.toString());
            prefix = ", ";
        }
        return sb.toString();
    }

    /**
     * Gets an appropriate String representation of an event's required amenities
     * @param jsonObject JSONObject object representing event
     * @return String presentation of an event's required amenities
     */
    private String getAmenities(JSONObject jsonObject) {
        StringBuilder sb = new StringBuilder();
        String prefix = "";
        String[] amenityKeys = new String[]{"Chairs", "Tables", "Projector", "SoundSystem"};
        for (String key : amenityKeys) {
            if (String.valueOf(jsonObject.get(key)).equals("true")) {
                sb.append(prefix);
                sb.append(key);
                prefix = ", ";
            }
        }
        if (sb.toString().equals("")) return "None";
        else return sb.toString();
    }
}
