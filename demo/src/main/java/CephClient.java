import com.ceph.rados.IoCTX;
import com.ceph.rados.Rados;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;

public class CephClient {

    public static void main (String args[]){
            try {
                Rados cluster = new Rados("admin");
                System.out.println("Created cluster handle.");

                File f = new File("/etc/ceph/ceph.conf");
                cluster.confReadFile(f);
                System.out.println("Read the configuration file.");

                cluster.connect();
                System.out.println("Connected to the cluster.");

              cluster.poolCreate("hifromjava");
                String[] poollist = cluster.poolList();
//                Arrays.asList(poollist).forEach(a -> {
//                    System.out.println(a);
//                });

                IoCTX io = cluster.ioCtxCreate("hifromjava");

                String oidone = "oid1";
                String contentone = "Hello World!";
                io.write(oidone, contentone);

                String oidtwo = "oid2";
                File d = ResourceUtils.getFile("classpath:application.properties");
                byte[] data = Files.readAllBytes(d.toPath());
                io.write(oidtwo, data);

                String[] objects = io.listObjects();
                for (String object: objects)
                    System.out.println("objects stored "+object);


                io.remove(oidone);
                io.remove(oidtwo);

                cluster.ioCtxDestroy(io);

            } catch (Exception e) {
                System.out.println(e.getMessage() + ": " + e);
                e.printStackTrace();
            }

    }
}