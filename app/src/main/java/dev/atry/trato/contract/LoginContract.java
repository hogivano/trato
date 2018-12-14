package dev.atry.trato.contract;

abstract class LoginContract {
    abstract void onSuccess(String str);
    abstract void onFailed(String str);
}
