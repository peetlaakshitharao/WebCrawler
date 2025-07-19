package org.example;

import java.util.Set;
import java.util.concurrent.Phaser;

public class CrawlerTask implements Runnable {
    private final URLStore urlStore;
    private final URLFetcher urlFetcher;
    private final int maxDepth;
    private final int currentDepth;
    private final Phaser phaser;
    private final String url;

    public CrawlerTask(URLStore urlStore, URLFetcher urlFetcher, String url, int currentDepth, int maxDepth, Phaser phaser) {
        this.urlStore = urlStore;
        this.urlFetcher = urlFetcher;
        this.maxDepth = maxDepth;
        this.currentDepth = currentDepth;
        this.phaser = phaser;
        this.url = url;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " crawling: " + url);

            if (url == null || currentDepth > maxDepth) return;

            Set<String> links = urlFetcher.fetchLinks(url);
            for (String link : links) {
                if (urlStore.addUrl(link)) {
                    WebCrawler.submitTask(urlStore, urlFetcher, link, currentDepth + 1, maxDepth);
                }
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            phaser.arriveAndDeregister();
        }
    }
}
