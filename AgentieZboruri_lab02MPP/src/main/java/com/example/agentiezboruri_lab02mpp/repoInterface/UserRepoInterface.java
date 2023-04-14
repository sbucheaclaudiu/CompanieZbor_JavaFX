package com.example.agentiezboruri_lab02mpp.repoInterface;

import com.example.agentiezboruri_lab02mpp.domain.Bilet;
import com.example.agentiezboruri_lab02mpp.domain.User;
import com.example.agentiezboruri_lab02mpp.repository.RepoInterface;

public interface UserRepoInterface extends RepoInterface<String, User> {
    public User findUser(String username, String password);
}
