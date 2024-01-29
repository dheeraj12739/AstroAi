package com.astro.ai.astroai.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@EnableScheduling
public class DemoService {

    public  int find(int dp[], int arr[], int index) {

        if(index >= arr.length) {
            return 0;
        }

        if(dp[index] != Integer.MIN_VALUE) {

            return dp[index];
        }

        for(int i = index; i < arr.length; i++) {

            int temp = find(dp, arr, i+1);

            dp[index] = Math.max(dp[index], Math.max(arr[i] ^ temp, temp));
        }

        return dp[index];
    }
    public long minJumps() {
        // Your code goes here
        int[] arr = new int[]{2,4,5};
        int N=3;
        int dp[] = new int[N];

        Arrays.fill(dp, Integer.MIN_VALUE);
        System.out.println(find(dp, arr, 0));

        return 0L;

    }
}
