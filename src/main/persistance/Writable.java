package persistance;

import org.json.JSONObject;

// Credit: the following interface is taken from
//         the JsonSerializationDemo project from the
//         CPSC 210 course that is a part of the
//         University of British Columbia
public interface Writable {

    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
