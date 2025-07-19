package org.example;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class WebCrawler {
    private static Phaser phaser;
    private static ExecutorService executorService;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your URL:");
        String url = sc.nextLine();

        System.out.println("Enter the depth of the crawler:");
        final int MAX_DEPTH = sc.nextInt();

        System.out.println("Enter the number of workers:");
        final int MAX_THREADS = sc.nextInt();

        URLStore urlStore = new URLStore();
        URLFetcher urlFetcher = new URLFetcher();

        phaser = new Phaser(1);
        executorService = Executors.newFixedThreadPool(MAX_THREADS);

        long start = System.currentTimeMillis();

        submitTask(urlStore, urlFetcher, url, 0, MAX_DEPTH);

        phaser.arriveAndAwaitAdvance();

        executorService.shutdown();
        long end = System.currentTimeMillis();
        System.out.println("Crawling finished in " + (end - start) + " ms.");
    }

    public static void submitTask(URLStore urlStore, URLFetcher urlFetcher, String url, int currentDepth, int maxDepth) {
        phaser.register();
        executorService.submit(new CrawlerTask(urlStore, urlFetcher, url, currentDepth, maxDepth, phaser));
    }
}
