import java.util.ArrayList;
import java.util.List;

import org.slf4j.helpers.Util;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.User;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.listener.Listener;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

public class Main {
    public static Listener listen(String address, int port) {
        ListenerFactory factory = new ListenerFactory();
        factory.setServerAddress(address);
        factory.setPort(port);
        return factory.createListener();
    }

    public static User createUser(String name, String pass, String root, boolean readonly) {
        BaseUser user = new BaseUser();

        user.setName(name);
        user.setPassword(pass);

        user.setHomeDirectory(root);

        List<Authority> authorities = new ArrayList<Authority>();
        if (readonly == false) {
            authorities.add(new WritePermission());
        }
        user.setAuthorities(authorities);

        return user;
    }

    public static void main(String[] args) {

        String address = "localhost";
        int port = 21;
        String root = System.getProperty("java.io.tmpdir");
        boolean readonly = false;
        String name = "anonymous";
        String pass = "anonymous";

        try {
            address = args[0];
            port = Integer.parseInt(args[1]);
            root = args[2];
            readonly = args[3].equals("readonly");
            name = args[4];
            pass = args[5];
        } catch (RuntimeException e) {
        }

        System.out.println("server address = " + address);
        System.out.println("listen port = " + port);
        System.out.println("root directory = " + root);
        System.out.println(readonly ? "read-only" : "writable");

        System.out.println("user name = " + name);
        System.out.println("password = " + pass);

        Util.report("Ignore the output from SLF4J!");
        runServer(address, port, name, pass, root, readonly);
    }

    public static void runServer(String address, int port, String name, String pass, String root, boolean readonly) {
        FtpServerFactory serverFactory = new FtpServerFactory();
        serverFactory.addListener("default", listen(address, port));
        PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
        UserManager um = userManagerFactory.createUserManager();
        try {
            um.save(createUser(name, pass, root, readonly));
        } catch (FtpException exception) {
            System.err.println(exception.getMessage());
            // exception.printStackTrace();
        }
        serverFactory.setUserManager(um);
        FtpServer server = serverFactory.createServer();
        try {
            server.start();
        } catch (FtpException exception) {
            System.err.println(exception.getMessage());
            // exception.printStackTrace();
        }
    }
}
