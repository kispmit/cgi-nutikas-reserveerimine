package com.tim.restaurant.dto;

import com.tim.restaurant.model.RestaurantTable;

import java.util.List;

public class RecommendationResponse {

    private RestaurantTable recommendedTable;
    private List<RestaurantTable> alternatives;
    private List<String> unmetPreferences;
    private String message;

    public RecommendationResponse() {
    }

    public RecommendationResponse(RestaurantTable recommendedTable,
                                  List<RestaurantTable> alternatives,
                                  List<String> unmetPreferences,
                                  String message) {
        this.recommendedTable = recommendedTable;
        this.alternatives = alternatives;
        this.unmetPreferences = unmetPreferences;
        this.message = message;
    }

    public RestaurantTable getRecommendedTable() {
        return recommendedTable;
    }

    public void setRecommendedTable(RestaurantTable recommendedTable) {
        this.recommendedTable = recommendedTable;
    }

    public List<RestaurantTable> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<RestaurantTable> alternatives) {
        this.alternatives = alternatives;
    }

    public List<String> getUnmetPreferences() {
        return unmetPreferences;
    }

    public void setUnmetPreferences(List<String> unmetPreferences) {
        this.unmetPreferences = unmetPreferences;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}