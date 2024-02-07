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


    public static int findLenIn1DArray(int index, int Mat[][], int M, int N) {

        int left = 0;
        int right = M-1;

        while(left <= right) {

            int mid = (left+right) / 2;
            if(mid-1 >=0 && Mat[index][mid]==1 && Mat[index][mid-1]==0) {
                return N-mid;

            } else if(Mat[index][mid] == 1) {

                right = mid-1;
            }else {
                left = mid+1;
            }
        }

        return 0;


    }
    public long minJumps() {

        // Your code goes here
        int N=4;
        int M=7;
        int Mat[][] = new int[][] {{0, 0, 1, 1, 1, 1, 1}, {0, 0, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 1, 1,}, {0, 0, 0, 1, 1, 1, 1}};

        int maxLen = Integer.MIN_VALUE;
        int ans = -1;

        for(int index = 0; index < N; index++) {

            int len = findLenIn1DArray(index, Mat, M, N);

            if(len>maxLen) {
                ans = index;
                maxLen = len;
            }
        }

        System.out.println(ans);

        return 0L;

    }
}
