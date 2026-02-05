package com.justnothing.xtchttplib;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class HttpScript {
    private String name;
    private final List<HttpRequest> requests;

    public HttpScript() {
        this.requests = new ArrayList<>();
    }

    public HttpScript(String name) {
        this.name = name;
        this.requests = new ArrayList<>();
    }

    public static HttpScript fromJson(JSONObject json) {
        HttpScript script = new HttpScript();
        script.setName(json.optString("name", ""));
        
        JSONArray requestsArray = json.optJSONArray("requests");
        if (requestsArray != null) {
            for (int i = 0; i < requestsArray.length(); i++) {
                JSONObject requestJson = requestsArray.getJSONObject(i);
                script.addRequest(HttpRequest.fromJson(requestJson));
            }
        }
        
        return script;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        
        JSONArray requestsArray = new JSONArray();
        for (HttpRequest request : requests) {
            requestsArray.put(request.toJson());
        }
        json.put("requests", requestsArray);
        
        return json;
    }

    public void addRequest(HttpRequest request) {
        requests.add(request);
    }

    public void removeRequest(int index) {
        if (index >= 0 && index < requests.size()) {
            requests.remove(index);
        }
    }

    public List<HttpRequest> getRequests() {
        return requests;
    }

    @Override
    public String toString() {
        return name + " (" + requests.size() + " 个请求)";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
