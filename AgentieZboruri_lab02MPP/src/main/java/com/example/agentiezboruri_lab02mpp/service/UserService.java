package com.example.agentiezboruri_lab02mpp.service;

import com.example.agentiezboruri_lab02mpp.domain.User;
import com.example.agentiezboruri_lab02mpp.repoInterface.UserRepoInterface;
import com.example.agentiezboruri_lab02mpp.repository.RepoInterface;

import java.util.List;

public class UserService {
    protected final UserRepoInterface repo;

    public UserService(UserRepoInterface repo){
        this.repo = repo;
    }

    public List<User> getAll(){
        return this.repo.getAll();
    }

    public User findUser(String username, String password){
        return repo.findUser(username, password);
    }
}
