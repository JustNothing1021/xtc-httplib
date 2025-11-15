package com.xtc.domain;

public enum Domain {
    H5("h5"),
    WATCH("watch"), 
    SPORT("sport"),
    TCX_POINTS("tcx_points"),
    LOCATION("location"),
    TCX_ACTIVITY("tcx_activity"),
    THIRD("third"),
    CHAT("chat"),
    WEATHER("weather"),
    TCX_STORE("tcx_store"),
    TCX_GAME("tcx_game"),
    THEME("theme"),
    GATEWAY("gateway"),
    TCX_GATEWAY("tcx_gateway"),
    MOMENT("moment"),
    ROUTE("route"),
    SENSOR_SERVICE("sensor_service"),
    IME_WS("ime_ws"),
    AIAST_WS("aiast_ws"),
    WEB_SOCKET("web_socket");
    
    private final String name;
    
    Domain(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}