package com.developers.graphql.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Amanjeet Singh on 27/1/18.
 */

public class Data {
    @SerializedName("repository")
    @Expose
    private Repository repository;

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
