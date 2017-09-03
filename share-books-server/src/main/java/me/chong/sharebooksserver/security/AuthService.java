package me.chong.sharebooksserver.security;

import me.chong.sharebooksserver.entity.User;

public interface AuthService {
    User register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}
