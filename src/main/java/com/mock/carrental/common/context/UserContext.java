package com.mock.carrental.common.context;

public class UserContext {

    private static final ThreadLocal<UserSession> PLAYER_HOLDER = new ThreadLocal<>();

    public static UserSession get() {
        return PLAYER_HOLDER.get();
    }

    public static void set(UserSession userSession) {
        PLAYER_HOLDER.set(userSession);
    }

    public static void remove() {
        PLAYER_HOLDER.remove();
    }
}
