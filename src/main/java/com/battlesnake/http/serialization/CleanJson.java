package com.battlesnake.http.serialization;

import java.util.Map.Entry;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * For the 2018 spec, adapter. <br>
 * Layers of the json are full of filler "data" entries that need to be handled.
 * @author Tony
 */
public final class CleanJson
{

    /**
     * Recursive cleaning method for removing the data fields.<br>
     * The assumption is that the data fields are always a single-field entry
     * for that level (and therefore why they are silly).
     * @param toClean
     * @return
     */
    private static JsonElement cleanElement(final JsonElement toClean)
    {
        if (toClean.isJsonArray())
        {
            JsonArray jsonArrayToClean = toClean.getAsJsonArray();
            JsonArray jsonArrayToReturn = new JsonArray();
            for (int i = 0; i < jsonArrayToClean.size(); i ++)
            {
                JsonElement entry = jsonArrayToClean.get(i);
                jsonArrayToReturn.add(cleanElement(entry));
            }
            return jsonArrayToReturn;
        }
        else if (toClean.isJsonObject())
        {
            final JsonObject objectToClean = toClean.getAsJsonObject();
            final JsonObject objectToReturn = new JsonObject();
            for (Entry<String, JsonElement> entry: objectToClean.entrySet())
            {
                if (entry.getKey().equals("data"))
                {
                    return cleanElement(entry.getValue());
                }
                else
                {
                    objectToReturn.add(
                            entry.getKey(), cleanElement(entry.getValue()));
                }
            }
            return objectToReturn;
        }
        else if (toClean.isJsonPrimitive())
        {
            return toClean;
        }
        else if (toClean.isJsonNull())
        {
            return toClean;
        }
        else
        {
            throw new RuntimeException("How'd I get here? " + toClean);
        }
    }

    /**
     * Strip out all the crazy "data" fields.
     * @param original
     * @return
     */
    public static String cleanJson(final String original)
    {
        JsonElement originalJsonElement = new JsonParser().parse(original);
        return cleanElement(originalJsonElement).toString();
    }

    /**
     * Utility class.
     */
    private CleanJson()
    {
    }

}
