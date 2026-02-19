package com.chickenPizza.JavaShoppingWorld.users;


    public class UserFactory {
        public static User createNewUser(String email, String passwordHash, UserType type, String nickname) {
            User user = new User();
            user.email = email;
            user.passwordHash = passwordHash;
            user.type = type;
            user.nickname = nickname;
            return user;
        }
    }

