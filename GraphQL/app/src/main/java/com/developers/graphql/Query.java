package com.developers.graphql;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Amanjeet Singh on 27/1/18.
 */

public class Query {

    @SerializedName("query")
    @Expose
    private String query;


    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
