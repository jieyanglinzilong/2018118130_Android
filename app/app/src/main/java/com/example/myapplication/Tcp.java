package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import java.io.IOException;

public class Tcp extends AppCompatActivity implements View.OnClickListener {
    EditText account, password,to,content;
    Button login,logout,send;
    XMPPTCPConnection connection;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcp);


        connection=getConnection();

        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);
        to = findViewById(R.id.to);
        content = (EditText) findViewById(R.id.content);
        login = (Button) findViewById(R.id.login);
        logout = (Button) findViewById(R.id.logout);
        send = (Button) findViewById(R.id.send);
        login.setOnClickListener(this);
        logout.setOnClickListener(this);
        send.setOnClickListener(this);

    }
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what){
                case 1:
                    Toast.makeText(getApplicationContext(),msg.obj+"",Toast.LENGTH_SHORT).show();
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private XMPPTCPConnection getConnection(){
        String server="121.37.212.47";
        int port=5222;
        XMPPTCPConnectionConfiguration.Builder builder = XMPPTCPConnectionConfiguration.builder();
        builder.setServiceName(server);
        builder.setHost(server);
        builder.setPort(port);
        builder.setCompressionEnabled(false);
        builder.setDebuggerEnabled(true);
        builder.setSendPresence(true);
        builder.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
        XMPPTCPConnection connection = new XMPPTCPConnection(builder.build());
        return connection;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:{
                final String a = account.getText().toString();
                final String p = password.getText().toString();
                if (TextUtils.isEmpty(a) || TextUtils.isEmpty(p)) {
                    Toast.makeText(getApplicationContext(), "账号或密码不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            connection.connect();
                            connection.login(a, p);
                            Presence presence = new Presence(Presence.Type.available);
                            presence.setStatus("我是在线状态");
                            connection.sendStanza(presence);
                            ChatManager chatmanager = ChatManager.getInstanceFor(connection);
                            chatmanager.addChatListener(new ChatManagerListener() {
                                @Override
                                public void chatCreated(Chat chat, boolean createdLocally) {
                                    chat.addMessageListener(new ChatMessageListener() {
                                        @Override
                                        public void processMessage(Chat chat, Message message) {
                                            String content=message.getBody();
                                            if (content!=null){
                                                Log.e("TAG", "from:" + message.getFrom() + " to:" + message.getTo() + " message:" + message.getBody());
                                                android.os.Message message1= android.os.Message.obtain();
                                                message1.what=1;
                                                message1.obj="收到消息：" + message.getBody()+" 来自:"+message.getFrom();
                                                mHandler.sendMessage(message1);
                                            }

                                        }
                                    });
                                }
                            });
                        } catch (SmackException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (XMPPException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
                break;
            }
            case R.id.logout:
                connection.disconnect();
                break;
            case R.id.send:
                final String t = to.getText().toString();
                final String c = content.getText().toString();
                if (TextUtils.isEmpty(t)||TextUtils.isEmpty(c)) {
                    Toast.makeText(getApplicationContext(), "接收方或内容", Toast.LENGTH_LONG).show();
                    return;
                }

                try {
                    ChatManager chatmanager = ChatManager.getInstanceFor(connection);
                    Chat mChat = chatmanager.createChat(t+"@121.37.212.47");
                    mChat.sendMessage(c);
                }
                catch (SmackException.NotConnectedException e) {
                    e.printStackTrace();
                }
                break;
        }

    }
}