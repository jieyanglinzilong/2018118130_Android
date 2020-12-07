package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.preference.Preference;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.roster.RosterGroup;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Tcp extends AppCompatActivity implements View.OnClickListener {
    EditText account, password,to,content;
    Button login,logout,send,addButton,addfriends;
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
        addButton=(Button)findViewById(R.id.addfriend);
        send = (Button) findViewById(R.id.send);
        addfriends=(Button)findViewById(R.id.group);
        addfriends.setOnClickListener(this);
        login.setOnClickListener(this);
        logout.setOnClickListener(this);
        send.setOnClickListener(this);
        addButton.setOnClickListener(this);
        //条件过滤器
        //AndFilter filter = new AndFilter(new StanzaTypeFilter(Presence.class));
        //添加监听
       //connection.addAsyncStanzaListener(packetListener, filter);

    }

    static StanzaListener packetListener = new StanzaListener() {
        private Log YbLogUtil;

        @Override
        public void processPacket(Stanza packet) throws SmackException.NotConnectedException {
            if (packet instanceof Presence) {
                Presence presence = (Presence) packet;
                String fromId = presence.getFrom();
                String from = presence.getFrom().split("@")[0];//我这里只为了打印去掉了后缀
                if (presence.getType().equals(Presence.Type.subscribe)) {
                  //  YbLogUtil.d(from);
                } else if (presence.getType().equals(Presence.Type.subscribed)) {//对方同意订阅
                   // YbLogUtil.d("yangbinnew同意订阅" + from);
                } else if (presence.getType().equals(Presence.Type.unsubscribe)) {//取消订阅
                    //YbLogUtil.d("yangbinnew取消订阅" + from);
                } else if (presence.getType().equals(Presence.Type.unsubscribed)) {//拒绝订阅
                   // YbLogUtil.d("yangbinnew拒绝订阅" + from);
                } else if (presence.getType().equals(Presence.Type.unavailable)) {//离线
                    //YbLogUtil.d("yangbinnew离线" + from);
                } else if (presence.getType().equals(Presence.Type.available)) {//上线
                    //YbLogUtil.d("yangbinnew上线" + from);
                }
            }
        }
    };
    /**
     * 创建一个新用户
     *
     * @param name 用户名
     * @param password 密码
     * @param attr     一些用户资料
     * @see AccountManager
     */
    public boolean registerAccount(String name, String password, Map<String, String> attr) {


        try {
           // getConnection();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        connection.connect();
                    } catch (SmackException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (XMPPException e) {
                        e.printStackTrace();
                    }
                    org.jivesoftware.smackx.iqregister.AccountManager manager =  org.jivesoftware.smackx.iqregister.AccountManager.getInstance(connection);
                    manager.sensitiveOperationOverInsecureConnection(true);
                    if (attr == null) {
                        try {
                            manager.createAccount(name, password);
                            System.out.println("hello1");
                        } catch (SmackException.NoResponseException e) {
                            e.printStackTrace();
                        } catch (XMPPException.XMPPErrorException e) {
                            e.printStackTrace();
                        } catch (SmackException.NotConnectedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            System.out.println("hello2");
                            manager.createAccount(name, password, attr);
                            Thread.sleep(1000);

                        } catch (SmackException.NoResponseException e) {
                            e.printStackTrace();
                        } catch (XMPPException.XMPPErrorException e) {
                            e.printStackTrace();
                        } catch (SmackException.NotConnectedException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("完成操作");



                }
            }).start();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
    public void Addfriends(String friendName,String name) throws IOException, XMPPException, SmackException {
        //Roster roster=Roster.getInstanceFor(getConnection());



        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                   //
                    //




                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                XMPPTCPConnection connection=getConnection();
                                connection.connect();
                                connection.login(friendName, "root");
                                ChatManager chatmanager = ChatManager.getInstanceFor(connection);
                                Roster roster=Roster.getInstanceFor(connection);
                                System.out.println(connection.getUser());
                                roster.createEntry(friendName.trim()+"@"+"121.37.212.47", name, new String[]{"Friends"});
                                System.out.println("添加好友成功！！");
                            } catch (SmackException.NotLoggedInException e) {
                                e.printStackTrace();
                            } catch (SmackException.NoResponseException e) {
                                e.printStackTrace();
                            } catch (XMPPException.XMPPErrorException e) {
                                e.printStackTrace();
                            } catch (SmackException.NotConnectedException e) {
                                e.printStackTrace();
                            } catch (XMPPException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (SmackException e) {
                                e.printStackTrace();
                            }

                        }
                    }).start();
                    System.out.println(connection.getUser());

                } catch (Exception e){
                    e.printStackTrace();
                }
                }

        }).start();

    }
    public  List<RosterEntry> getallEntries(Roster roster) {

        List<RosterEntry> Entrieslist = new ArrayList<RosterEntry>();
        Collection<RosterEntry> rosterEntry = roster.getEntries();
        Iterator<RosterEntry> i = rosterEntry.iterator();
        while (i.hasNext()) {
            Entrieslist.add(i.next());
        }
        return Entrieslist;
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
                            presence.setType(Presence.Type.subscribed);
                            presence.setStatus("我是在线状态");
                            connection.sendStanza(presence);

                            ChatManager chatmanager = ChatManager.getInstanceFor(connection);
                            //获取好友列表
                            System.out.println("获取好友列表");
                            Roster roster =Roster.getInstanceFor(connection);
                            Set<RosterEntry> rosterEntries = roster.getEntries();
                            Iterator<RosterEntry> rosterEntryIterator =rosterEntries.iterator();
                            while (rosterEntryIterator.hasNext()){
                                RosterEntry rosterEntry = rosterEntryIterator.next();
                                System.out.println(rosterEntry);
                            }

                            System.out.println("获取好友完毕");
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
            case R.id.addfriend:
                Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show();

                String name="test4";
                Map<String,String> map=new HashMap<String, String>();
                map.put("name",name);
                map.put("email","1679569188@qq.com");
                map.put("city","韶关");
                String password="root";
                System.out.println("添加用户");
                registerAccount(name,password,map);
                break;
            case R.id.group:
                String friendsname="test3";
                String user="test1";
                try {
                    Addfriends(user, friendsname);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XMPPException e) {
                    e.printStackTrace();
                } catch (SmackException e) {
                    e.printStackTrace();
                }
            default:
                break;
        }

    }
}