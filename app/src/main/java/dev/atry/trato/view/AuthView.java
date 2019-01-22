package dev.atry.trato.view;

import dev.atry.trato.BaseView;

public interface AuthView extends BaseView {
    void onLogin(boolean login, String msg);
    void onRegister(boolean register, String msg);
}
