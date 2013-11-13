package jp.ac.aiit.jointry.controllers;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import jp.ac.aiit.jointry.models.Room;
import broker.core.Agent;
import broker.core.DInfo;
import jp.ac.aiit.jointry.services.broker.app.JointryCommon;
import jp.ac.aiit.jointry.util.StageUtil;

public class CooperationController implements Initializable, JointryCommon {

    @FXML
    private TextField name;
    @FXML
    private TextField url;
    @FXML
    private FlowPane roomList;
    @FXML
    private Label messages;
    private Agent agent;
    private Agent dummyAgent;
    private RoomController selectRoom;
    /**
     * ルーム状況を取得するためのダミーネーム
     */
    public static final String DUMMY_AGENT_NAME = "dummyAgent";

    @FXML
    protected void createRoom(ActionEvent event) {
        agent = new Agent();

        if (agent.open(url.getText(), CHAT_SERVICE, SERVER, name.getText(), "", null)) {
            agent.startListening(CHAT_TIMEOUT);

            windowClose();
        }
    }

    @FXML
    protected void participationRoom(ActionEvent event) {
        agent = new Agent();

        if (agent.open(url.getText(), accessMapping(CHAT_SERVICE, CLIENT, name.getText(), "", null, selectRoom.getRoom().getProxyId()))) {
            agent.startListening(CHAT_TIMEOUT);

            windowClose();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dummyAgent = new Agent();

        //立ち上がっているサーバー一覧を取得する
        if (dummyAgent.open(this.url.getText(), CHAT_SERVICE, SERVER, DUMMY_AGENT_NAME, "", null)) {
            dummyAgent.startListening(CHAT_TIMEOUT);

            DInfo info = dummyAgent.query(K_SERVER_INFO);
            String[] serverList = info.get(K_SERVER_INFO).split(":");

            int roomId = 1; //部屋番号
            for (String server : serverList) {
                Room room = new Room(server);
                if (room.getName().equals(DUMMY_AGENT_NAME)) continue;

                addRoom(roomId++, room);//部屋登録
            }
        } else {
            messages.setText("協同編集用サーバーに接続出来ません。");
        }
    }

    private void addRoom(int roomId, Room room) {
        URL fxml = getClass().getResource("Room.fxml"); //表示するfxml
        final StageUtil roomStage = new StageUtil(null, null, fxml, null);

        final RoomController ctrl = (RoomController) roomStage.getController();
        ctrl.setRoom(roomId, room);

        ctrl.getBG().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if(selectRoom != null) selectRoom.getBG().setStyle("-fx-border-color: white;");

                ctrl.getBG().setStyle("-fx-border-color: red;");
                selectRoom = ctrl;
            }
        });

        roomList.getChildren().add(roomStage.getParent());
    }

    private Map<String, String> accessMapping(String serviceId, String role,
            String userId, String password, String proxyFQCN, String proxyId) {
        Map<String, String> paramMap = new HashMap();
        paramMap.put(USER_ROLE, role);
        paramMap.put(SERVICE_ID, serviceId);
        paramMap.put(USER_ID, userId);
        paramMap.put(PASSWORD, password);
        paramMap.put(PROXY_ID, proxyId);
        if (proxyFQCN != null) paramMap.put(PROXY_FQCN, proxyFQCN);

        return paramMap;
    }

    public void windowClose() {
        if (dummyAgent != null) dummyAgent.close();
        Stage stage = (Stage) roomList.getScene().getWindow();
        stage.close();
    }

    public Agent getAgent() {
        return agent;
    }
}
