package com.hillel.task_30_aws.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {
    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials()))
                .withRegion(Regions.EU_CENTRAL_1)
                .build();
    }

    private AWSCredentials awsCredentials() {
        return new BasicAWSCredentials("AKIAVUS2RNZLDX4MNIPF", "rVHsP4elPonQvXs9Ccrlcgfd7v5aw7abng/dyiAz");
    }
}

