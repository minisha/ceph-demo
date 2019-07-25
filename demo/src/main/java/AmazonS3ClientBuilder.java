import com.amazonaws.*;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.util.StringUtils;

import java.util.List;

public class AmazonS3ClientBuilder {
    public static void main(String[] args) {
        BasicAWSCredentials credentials = new BasicAWSCredentials("OAB8XU4MBPA223DGXIZ9", "iABuhhqPxBalOqivhLRIR7lJfTtKK4B652ii9vo4");
        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTP);

        AmazonS3 conn = new AmazonS3Client(credentials, clientConfig);
        conn.setEndpoint("localhost:8080");


        List<Bucket> buckets = conn.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println("bucket list");
            System.out.println(bucket.getName() + "\t" +
                    StringUtils.fromDate(bucket.getCreationDate()));
        }

        Bucket bucket = conn.createBucket("my-new-bucket");



    }
}