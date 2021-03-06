package jp.ac.aiit.jointry.services.broker.app;

import jp.ac.aiit.jointry.services.broker.core.Account;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class JointryAccount extends Account {

    //proxy単位にユーザーを管理
    private static final ObservableList<String> userList = FXCollections.observableArrayList();

    @Override
    public void save() {
        //機能無効
    }

    @Override
    public boolean certify(String name, String password) {
        return true; //パスワード認証は行わない
    }

    public static void addUser(String userName) {
        userList.add(userName);
    }

    public static void addAllUser(String[] users) {
        for (String userName : users) {
            addUser(userName);
        }
    }

    @Override
    public boolean isActiveUser(String name) {
        boolean result = super.isActiveUser(name);
        if (result && JointryCommon.DUMMY_AGENT_NAME.equals(name)) {
            return false;
        }

        return result;
    }

    public static void removeUser(String userName) {
        userList.remove(userName); //最初に見つけたユーザのみを削除
    }

    public static void clearUser() {
        userList.clear();
    }

    public static ObservableList getUsers() {
        return userList;
    }
}
