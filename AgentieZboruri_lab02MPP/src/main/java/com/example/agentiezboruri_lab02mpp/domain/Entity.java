package com.example.agentiezboruri_lab02mpp.domain;

public class Entity <ID>{
    private ID id;

    public Entity(ID id) {
        this.id = id;
    }

    public ID getId(){
        return id;
    }

    public void setID(ID new_id){
        this.id = new_id;
    }

    public String toString() {
        return "Entity: " + "id = " + id;
    }
}
