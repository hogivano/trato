package dev.atry.trato;

public interface BasePresenter <T extends BaseView> {
    void onAttach(T BaseView);
    void onDetach();
}
