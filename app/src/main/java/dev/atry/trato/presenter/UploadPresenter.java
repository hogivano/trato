package dev.atry.trato.presenter;

import dev.atry.trato.BasePresenter;
import dev.atry.trato.view.UploadView;

public class UploadPresenter implements BasePresenter<UploadView> {
    private UploadView uploadView;

    @Override
    public void onAttach(UploadView view) {
        uploadView = view;
    }

    @Override
    public void onDetach() {
        uploadView = null;
    }

    public void showString(){
        uploadView.onShowUpload("String");
    }
}
