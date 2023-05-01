package com.hillel.task_30_aws;

import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
@RequiredArgsConstructor
public class App implements CommandLineRunner {

    private final AmazonS3 s3;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        s3.putObject("avhillelbucket/DoneLesson"
                , "TestFilePut"
                , new File("forbucket/Hillel_LMS.jpg"));


        System.out.println("Before add new bucket: ");
        s3.listBuckets()
                .forEach(System.out::println);
        System.out.println("========================");

        System.out.println("After add new bucket: ");
        s3.createBucket("bucket-from-program");

        s3.listBuckets()
                .forEach(System.out::println);
        System.out.println("========================");
    }
}
